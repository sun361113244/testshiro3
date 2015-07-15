package sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.ActiveUser;
import sys.entity.RbacRole;
import sys.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/roleController")
public class RoleController
{
    @Resource
    private RoleService roleService;

    @RequestMapping("/selectAllRoleOnActiveUser")
    public ModelAndView selectAllRoleOnActiveUser(Integer draw)
    {
        try
        {
            Subject currentUser = SecurityUtils.getSubject();
            ActiveUser activeUser = (ActiveUser)currentUser.getPrincipal();

            ModelAndView mav = new ModelAndView("DataTablesAjaxView");
            List<RbacRole> roleList = roleService.selectRoleListByUserId(activeUser.getUserid());

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
}
