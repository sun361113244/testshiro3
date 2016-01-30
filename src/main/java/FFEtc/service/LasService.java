package FFEtc.service;

import java.util.Date;

public interface LasService
{
    int selectUnChkNum_ByTime(Date dtfrom, Date dtto);

    int selectLackLasLnNum_ByTime(Date dtfrom, Date dtto);
}
