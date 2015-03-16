package org.mcxz.mcxz.utils;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Teleporter
{

    private static void move(Entity ent, double px, double py, double pz, float yaw, float pitch, double mx, double my, double mz)
    {
        if (ent instanceof EntityPlayerMP)
        {
            EntityPlayerMP mp = (EntityPlayerMP) ent;
            mp.playerNetServerHandler.setPlayerLocation(px, py, pz, yaw, pitch);
            if (mx != 0D || my != 0D || mz != 0D)
            {
                mp.motionX = mx;
                mp.motionY = my;
                mp.motionZ = mz;
                mp.playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(mp));
            }
            mp.fallDistance = 0F;
        }
        else
        {
            ent.setPositionAndRotation(px, py, pz, yaw, pitch);
            ent.motionX = mx;
            ent.motionY = my;
            ent.motionZ = mz;
            ent.fallDistance = 0F;
        }
    }

    private static void syncPlayerExperience(EntityPlayerMP mp)
    {
        mp.addExperienceLevel(0); // mark as dirty
    }

    @SuppressWarnings("rawtypes")
    private static void syncPlayerPotionEffect(EntityPlayerMP mp)
    {
        Iterator ite = mp.getActivePotionEffects().iterator();
        while (ite.hasNext())
        {
            PotionEffect pe = (PotionEffect) ite.next();
            mp.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(mp.getEntityId(), pe));
        }
    }

    public static Entity teleport(Entity ent, int dimension, double px, double py, double pz, float yaw, float pitch, double mx, double my, double mz)
    {
        if (ent.isDead || ent.worldObj.isRemote)
            return null;

        Entity ridingEntity = ent.ridingEntity;
        if (ridingEntity != null)
        {
            ent.mountEntity(null);
            ridingEntity = teleport(ridingEntity, dimension, px, py, pz, yaw, pitch, mx, my, mz);
        }
        Entity riddenByEntity = ent.riddenByEntity;
        if (riddenByEntity != null)
        {
            riddenByEntity.mountEntity(null);
            riddenByEntity = teleport(riddenByEntity, dimension, px, py, pz, yaw, pitch, mx, my, mz);
        }

        int oldD = ent.dimension;
        int newD = dimension == -999 ? oldD : dimension;
        MinecraftServer s = FMLCommonHandler.instance().getMinecraftServerInstance();
        ServerConfigurationManager scm = s.getConfigurationManager();
        WorldServer oldW = s.worldServerForDimension(oldD);
        WorldServer newW = oldD == newD ? oldW : s.worldServerForDimension(newD);
        boolean DCR = oldW != newW; // DimensionChangeRequired
        if (DCR)
        {
            if (ent instanceof EntityPlayerMP)
            {
                EntityPlayerMP mp = (EntityPlayerMP) ent;
                mp.dimension = newD;
                mp.playerNetServerHandler.sendPacket(new S07PacketRespawn(mp.dimension, mp.worldObj.getDifficulty(), mp.worldObj.getWorldInfo().getTerrainType(), mp.theItemInWorldManager.getGameType()));
                oldW.removePlayerEntityDangerously(mp);
                // mp.isDead = false;
            }
            ent.worldObj.removeEntity(ent); // use wolrdObj instead of oldW
            ent.isDead = false;
            ent.setWorld(newW);
        }

        move(ent, px, py, pz, yaw, pitch, mx, my, mz);

        if (DCR)
        {
            if (ent.isEntityAlive())
            {
                ent.worldObj.spawnEntityInWorld(ent);
                ent.worldObj.updateEntityWithOptionalForce(ent, false);
            }
            if (ent instanceof EntityPlayerMP)
            {
                EntityPlayerMP mp = (EntityPlayerMP) ent;
                oldW.getPlayerManager().removePlayer(mp);
                newW.getPlayerManager().addPlayer(mp);
                newW.theChunkProviderServer.loadChunk((int) mp.posX >> 4, (int) mp.posZ >> 4);
                move(ent, px, py, pz, yaw, pitch, mx, my, mz);
                mp.theItemInWorldManager.setWorld(newW);
                scm.updateTimeAndWeatherForPlayer(mp, newW);
                scm.syncPlayerInventory(mp);
                syncPlayerPotionEffect(mp);
                syncPlayerExperience(mp); // in case some mods reset it on client side
                FMLCommonHandler.instance().firePlayerChangedDimensionEvent(mp, oldD, newD); // inform other mods
            }
            else
            {
                Entity newE = EntityList.createEntityByName(EntityList.getEntityString(ent), ent.worldObj);
                NBTTagCompound data = new NBTTagCompound();
                ent.writeToNBT(data);
                newE.readFromNBT(data);
                newE.worldObj.spawnEntityInWorld(newE);
                ent.isDead = true;
                ent = newE;
            }
        }

        if (ridingEntity != null)
            ent.mountEntity(ridingEntity);
        if (riddenByEntity != null)
            riddenByEntity.mountEntity(ent);

        return ent;
    }

}
