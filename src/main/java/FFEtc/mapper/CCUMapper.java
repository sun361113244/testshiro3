package FFEtc.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CCUMapper
{

    int selectTotoalRec_ByTime(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectTotalTrdRec_HasObuID_ByTime(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectTotalTrdRec_No12_ByTime(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectObuNoMatchLas_Suc_ByTime(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectObuNoMatchLas_Fail_ByTime(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);
}
