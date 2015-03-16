package org.mcxz.mcxz.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class NetworkManagerClient extends NetworkManager
{

    @SubscribeEvent
    public void handleEvent(FMLNetworkEvent.ClientCustomPacketEvent event) throws InstantiationException, IllegalAccessException
    {
        PacketBuffer buf = new PacketBuffer(event.packet.payload());
        int id = buf.readInt();
        Class<? extends NetworkPacket> clazz = REGISTRY.get(id);
        if (clazz != null)
        {
            NetworkPacket packet = clazz.newInstance();
            if (packet != null)
            {
                packet.readFromBuffer(buf);
                packet.handlePacketClient();
            }
        }
    }

    @Override
    public void sendToServer(NetworkPacket packet)
    {
        if (packet != null)
        {
            int id = REGISTRY.inverse().get(packet.getClass());
            if (id != 0)
            {
                PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
                buf.writeInt(id);
                packet.writeToBuffer(buf);
                channel.sendToServer(new FMLProxyPacket(buf, CHANNELNAME));
            }
        }
    }

}
