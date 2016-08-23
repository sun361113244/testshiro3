package sys.entity;

public class ETreeNode
{
    private String id;
    private String text;
    private String state;
    private String iconCls;
    private ETreeNode[] children;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public ETreeNode[] getChildren()
    {
        return children;
    }

    public void setChildren(ETreeNode[] children)
    {
        this.children = children;
    }
}
