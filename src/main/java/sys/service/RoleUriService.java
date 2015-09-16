package sys.service;

import sys.entity.RbacUri;

import java.util.List;

public interface RoleUriService
{
    List<RbacUri> selectUriListByRoleId(Integer id);
}
