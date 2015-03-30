package org.mcxz.mcxz.lib.body;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.mcxz.mcxz.lib.state.State;
import com.google.common.collect.Maps;

public class HumanBody extends Body
{

    private Map<String, Meridian> meridians = Maps.newHashMap();
    private Map<String, Parameter> parameters = Maps.newHashMap();
    private Map<String, Skill> skills = Maps.newHashMap();
    private Map<String, State> states = Maps.newHashMap();

    public HumanBody addMeridian(Meridian meridian)
    {
        this.meridians.put(meridian.getUnlocalizedName(), meridian);
        return this;
    }

    public HumanBody addParameter(Parameter parameter)
    {
        this.parameters.put(parameter.getUnlocalizedName(), parameter);
        return this;
    }

    public HumanBody addSkill(Skill skill)
    {
        this.skills.put(skill.getUnlocalizedName(), skill);
        return this;
    }

    public HumanBody addState(State state)
    {
        this.states.put(state.getUnlocalizedName(), state);
        return this;
    }

    public Meridian getMeridian(Meridian meridian)
    {
        return this.meridians.get(meridian.getUnlocalizedName());
    }

    public Meridian getMeridian(String name)
    {
        return this.meridians.get(name);
    }

    public Collection<Meridian> getMeridians()
    {
        return Collections.unmodifiableCollection(this.meridians.values());
    }

    public Parameter getParameter(Parameter parameter)
    {
        return this.parameters.get(parameter.getUnlocalizedName());
    }

    public Parameter getParameter(String name)
    {
        return this.parameters.get(name);
    }

    public Collection<Parameter> getParameters()
    {
        return Collections.unmodifiableCollection(this.parameters.values());
    }

    public Skill getSkill(Skill skill)
    {
        return this.skills.get(skill.getUnlocalizedName());
    }

    public Skill getSkill(String name)
    {
        return this.skills.get(name);
    }

    public Collection<Skill> getSkills()
    {
        return Collections.unmodifiableCollection(this.skills.values());
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

}
