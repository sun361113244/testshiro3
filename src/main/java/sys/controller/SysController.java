package sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.ActiveUser;
import sys.service.MenuService;
import sys.service.UserDepService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sysController")
public class SysController
{
    @Resource
    private MenuService menuService;

    @RequestMapping("/selectActiveInfo")
    public ModelAndView selectActiveInfo(Integer index)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        Subject currentUser = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) currentUser.getPrincipal();
        String menuList = menuService.getMenuStr(activeUser.getMenus() ,index);

        mav.addObject("activeUserName", activeUser.getUsername());
        mav.addObject("activeUserCode" , activeUser.getUsercode());
        mav.addObject("menuStr", menuList);
        return mav;
    }
}
