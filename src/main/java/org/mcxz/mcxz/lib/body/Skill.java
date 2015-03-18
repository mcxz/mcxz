package org.mcxz.mcxz.lib.body;

public class Skill
{

    private String unlocalizedName;
    private int maxLevel;
    private int defaultLevel;

    public int getDefaultLevel()
    {
        return this.defaultLevel;
    }

    public int getMaxLevel()
    {
        return this.maxLevel;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public Skill setDefaultLevel(int defaultLevel)
    {
        this.defaultLevel = defaultLevel;
        return this;
    }

    public Skill setMaxLevel(int maxLevel)
    {
        this.maxLevel = maxLevel;
        return this;
    }

    public Skill setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

}
