package hdfs.service.impl;

import hdfs.entity.HDFSNode;
import hdfs.service.HDFSService;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.ClusterStatus;
import org.apache.hadoop.mapred.JobClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sys.entity.ETreeNode;
import sys.entity.ETreeNodeAttribute;
import sys.tree.TreeHelper;
import sys.tree.TreeNode;
import util.Config;
import util.HDFSUtil;
import util.HadoopCommonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class HDFSServiceImpl implements HDFSService
{
    private static final Logger logger = LoggerFactory.getLogger(HDFSServiceImpl.class);

    public int isHadoopClusterEnabled(String url)
    {
        try
        {
            FileSystem fs = FileSystem.get(HadoopCommonUtil.getConf());
            boolean fsOnline=fs.exists(new Path("/"));
            if(!fsOnline)
            {
                return -1;
            }
            JobClient jc = new JobClient(HadoopCommonUtil.getConf());
            ClusterStatus cs = jc.getClusterStatus();
            if(!"RUNNING".equals(cs.getJobTrackerStatus().toString()))
            {
                return -2;
            }
        } catch (IOException e)
        {
            logger.error("HDFSServiceImpl 异常:" + e.getMessage());
        }
        return 1;
    }

    public ETreeNode listDirectoryStructs(String path) throws IOException
    {
        List<HDFSNode> hdfsNodes = listHDFSDirectory(path);
        List<TreeNode> fileTree = TreeHelper.changeEnititiesToTreeNodes(hdfsNodes);
        AddRootNode(fileTree , hdfsNodes);
        TreeHelper treeHelper = new TreeHelper(fileTree);

        ETreeNode eTreeNodes = new ETreeNode();
        CopyTree(eTreeNodes ,treeHelper.getRoot() , hdfsNodes);

        return eTreeNodes;
    }

    private void CopyTree(ETreeNode eTreeNode, TreeNode node, List<HDFSNode> hdfsNodes)
    {
        HDFSNode hdfsNode = getHDFSNodeById(node.getSelfId() , hdfsNodes);

        if(hdfsNode == null)
        {
            throw new NullPointerException("hdfsNode is null!!");
        }

        GenerateEtreeNode(eTreeNode ,hdfsNode);

        if(!node.isLeaf())
        {
            ETreeNode[] eTreeNodes = new ETreeNode[node.getChildList().size()];
            for(int i= 0 ;i < eTreeNodes.length ; i++)
            {
                eTreeNodes[i] = new ETreeNode();
            }
            eTreeNode.setChildren(eTreeNodes);

            int index = 0;
            for(TreeNode treeNode : node.getChildList())
            {
                CopyTree(eTreeNodes[index++] , treeNode, hdfsNodes);
            }
        }
    }

    private void GenerateEtreeNode(ETreeNode eTreeNode, HDFSNode hdfsNode)
    {
        eTreeNode.setId(String.valueOf(hdfsNode.getId()));
        eTreeNode.setText(hdfsNode.getName());
        ETreeNodeAttribute nodeAttribute = new ETreeNodeAttribute();
        nodeAttribute.setAbsPath(hdfsNode.getPath());
        eTreeNode.setAttributes(nodeAttribute);
        if(hdfsNode.isDirectory())
        {
            eTreeNode.setIconCls("icon-folder");

            if(!hdfsNode.isLeaf())
            {
                eTreeNode.setState("closed");
            }
        }
    }

    private HDFSNode getHDFSNodeById(int id, List<HDFSNode> hdfsNodes)
    {
        for(HDFSNode hdfsnode : hdfsNodes)
        {
            if(hdfsnode.getId() == id )
                return hdfsnode;
        }
        return null;
    }

    private void AddRootNode(List<TreeNode> menuTree, List<HDFSNode> hdfsNodes)
    {
        HDFSNode rootUri = new HDFSNode(0, -1 ,"根目录" ,"/");
        TreeNode treeNode = new TreeNode();
        treeNode.setObj(rootUri);
        treeNode.setParentId(rootUri.getParentId());
        treeNode.setSelfId(rootUri.getId());
        treeNode.setNodeName(rootUri.getName());

        menuTree.add(treeNode);
        hdfsNodes.add(rootUri);
    }

    private List<HDFSNode> listHDFSDirectory(String path) throws IOException
    {
        int id = 1;
        List<HDFSNode> hdfsNodes = new ArrayList<HDFSNode>();
        Queue queue=new LinkedList<HDFSNode>();

        FileStatus[] fileStatus = HDFSUtil.listStatus(path);
        for(FileStatus status : fileStatus)
        {
            HDFSNode node = new HDFSNode();
            node.setId(id++);
            node.setParentId(0);
            node.setName(status.getPath().getName());
            node.setPath(status.getPath().toString().replaceAll(Config.HADOOP_HDFS_URI, ""));
            node.setDirectory(status.isDirectory());

            queue.offer(node);
        }

        while (!queue.isEmpty())
        {
            HDFSNode dirRoot = (HDFSNode)queue.poll();
            if(dirRoot.isDirectory())
            {
                FileStatus[] dirFileStatus = HDFSUtil.listStatus(dirRoot.getPath());
                for(FileStatus status : dirFileStatus)
                {
                    HDFSNode node = new HDFSNode();
                    node.setId(id++);
                    node.setParentId(dirRoot.getId());
                    node.setName(status.getPath().getName());
                    node.setPath(status.getPath().toString().replaceAll(Config.HADOOP_HDFS_URI, ""));
                    node.setDirectory(status.isDirectory());

                    queue.offer(node);
                }
            }

            hdfsNodes.add(dirRoot);
        }

        return  hdfsNodes;
    }
}
