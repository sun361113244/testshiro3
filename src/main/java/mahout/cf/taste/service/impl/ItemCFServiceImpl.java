package mahout.cf.taste.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import mahout.cf.taste.entity.IRStatisticsEntity;
import mahout.cf.taste.service.ItemCFService;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.hadoop.item.RecommenderJob;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.common.ClassUtils;
import org.apache.mahout.common.RandomUtils;
import org.springframework.stereotype.Service;
import util.HDFSUtil;
import util.HadoopCommonUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class ItemCFServiceImpl implements ItemCFService
{
    private static final Pattern DELIMITER = Pattern.compile("[\t]");

    private static final Set<String> similarityClassnameSet = Sets.newHashSet("SIMILARITY_CITY_BLOCK" , "SIMILARITY_COOCCURRENCE" ,
            "SIMILARITY_COSINE" ,"SIMILARITY_EUCLIDEAN_DISTANCE" , "SIMILARITY_LOGLIKELIHOOD" , "SIMILARITY_PEARSON_CORRELATION" ,
            "SIMILARITY_TANIMOTO_COEFFICIENT");

    public int checkRunInputIllegal(Boolean onCluster ,Boolean isOverWrite, String input, String output, String tempDir, String similarityClassname,
                                        String outputPathForSimilarityMatrix, String usersFile, String itemsFile,
                                        String filterFile, Integer numRecommendations, Boolean booleanData, Integer maxPrefsPerUser,
                                        Integer maxSimilaritiesPerItem, Integer minPrefsPerUser, Integer maxPrefsPerUserInItemSimilarity,
                                        Double threshold)
    {
        if(onCluster)
        {
            try
            {
                if(!HDFSUtil.exits(input))
                    return -1;
                if(isOverWrite)
                {
                    if(HDFSUtil.exits(output))
                        HDFSUtil.deleteFile(output);
                    if(HDFSUtil.exits(tempDir))
                        HDFSUtil.deleteFile(tempDir);
                    if(HDFSUtil.exits(outputPathForSimilarityMatrix))
                        HDFSUtil.deleteFile(outputPathForSimilarityMatrix);
                }
                else
                {
                    if(HDFSUtil.exits(output))
                        return -2;
                    if(HDFSUtil.exits(tempDir))
                        return -3;
                    if(HDFSUtil.exits(outputPathForSimilarityMatrix))
                        return -4;
                }
                if(!similarityClassnameSet.contains(similarityClassname))
                    return -5;
            } catch (IOException e)
            {
                return -401;
            }
            return 1;
        }
        else
        {
            File input_file = new File(input);
            File output_file = new File(output);
            File tempDir_file = new File(tempDir);
            File outputPathForSimilarityMatrix_file = new File(outputPathForSimilarityMatrix);

            if(!input_file.isFile())
                return -1;
            if(isOverWrite)
            {
                if(output_file.exists())
                    output_file.deleteOnExit();
                if(tempDir_file.exists())
                    tempDir_file.deleteOnExit();
                if(outputPathForSimilarityMatrix_file.exists())
                    outputPathForSimilarityMatrix_file.deleteOnExit();
            }
            else
            {
                if(output_file.exists())
                    return -2;
                if(tempDir_file.exists())
                    return -3;
                if(outputPathForSimilarityMatrix_file.exists())
                    return -4;
            }
            if(!similarityClassnameSet.contains(similarityClassname))
                return -5;
            return 1;
        }
    }

    public int runItemCFLocal(String input, String output, String tempDir, String similarityClassname,
                              String outputPathForSimilarityMatrix, String usersFile, String itemsFile,
                              String filterFile, Integer numRecommendations, Boolean booleanData, Integer maxPrefsPerUser,
                              Integer maxSimilaritiesPerItem, Integer minPrefsPerUser, Integer maxPrefsPerUserInItemSimilarity,
                              Double threshold) throws IOException, TasteException
    {
        DataModel model = new FileDataModel(new File(input));
//        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
        ItemSimilarity similarity = ClassUtils.instantiateAs(PearsonCorrelationSimilarity.class, ItemSimilarity.class);

        Recommender recommender = new GenericItemBasedRecommender(model, similarity);
        return 0;

    }

    public int runItemCFOnCluster(String input, String output, String tempDir, String similarityClassname,
                                  String outputPathForSimilarityMatrix, String usersFile, String itemsFile,
                                  String filterFile, Integer numRecommendations, Boolean booleanData, Integer maxPrefsPerUser,
                                  Integer maxSimilaritiesPerItem, Integer minPrefsPerUser, Integer maxPrefsPerUserInItemSimilarity,
                                  Double threshold)
    {

        String[] args = generateParams(input , output ,tempDir , similarityClassname ,
                outputPathForSimilarityMatrix , usersFile , itemsFile ,filterFile ,numRecommendations ,
                booleanData , maxPrefsPerUser , maxSimilaritiesPerItem , minPrefsPerUser ,
                maxPrefsPerUserInItemSimilarity , threshold);
        try
        {
            ToolRunner.run(HadoopCommonUtil.getConf(), new RecommenderJob(), args);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    private String[] generateParams(String input, String output, String tempDir, String similarityClassname,
                                    String outputPathForSimilarityMatrix, String usersFile, String itemsFile,
                                    String filterFile, Integer numRecommendations, Boolean booleanData, Integer maxPrefsPerUser,
                                    Integer maxSimilaritiesPerItem, Integer minPrefsPerUser, Integer maxPrefsPerUserInItemSimilarity,
                                    Double threshold)
    {
        List<String> args = new ArrayList<String>();

        args.add("--input");
        args.add(input);

        args.add("--output");
        args.add(output);

        args.add("--tempDir");
        args.add(tempDir);

        args.add("--similarityClassname");
        args.add(similarityClassname);

        if(!Strings.isNullOrEmpty(outputPathForSimilarityMatrix))
        {
            args.add("--outputPathForSimilarityMatrix");
            args.add(outputPathForSimilarityMatrix);
        }

        if(!Strings.isNullOrEmpty(usersFile))
        {
            args.add("--usersFile");
            args.add(usersFile);
        }

        if(!Strings.isNullOrEmpty(itemsFile))
        {
            args.add("--itemsFile");
            args.add(itemsFile);
        }

        if(!Strings.isNullOrEmpty(filterFile))
        {
            args.add("--filterFile");
            args.add(filterFile);
        }

        if(!(numRecommendations == null))
        {
            args.add("--numRecommendations");
            args.add(numRecommendations.toString());
        }

        if(!(booleanData == null))
        {
            args.add("--booleanData");
            args.add(booleanData.toString());
        }

        if(!(maxPrefsPerUser == null))
        {
            args.add("--maxPrefsPerUser");
            args.add(maxPrefsPerUser.toString());
        }

        if(!(maxSimilaritiesPerItem == null))
        {
            args.add("--maxSimilaritiesPerItem");
            args.add(maxSimilaritiesPerItem.toString());
        }

        if(!(minPrefsPerUser == null))
        {
            args.add("--minPrefsPerUser");
            args.add(minPrefsPerUser.toString());
        }

        if(!(maxPrefsPerUserInItemSimilarity == null))
        {
            args.add("--maxPrefsPerUserInItemSimilarity");
            args.add(maxPrefsPerUserInItemSimilarity.toString());
        }

        if(!(threshold == null))
        {
            args.add("--threshold");
            args.add(threshold.toString());
        }
        return (String[])args.toArray(new String[args.size()]);
    }
//--------------------------------------------------------------------------------------------------------------------
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
