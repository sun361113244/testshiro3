package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.RbacDep;
import sys.mapper.RbacUserDepMapper;
import sys.service.UserDepService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDepServiceImpl implements UserDepService
{
    @Resource
    private RbacUserDepMapper rbacUserDepMapper;

    public List<RbacDep> selectDepListByUserId(Integer id)
    {
        return rbacUserDepMapper.selectDepListByUserId(id);
    }

    public int deleteByUserId(Integer id)
    {
        return rbacUserDepMapper.deleteByUserId(id);
    }

    public void insertUserDep(Integer id, Integer stationId)
    {
        rbacUserDepMapper.insertUserDep(id , stationId);
    }
}
