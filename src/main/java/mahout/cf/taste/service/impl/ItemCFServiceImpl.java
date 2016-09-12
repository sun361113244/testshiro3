package mahout.cf.taste.service.impl;

import mahout.cf.taste.entity.IRStatisticsEntity;
import mahout.cf.taste.service.ItemCFService;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.common.ClassUtils;
import org.apache.mahout.common.RandomUtils;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.VectorSimilarityMeasure;
import org.springframework.stereotype.Service;
import util.HDFSUtil;
import util.HadoopCommonUtil;

import java.io.*;
import java.util.regex.Pattern;

@Service
public class ItemCFServiceImpl implements ItemCFService
{
    private static final Pattern DELIMITER = Pattern.compile("[\t]");

    public String getRecommendResultByUserId(String filePath, String userId, String recommendNum) throws IOException
    {
        String content = null;

        FileSystem fs = FileSystem.get(HadoopCommonUtil.getConf());
        Path path = new Path(filePath);
        InputStream inputStream = null;
        BufferedReader outputStream = null;
        try
        {
            inputStream = fs.open(path);
            outputStream = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = outputStream.readLine())!= null) // 判断最后一行不存在，为空结束循环
            {
                String[] tokens = DELIMITER.split(line.toString());

                if (userId.equals(tokens[0]))
                {
                    content = tokens[1];
                    break;
                }
            };
        } finally
        {
            IOUtils.closeStream(inputStream);
            IOUtils.closeStream(outputStream);
            fs.close();
        }
        return content;
    }

    public IRStatisticsEntity evaluateRecommend(String evaluatePath, String evaluateTopN, final String evaluateSimilarityClassname) throws IOException, TasteException
    {
        RandomUtils.useTestSeed();
        DataModel dataModel = new FileDataModel(new File(evaluatePath));

        RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException
            {
                ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
                return new GenericItemBasedRecommender(model, similarity);
            }
        };

        RecommenderIRStatsEvaluator statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        IRStatistics stats = statsEvaluator.evaluate(recommenderBuilder,
                null, dataModel, null, 2,
                GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD,
                1.0);

        IRStatisticsEntity irStatisticsEntity = new IRStatisticsEntity();
        irStatisticsEntity.setPrecision(stats.getPrecision());
        irStatisticsEntity.setRecall(stats.getRecall());
        irStatisticsEntity.setFallOut(stats.getFallOut());
        irStatisticsEntity.setReach(stats.getReach());

        return irStatisticsEntity;
    }
}
