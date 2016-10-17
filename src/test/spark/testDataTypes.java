package spark;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.junit.Test;

/**
 * Created by charles on 16/10/14.
 */
public class testDataTypes
{
    @Test
    public void test1()
    {
        Vector dv = Vectors.dense(1 , 0 , 3);
        Vector sv = Vectors.sparse(3 , new int[] { 0 ,2} , new double[] {1, 3 });
    }
}
