package org.mcxz.mcxz.utils;

import net.minecraft.nbt.NBTTagCompound;

public interface DataStorageAttachment
{

    void loadData(NBTTagCompound data);

    void saveData(NBTTagCompound data);

}
