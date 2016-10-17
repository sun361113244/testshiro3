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
        String content = HDFSUtil.readFile("hdfs://master:9000/itemcf/output/result/part-r-00000");
        System.out.println(content);
    }

    @Test
    public void test2()
    {
        double shang = 1;

        double c1= 3.0/ 4;
        double c2 = 1.0/ 4;
        double e1 = Math.log(c1) / Math.log(2);
        double e2 = Math.log(c2) / Math.log(2);

        shang = c1*e1 + c2*e2;
        System.out.println(shang);
    }
}
