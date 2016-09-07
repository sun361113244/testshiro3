package mahout.cf.taste.controller;

import org.apache.hadoop.hdfs.client.HdfsUtils;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.cf.taste.hadoop.item.RecommenderJob;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.HDFSUtil;
import util.HadoopCommonUtil;

@Controller
@RequestMapping("/ItemCFController")
public class ItemCFController
{
    @RequestMapping("/runItemCF")
    public ModelAndView runItemCF(@RequestParam("input")String input ,
                                  @RequestParam("output")String output ,
                                  @RequestParam("outputPathForSimilarityMatrix")String outputPathForSimilarityMatrix ,
                                  @RequestParam("similarityClassname")String similarityClassname)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        String[] args = new String[]{
                "-i" , input,
                "-o", output ,
                "-s", similarityClassname ,
                "--outputPathForSimilarityMatrix", outputPathForSimilarityMatrix};

        try
        {
            if(HDFSUtil.exits(output))
                HDFSUtil.deleteFile(output);
            if(HDFSUtil.exits(outputPathForSimilarityMatrix))
                HDFSUtil.deleteFile(outputPathForSimilarityMatrix);

            ToolRunner.run(HadoopCommonUtil.getConf(), new RecommenderJob(), args);
        } catch (Exception e)
        {
            mav.addObject("result" , -1);
            e.printStackTrace();
        }
        mav.addObject("result" , 1);
        return mav;
    }
}
