package org.mcxz.mcxz.client;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.mcxz.mcxz.GameProxy;
import org.mcxz.mcxz.status.PlayerStatus;
import org.mcxz.mcxz.status.PlayerStatusClient;

public class GameProxyClient extends GameProxy
{

    @Override
    public PlayerStatus getPlayerStatus(EntityPlayer player)
    {
        if (player instanceof AbstractClientPlayer)
        {
            IExtendedEntityProperties obj = player.getExtendedProperties(PlayerStatus.ID);
            if (obj == null)
                player.registerExtendedProperties(PlayerStatus.ID, obj = new PlayerStatusClient());
            if (obj instanceof PlayerStatus)
                return (PlayerStatus) obj;
            return null;
        }
        return super.getPlayerStatus(player);
    }

}
