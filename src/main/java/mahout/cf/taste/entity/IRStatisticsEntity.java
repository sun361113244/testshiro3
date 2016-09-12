package mahout.cf.taste.entity;

/**
 * Created by charles on 16/9/9.
 */
public class IRStatisticsEntity
{
    private double precision;

    private double recall;

    private double fallOut;

    private double reach;

    public double getPrecision()
    {
        return precision;
    }

    public void setPrecision(double precision)
    {
        this.precision = precision;
    }

    public double getRecall()
    {
        return recall;
    }

    public void setRecall(double recall)
    {
        this.recall = recall;
    }

    public double getFallOut()
    {
        return fallOut;
    }

    public void setFallOut(double fallOut)
    {
        this.fallOut = fallOut;
    }

    public double getReach()
    {
        return reach;
    }

    public void setReach(double reach)
    {
        this.reach = reach;
    }
}
