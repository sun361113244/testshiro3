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
        String path = "hdfs://master:9000/itemcf/output/matrix/part-r-00000";
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

    @Test
    public void testCal()
    {
        double precious = 0.00268097;
        double F1 = 0.52683125 * 0.01;

        int preCount = 30409;
        double recall = (F1 * precious) / (2*precious - F1);
        System.out.println("准确率:" + precious * 100 + "---- 召回率:" + recall * 100);

        int precious_count = (int)(preCount * precious);
        int all_count = (int)(precious_count / recall);

        System.out.println("准确数:" + precious_count + "---- 总数:" + all_count);
    }


}
