package FFEtc.mapper;

import FFEtc.entity.RSUTransStatusRes;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RSUMapper
{
    List<RSUTransStatusRes> selectTransStatusRes_ByTime(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);
}
