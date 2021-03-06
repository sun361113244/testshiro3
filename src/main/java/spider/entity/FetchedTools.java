package spider.entity;

import java.util.Date;

public class FetchedTools
{
    private Integer id;

    private String toolArea;

    private String toolName;

    private String toolType;

    private Integer isfree;

    private Integer isNew;

    private Integer isExist;

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

    public Integer getIsfree()
    {
        return isfree;
    }

    public void setIsfree(Integer isfree)
    {
        this.isfree = isfree;
    }

    public Integer getIsNew()
    {
        return isNew;
    }

    public void setIsNew(Integer isNew)
    {
        this.isNew = isNew;
    }

    public Integer getIsExist()
    {
        return isExist;
    }

    public void setIsExist(Integer isExist)
    {
        this.isExist = isExist;
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
