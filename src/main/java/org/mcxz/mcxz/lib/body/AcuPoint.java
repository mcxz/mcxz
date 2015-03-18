package org.mcxz.mcxz.lib.body;

public class AcuPoint
{

    private String unlocalizedName;
    private int maxCapacity;
    private int initialCapacity;

    public int getInitialCapacity()
    {
        return this.initialCapacity;
    }

    public int getMaxCapacity()
    {
        return this.maxCapacity;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public AcuPoint setInitialCapacity(int initialCapacity)
    {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public AcuPoint setMaxCapacity(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
        return this;
    }

    public AcuPoint setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

}
