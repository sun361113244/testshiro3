package mahout.classifier.service.impl;

import mahout.classifier.service.NaivebayesService;
import org.apache.commons.io.Charsets;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.text.SequenceFilesFromDirectory;
import org.apache.mahout.utils.SplitInput;
import org.springframework.stereotype.Service;
import util.HadoopCommonUtil;

@Service
public class NaivebayesServiceImpl implements NaivebayesService
{
    public void runClassifier(Boolean onCluster, Boolean isOverWrite, String input, String output) throws Exception
    {
        runSequenceFilesFromDirectory(onCluster , isOverWrite , input , output);
    }

    public void splitInput(Boolean onCluster, Boolean isOverWrite, Boolean sequenceFiles, String input,
                           String trainingOutput, String testOutput, Integer randomSelectionPct) throws Exception
    {
        String[] args =
                    {   "--method", onCluster ? "mapreduce" : "sequential",
                        "--input", input,
                        "--trainingOutput", trainingOutput ,
                        "--testOutput" , testOutput ,
                        "--randomSelectionPct", Integer.toString(randomSelectionPct),
                        "--sequenceFiles" ,
                        "-ow" };
        ToolRunner.run(HadoopCommonUtil.getConf() , new SplitInput(), args);
    }

    private void runSequenceFilesFromDirectory(Boolean onCluster, Boolean isOverWrite, String input, String output) throws Exception
    {
        String[] args = { "--input" , input ,
                "--output" , output ,
                "--charset", Charsets.UTF_8.name(),
                "--method", onCluster ? "mapreduce" : "sequential"
        };

        SequenceFilesFromDirectory.main(args);
    }
}
