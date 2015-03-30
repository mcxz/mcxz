package org.mcxz.mcxz.lib.modifier;

public class Modifier<T>
{

    private String unlocalizedName;
    private T value;
    private boolean dirty;

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj instanceof Modifier)
        {
            Modifier mod = (Modifier) obj;
            if (this.getUnlocalizedName() != null && this.getUnlocalizedName().equals(mod.getUnlocalizedName()))
                return (this.getValue() == mod.getValue()) || (this.getValue() != null && this.getValue().equals(mod.getValue()));
        }
        return false;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public T getValue()
    {
        return this.value;
    }

    public boolean setClean()
    {
        return this.setClean(true);
    }

    public boolean setClean(boolean doSet)
    {
        if (this.dirty)
        {
            if (doSet)
                this.dirty = false;
            return true;
        }
        return false;
    }

    public void setDirty()
    {
        this.dirty = true;
    }

    public Modifier<T> setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    @SuppressWarnings("unchecked")
    public boolean setValue(Object value)
    {
        try
        {
            this.value = (T) value;
            this.setDirty();
            return true;
        }
        catch (ClassCastException e)
        {
            return false;
        }
    }

}
