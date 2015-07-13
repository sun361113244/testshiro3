package sys.entity;

import java.util.Date;

public class RbacDep {
    private Integer id;

    private String code;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Byte status;

    public RbacDep()
    {

    }

    public RbacDep(String code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public RbacDep(Integer id, String code, String name)
    {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public RbacDep(String code, String name, Date create_time, Date update_time)
    {
        this.code = code;
        this.name = name;
        this.createTime = create_time;
        this.updateTime = update_time;
    }

    public RbacDep(Integer id, String code, String name, Date create_time, Date update_time)
    {
        this.id = id;
        this.code = code;
        this.name = name;
        this.createTime = create_time;
        this.updateTime = update_time;
    }

    public RbacDep(Integer id, String code, String name, Date create_time, Date update_time, Byte status)
    {
        this.id = id;
        this.code = code;
        this.name = name;
        this.createTime = create_time;
        this.updateTime = update_time;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}