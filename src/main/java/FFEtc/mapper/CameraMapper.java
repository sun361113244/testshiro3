package FFEtc.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CameraMapper
{
    int selectPlateRec(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectPlateNoRec(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectPlateNullRec(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectChkTotal_Obu(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectPlateRec_Obu(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectPlateNoRec_Obu(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectPlateNullRec_Obu(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);

    int selectPlateRightRec_Obu(@Param("dtfrom") Date dtfrom, @Param("dtto") Date dtto);
}
