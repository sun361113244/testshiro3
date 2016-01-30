package FFEtc.service;

import java.util.Date;

public interface CameraService
{
    int selectPlateRec(Date dtfrom, Date dtto);

    int selectPlateNoRec(Date dtfrom, Date dtto);

    int selectPlateNullRec(Date dtfrom, Date dtto);

    int selectChkTotal_Obu(Date dtfrom, Date dtto);

    int selectPlateRec_Obu(Date dtfrom, Date dtto);

    int selectPlateNoRec_Obu(Date dtfrom, Date dtto);

    int selectPlateNullRec_Obu(Date dtfrom, Date dtto);

    int selectPlateRightRec_Obu(Date dtfrom, Date dtto);
}
