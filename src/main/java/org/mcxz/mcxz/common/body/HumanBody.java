package org.mcxz.mcxz.common.body;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import com.google.common.collect.Maps;

public class HumanBody
{

    private Map<String, Meridian> meridians = Maps.newHashMap();
    private Map<String, Skill> skills = Maps.newHashMap();

    public HumanBody addMeridian(Meridian meridian)
    {
        this.meridians.put(meridian.getUnlocalizedName(), meridian);
        return this;
    }

    public HumanBody addSkill(Skill skill)
    {
        this.skills.put(skill.getUnlocalizedName(), skill);
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

}
