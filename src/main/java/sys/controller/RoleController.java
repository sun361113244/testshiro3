package sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.RbacRole;

import sys.service.UserRoleService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/roleController")
public class RoleController
{
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping("/selectAllRoles")
    public ModelAndView selectAllRoles(Integer draw)
    {
        try
        {
            ModelAndView mav = new ModelAndView("DataTablesAjaxView");
            List<RbacRole> roleList = userRoleService.selectAllRoles();

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
