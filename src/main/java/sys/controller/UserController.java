package sys.controller;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.RbacUser;
import sys.service.UserRoleService;
import sys.service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/userController")
public class UserController
{
    @Resource
    private UserService userService;

    @RequestMapping("/selectAllUsers")
    public ModelAndView selectAllUsers(Integer draw)
    {
        try
        {
            ModelAndView mav = new ModelAndView("DataTablesAjaxView");
            List<RbacUser> userList = userService.selectAllUsers();

            if(draw != null)
            {
                mav.addObject("draw", draw);
            }
            mav.addObject("records", userList);
            mav.addObject("recordsTotal", userList.size());
            mav.addObject("recordsFiltered", userList.size());
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

    @RequestMapping("/addUser")
    public ModelAndView addUser(Integer id , String name , String pwd , String givenname , Byte status)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");

            Md5Hash md5Hashpwd = new Md5Hash(pwd );
            RbacUser rbacUser = new RbacUser(null , name, md5Hashpwd.toString(), givenname ,status ,new Date(), new Date() , null , 0 );

            // 用户编号存在 返回-2
            if(userService.selectIsUserCodeExist(name) > 0)
            {
                mav.addObject("sqlresult" , -2);
                return mav;
            }

            int sqlRes = userService.insertRabUser(rbacUser);

            //userRoleService.insertSrchRoleBy
            mav.addObject("sqlresult" ,sqlRes);

            return mav;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("sqlresult" , -1);
            return mav;
        }
    }

    @RequestMapping("/editUser")
    public ModelAndView editUser(Integer id , String name , String pwd , String givenname , Byte status)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");

            Md5Hash md5Hashpwd = new Md5Hash(pwd );
            RbacUser rbacUser = new RbacUser(id , name, md5Hashpwd.toString(), givenname ,status , null, new Date() , null , 0 );

            // 用户编号存在 返回-2
            if(userService.selectIsUserCodeExistExceptId( id , name) > 0)
            {
                mav.addObject("sqlresult" , -2);
                return mav;
            }

            int sqlRes = userService.updateUser(rbacUser);
            mav.addObject("sqlresult" ,sqlRes);

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

    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(Integer id)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");
            int res = userService.updateUserStatusById(id , 2);

            mav.addObject("sqlresult" ,res);
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
