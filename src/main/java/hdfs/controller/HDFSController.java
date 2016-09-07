package hdfs.controller;

import hdfs.service.HDFSService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.ETreeNode;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/hdfsController")
public class HDFSController
{
    @Resource
    private HDFSService hdfsService;

    @RequestMapping("/isHadoopClusterEnabled")
    public ModelAndView isHadoopClusterEnabled(@RequestParam("url")String url)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        int result = hdfsService.isHadoopClusterEnabled(url);

        if(result == 1)
        {
            mav.addObject("result" ,1);
        }
        else
        {
            mav.addObject("result" ,result);
        }

        return mav;
    }

    @RequestMapping("/listDirectoryStructs")
    public ModelAndView listDirectoryStructs() throws IOException
    {
        ModelAndView mav = new ModelAndView("ETreeView");
        ETreeNode eTreeNodes = hdfsService.listDirectoryStructs("/");
        mav.addObject("root" ,eTreeNodes);
        return mav;
    }

    @RequestMapping("/addNewFolder")
    public ModelAndView addNewFolder(String path , String newFolderName) throws IOException
    {
        ModelAndView mav = new ModelAndView("ETreeView");

//        ETreeNode eTreeNodes = hdfsService.listDirectoryStructs("/");
//        mav.addObject("root" ,eTreeNodes);
        return mav;
    }
}
