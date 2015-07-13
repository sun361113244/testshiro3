package sys.service;

import sys.entity.RbacUser;

import java.util.List;

public interface UserService
{
    RbacUser getRbacUserByUserCode(String userCode);

    List<RbacUser> selectAllUsers();

    int selectIsUserCodeExist(String name);

    int selectIsUserCodeExistExceptId(Integer id, String name);

    int insertRabUser(RbacUser rbacUser);

    int deleteUserById(Integer id);

    int updateUser(RbacUser rbacUser);

    int updateUserStatusById(Integer id, Integer status);

}
