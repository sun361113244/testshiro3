package hdfs.controller;

import hdfs.service.HDFSService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
