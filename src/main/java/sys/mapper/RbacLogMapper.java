package sys.mapper;

import org.apache.ibatis.annotations.Param;
import sys.entity.RbacLog;

import java.util.List;

public interface RbacLogMapper
{
    List<RbacLog> selectLogList();

    int insertLog(RbacLog rbacLog);
}
