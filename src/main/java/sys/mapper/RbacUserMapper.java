package sys.mapper;

import org.apache.ibatis.annotations.Param;
import sys.entity.RbacUser;

import java.util.List;

public interface RbacUserMapper
{
    RbacUser getRbacUserByUserCode(String userCode);

    List<RbacUser> selectAllUsers();

    int selectIsUserCodeExist(String name);

    int selectIsUserCodeExistExceptId(@Param("id")Integer id, @Param("name")String name);

    int insertRabUser(RbacUser rbacUser);

    int deleteUserById(Integer id);

    int updateUser(RbacUser rbacUser);

    int updateUserStatusById(@Param("id")Integer id, @Param("status")Integer status);

}
