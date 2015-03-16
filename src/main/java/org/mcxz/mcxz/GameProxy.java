package org.mcxz.mcxz;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.mcxz.mcxz.utils.DataStorage;
import org.mcxz.mcxz.utils.MinecraftUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GameProxy
{

    private final LoadingCache<File, DataStorage> stores = CacheBuilder.newBuilder().expireAfterAccess(30L, TimeUnit.MINUTES).removalListener(new RemovalListener<File, DataStorage>()
    {

        @Override
        public void onRemoval(RemovalNotification<File, DataStorage> notification)
        {
            DataStorage store = notification.getValue();
            if (store != null)
                store.save();
        }

    }).build(new CacheLoader<File, DataStorage>()
    {

        @Override
        public DataStorage load(File file) throws Exception
        {
            if (file == null)
                throw new NullPointerException("The file to be used as DataStorage must not be null.");
            if (file.exists() && (file.isDirectory() || !file.canRead() || !file.canWrite()))
                throw new IllegalArgumentException("The file('" + file.getAbsolutePath() + "') cannot be used as DataStorage. It must be readable and writable. It must be a file, not a directory.");
            return new DataStorage(file);
        }

    });

    public DataStorage getPlayerDataStorage(UUID uuid)
    {
        return getPlayerDataStorage(uuid, true);
    }

    public DataStorage getPlayerDataStorage(UUID uuid, boolean doLoad)
    {
        File file = MinecraftUtils.getSaveDirFile("playerdata", uuid.toString() + ".mcxzds");
        if (!doLoad)
            return stores.getIfPresent(file);
        return stores.getUnchecked(file);
    }

    public DataStorage getWorldDataStorage()
    {
        return getWorldDataStorage(true);
    }

    public DataStorage getWorldDataStorage(boolean doLoad)
    {
        File file = MinecraftUtils.getSaveDirFile("WorldDataStorage.mcxzds");
        if (!doLoad)
            return stores.getIfPresent(file);
        return stores.getUnchecked(file);
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }

    public void preInit(FMLPreInitializationEvent event)
    {
    }

    public void serverStarting(FMLServerStartingEvent event)
    {
    }

    public void serverStopping(FMLServerStoppingEvent event)
    {
    }

}
