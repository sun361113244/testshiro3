package mahout.classifier.controller;

import mahout.classifier.service.NaivebayesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/naivebayesController")
public class NaivebayesController
{
    @Resource
    private NaivebayesService naivebayesService;


    @RequestMapping("/runnaivebayes")
    public ModelAndView runnaivebayes(@RequestParam("onCluster")Boolean onCluster,
                                      @RequestParam("isOverWrite")Boolean isOverWrite,
                                      @RequestParam("input")String input ,
                                      @RequestParam("output")String output )
    {
        ModelAndView mav = new ModelAndView("JsonView");

        try
        {
            naivebayesService.runClassifier(onCluster , isOverWrite , input , output);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return mav;
    }
}
