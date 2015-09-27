package sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.RbacRole;

import sys.entity.RbacUri;
import sys.entity.zTreeNode;
import sys.service.RoleService;
import sys.service.RoleUriService;
import sys.service.UriService;
import sys.service.UserUriService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/roleController")
public class RoleController
{
    @Resource
    private RoleService roleService;

    @Resource
    private UriService uriService;

    @Resource
    private RoleUriService roleUriService;

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

    @RequestMapping("/selectTreeRolesById")
    public ModelAndView selectTreeRolesById(@RequestParam("id")Integer id)
    {
        try
        {
            // 获取所有权限
            ModelAndView mav = new ModelAndView("JsonView");
            List<RbacUri> uriList = uriService.selectUriList();

            List<RbacUri> idUriList = roleUriService.selectUriListByRoleId(id);

            List<zTreeNode> zTreeNodes = new ArrayList<zTreeNode>();
            for(RbacUri rbacUri : uriList)
            {
                if(idUriList.contains(rbacUri))
                {
                    zTreeNode node = new zTreeNode(rbacUri.getId() ,rbacUri.getParentId() , rbacUri.getName() ,true ,true );
                    zTreeNodes.add(node);
                }
                else
                {
                    zTreeNode node = new zTreeNode(rbacUri.getId() ,rbacUri.getParentId() , rbacUri.getName() ,true ,false );
                    zTreeNodes.add(node);
                }
            }

            mav.addObject("zNodes", zTreeNodes);
            mav.addObject("sqlRes", 1);
            return mav;
        }
        catch (Exception ex)
        {
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("sqlRes" , -1);
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

    @RequestMapping("/authorizeRoleUris")
    public ModelAndView authorizeRoleUris(@RequestParam("id")Integer id ,@RequestParam("nodes[]")Integer[] nodes)
    {
        try
        {
            ModelAndView mav = new ModelAndView("JsonView");

            int res = roleUriService.insertRoleUris(id , nodes);

            mav.addObject("sqlRes" , 1);
            return mav;
        }
        catch (Exception ex)
        {
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("sqlRes" ,-1);
            return mav;
        }
    }
}
