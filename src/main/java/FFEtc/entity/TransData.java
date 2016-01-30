package FFEtc.entity;

import java.util.Date;

public class TransData
{
    private Integer id;

    private String vehid;

    private Byte stationno;

    private Byte laneno;

    private Date transtime;

    private Integer transtimemill;

    private Short transstatus;

    private Integer positionx;

    private Integer positiony;

    private String obuissuerid;

    private String obuid;

    private String obustatus;

    private String obucontractver;

    private String obucontractno;

    private Date obusigndate;

    private Integer obusigndatemill;

    private Date obuexpiredate;

    private Integer obuexpiredatemill;

    private String vehicleplatecolor;

    private String vehicleclass;

    private String vehicleusertype;

    private String iccardtype;

    private String iccardversion;

    private String iccardnetno;

    private String iccardissuerid;

    private String iccardcontractno;

    private Date iccardsigndate;

    private Integer iccardsigndatemill;

    private Date iccardexpiredate;

    private Integer iccardexpiredatemill;

    private Integer iccardbalance;

    private Integer fee;

    private String transtype;

    private String terminalno;

    private String ictransno;

    private String rsutransno;

    private Date iccardtranstime;

    private Integer iccardtranstimemill;

    private String tac;

    private String exstationno;

    private Date extranstime;

    private Integer extranstimemill;

    private String tarrifversion;

    private Date picturetime;

    private Integer picturetimemill;

    private Date detecttime;

    private Integer detecttimemill;

    private Date capturetime;

    private Integer capturetimemill;

    private Integer platecolor;

    private Integer picplateindex;

    private Integer picconfidence;

    private Integer obuplateindex;

    private Integer icplateindex;

    private String picplate;

    private String obuplate;

    private String icplate;

    private Integer pic1Length;

    private Integer pic2Length;

    private Integer pic3Length;

    private Integer laserid;

    private Integer laserpositionx;

    private Integer laserpositiony;

    private String picpath1;

    private String picpath2;

    private String picpath3;

    private String picpath4;

    private String picpath5;

    private Integer vehspeed;

    private String realpicplate;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehid() {
        return vehid;
    }

    public void setVehid(String vehid) {
        this.vehid = vehid == null ? null : vehid.trim();
    }

    public Byte getStationno() {
        return stationno;
    }

    public void setStationno(Byte stationno) {
        this.stationno = stationno;
    }

    public Byte getLaneno() {
        return laneno;
    }

    public void setLaneno(Byte laneno) {
        this.laneno = laneno;
    }

    public Date getTranstime() {
        return transtime;
    }

    public void setTranstime(Date transtime) {
        this.transtime = transtime;
    }

    public Integer getTranstimemill() {
        return transtimemill;
    }

    public void setTranstimemill(Integer transtimemill) {
        this.transtimemill = transtimemill;
    }

    public Short getTransstatus() {
        return transstatus;
    }

    public void setTransstatus(Short transstatus) {
        this.transstatus = transstatus;
    }

    public Integer getPositionx() {
        return positionx;
    }

    public void setPositionx(Integer positionx) {
        this.positionx = positionx;
    }

    public Integer getPositiony() {
        return positiony;
    }

    public void setPositiony(Integer positiony) {
        this.positiony = positiony;
    }

    public String getObuissuerid() {
        return obuissuerid;
    }

    public void setObuissuerid(String obuissuerid) {
        this.obuissuerid = obuissuerid == null ? null : obuissuerid.trim();
    }

    public String getObuid() {
        return obuid;
    }

    public void setObuid(String obuid) {
        this.obuid = obuid == null ? null : obuid.trim();
    }

    public String getObustatus() {
        return obustatus;
    }

    public void setObustatus(String obustatus) {
        this.obustatus = obustatus == null ? null : obustatus.trim();
    }

    public String getObucontractver() {
        return obucontractver;
    }

    public void setObucontractver(String obucontractver) {
        this.obucontractver = obucontractver == null ? null : obucontractver.trim();
    }

    public String getObucontractno() {
        return obucontractno;
    }

    public void setObucontractno(String obucontractno) {
        this.obucontractno = obucontractno == null ? null : obucontractno.trim();
    }

    public Date getObusigndate() {
        return obusigndate;
    }

    public void setObusigndate(Date obusigndate) {
        this.obusigndate = obusigndate;
    }

    public Integer getObusigndatemill() {
        return obusigndatemill;
    }

    public void setObusigndatemill(Integer obusigndatemill) {
        this.obusigndatemill = obusigndatemill;
    }

    public Date getObuexpiredate() {
        return obuexpiredate;
    }

    public void setObuexpiredate(Date obuexpiredate) {
        this.obuexpiredate = obuexpiredate;
    }

    public Integer getObuexpiredatemill() {
        return obuexpiredatemill;
    }

    public void setObuexpiredatemill(Integer obuexpiredatemill) {
        this.obuexpiredatemill = obuexpiredatemill;
    }

    public String getVehicleplatecolor() {
        return vehicleplatecolor;
    }

    public void setVehicleplatecolor(String vehicleplatecolor) {
        this.vehicleplatecolor = vehicleplatecolor == null ? null : vehicleplatecolor.trim();
    }

    public String getVehicleclass() {
        return vehicleclass;
    }

    public void setVehicleclass(String vehicleclass) {
        this.vehicleclass = vehicleclass == null ? null : vehicleclass.trim();
    }

    public String getVehicleusertype() {
        return vehicleusertype;
    }

    public void setVehicleusertype(String vehicleusertype) {
        this.vehicleusertype = vehicleusertype == null ? null : vehicleusertype.trim();
    }

    public String getIccardtype() {
        return iccardtype;
    }

    public void setIccardtype(String iccardtype) {
        this.iccardtype = iccardtype == null ? null : iccardtype.trim();
    }

    public String getIccardversion() {
        return iccardversion;
    }

    public void setIccardversion(String iccardversion) {
        this.iccardversion = iccardversion == null ? null : iccardversion.trim();
    }

    public String getIccardnetno() {
        return iccardnetno;
    }

    public void setIccardnetno(String iccardnetno) {
        this.iccardnetno = iccardnetno == null ? null : iccardnetno.trim();
    }

    public String getIccardissuerid() {
        return iccardissuerid;
    }

    public void setIccardissuerid(String iccardissuerid) {
        this.iccardissuerid = iccardissuerid == null ? null : iccardissuerid.trim();
    }

    public String getIccardcontractno() {
        return iccardcontractno;
    }

    public void setIccardcontractno(String iccardcontractno) {
        this.iccardcontractno = iccardcontractno == null ? null : iccardcontractno.trim();
    }

    public Date getIccardsigndate() {
        return iccardsigndate;
    }

    public void setIccardsigndate(Date iccardsigndate) {
        this.iccardsigndate = iccardsigndate;
    }

    public Integer getIccardsigndatemill() {
        return iccardsigndatemill;
    }

    public void setIccardsigndatemill(Integer iccardsigndatemill) {
        this.iccardsigndatemill = iccardsigndatemill;
    }

    public Date getIccardexpiredate() {
        return iccardexpiredate;
    }

    public void setIccardexpiredate(Date iccardexpiredate) {
        this.iccardexpiredate = iccardexpiredate;
    }

    public Integer getIccardexpiredatemill() {
        return iccardexpiredatemill;
    }

    public void setIccardexpiredatemill(Integer iccardexpiredatemill) {
        this.iccardexpiredatemill = iccardexpiredatemill;
    }

    public Integer getIccardbalance() {
        return iccardbalance;
    }

    public void setIccardbalance(Integer iccardbalance) {
        this.iccardbalance = iccardbalance;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype == null ? null : transtype.trim();
    }

    public String getTerminalno() {
        return terminalno;
    }

    public void setTerminalno(String terminalno) {
        this.terminalno = terminalno == null ? null : terminalno.trim();
    }

    public String getIctransno() {
        return ictransno;
    }

    public void setIctransno(String ictransno) {
        this.ictransno = ictransno == null ? null : ictransno.trim();
    }

    public String getRsutransno() {
        return rsutransno;
    }

    public void setRsutransno(String rsutransno) {
        this.rsutransno = rsutransno == null ? null : rsutransno.trim();
    }

    public Date getIccardtranstime() {
        return iccardtranstime;
    }

    public void setIccardtranstime(Date iccardtranstime) {
        this.iccardtranstime = iccardtranstime;
    }

    public Integer getIccardtranstimemill() {
        return iccardtranstimemill;
    }

    public void setIccardtranstimemill(Integer iccardtranstimemill) {
        this.iccardtranstimemill = iccardtranstimemill;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac == null ? null : tac.trim();
    }

    public String getExstationno() {
        return exstationno;
    }

    public void setExstationno(String exstationno) {
        this.exstationno = exstationno == null ? null : exstationno.trim();
    }

    public Date getExtranstime() {
        return extranstime;
    }

    public void setExtranstime(Date extranstime) {
        this.extranstime = extranstime;
    }

    public Integer getExtranstimemill() {
        return extranstimemill;
    }

    public void setExtranstimemill(Integer extranstimemill) {
        this.extranstimemill = extranstimemill;
    }

    public String getTarrifversion() {
        return tarrifversion;
    }

    public void setTarrifversion(String tarrifversion) {
        this.tarrifversion = tarrifversion == null ? null : tarrifversion.trim();
    }

    public Date getPicturetime() {
        return picturetime;
    }

    public void setPicturetime(Date picturetime) {
        this.picturetime = picturetime;
    }

    public Integer getPicturetimemill() {
        return picturetimemill;
    }

    public void setPicturetimemill(Integer picturetimemill) {
        this.picturetimemill = picturetimemill;
    }

    public Date getDetecttime() {
        return detecttime;
    }

    public void setDetecttime(Date detecttime) {
        this.detecttime = detecttime;
    }

    public Integer getDetecttimemill() {
        return detecttimemill;
    }

    public void setDetecttimemill(Integer detecttimemill) {
        this.detecttimemill = detecttimemill;
    }

    public Date getCapturetime() {
        return capturetime;
    }

    public void setCapturetime(Date capturetime) {
        this.capturetime = capturetime;
    }

    public Integer getCapturetimemill() {
        return capturetimemill;
    }

    public void setCapturetimemill(Integer capturetimemill) {
        this.capturetimemill = capturetimemill;
    }

    public Integer getPlatecolor() {
        return platecolor;
    }

    public void setPlatecolor(Integer platecolor) {
        this.platecolor = platecolor;
    }

    public Integer getPicplateindex() {
        return picplateindex;
    }

    public void setPicplateindex(Integer picplateindex) {
        this.picplateindex = picplateindex;
    }

    public Integer getPicconfidence() {
        return picconfidence;
    }

    public void setPicconfidence(Integer picconfidence) {
        this.picconfidence = picconfidence;
    }

    public Integer getObuplateindex() {
        return obuplateindex;
    }

    public void setObuplateindex(Integer obuplateindex) {
        this.obuplateindex = obuplateindex;
    }

    public Integer getIcplateindex() {
        return icplateindex;
    }

    public void setIcplateindex(Integer icplateindex) {
        this.icplateindex = icplateindex;
    }

    public String getPicplate() {
        return picplate;
    }

    public void setPicplate(String picplate) {
        this.picplate = picplate == null ? null : picplate.trim();
    }

    public String getObuplate() {
        return obuplate;
    }

    public void setObuplate(String obuplate) {
        this.obuplate = obuplate == null ? null : obuplate.trim();
    }

    public String getIcplate() {
        return icplate;
    }

    public void setIcplate(String icplate) {
        this.icplate = icplate == null ? null : icplate.trim();
    }

    public Integer getPic1Length() {
        return pic1Length;
    }

    public void setPic1Length(Integer pic1Length) {
        this.pic1Length = pic1Length;
    }

    public Integer getPic2Length() {
        return pic2Length;
    }

    public void setPic2Length(Integer pic2Length) {
        this.pic2Length = pic2Length;
    }

    public Integer getPic3Length() {
        return pic3Length;
    }

    public void setPic3Length(Integer pic3Length) {
        this.pic3Length = pic3Length;
    }

    public Integer getLaserid() {
        return laserid;
    }

    public void setLaserid(Integer laserid) {
        this.laserid = laserid;
    }

    public Integer getLaserpositionx() {
        return laserpositionx;
    }

    public void setLaserpositionx(Integer laserpositionx) {
        this.laserpositionx = laserpositionx;
    }

    public Integer getLaserpositiony() {
        return laserpositiony;
    }

    public void setLaserpositiony(Integer laserpositiony) {
        this.laserpositiony = laserpositiony;
    }

    public String getPicpath1() {
        return picpath1;
    }

    public void setPicpath1(String picpath1) {
        this.picpath1 = picpath1 == null ? null : picpath1.trim();
    }

    public String getPicpath2() {
        return picpath2;
    }

    public void setPicpath2(String picpath2) {
        this.picpath2 = picpath2 == null ? null : picpath2.trim();
    }

    public String getPicpath3() {
        return picpath3;
    }

    public void setPicpath3(String picpath3) {
        this.picpath3 = picpath3 == null ? null : picpath3.trim();
    }

    public String getPicpath4() {
        return picpath4;
    }

    public void setPicpath4(String picpath4) {
        this.picpath4 = picpath4 == null ? null : picpath4.trim();
    }

    public String getPicpath5() {
        return picpath5;
    }

    public void setPicpath5(String picpath5) {
        this.picpath5 = picpath5 == null ? null : picpath5.trim();
    }

    public Integer getVehspeed() {
        return vehspeed;
    }

    public void setVehspeed(Integer vehspeed) {
        this.vehspeed = vehspeed;
    }

    public String getRealpicplate() {
        return realpicplate;
    }

    public void setRealpicplate(String realpicplate) {
        this.realpicplate = realpicplate == null ? null : realpicplate.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}