package sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.RbacUri;
import sys.service.UriService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/uriController")
public class UriController
{
    @Resource
    private UriService uriService;

    /**
     * 根据当前用户从数据库加载部门
     */
    @RequestMapping("/selectAllUris")
    public ModelAndView selectAllUris(Integer draw)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            List<RbacUri> uriList = uriService.selectUriList();

            if(draw != null)
            {
                mav.addObject("draw", draw);
            }

            mav.addObject("data", uriList);
            mav.addObject("recordsTotal", uriList.size());
            mav.addObject("recordsFiltered", uriList.size());
            return mav;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("result" , -1);
            return mav;
        }
    }

    @RequestMapping("/addUri")
    public ModelAndView addDepartment( @RequestParam("parentId")Integer parentId, @RequestParam("uriName")String uriName, @RequestParam("uriPermission")String uriPermission,
                                       @RequestParam("uriLoc")String uriLoc, @RequestParam("iconCls")String iconCls, @RequestParam("showType")Byte showType,
                                       @RequestParam("treeCode")String treeCode, @RequestParam("sort")Byte sort)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            RbacUri rbacUri = new RbacUri(parentId, uriName, uriPermission, uriLoc, iconCls, showType, treeCode, sort, new Date() , new Date());

            int res = uriService.insertUri(rbacUri);
            mav.addObject("sqlresult" ,res);
            return mav;
        }
        catch (Exception ex)
        {
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("sqlresult" ,-1);
            return mav;
        }
    }

    @RequestMapping("/editUri")
    public ModelAndView editUri(@RequestParam("id")Integer id , @RequestParam("parentId")Integer parentId, @RequestParam("uriName")String uriName,
                                @RequestParam("uriPermission")String uriPermission, @RequestParam("uriLoc")String uriLoc, @RequestParam("iconCls")String iconCls,
                                @RequestParam("showType")Byte showType, @RequestParam("treeCode")String treeCode, @RequestParam("sort")Byte sort)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            RbacUri rbacUri = new RbacUri(id ,parentId, uriName, uriPermission, uriLoc, iconCls, showType, treeCode, sort, null , new Date());

            int res = uriService.updateUri(rbacUri);
            mav.addObject("sqlresult" ,res);
            return mav;
        }
        catch (Exception ex)
        {
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("sqlresult" ,-1);
            return mav;
        }
    }

    @RequestMapping("/deleteUri")
    public ModelAndView deleteUri(@RequestParam("id")Integer id)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            int res = uriService.deleteUriById(id);

            mav.addObject("sqlresult" ,res);
            return mav;
        }
        catch (Exception ex)
        {
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("sqlresult" ,-1);
            return mav;
        }
    }

}
