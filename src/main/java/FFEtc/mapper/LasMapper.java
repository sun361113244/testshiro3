package FFEtc.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LasMapper
{
    int selectUnChkNum_ByTime(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectLackLasLnNum_ByTime(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);
}
