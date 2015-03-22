package org.mcxz.mcxz.lib.body;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.mcxz.mcxz.lib.state.State;
import com.google.common.collect.Maps;

public class Skill
{

    private String unlocalizedName;
    private int maxLevel;
    private int defaultLevel;
    private Map<String, State> states = Maps.newHashMap();

    public Skill addState(State state)
    {
        this.states.put(state.getUnlocalizedName(), state);
        return this;
    }

    public int getDefaultLevel()
    {
        return this.defaultLevel;
    }

    public int getMaxLevel()
    {
        return this.maxLevel;
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
