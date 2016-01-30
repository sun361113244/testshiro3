package FFEtc.service.impl;

import FFEtc.entity.RSUTransStatusRes;
import FFEtc.mapper.RSUMapper;
import FFEtc.service.RSUService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RSUServiceImpl implements RSUService
{
    @Resource
    private RSUMapper rsuMapper;

    public List<RSUTransStatusRes> selectTransStatusRes_ByTime(Date dtfrom, Date dtto)
    {
        return rsuMapper.selectTransStatusRes_ByTime(dtfrom, dtto);
    }
}
