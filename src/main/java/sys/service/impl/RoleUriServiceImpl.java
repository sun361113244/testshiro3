package sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sys.entity.RbacUri;
import sys.mapper.RbacRoleUriMapper;
import sys.service.RoleUriService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RoleUriServiceImpl implements RoleUriService
{
    @Resource
    private RbacRoleUriMapper rbacRoleUriMapper;

    public List<RbacUri> selectUriListByRoleId(Integer id)
    {
        return rbacRoleUriMapper.selectUriListByRoleId(id);
    }

    @Transactional
    public int insertRoleUris(Integer id, Integer[] nodes)
    {
        rbacRoleUriMapper.deleteRoleUris(id);
        for(int i = 0; i<nodes.length;i++)
        {
            rbacRoleUriMapper.insertRoleUri(id, nodes[i]);
        }
        return 1;
    }
}
