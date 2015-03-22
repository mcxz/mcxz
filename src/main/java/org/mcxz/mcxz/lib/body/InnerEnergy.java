package org.mcxz.mcxz.lib.body;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.mcxz.mcxz.lib.state.State;
import com.google.common.collect.Maps;

public class InnerEnergy
{

    private String unlocalizedName;
    private int density;
    private Map<String, State> states = Maps.newHashMap();

    public InnerEnergy addState(State state)
    {
        this.states.put(state.getUnlocalizedName(), state);
        return this;
    }

    public int getDensity()
    {
        return this.density;
    }

    public State getState(State state)
    {
        return this.states.get(state.getUnlocalizedName());
    }

    public State getState(String name)
    {
        return this.states.get(name);
    }

    public Collection<State> getStates()
    {
        return Collections.unmodifiableCollection(this.states.values());
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
