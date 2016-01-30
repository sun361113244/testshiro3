package FFEtc.LocSrv;


import FFEtc.entity.TransData;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface UpLoadService
{
    public int SendTransData(@WebParam(name = "TransData")TransData transData);
}
