package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.RbacUri;
import sys.mapper.RbacUserUriMapper;
import sys.service.UserUriService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserUriServiceImpl implements UserUriService
{
    @Resource
    private RbacUserUriMapper rbacUserUriMapper;

    public List<RbacUri> selectMenuListByUserId(Integer id)
    {
        return rbacUserUriMapper.selectMenuListByUserId(id);
    }
}
