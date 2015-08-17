package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.RbacRole;
import sys.mapper.RbacRoleMapper;
import sys.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    @Resource
    private RbacRoleMapper rbacRoleMapper;

    @Override
    public List<RbacRole> selectAllRoles()
    {
        return rbacRoleMapper.selectAllRoles();
    }

    @Override
    public int selectIsRoleNameExist(String name)
    {
        return rbacRoleMapper.selectIsRoleNameExist(name);
    }

    @Override
    public int selectIsRoleNameExistExceptID(Integer id, String name)
    {
        return rbacRoleMapper.selectIsRoleNameExistExceptID(id, name);
    }

    @Override
    public int insertRole(RbacRole role)
    {
        return rbacRoleMapper.insertRole(role);
    }

    @Override
    public int deleteUserRoleById(Integer id)
    {
        RbacRole role = new RbacRole();
        role.setId(id);
        role.setStatus(2);
        return rbacRoleMapper.updateRoleById(role);
    }

    @Override
    public int updateRoleById(RbacRole role)
    {
        return rbacRoleMapper.updateRoleById(role);
    }
}
