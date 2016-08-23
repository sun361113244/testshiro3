package hdfs.entity;

import sys.entity.TreeNodeStruc;

public class HDFSNode extends TreeNodeStruc
{
    private String path;
    private boolean isDirectory;
    private int blockSize;
    private String owner;
    private String permission;
    private int fileLength;
    private int replicationNum;

    public HDFSNode()
    {
        super();
    }

    public HDFSNode(Integer id, Integer parentId , String nodeName)
    {
        super(id , parentId , nodeName);
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public boolean isDirectory()
    {
        return isDirectory;
    }

    public void setDirectory(boolean directory)
    {
        isDirectory = directory;
    }

    public int getBlockSize()
    {
        return blockSize;
    }

    public void setBlockSize(int blockSize)
    {
        this.blockSize = blockSize;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public int getFileLength()
    {
        return fileLength;
    }

    public void setFileLength(int fileLength)
    {
        this.fileLength = fileLength;
    }

    public int getReplicationNum()
    {
        return replicationNum;
    }

    public void setReplicationNum(int replicationNum)
    {
        this.replicationNum = replicationNum;
    }
}
