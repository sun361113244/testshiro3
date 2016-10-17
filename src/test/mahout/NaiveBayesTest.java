package mahout;

import mahout.classifier.service.NaivebayesService;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.vectorizer.SparseVectorsFromSequenceFiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.HadoopCommonUtil;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" , "classpath:spring-mybatis.xml"})
public class NaiveBayesTest
{
    @Resource
    private NaivebayesService naivebayesService;

    @Test
    public void test1() throws Exception
    {
        String input = "/naivebayes/input";
        String output = "/naivebayes/output";
        boolean onCluster = false;
        boolean isOverWrite = true;

        HadoopUtil.delete(HadoopCommonUtil.getConf() , new Path(output));

        naivebayesService.runClassifier(onCluster ,isOverWrite ,input ,output);
    }

    @Test
    public void test2() throws Exception
    {
        String input = "/naivebayes/output";
        String output = "/naivebayes/vectors";
        String tempDir = "/naivebayes/temp";

        HadoopUtil.delete(HadoopCommonUtil.getConf() , new Path(output));
        HadoopUtil.delete(HadoopCommonUtil.getConf() , new Path(tempDir));

        SparseVectorsFromSequenceFiles.main(new String[]{
                "--input", input,
                "--output", output ,
                "--sequentialAccessVector" ,
                "-a","org.apache.lucene.analysis.core.WhitespaceAnalyzer" ,"-ow"});
    }

    @Test
    public void testsplitInput() throws Exception
    {
        String input = "/naivebayes/vectors/tfidf-vectors";
        String trainingOutput = "/naivebayes/trainingOutput";
        String testOutput = "/naivebayes/testOutput";
        boolean onCluster = false;
        boolean isOverWrite = true;
        naivebayesService.splitInput(onCluster , isOverWrite ,true ,input ,trainingOutput , testOutput , 20);
    }
}
