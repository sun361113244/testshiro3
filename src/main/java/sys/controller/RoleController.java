package sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.RbacRole;

import sys.service.RoleService;
import sys.service.UserRoleService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/roleController")
public class RoleController
{
    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @RequestMapping("/selectAllRoles")
    public ModelAndView selectAllRoles(Integer draw)
    {
        try
        {
            ModelAndView mav = new ModelAndView("DataTablesAjaxView");
            List<RbacRole> roleList = roleService.selectAllRoles();

            if(draw != null)
            {
                mav.addObject("draw", draw);
            }
            mav.addObject("data", roleList);
            mav.addObject("recordsTotal", roleList.size());
            mav.addObject("recordsFiltered", roleList.size());
            return mav;
        }
        catch (Exception ex)
        {
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("result" , -1);
            return mav;
        }
    }

    @RequestMapping("/addRole")
    public ModelAndView addRole( @RequestParam("name")String name, @RequestParam("description")String description)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            RbacRole role = new RbacRole(null , name , description , new Date() , new Date(), 1);

            //角色名称存在 返回-2
            if(roleService.selectIsRoleNameExist(name) > 0)
            {
                mav.addObject("sqlresult" , -2);
                return mav;
            }
            int res = roleService.insertRole(role);
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

    @RequestMapping("/editRole")
    public ModelAndView editRole(@RequestParam("id")Integer id , @RequestParam("name")String name,
                                @RequestParam("description")String description)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            RbacRole role = new RbacRole(id , name , description , null , new Date(), null);

            //站点编号存在 返回-2
            if(roleService.selectIsRoleNameExistExceptID(id, name) > 0)
            {
                mav.addObject("sqlresult" , -2);
                return mav;
            }

            int res = roleService.updateRoleById(role);
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

    @RequestMapping("/deleteRole")
    public ModelAndView deleteRole(@RequestParam("id")Integer id)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            int res = roleService.deleteUserRoleById(id);

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
