package sys.service;

import sys.entity.RbacRole;

import java.util.List;

/**
 * Created by Csun on 2015-06-06.
 */
public interface UserRoleService
{
    List<RbacRole> selectAllRoles();
}
