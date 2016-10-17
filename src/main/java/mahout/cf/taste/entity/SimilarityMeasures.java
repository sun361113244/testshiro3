package mahout.cf.taste.entity;

import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.Arrays;

/**
 * Created by charles on 16/9/18.
 */
public enum SimilarityMeasures
{
    SIMILARITY_LOGLIKELIHOOD(LogLikelihoodSimilarity.class),
    SIMILARITY_TANIMOTO_COEFFICIENT(TanimotoCoefficientSimilarity.class),
    SIMILARITY_CITY_BLOCK(CityBlockSimilarity.class),
    SIMILARITY_PEARSON_CORRELATION(PearsonCorrelationSimilarity.class),
    SIMILARITY_EUCLIDEAN_DISTANCE(EuclideanDistanceSimilarity.class);

    private final Class<? extends ItemSimilarity> implementingClass;

    SimilarityMeasures(Class<? extends ItemSimilarity> impl) {
        this.implementingClass = impl;
    }

    public String getClassname() {
        return implementingClass.getName();
    }

    public static String list() {
        return Arrays.toString(values());
    }
}
