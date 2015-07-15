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
    private RbacRoleMapper rbacDepMapper;

    public List<RbacRole> selectRoleListByUserId(Integer userid)
    {
        return rbacDepMapper.selectRoleListByUserId(userid);
    }
}
