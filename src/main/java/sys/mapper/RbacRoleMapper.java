package sys.mapper;

import sys.entity.RbacRole;

import java.util.List;


public interface RbacRoleMapper
{
    List<RbacRole> selectAllRoles();
}
