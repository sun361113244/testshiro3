package hadoop;

import org.junit.Test;
import util.HDFSUtil;

import java.io.IOException;

/**
 * Created by charles on 16/9/8.
 */
public class testhdfs
{
    @Test
    public void test1() throws IOException
    {
        String content = HDFSUtil.readFile("/user/hadoop/temp/notUsed/part-r-00000");
        System.out.println(content);
    }
}
