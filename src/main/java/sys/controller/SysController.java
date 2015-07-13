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

    @Resource
    private UserDepService userDepService;

    @RequestMapping("/selectActiveInfo")
    public ModelAndView selectActiveInfo(Integer index)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        Subject currentUser = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) currentUser.getPrincipal();
        String menuList = menuService.getMenuStr(activeUser.getMenus() ,index);

        mav.addObject("activeUserName", activeUser.getUsername());
        mav.addObject("menuStr", menuList);
        return mav;
    }

    @RequestMapping("/authorizeDep")
    public ModelAndView authorizeDep(@RequestParam("id")Integer id , @RequestParam("arrayStations[]")Integer[] arrayStations)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");

            int delRes = userDepService.deleteByUserId(id);

            for(int i = 0 ; i <arrayStations.length ; i++)
            {
                userDepService.insertUserDep( id , arrayStations[i]);
            }

            mav.addObject("sqlresult" ,delRes);
            return mav;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("sqlresult" ,-1);
            return mav;
        }
    }
}
