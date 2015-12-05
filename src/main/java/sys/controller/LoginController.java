package sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.RbacRegUser;
import sys.service.RegUserService;
import sys.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Controller
@RequestMapping("/loginController")
public class LoginController
{
    @Resource
    private UserService userService;

    @Resource
    private RegUserService regUserService;

    @RequestMapping("/Login")
    public String userLogin(@RequestParam(value = "loginName") String loginName,
                            @RequestParam(value = "loginPwd") String loginPwd, HttpServletRequest request)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);

        String error = request.getParameter("shiroLoginFailure");

        Subject currentUser = SecurityUtils.getSubject();

        try
        {
            currentUser.login(token);
        } catch (UnknownAccountException e)
        {
            error = "用户名未知";
        } catch (IncorrectCredentialsException e)
        {
            error = "密码错误";
        } catch (LockedAccountException e)
        {
            error = "用户被锁定";
        } catch (ExcessiveAttemptsException e)
        {
            error = "尝试次数超限";
        } catch (AuthenticationException e)
        {
            error = "其它错误:" + e.getMessage();
        }

        if (error != null)
        {
            return "redirect:/html/login.html";
        }
        else
        {
            userService.updateLoginTime(loginName , new Date());
            return "redirect:/html/homepage.html";
        }
    }

    @RequestMapping("/submitRegUserInfo")
    public ModelAndView submitRegUserInfo(@RequestParam("userName")String userName ,@RequestParam("userCode")String userCode ,
                                          @RequestParam("pwd")String pwd , @RequestParam("confirmPwd")String confirmPwd)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        if (!pwd.equals(confirmPwd))
        {
            mav.addObject("result" , -2);
            return mav;
        }
        Md5Hash md5Hashpwd = new Md5Hash(pwd );
        RbacRegUser rbacRegUser = new RbacRegUser(userName , userCode , md5Hashpwd.toString() , new Date());

        regUserService.insertRegUser(rbacRegUser);
        mav.addObject("result" , 1);
        return mav;
    }
}
