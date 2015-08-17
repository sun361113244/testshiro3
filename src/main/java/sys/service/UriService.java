package sys.service;

import sys.entity.RbacUri;

import java.util.List;

public interface UriService
{
    List<RbacUri> selectUriList();

    int insertUri(RbacUri rbacUri);

    int deleteUriById(Integer id);

    int updateUri(RbacUri rbacUri);
}
