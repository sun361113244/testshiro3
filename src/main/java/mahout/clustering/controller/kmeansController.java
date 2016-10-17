package mahout.clustering.controller;

import mahout.clustering.service.KmeansService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("/kmeansController")
public class kmeansController
{
    @Resource
    private KmeansService kmeansService;

    @RequestMapping("/runKmeans")
    public ModelAndView runKmeans(@RequestParam("onCluster")Boolean onCluster,
                                  @RequestParam("isOverWrite")Boolean isOverWrite,
                                  @RequestParam("input")String input ,
                                  @RequestParam("output")String output ,
                                  @RequestParam("distanceMeasureClassName")String distanceMeasureClassName ,
                                  @RequestParam("k")Integer k ,
                                  @RequestParam("convergenceDelta")Double convergenceDelta ,
                                  @RequestParam("maxIterations")Integer maxIterations)
    {
        ModelAndView mav = new ModelAndView("");

        int checkRes = kmeansService.checkInput(onCluster , isOverWrite , input , output , distanceMeasureClassName , k , convergenceDelta ,
                maxIterations);

        if(checkRes > 0)
        {
            try
            {
                kmeansService.runKmeans(onCluster , input , output , distanceMeasureClassName , k , convergenceDelta , maxIterations);
            } catch (InterruptedException e)
            {
                mav.addObject("result" , -11);
                e.printStackTrace();
            } catch (IOException e)
            {
                mav.addObject("result" , -12);
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                mav.addObject("result" , -13);
                e.printStackTrace();
            }
        }
        else
        {
            mav.addObject("result" , checkRes);
        }

        return mav;
    }
}
