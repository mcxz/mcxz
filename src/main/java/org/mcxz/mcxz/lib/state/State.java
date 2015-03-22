package org.mcxz.mcxz.lib.state;

import java.util.Set;

public class State
{

    private String unlocalizedName;
    private Set<String> applicables;

    public State addApplicableState(State state)
    {
        this.applicables.add(state.getUnlocalizedName());
        return this;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public boolean isApplicableState(State state)
    {
        return this.applicables.contains(state.getUnlocalizedName());
    }

    public State setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

}
