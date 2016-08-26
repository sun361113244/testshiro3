import hdfs.util.HDFSUtil;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by charles on 16/8/21.
 */
public class testHadoop
{
    @Test
    public void testListFiles() throws IOException
    {
//        HDFSUtil.listFiles("/");
    }

    @Test
    public void testExistFile()
    {
        try
        {
            boolean res = HDFSUtil.exits("/aaa.txt");
            System.out.println(res);
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testCreateFile() throws IOException
    {
        HDFSUtil.createFile("/bb.txt" , "");
    }

    @Test
    public void testCopyFromLocalFile() throws IOException
    {
        HDFSUtil.copyFromLocalFile(true , true , "/Users/charles/Desktop/tmp_city.txt" , "/");
    }

    @Test
    public void testdeleteFile() throws IOException
    {
        HDFSUtil.deleteFile("/bb.txt");
    }

    @Test
    public void testlistFiles() throws IOException
    {
        RemoteIterator<LocatedFileStatus> remoteIterator =  HDFSUtil.listFiles("/" , true);
        while(remoteIterator.hasNext())
        {
            System.out.println(remoteIterator.next().getPath());
        }
    }

    @Test
    public void testreadFile() throws IOException
    {
        String content = HDFSUtil.readFile("/aa.txt");
        System.out.println(content);
    }
}
