package mahout;

import org.apache.hadoop.fs.Path;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.Vectors;
import org.apache.mahout.utils.SequenceFileDumper;
import org.apache.mahout.utils.vectors.VectorDumper;
import org.junit.Test;
import util.HadoopCommonUtil;

import java.io.IOException;

/**
 * Created by charles on 16/9/8.
 */
public class SequenceFile
{
    private void dumpSequenceFile(String path) throws Exception
    {
        SequenceFileDumper.main(new String[]{
                "--input", path
        });
    }

    // 读取.bin文件
    public Vector getVector(String path)
    {

        Vector vector = null;
        try
        {
            vector = Vectors.read(new Path(path), HadoopCommonUtil.getConf());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return vector;
    }

    @Test
    public void test1() throws Exception
    {
        String path = "hdfs://172.16.174.139:9000/user/hadoop/temp/similarityMatrix/part-r-00000";
        dumpSequenceFile(path);

//        String path1 = "hdfs://172.16.174.139:9000/user/hadoop/temp/preparePreferenceMatrix/userVectors/part-r-00000";
//        dumpSequenceFile(path1);
//
//        String path2 = "hdfs://172.16.174.139:9000/user/hadoop/temp/preparePreferenceMatrix/ratingMatrix/part-r-00000";
//        dumpSequenceFile(path2);

    }

    @Test
    public void test2() throws Exception
    {
        String path = "hdfs://master:9000/user/hadoop/temp/norms.bin";
        Vector map=getVector(path);
        System.out.println(map);
    }


}
