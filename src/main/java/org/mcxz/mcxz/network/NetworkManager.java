package org.mcxz.mcxz.network;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class NetworkManager
{

    public static void registerPacket(Class<? extends NetworkPacket> packetClass)
    {
        REGISTRY.forcePut(packetClass.getName().hashCode(), packetClass);
    }

    protected static final BiMap<Integer, Class<? extends NetworkPacket>> REGISTRY = HashBiMap.create();
    protected static final String CHANNELNAME = "mcxz";
    protected static FMLEventChannel channel;

    public NetworkManager()
    {
        if (channel == null)
            channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(CHANNELNAME);
        channel.register(this);
    }

    @SubscribeEvent
    public void handleEvent(FMLNetworkEvent.ServerCustomPacketEvent event) throws InstantiationException, IllegalAccessException
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
                packet.handlePacketServer(((NetHandlerPlayServer) event.handler).playerEntity);
            }
        }
    }

    public void sendTo(NetworkPacket packet, EntityPlayerMP player)
    {
        if (packet != null && player != null)
        {
            int id = REGISTRY.inverse().get(packet.getClass());
            if (id != 0)
            {
                PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
                buf.writeInt(id);
                packet.writeToBuffer(buf);
                channel.sendTo(new FMLProxyPacket(buf, CHANNELNAME), player);
            }
        }
    }

    public void sendToAll(NetworkPacket packet)
    {
        if (packet != null)
        {
            int id = REGISTRY.inverse().get(packet.getClass());
            if (id != 0)
            {
                PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
                buf.writeInt(id);
                packet.writeToBuffer(buf);
                channel.sendToAll(new FMLProxyPacket(buf, CHANNELNAME));
            }
        }
    }

    public void sendToAllAround(NetworkPacket packet, TargetPoint point)
    {
        if (packet != null && point != null)
        {
            int id = REGISTRY.inverse().get(packet.getClass());
            if (id != 0)
            {
                PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
                buf.writeInt(id);
                packet.writeToBuffer(buf);
                channel.sendToAllAround(new FMLProxyPacket(buf, CHANNELNAME), point);
            }
        }
    }

    public void sendToDimension(NetworkPacket packet, int dimensionId)
    {
        if (packet != null)
        {
            int id = REGISTRY.inverse().get(packet.getClass());
            if (id != 0)
            {
                PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
                buf.writeInt(id);
                packet.writeToBuffer(buf);
                channel.sendToDimension(new FMLProxyPacket(buf, CHANNELNAME), dimensionId);
            }
        }
    }

    public void sendToServer(NetworkPacket packet)
    {
    }

}
