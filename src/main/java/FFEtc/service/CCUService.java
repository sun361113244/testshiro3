package FFEtc.service;

import java.util.Date;

public interface CCUService
{

    int selectTotoalRec_ByTime(Date dtfrom, Date dtto);

    int selectTotalTrdRec_HasObuID_ByTime(Date dtfrom, Date dtto);

    int selectTotalTrdRec_No12_ByTime(Date dtfrom, Date dtto);

    int selectObuNoMatchLas_Suc_ByTime(Date dtfrom, Date dtto);

    int selectObuNoMatchLas_Fail_ByTime(Date dtfrom, Date dtto);
}
