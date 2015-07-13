package sys.reamls;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import sys.entity.ActiveUser;
import sys.entity.RbacDep;
import sys.entity.RbacUri;
import sys.entity.RbacUser;
import sys.service.UserDepService;
import sys.service.UserService;
import sys.service.UserUriService;

import javax.annotation.Resource;
import java.util.List;

public class UserRealm extends AuthorizingRealm
{
    @Resource
    private UserService userService;

    @Resource
    private UserUriService userUriService;

    @Resource
    private UserDepService userDepService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        // token是用户输入的用户名和密码
        String userCode = (String)token.getPrincipal();

        RbacUser rbacUser = null;
        try
        {
            rbacUser = userService.getRbacUserByUserCode(userCode);
        }
        catch ( Exception ex)
        {
            ex.printStackTrace();
        }
        if(rbacUser == null)
        {
            throw new UnknownAccountException();//没找到帐号
        }
        if (rbacUser.getStatus() == 2)
        {
            throw new LockedAccountException(); //帐号锁定
        }

        List<RbacUri> menus = null;
        try
        {
            menus = userUriService.selectMenuListByUserId(rbacUser.getId());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        List<RbacDep> departments = null;

        try
        {
            departments = userDepService.selectDepListByUserId(rbacUser.getId());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserid(rbacUser.getId());
        activeUser.setUsercode(rbacUser.getUserName());
        activeUser.setUsername(rbacUser.getGivenName());
        activeUser.setMenus(menus);
        activeUser.setDepartments(departments);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, rbacUser.getUserPasswrod(),this.getName());

        return simpleAuthenticationInfo;
    }
}
