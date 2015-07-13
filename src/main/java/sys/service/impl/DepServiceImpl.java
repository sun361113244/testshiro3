package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.RbacDep;
import sys.mapper.RbacDepMapper;
import sys.service.DepService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepServiceImpl implements DepService
{
    @Resource
    private RbacDepMapper rbacDepMapper;

    @Override
    public int selectIsDepCodeExist(String code)
    {
        return rbacDepMapper.selectIsDepCodeExist(code);
    }

    @Override
    public int selectIsDepNameExist(String name)
    {
        return rbacDepMapper.selectIsDepNameExist(name);
    }

    @Override
    public int selectIsDepCodeExistExceptID(Integer id, String code)
    {
        return rbacDepMapper.selectIsDepCodeExistExceptID(id, code);
    }

    @Override
    public int selectIsDepNameExistExceptID(Integer id, String name)
    {
        return rbacDepMapper.selectIsDepNameExistExceptID(id , name);
    }

    @Override
    public List<RbacDep> selectDepList()
    {
        return rbacDepMapper.selectDepList();
    }

    @Override
    public int insertDep(RbacDep dep)
    {
        return rbacDepMapper.insertDep(dep);
    }

    @Override
    public int updateDep(RbacDep dep)
    {
        return rbacDepMapper.updateDep(dep);
    }
}
