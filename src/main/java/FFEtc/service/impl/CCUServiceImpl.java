package FFEtc.service.impl;

import FFEtc.mapper.CCUMapper;
import FFEtc.service.CCUService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CCUServiceImpl implements CCUService
{
    @Resource
    private CCUMapper ccuMapper;

    public int selectTotoalRec_ByTime(Date dtfrom, Date dtto)
    {
        return ccuMapper.selectTotoalRec_ByTime(dtfrom , dtto);
    }

    public int selectTotalTrdRec_HasObuID_ByTime(Date dtfrom, Date dtto)
    {
        return ccuMapper.selectTotalTrdRec_HasObuID_ByTime(dtfrom, dtto);
    }

    public int selectTotalTrdRec_No12_ByTime(Date dtfrom, Date dtto)
    {
        return ccuMapper.selectTotalTrdRec_No12_ByTime(dtfrom, dtto);
    }

    public int selectObuNoMatchLas_Suc_ByTime(Date dtfrom, Date dtto)
    {
        return ccuMapper.selectObuNoMatchLas_Suc_ByTime(dtfrom, dtto);
    }

    public int selectObuNoMatchLas_Fail_ByTime(Date dtfrom, Date dtto)
    {
        return ccuMapper.selectObuNoMatchLas_Fail_ByTime(dtfrom, dtto);
    }
}
