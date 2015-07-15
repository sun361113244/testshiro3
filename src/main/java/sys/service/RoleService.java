package sys.service;

import sys.entity.RbacRole;

import java.util.List;

public interface RoleService
{
    List<RbacRole> selectRoleListByUserId(Integer userid);
}
