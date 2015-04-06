package org.mcxz.mcxz.init.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.mcxz.mcxz.init.InitProxy;

public class InitProxyClient extends InitProxy
{

    public void init(FMLInitializationEvent event)
    {
        super.init(event);

        KeyBindings.registerKeyBindings();
    }

}
