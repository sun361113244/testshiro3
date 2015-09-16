package sys.mapper;

import sys.entity.RbacUri;

import java.util.List;


public interface RbacUserUriMapper
{
    List<RbacUri> selectMenuListByUserId(Integer id);

    List<RbacUri> selectUriListByUserId(Integer id);
}
