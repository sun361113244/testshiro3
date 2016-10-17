package mahout.classifier.service;

public interface NaivebayesService
{
    void runClassifier(Boolean onCluster, Boolean isOverWrite, String input, String output) throws Exception;

    void splitInput(Boolean onCluster, Boolean isOverWrite, Boolean sequenceFiles , String input, String trainingOutput ,
                    String testOutput , Integer randomSelectionPct) throws Exception;
}
