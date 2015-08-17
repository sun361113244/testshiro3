package sys.entity;

public class zTreeNode
{
    private Integer id;

    private Integer pid;

    private String name;

    private Boolean open;

    private Boolean checked;

    public zTreeNode(Integer node_id , Integer node_pid, String node_name, Boolean node_open, Boolean node_checked)
    {
        this.id = node_id;
        this.pid = node_pid;
        this.name = node_name;
        this.open = node_open;
        this.checked = node_checked;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getPid()
    {
        return pid;
    }

    public void setPid(Integer pid)
    {
        this.pid = pid;
    }

    public Boolean getOpen()
    {
        return open;
    }

    public void setOpen(Boolean open)
    {
        this.open = open;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getChecked()
    {
        return checked;
    }

    public void setChecked(Boolean checked)
    {
        this.checked = checked;
    }
}
