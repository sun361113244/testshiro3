package mahout;

import mahout.clustering.service.KmeansService;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.common.HadoopUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.HDFSUtil;
import util.HadoopCommonUtil;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" , "classpath:spring-mybatis.xml"})
public class kmeansTest
{

    @Resource
    private KmeansService kmeansService;

    @Test
    public void test1() throws InterruptedException, IOException, ClassNotFoundException
    {
        int k = 3;
        double convergenceDelta = 0.1;
        int maxIterations = 10;
        String input = "/kmeans/input/irisNoLabel1.data";
        String output = "/kmeans/output/";

        HadoopUtil.delete(HadoopCommonUtil.getConf() , new Path(output));

        kmeansService.runKmeans(true , input , output , "MEASURE_EUCLIDEAN_DISTANCE" ,k ,convergenceDelta,maxIterations);
//        KmeansServiceLocalImpl kmeansServiceLocal = new KmeansServiceLocalImpl();
//        HDFSUtil.deleteFile("/kmeans/output/" , true);
//        int k = 3;
//        kmeansServiceLocal.runKmeans(true , "/kmeans/input/irisNoLabel1.data" ,"/kmeans/output/" , "" , k ,0.01 ,10);

    }
}
