package hdfs.service.impl;

import hdfs.entity.HDFSNode;
import hdfs.service.HDFSService;
import hdfs.util.HDFSUtil;
import org.apache.hadoop.fs.FileStatus;
import org.apache.mahout.common.HadoopUtil;
import org.springframework.stereotype.Service;
import sys.entity.ETreeNode;
import sys.tree.TreeHelper;
import sys.tree.TreeNode;
import util.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class HDFSServiceImpl implements HDFSService
{
    public ETreeNode listDirectoryStructs() throws IOException
    {
        List<HDFSNode> hdfsNodes = listHDFSDirectory("/");
        List<TreeNode> fileTree = TreeHelper.changeEnititiesToTreeNodes(hdfsNodes);
        AddRootNode(fileTree);
        TreeHelper treeHelper = new TreeHelper(fileTree);

        ETreeNode eTreeNodes = new ETreeNode();
        CopyTree(eTreeNodes ,treeHelper.getRoot());

        return eTreeNodes;
    }

    private void CopyTree(ETreeNode eTreeNode, TreeNode node)
    {
        eTreeNode.setId(String.valueOf(node.getSelfId()));
        eTreeNode.setText(node.getNodeName());
//                eTreeNode.setState("closed");

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
                CopyTree(eTreeNodes[index++] , treeNode);
            }
        }
    }

    private void AddRootNode(List<TreeNode> menuTree)
    {
        HDFSNode rootUri = new HDFSNode(0, -1 ,"root");
        TreeNode treeNode = new TreeNode();
        treeNode.setObj(rootUri);
        treeNode.setParentId(rootUri.getParentId());
        treeNode.setSelfId(rootUri.getId());
        treeNode.setNodeName(rootUri.getName());
        menuTree.add(treeNode);
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
                    node.setPath(status.getPath().toString());
                    node.setDirectory(status.isDirectory());

                    queue.offer(node);
                }
            }

            hdfsNodes.add(dirRoot);
        }

        return  hdfsNodes;
    }
}
