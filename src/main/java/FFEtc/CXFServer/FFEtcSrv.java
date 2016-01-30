package FFEtc.CXFServer;

import FFEtc.LocSrv.UpLoadService;
import FFEtc.entity.TransData;
import FFEtc.service.TransDataService;

import javax.annotation.Resource;
import javax.jws.WebService;

@WebService(endpointInterface = "FFEtc.LocSrv.UpLoadService", serviceName = "UpLoadService")
public class FFEtcSrv implements UpLoadService
{
    @Resource
    private TransDataService transDataService;

    //return 1：成功。-1：失败。
    public int SendTransData(TransData transData)
    {
        try
        {
            if(transData.getVehid() != null &&  !transData.getVehid().equals(""))
            {
                int res = transDataService.insertSelective(transData);
                if(res >  0)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
        }
        catch (Exception ex)
        {
            ;return -1;
        }
        return 0;
    }
}
