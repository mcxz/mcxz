package org.mcxz.mcxz.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;

public abstract class NetworkPacket
{

    public abstract void handlePacketClient();

    public abstract void handlePacketServer(EntityPlayerMP player);

    public abstract void readFromBuffer(PacketBuffer buf);

    public abstract void writeToBuffer(PacketBuffer buf);

}
