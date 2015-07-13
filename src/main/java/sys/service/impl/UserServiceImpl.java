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

    public RbacUser getRbacUserByUserCode(String userCode)
    {
        return rbacUserMapper.getRbacUserByUserCode(userCode);
    }

    public List<RbacUser> selectAllUsers()
    {
        return rbacUserMapper.selectAllUsers();
    }

    public int selectIsUserCodeExist(String name)
    {
        return rbacUserMapper.selectIsUserCodeExist(name);
    }

    public int selectIsUserCodeExistExceptId(Integer id, String name)
    {
        return rbacUserMapper.selectIsUserCodeExistExceptId( id , name);
    }

    public int insertRabUser(RbacUser rbacUser)
    {
        return rbacUserMapper.insertRabUser(rbacUser);
    }

    public int deleteUserById(Integer id)
    {
        return rbacUserMapper.deleteUserById(id);
    }

    public int updateUser(RbacUser rbacUser)
    {
        return rbacUserMapper.updateUser(rbacUser);
    }

    public int updateUserStatusById(Integer id, Integer status)
    {
        return rbacUserMapper.updateUserStatusById( id , status);
    }

}
