package sys.mapper;

import org.apache.ibatis.annotations.Param;
import sys.entity.RbacDep;

import java.util.List;

public interface RbacUserDepMapper
{
    List<RbacDep> selectDepListByUserId(Integer id);

    int deleteByUserId(Integer id);

    void insertUserDep(@Param("id")Integer id, @Param("stationId")Integer stationId);
}
