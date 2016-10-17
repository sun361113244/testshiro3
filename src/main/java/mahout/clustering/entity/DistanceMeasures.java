package mahout.clustering.entity;

import org.apache.mahout.common.distance.ChebyshevDistanceMeasure;
import org.apache.mahout.common.distance.CosineDistanceMeasure;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;

import java.util.Arrays;

public enum DistanceMeasures
{
    MEASURE_EUCLIDEAN_DISTANCE(EuclideanDistanceMeasure.class),
    MEASURE_COSINE_DISTANCE(CosineDistanceMeasure.class),
    MEASURE_CHEBYSHEV_DISTANCE(ChebyshevDistanceMeasure.class);

    private final Class<? extends DistanceMeasure> implementingClass;

    DistanceMeasures(Class<? extends DistanceMeasure> impl) {
        this.implementingClass = impl;
    }

    public String getClassname() {
        return implementingClass.getName();
    }

    public static String list() {
        return Arrays.toString(values());
    }
}
