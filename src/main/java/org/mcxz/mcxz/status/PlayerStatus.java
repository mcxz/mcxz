package org.mcxz.mcxz.status;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.mcxz.mcxz.lib.body.Body;
import org.mcxz.mcxz.lib.modifier.Modifier;
import com.google.common.collect.Maps;

public abstract class PlayerStatus
{

    public static final String ID = "1b6f8b84-fe25-47e9-9529-d3e4d4390394";

    private Body body;

    private Map<String, Modifier<Float>> bodyModifiers = Maps.newHashMap();
    private Map<String, Modifier<Float>> soulModifiers = Maps.newHashMap();
    private Map<String, Modifier<Float>> dimensionModifiers = Maps.newHashMap();

    public PlayerStatus addBodyModifier(Modifier<Float> modifier)
    {
        this.bodyModifiers.put(modifier.getUnlocalizedName(), modifier);
        return this;
    }

    public PlayerStatus addDimensionModifier(Modifier<Float> modifier)
    {
        this.dimensionModifiers.put(modifier.getUnlocalizedName(), modifier);
        return this;
    }

    public PlayerStatus addSoulModifier(Modifier<Float> modifier)
    {
        this.soulModifiers.put(modifier.getUnlocalizedName(), modifier);
        return this;
    }

    public void clearBodyModifiers()
    {
        this.bodyModifiers.clear();
    }

    public void clearDimensionModifiers()
    {
        this.dimensionModifiers.clear();
    }

    public void clearSoulModifiers()
    {
        this.soulModifiers.clear();
    }

    public Body getBody()
    {
        return this.body;
    }

    public Modifier<Float> getBodyModifier(String name)
    {
        return this.bodyModifiers.get(name);
    }

    public Collection<Modifier<Float>> getBodyModifiers()
    {
        return Collections.unmodifiableCollection(this.bodyModifiers.values());
    }

    public Modifier<Float> getDimensionModifier(String name)
    {
        return this.dimensionModifiers.get(name);
    }

    public Collection<Modifier<Float>> getDimensionModifiers()
    {
        return Collections.unmodifiableCollection(this.dimensionModifiers.values());
    }

    public Modifier<Float> getSoulModifier(String name)
    {
        return this.soulModifiers.get(name);
    }

    public Collection<Modifier<Float>> getSoulModifiers()
    {
        return Collections.unmodifiableCollection(this.soulModifiers.values());
    }

    public Modifier<Float> removeBodyModifier(Modifier<Float> modifier)
    {
        return this.bodyModifiers.remove(modifier.getUnlocalizedName());
    }

    public Modifier<Float> removeBodyModifier(String name)
    {
        return this.bodyModifiers.remove(name);
    }

    public Modifier<Float> removeDimensionModifier(Modifier<Float> modifier)
    {
        return this.dimensionModifiers.remove(modifier.getUnlocalizedName());
    }

    public Modifier<Float> removeDimensionModifier(String name)
    {
        return this.dimensionModifiers.remove(name);
    }

    public Modifier<Float> removeSoulModifier(Modifier<Float> modifier)
    {
        return this.soulModifiers.remove(modifier.getUnlocalizedName());
    }

    public Modifier<Float> removeSoulModifier(String name)
    {
        return this.soulModifiers.remove(name);
    }

    public PlayerStatus setBody(Body body)
    {
        this.body = body;
        return this;
    }

}
