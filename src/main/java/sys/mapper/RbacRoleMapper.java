package sys.mapper;

import org.apache.ibatis.annotations.Param;
import sys.entity.RbacRole;

import java.util.List;


public interface RbacRoleMapper
{
    List<RbacRole> selectAllRoles();

    int selectIsRoleNameExist(String name);

    int selectIsRoleNameExistExceptID(@Param("id") Integer id, @Param("name") String name);

    int insertRole(RbacRole role);

    int updateRoleById(RbacRole role);
}
