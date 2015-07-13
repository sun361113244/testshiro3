package sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/loginController")
public class LoginController
{
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
            return "redirect:/html/login.html";
        else
            return "redirect:/html/homepage.html";
    }
}
