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
    public ModelAndView runItemCF(@RequestParam("onCluster")Boolean onCluster,
                                  @RequestParam("isOverWrite")Boolean isOverWrite,
                                  @RequestParam("input")String input ,
                                  @RequestParam("output")String output ,
                                  @RequestParam("tempDir")String tempDir ,
                                  @RequestParam("similarityClassname")String similarityClassname ,
                                  @RequestParam("outputPathForSimilarityMatrix")String outputPathForSimilarityMatrix ,
                                  String usersFile ,
                                  String itemsFile ,
                                  String filterFile ,
                                  Integer numRecommendations ,
                                  Boolean booleanData ,
                                  Integer maxPrefsPerUser ,
                                  Integer maxSimilaritiesPerItem ,
                                  Integer minPrefsPerUser ,
                                  Integer maxPrefsPerUserInItemSimilarity ,
                                  Double threshold)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        int checkRes = itemCFService.checkRunInputIllegal(onCluster ,isOverWrite ,input , output ,tempDir , similarityClassname ,
                outputPathForSimilarityMatrix , usersFile , itemsFile ,filterFile ,numRecommendations ,
                booleanData , maxPrefsPerUser , maxSimilaritiesPerItem , minPrefsPerUser ,
                maxPrefsPerUserInItemSimilarity , threshold);
        if( 1 == checkRes)
        {
            int runState = -10;
            if(onCluster)
            {
                runState = itemCFService.runItemCFOnCluster(input , output ,tempDir , similarityClassname ,
                        outputPathForSimilarityMatrix , usersFile , itemsFile ,filterFile ,numRecommendations ,
                        booleanData , maxPrefsPerUser , maxSimilaritiesPerItem , minPrefsPerUser ,
                        maxPrefsPerUserInItemSimilarity , threshold);
            }
            else
            {
//                runState = itemCFService.runItemCFLocal(input , output ,tempDir , similarityClassname ,
//                        outputPathForSimilarityMatrix , usersFile , itemsFile ,filterFile ,numRecommendations ,
//                        booleanData , maxPrefsPerUser , maxSimilaritiesPerItem , minPrefsPerUser ,
//                        maxPrefsPerUserInItemSimilarity , threshold);
            }
            mav.addObject("result" , runState);
        }
        else
        {
            mav.addObject("result" , checkRes);
        }
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
