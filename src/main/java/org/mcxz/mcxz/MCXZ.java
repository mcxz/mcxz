package org.mcxz.mcxz;

import lain.mods.helper.network.NetworkManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.mcxz.mcxz.init.InitProxy;

@Mod(modid = "mcxz", useMetadata = true)
public class MCXZ
{

    @SidedProxy(serverSide = "org.mcxz.mcxz.network.NetworkManager", clientSide = "org.mcxz.mcxz.network.NetworkManagerClient")
    public static NetworkManager network;

    @SidedProxy(serverSide = "org.mcxz.mcxz.init.InitProxy", clientSide = "org.mcxz.mcxz.init.client.InitProxyClient")
    public static InitProxy initproxy;

    @SidedProxy(serverSide = "org.mcxz.mcxz.GameProxy", clientSide = "org.mcxz.mcxz.client.GameProxyClient")
    public static GameProxy proxy;

    @Mod.Instance("mcxz")
    public static MCXZ instance;

    @Mod.EventHandler
    public void handleEvent(FMLInitializationEvent event)
    {
        initproxy.init(event);
        proxy.init(event);
    }

    @Mod.EventHandler
    public void handleEvent(FMLPostInitializationEvent event)
    {
        initproxy.postInit(event);
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void handleEvent(FMLPreInitializationEvent event)
    {
        initproxy.preInit(event);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void handleEvent(FMLServerStartingEvent event)
    {
        initproxy.serverStarting(event);
        proxy.serverStarting(event);
    }

    @Mod.EventHandler
    public void handleEvent(FMLServerStoppingEvent event)
    {
        initproxy.serverStopping(event);
        proxy.serverStopping(event);
    }

}
