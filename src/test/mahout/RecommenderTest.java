package mahout;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecommenderTest
{
    final static int NEIGHBORHOOD_NUM = 2;
    final static int RECOMMENDER_NUM = 3;

    final static String test_file_path = "/Users/charles/Desktop/乐享天下资料/练习/item.csv";

    public static void userCF(DataModel dataModel) throws TasteException{}
    public static void itemCF(DataModel dataModel) throws TasteException{}
    public static void slopeOne(DataModel dataModel) throws TasteException{}

    @Test
    public void testuserCF() throws IOException, TasteException
    {
        DataModel dataModel = new FileDataModel(new File(test_file_path));
        UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood( 2, userSimilarity , dataModel);

        Recommender recommender = new GenericUserBasedRecommender(dataModel , userNeighborhood , userSimilarity);
        List<RecommendedItem> recommendedItems = recommender.recommend(1 , 1);
        for(RecommendedItem recommendedItem : recommendedItems)
        {
            System.out.println(recommendedItem);
        }
    }

    @Test
    public void testEvaluate() throws IOException, TasteException
    {
        RandomUtils.useTestSeed();
        DataModel dataModel = new FileDataModel(new File(test_file_path));

        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

        RecommenderBuilder recommender = new RecommenderBuilder()
        {
            public Recommender buildRecommender(DataModel dataModel) throws TasteException
            {
                UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
                UserNeighborhood userNeighborhood = new NearestNUserNeighborhood( 2, userSimilarity , dataModel);
                return new GenericUserBasedRecommender(dataModel , userNeighborhood , userSimilarity);
            }
        };
        double source = evaluator.evaluate(recommender , null ,dataModel , 0.9 ,1.0);
        System.out.println(source);
    }
}
