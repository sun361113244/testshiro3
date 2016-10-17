package mahout.clustering.service;

import java.io.IOException;

public interface KmeansService
{
    int checkInput(Boolean onCluster, Boolean isOverWrite, String input, String output, String distanceMeasureClassName,
                   Integer k, Double convergenceDelta, Integer maxIterations);

    int runKmeans(Boolean onCluster, String input, String output, String distanceMeasureClassName, Integer k,
                   Double convergenceDelta, Integer maxIterations) throws InterruptedException, IOException, ClassNotFoundException;
}
