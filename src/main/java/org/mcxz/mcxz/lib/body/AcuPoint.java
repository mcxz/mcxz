package org.mcxz.mcxz.lib.body;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.mcxz.mcxz.lib.state.State;
import com.google.common.collect.Maps;

public class AcuPoint
{

    private String unlocalizedName;
    private int maxCapacity;
    private int initialCapacity;
    private Map<String, State> states = Maps.newHashMap();

    public AcuPoint addState(State state)
    {
        this.states.put(state.getUnlocalizedName(), state);
        return this;
    }

    public int getInitialCapacity()
    {
        return this.initialCapacity;
    }

    public int getMaxCapacity()
    {
        return this.maxCapacity;
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
