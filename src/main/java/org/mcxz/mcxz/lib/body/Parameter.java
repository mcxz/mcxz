package org.mcxz.mcxz.lib.body;

public class Parameter
{

    private String unlocalizedName;
    private int maxValue;
    private int defaultValue;

    public int getDefaultValue()
    {
        return this.defaultValue;
    }

    public int getMaxValue()
    {
        return this.maxValue;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public Parameter setDefaultValue(int defaultValue)
    {
        this.defaultValue = defaultValue;
        return this;
    }

    public Parameter setMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
        return this;
    }

    public Parameter setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

}
