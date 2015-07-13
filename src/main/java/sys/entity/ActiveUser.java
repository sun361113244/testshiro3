package sys.entity;

import java.util.List;

public class ActiveUser implements java.io.Serializable
{
    private Integer userid;                                  // 用户id（主键）
    private String usercode;                             // 用户账号
    private String username;                             // 用户名称

    private List<RbacUri> menus;                  // 菜单
    private List<RbacUri> permissions;           // 权限
    private List<RbacDep> departments;              // 站点

    public Integer getUserid()
    {
        return userid;
    }

    public void setUserid(Integer userid)
    {
        this.userid = userid;
    }

    public String getUsercode()
    {
        return usercode;
    }

    public void setUsercode(String usercode)
    {
        this.usercode = usercode;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public List<RbacUri> getMenus()
    {
        return menus;
    }

    public void setMenus(List<RbacUri> menus)
    {
        this.menus = menus;
    }

    public List<RbacUri> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(List<RbacUri> permissions)
    {
        this.permissions = permissions;
    }

    public List<RbacDep> getDepartments()
    {
        return departments;
    }

    public void setDepartments(List<RbacDep> departments)
    {
        this.departments = departments;
    }
}

