package sys.mapper;

import org.apache.ibatis.annotations.Param;
import sys.entity.RbacDep;

import java.util.List;

public interface RbacDepMapper
{
    int selectIsDepCodeExist(String code);

    int selectIsDepNameExist(String name);

    int selectIsDepCodeExistExceptID(@Param("id")Integer id, @Param("code")String code);

    int selectIsDepNameExistExceptID(@Param("id")Integer id, @Param("name")String name);

    List<RbacDep> selectDepList();

    int insertDep(RbacDep dep);

    int updateDep(RbacDep dep);

}
