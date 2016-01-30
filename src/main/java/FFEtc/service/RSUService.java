package FFEtc.service;

import FFEtc.entity.RSUTransStatusRes;

import java.util.Date;
import java.util.List;

public interface RSUService
{
    List<RSUTransStatusRes> selectTransStatusRes_ByTime(Date dtfrom, Date dtto);
}
