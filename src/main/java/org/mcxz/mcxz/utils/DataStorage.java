package org.mcxz.mcxz.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import com.google.common.collect.Maps;

public class DataStorage
{

    private final File file;
    private final Map<String, DataStorageAttachment> objects;
    private NBTTagCompound data;

    public DataStorage(File file)
    {
        this.file = file;
        this.objects = Maps.newHashMap();

        load();
    }

    public File getFile()
    {
        return file;
    }

    public void load()
    {
        try
        {
            data = CompressedStreamTools.readCompressed(new FileInputStream(getFile()));
        }
        catch (IOException e)
        {
            data = new NBTTagCompound();
        }
        for (String name : objects.keySet())
            objects.get(name).loadData(data.getCompoundTag(name));
    }

    public void registerAttachmentObject(String name, DataStorageAttachment obj)
    {
        if (objects.get(name) == obj)
            return;
        if (objects.get(name) != null)
            objects.get(name).saveData(data.getCompoundTag(name));
        obj.loadData(data.getCompoundTag(name));
        objects.put(name, obj);
    }

    public void save()
    {
        for (String name : objects.keySet())
        {
            NBTTagCompound item = data.getCompoundTag(name);
            objects.get(name).saveData(item);
            data.setTag(name, item);
        }
        try
        {
            CompressedStreamTools.writeCompressed(data, new FileOutputStream(getFile()));
        }
        catch (IOException e)
        {
        }
    }

}
