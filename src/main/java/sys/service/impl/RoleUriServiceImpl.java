package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.RbacUri;
import sys.mapper.RbacRoleUriMapper;
import sys.service.RoleUriService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleUriServiceImpl implements RoleUriService
{
    @Resource
    private RbacRoleUriMapper rbacRoleUriMapper;

    @Override
    public List<RbacUri> selectUriListByRoleId(Integer id)
    {
        return rbacRoleUriMapper.selectUriListByRoleId(id);
    }
}
