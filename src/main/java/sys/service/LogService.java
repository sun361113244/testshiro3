package sys.service;

import sys.entity.RbacLog;

import java.util.List;

public interface LogService
{
    List<RbacLog> selectLogList();

    int insertLog(RbacLog rbacLog);
}
