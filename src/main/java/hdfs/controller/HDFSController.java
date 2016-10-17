package hdfs.controller;

import hdfs.service.HDFSService;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.Vectors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.ETreeNode;
import util.HDFSUtil;
import util.HadoopCommonUtil;

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

    @RequestMapping("/showFileContent")
    public ModelAndView showFileContent(@RequestParam("path") String path)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        String content = "";
        try
        {
            if(path.contains(".bin"))
            {
                Vector map=getVector(path);
                content = map.asFormatString();
            }
            else
            {
                content = HDFSUtil.readFile(path).replaceAll("\t" , "&nbsp;&nbsp;").replaceAll("\n" , "<br/>");
            }

            mav.addObject("content" , content);
            mav.addObject("result" , 1);
        } catch (IOException e)
        {
            e.printStackTrace();
            mav.addObject("result" , -1);
        }

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

    @RequestMapping("/hdfsDel")
    public ModelAndView hdfsDel(@RequestParam("path") String path) throws IOException
    {
        ModelAndView mav = new ModelAndView("JsonView");
        HDFSUtil.deleteFile(path);

        mav.addObject("result" ,1);
        return mav;
    }

    // 读取.bin文件
    public Vector getVector(String path)
    {

        Vector vector = null;
        try
        {
            vector = Vectors.read(new Path(path), HadoopCommonUtil.getConf());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return vector;
    }
}
