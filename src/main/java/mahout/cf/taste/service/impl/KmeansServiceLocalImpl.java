//package mahout.cf.taste.service.impl;
//
//import com.google.common.collect.Lists;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.mahout.clustering.Cluster;
//import org.apache.mahout.clustering.classify.ClusterClassifier;
//import org.apache.mahout.clustering.iterator.ClusterIterator;
//import org.apache.mahout.clustering.iterator.KMeansClusteringPolicy;
//import org.apache.mahout.common.HadoopUtil;
//import org.apache.mahout.common.distance.DistanceMeasure;
//import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
//import org.apache.mahout.math.DenseVector;
//import org.apache.mahout.math.Vector;
//import org.springframework.stereotype.Service;
//import util.HDFSUtil;
//import util.HadoopCommonUtil;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Service
//public class KmeansServiceLocalImpl extends KmeansServiceAbstract
//{
//
//    public int runKmeans(Boolean isOverWrite, String input, String output, String similarityClassname, Integer k,
//                         Double convergenceDelta, Integer maxIterations) throws IOException
//    {
//        Collection<Vector> points = Lists.newArrayList();
//        generateKmeansData(points , input);
//
//        DistanceMeasure measure = new EuclideanDistanceMeasure();
//
//        List<Cluster> initialClusters = Lists.newArrayList();
//        generateInitialClusters(initialClusters , points , measure , k);
//
//        ClusterClassifier prior = new ClusterClassifier(initialClusters, new KMeansClusteringPolicy(convergenceDelta));
//        Path priorPath = new Path(output, Cluster.INITIAL_CLUSTERS_DIR);
//        prior.writeToSeqFiles(priorPath);
//
////        ClusterIterator.iterateSeq(HadoopCommonUtil.getConf(), samples, priorPath, new Path(output), maxIterations);
//        prior = ClusterIterator.iterate(points, prior, maxIterations);
//        Path finalPriorPath = new Path(output, Cluster.INITIAL_CLUSTERS_DIR + Cluster.FINAL_ITERATION_SUFFIX);
//        prior.writeToSeqFiles(finalPriorPath);
//
//        return 0;
//    }
//
//    private void generateInitialClusters(List<Cluster> initialClusters, Collection<Vector> points, DistanceMeasure measure, Integer k)
//    {
//        for (int id = 0 ; id < k ; id++)
//        {
//            initialClusters.add(new org.apache.mahout.clustering.kmeans.Kluster(points.iterator().next(), id, measure));
//        }
//    }
//
//    private void generateKmeansData(Collection<Vector> points, String input) throws IOException
//    {
//        String[] lines = HDFSUtil.readFile(input).replaceAll("\r" , "").split("\n");
//        for(String line : lines)
//        {
//            String[] fields = line.split("\\s+");
//            double[] values = new double[fields.length];
//            for(int i = 0 ; i < fields.length ; i++)
//            {
//                values[i] = Double.parseDouble(fields[i]);
//            }
//            points.add(new DenseVector(values));
//        }
//    }
//
//    public int runKmeans(Boolean onCluster, Boolean isOverWrite, String input, String output, String similarityClassname, Integer k, Double convergenceDelta, Integer maxIterations) throws InterruptedException, IOException, ClassNotFoundException
//    {
//        return 0;
//    }
//}
