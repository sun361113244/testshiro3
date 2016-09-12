package mahout.cf.taste.service;

import mahout.cf.taste.entity.IRStatisticsEntity;
import org.apache.mahout.cf.taste.common.TasteException;

import java.io.IOException;

public interface ItemCFService
{
    String getRecommendResultByUserId(String path, String userId, String recommendNum) throws IOException;

    IRStatisticsEntity evaluateRecommend(String evaluatePath, String evaluateTopN, String evaluateSimilarityClassname) throws IOException, TasteException;
}
