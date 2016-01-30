package FFEtc.service.impl;

import FFEtc.mapper.LasMapper;
import FFEtc.service.LasService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class LasServiceImpl implements LasService
{
    @Resource
    private LasMapper lasMapper;

    public int selectUnChkNum_ByTime(Date dtfrom, Date dtto)
    {
        return lasMapper.selectUnChkNum_ByTime(dtfrom , dtto);
    }

    public int selectLackLasLnNum_ByTime(Date dtfrom, Date dtto)
    {
        return lasMapper.selectLackLasLnNum_ByTime(dtfrom , dtto);
    }
}
