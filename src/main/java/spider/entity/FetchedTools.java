package spider.entity;

import java.util.Date;

public class FetchedTools
{
    private Integer id;

    private String toolArea;

    private String toolName;

    private String toolType;

    private Boolean isfree;

    private Boolean isNew;

    private Boolean isExist;

    private Date createTime;

    private Date updateTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getToolArea()
    {
        return toolArea;
    }

    public void setToolArea(String toolArea)
    {
        this.toolArea = toolArea;
    }

    public String getToolType()
    {
        return toolType;
    }

    public void setToolType(String toolType)
    {
        this.toolType = toolType;
    }

    public String getToolName()
    {
        return toolName;
    }

    public void setToolName(String toolName)
    {
        this.toolName = toolName;
    }

    public Boolean getIsfree()
    {
        return isfree;
    }

    public void setIsfree(Boolean isfree)
    {
        this.isfree = isfree;
    }

    public Boolean getNew()
    {
        return isNew;
    }

    public void setNew(Boolean aNew)
    {
        isNew = aNew;
    }

    public Boolean getExist()
    {
        return isExist;
    }

    public void setExist(Boolean exist)
    {
        isExist = exist;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(toolArea + ",");
        sb.append(toolName + ",");
        sb.append(toolType + ",");
        sb.append(isfree + ",");
        sb.append(isNew + ",");
        sb.append(isExist);
//        sb.append(createTime + ",");
//        sb.append(updateTime + ",");

        return sb.toString();
    }
}
