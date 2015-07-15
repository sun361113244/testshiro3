package sys.controller;

import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.ActiveUser;
import sys.entity.RbacDep;
import sys.service.DepService;
import sys.service.UserDepService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/departmentController")
public class DepartmentController
{
    @Resource
    private DepService depService;

    @Resource
    private UserDepService userDepService;

    /**
     * 根据当前用户从数据库加载部门
     */
    @RequestMapping("/selectAllStation")
    public ModelAndView selectAllDepartment(Integer draw)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            List<RbacDep> stationList = depService.selectDepList();

            if(draw != null)
            {
                mav.addObject("draw", draw);
            }
            mav.addObject("records", stationList);
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
    @RequestMapping("/selectAllStationOnSelUser")
    public ModelAndView selectAllStationOnSelUser(@RequestParam("id")Integer id)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            List<RbacDep> stationList = depService.selectDepList();

            List<RbacDep> userStationList = userDepService.selectDepListByUserId(id);

            mav.addObject("stationList", stationList);
            mav.addObject("userStationList", userStationList);
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

    /**
     * 根据当前用户从数据库加载部门
     */
    @RequestMapping("/selectAllDepartmentOnActiveUser")
    public ModelAndView selectAllDepartmentOnActiveUser(Integer draw)
    {
        try
        {
            Subject currentUser = SecurityUtils.getSubject();
            ActiveUser activeUser = (ActiveUser)currentUser.getPrincipal();

            ModelAndView mav = new ModelAndView("DataTablesAjaxView");
            List<RbacDep> departmentList = userDepService.selectDepListByUserId(activeUser.getUserid());

            if(draw != null)
            {
                mav.addObject("draw", draw);
            }
            mav.addObject("data", departmentList);
            mav.addObject("recordsTotal", departmentList.size());
            mav.addObject("recordsFiltered", departmentList.size());
            return mav;
        }
        catch (Exception ex)
        {
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("result" , -1);
            return mav;
        }
    }

    @RequestMapping("/addDepartment")
    public ModelAndView addDepartment( @RequestParam("code")String code, @RequestParam("name")String name)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            RbacDep dep = new RbacDep(code , name , new Date() , new Date());

            //站点编号存在 返回-2
            if(depService.selectIsDepCodeExist(code) > 0)
            {
                mav.addObject("sqlresult" , -2);
                return mav;
            }
            //站点名称存在 返回-3
            if(depService.selectIsDepNameExist(name) > 0)
            {
                mav.addObject("sqlresult" , -3);
                return mav;
            }
            int res = depService.insertDep(dep);
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

    @RequestMapping("/editDepartment")
    public ModelAndView editDep(@RequestParam("id")Integer id , @RequestParam("code")String code,
                                    @RequestParam("name")String name)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            RbacDep dep = new RbacDep(id ,code , name , null , new Date());

            //站点编号存在 返回-2
            if(depService.selectIsDepCodeExistExceptID(id, code) > 0)
            {
                mav.addObject("sqlresult" , -2);
                return mav;
            }
            //站点名称存在 返回-3
            if(depService.selectIsDepNameExistExceptID(id, name) > 0)
            {
                mav.addObject("sqlresult" , -3);
                return mav;
            }

            int res = depService.updateDep(dep);
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

    @RequestMapping("/deleteDepartment")
    public ModelAndView deleteDep(@RequestParam("id")Integer id)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            RbacDep dep = new RbacDep( id ,null ,null , null, null , (byte)2);
            int res = depService.updateDep(dep);

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
