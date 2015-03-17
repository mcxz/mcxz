package org.mcxz.mcxz.common.body;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import com.google.common.collect.Maps;

public class Meridian
{

    private String unlocalizedName;
    private Map<String, AcuPoint> acupoints = Maps.newHashMap();

    public Meridian addAcuPoint(AcuPoint acupoint)
    {
        this.acupoints.put(acupoint.getUnlocalizedName(), acupoint);
        return this;
    }

    public AcuPoint getAcuPoint(String name)
    {
        return acupoints.get(name);
    }

    public Collection<AcuPoint> getAcuPoints()
    {
        return Collections.unmodifiableCollection(this.acupoints.values());
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public Meridian setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

}