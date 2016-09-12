package mahout.cf.taste.controller;

import mahout.cf.taste.entity.IRStatisticsEntity;
import mahout.cf.taste.service.ItemCFService;
import org.apache.hadoop.hdfs.client.HdfsUtils;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.hadoop.item.RecommenderJob;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.HDFSUtil;
import util.HadoopCommonUtil;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("/ItemCFController")
public class ItemCFController
{
    @Resource
    private ItemCFService itemCFService;

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

    @RequestMapping("/getRecommendResult")
    public ModelAndView getRecommendResult(@RequestParam("userId") String userId ,
                                   @RequestParam("recommendNum") String recommendNum)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        try
        {
            String content = itemCFService.getRecommendResultByUserId("/itemcf/output/result/part-r-00000" , userId , recommendNum);


            mav.addObject("result" , 1);
            mav.addObject("content" , content);
        } catch (IOException e)
        {
            mav.addObject("result" , -1);
            mav.addObject("content" , "文件打开失败,msg = " + e.getMessage());
            e.printStackTrace();
        }
        mav.addObject("userId" , userId);
        return mav;
    }

    @RequestMapping("/evaluateRecommend")
    public ModelAndView evaluateRecommend(@RequestParam("evaluatePath")String evaluatePath ,
                                          @RequestParam("evaluateTopN")String evaluateTopN ,
                                          @RequestParam("evaluateSimilarityClassname")String evaluateSimilarityClassname)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        try
        {
            IRStatisticsEntity status = itemCFService.evaluateRecommend(evaluatePath , evaluateTopN , evaluateSimilarityClassname);
            mav.addObject("result" , 1);
            mav.addObject("fallout" , status.getFallOut());
            mav.addObject("precision" , status.getPrecision());
            mav.addObject("reach" , status.getReach());
            mav.addObject("recall" , status.getRecall());

        } catch (IOException e)
        {
            mav.addObject("result" , -1);
            e.printStackTrace();
        } catch (TasteException e)
        {
            mav.addObject("result" , -2);
            e.printStackTrace();
        }

        return mav;
    }
}
