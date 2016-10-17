package mahout.cf.taste.service;

import mahout.cf.taste.entity.IRStatisticsEntity;
import org.apache.mahout.cf.taste.common.TasteException;

import java.io.IOException;

public interface ItemCFService
{
    int checkRunInputIllegal(Boolean onCluster, Boolean isOverWrite, String input, String output, String tempDir,
                                 String similarityClassname, String outputPathForSimilarityMatrix,
                                 String usersFile, String itemsFile, String filterFile, Integer numRecommendations,
                                 Boolean booleanData, Integer maxPrefsPerUser, Integer maxSimilaritiesPerItem,
                                 Integer minPrefsPerUser, Integer maxPrefsPerUserInItemSimilarity, Double threshold);

    int runItemCFLocal(String input, String output, String tempDir, String similarityClassname, String outputPathForSimilarityMatrix,
                       String usersFile, String itemsFile, String filterFile, Integer numRecommendations, Boolean booleanData,
                       Integer maxPrefsPerUser, Integer maxSimilaritiesPerItem, Integer minPrefsPerUser, Integer maxPrefsPerUserInItemSimilarity,
                       Double threshold) throws IOException, TasteException;

    int runItemCFOnCluster(String input, String output, String tempDir, String similarityClassname, String outputPathForSimilarityMatrix,
                           String usersFile, String itemsFile, String filterFile, Integer numRecommendations, Boolean booleanData,
                           Integer maxPrefsPerUser, Integer maxSimilaritiesPerItem, Integer minPrefsPerUser, Integer maxPrefsPerUserInItemSimilarity,
                           Double threshold);

    String getRecommendResultByUserId(String path, String userId, String recommendNum) throws IOException;

    IRStatisticsEntity evaluateRecommend(String evaluatePath, String evaluateTopN, String evaluateSimilarityClassname) throws IOException, TasteException;

}
