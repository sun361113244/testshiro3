package FFEtc.service.impl;

import FFEtc.mapper.CameraMapper;
import FFEtc.service.CameraService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CameraServiceImpl implements CameraService
{
    @Resource
    private CameraMapper cameraMapper;

    public int selectPlateRec(Date dtfrom, Date dtto)
    {
        return cameraMapper.selectPlateRec(dtfrom, dtto);
    }

    public int selectPlateNoRec(Date dtfrom, Date dtto)
    {
        return cameraMapper.selectPlateNoRec(dtfrom, dtto);
    }

    public int selectPlateNullRec(Date dtfrom, Date dtto)
    {
        return cameraMapper.selectPlateNullRec(dtfrom , dtto);
    }

    public int selectChkTotal_Obu(Date dtfrom, Date dtto)
    {
        return cameraMapper.selectChkTotal_Obu(dtfrom, dtto);
    }

    public int selectPlateRec_Obu(Date dtfrom, Date dtto)
    {
        return cameraMapper.selectPlateRec_Obu(dtfrom, dtto);
    }

    public int selectPlateNoRec_Obu(Date dtfrom, Date dtto)
    {
        return cameraMapper.selectPlateNoRec_Obu(dtfrom, dtto);
    }

    public int selectPlateNullRec_Obu(Date dtfrom, Date dtto)
    {
        return cameraMapper.selectPlateNullRec_Obu(dtfrom, dtto);
    }

    public int selectPlateRightRec_Obu(Date dtfrom, Date dtto)
    {
        return cameraMapper.selectPlateRightRec_Obu(dtfrom , dtto);
    }
}
