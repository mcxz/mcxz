package org.mcxz.mcxz.status;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public abstract class AbstractPlayerStatus extends PlayerStatus implements IExtendedEntityProperties
{

    @Override
    public void init(Entity arg0, World arg1)
    {
    }

    @Override
    public void loadNBTData(NBTTagCompound arg0)
    {
    }

    @Override
    public void saveNBTData(NBTTagCompound arg0)
    {
    }

}
