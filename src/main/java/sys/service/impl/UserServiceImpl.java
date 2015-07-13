package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.mapper.RbacUserMapper;
import sys.entity.RbacUser;
import sys.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private RbacUserMapper rbacUserMapper;

    @Override
    public RbacUser getRbacUserByUserCode(String userCode)
    {
        return rbacUserMapper.getRbacUserByUserCode(userCode);
    }

    @Override
    public List<RbacUser> selectAllUsers()
    {
        return rbacUserMapper.selectAllUsers();
    }

    @Override
    public int selectIsUserCodeExist(String name)
    {
        return rbacUserMapper.selectIsUserCodeExist(name);
    }

    @Override
    public int selectIsUserCodeExistExceptId(Integer id, String name)
    {
        return rbacUserMapper.selectIsUserCodeExistExceptId( id , name);
    }

    @Override
    public int insertRabUser(RbacUser rbacUser)
    {
        return rbacUserMapper.insertRabUser(rbacUser);
    }

    @Override
    public int deleteUserById(Integer id)
    {
        return rbacUserMapper.deleteUserById(id);
    }

    @Override
    public int updateUser(RbacUser rbacUser)
    {
        return rbacUserMapper.updateUser(rbacUser);
    }

    @Override
    public int updateUserStatusById(Integer id, Integer status)
    {
        return rbacUserMapper.updateUserStatusById( id , status);
    }

}
