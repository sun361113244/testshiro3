package mahout.clustering.service.impl;

import com.google.common.base.Strings;
import mahout.clustering.entity.DistanceMeasures;
import mahout.clustering.service.KmeansService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.clustering.conversion.InputDriver;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.clustering.kmeans.RandomSeedGenerator;
import org.apache.mahout.common.ClassUtils;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.VectorSimilarityMeasures;
import org.springframework.stereotype.Service;
import util.HDFSUtil;
import util.HadoopCommonUtil;

import java.io.IOException;

@Service
public class KmeansServiceImpl implements KmeansService
{
    private final static String seqFile = "seqfile";
    private final static String initialPoints = "initialPoints";

    public int checkInput(Boolean onCluster, Boolean isOverWrite, String input, String output, String distanceMeasureClassName,
                          Integer k, Double convergenceDelta, Integer maxIterations)
    {
        int res = 1;
        if(onCluster == null || isOverWrite == null || Strings.isNullOrEmpty(input) || Strings.isNullOrEmpty(output) ||
                Strings.isNullOrEmpty(distanceMeasureClassName) || k == null || convergenceDelta == null || maxIterations == null)
        {
            res = -1;
        }

        try
        {
            if(!isOverWrite && HDFSUtil.exits(output))
            {
                res = -2;
            }
            else
            {
                HadoopUtil.delete(HadoopCommonUtil.getConf() , new Path(output));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            res = -3;
        }
        return res;
    }

    public int runKmeans(Boolean onCluster, String input, String output, String distanceMeasureClassName,
                         Integer k, Double convergenceDelta, Integer maxIterations) throws InterruptedException, IOException, ClassNotFoundException
    {
        String distanceClassName = DistanceMeasures.valueOf(distanceMeasureClassName).getClassname();
        DistanceMeasure measure = ClassUtils.instantiateAs(distanceClassName , DistanceMeasure.class);
        Path input_path = new Path(input);
        Path output_path = new Path(output);

        Configuration conf = HadoopCommonUtil.getConf();

        Path seqFilePath = new Path(output_path , seqFile);
        HadoopUtil.delete(conf , seqFilePath);
        InputDriver.runJob(input_path, seqFilePath, "org.apache.mahout.math.DenseVector");

        Path initialPointsPath = new Path(output_path , initialPoints);
        HadoopUtil.delete(conf , initialPointsPath);
        RandomSeedGenerator.buildRandom( conf , seqFilePath , initialPointsPath , k, measure , 1L);

        KMeansDriver.run(conf, seqFilePath, initialPointsPath, output_path, convergenceDelta,
                maxIterations, true, 0.01, !onCluster);

        return 0;
    }

}
