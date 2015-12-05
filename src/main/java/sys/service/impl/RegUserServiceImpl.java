package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.RbacRegUser;
import sys.mapper.RbacRegUserMapper;
import sys.service.RegUserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegUserServiceImpl implements RegUserService
{
    @Resource
    private RbacRegUserMapper rbacRegUserMapper;

    public List<RbacRegUser> selectAllRegUsers()
    {
        return rbacRegUserMapper.selectAllRegUsers();
    }

    public RbacRegUser selectRegUserByID(Integer id)
    {
        return rbacRegUserMapper.selectRegUserByID(id);
    }

    public int insertRegUser(RbacRegUser rbacRegUser)
    {
        return rbacRegUserMapper.insertRegUser(rbacRegUser);
    }

    public int deleteRegUserByID(Integer id) {
        return rbacRegUserMapper.deleteRegUserByID(id);
    }
}
