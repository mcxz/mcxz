package org.mcxz.mcxz.lib.body;

public class InnerEnergy
{

    private String unlocalizedName;
    private int density;

    public int getDensity()
    {
        return this.density;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public InnerEnergy setDensity(int density)
    {
        this.density = density;
        return this;
    }

    public InnerEnergy setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

}
