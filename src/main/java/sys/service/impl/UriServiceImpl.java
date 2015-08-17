package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.RbacUri;
import sys.mapper.RbacUriMapper;
import sys.service.UriService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UriServiceImpl implements UriService
{
    @Resource
    private RbacUriMapper rbacUriMapper;

    @Override
    public List<RbacUri> selectUriList()
    {
        return rbacUriMapper.selectUriList();
    }

    @Override
    public int insertUri(RbacUri rbacUri)
    {
        return rbacUriMapper.insertUri(rbacUri);
    }

    @Override
    public int updateUri(RbacUri rbacUri)
    {
        return rbacUriMapper.updateUri(rbacUri);
    }

    @Override
    public int deleteUriById(Integer id)
    {
        return rbacUriMapper.deleteUriById(id);
    }
}
