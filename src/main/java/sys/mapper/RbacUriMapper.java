package sys.mapper;

import org.apache.ibatis.annotations.Param;
import sys.entity.RbacUri;

import java.util.List;

public interface RbacUriMapper
{
    List<RbacUri> selectUriList();

    int insertUri(RbacUri rbacUri);

    int deleteUriById(@Param("id") Integer id);

    int updateUri(RbacUri rbacUri);
}
