package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.RbacLog;
import sys.mapper.RbacLogMapper;
import sys.service.LogService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService
{
    @Resource
    private RbacLogMapper rbacLogMapper;

    public List<RbacLog> selectLogList()
    {
        return rbacLogMapper.selectLogList();
    }

    public int insertLog(RbacLog rbacLog)
    {
        return rbacLogMapper.insertLog(rbacLog);
    }
}
