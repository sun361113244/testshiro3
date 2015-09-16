package sys.mapper;

import org.apache.ibatis.annotations.Param;
import sys.entity.RbacUri;

import java.util.List;

public interface RbacRoleUriMapper
{
    List<RbacUri> selectUriListByRoleId(@Param("id") Integer id);
}
