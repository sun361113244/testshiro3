package sys.mapper;

import org.apache.ibatis.annotations.Param;
import sys.entity.RbacRole;

import java.util.List;

public interface RbacUserRoleMapper
{
    List<RbacRole> selectRoleListByUserId(@Param("userid") Integer userid);

    int insertUserRoles(@Param("id") Integer id, @Param("node") Integer node);

    int deleteByUserId(@Param("id") Integer id);
}
