package org.mcxz.mcxz.utils;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class PositionData
{

    public int dimension = -999;
    public double x, y, z;
    public float yaw, pitch;

    public PositionData()
    {
    }

    public PositionData(BlockPos par1)
    {
        this.x = (double) par1.getX() + 0.5D;
        this.y = (double) par1.getY() + 0.5D;
        this.z = (double) par1.getZ() + 0.5D;
    }

    public PositionData(BlockPos par1, int par2)
    {
        this(par1);
        this.dimension = par2;
    }

    public PositionData(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PositionData(double x, double y, double z, float yaw, float pitch)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public PositionData(Entity par1)
    {
        this.dimension = par1.dimension;
        this.x = par1.posX;
        this.y = par1.posY;
        this.z = par1.posZ;
        this.yaw = par1.rotationYaw;
        this.pitch = par1.rotationPitch;
    }

    public PositionData(int dimension, double x, double y, double z, float yaw, float pitch)
    {
        this.dimension = dimension;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public PositionData(PositionData par1)
    {
        this.dimension = par1.dimension;
        this.x = par1.x;
        this.y = par1.y;
        this.z = par1.z;
        this.yaw = par1.yaw;
        this.pitch = par1.pitch;
    }

    public PositionData align()
    {
        return new PositionData(dimension, (Math.floor(x) + 0.5D), (Math.floor(y) + 0.5D), (Math.floor(z) + 0.5D), (Math.round(yaw / 90.0F) * 90.0F), (pitch > 60.0F ? 90.0F : (pitch < -60.0F ? -90.0F : 0.0F)));
    }

    public void readFromNBT(NBTTagCompound par1)
    {
        dimension = par1.getInteger("dimension");
        x = par1.getDouble("x");
        y = par1.getDouble("y");
        z = par1.getDouble("z");
        yaw = par1.getFloat("yaw");
        pitch = par1.getFloat("pitch");
    }

    public void teleportEntity(Entity par1)
    {
        teleportEntity(par1, false);
    }

    public void teleportEntity(Entity par1, boolean par2)
    {
        Teleporter.teleport(par1, dimension, x, y, z, yaw, pitch, 0, 0, 0);
        if (par2)
        {
            boolean flag = false;
            while (!par1.worldObj.getCollidingBoundingBoxes(par1, par1.getEntityBoundingBox()).isEmpty())
            {
                par1.setPosition(par1.posX, par1.posY + 1.0D, par1.posZ);
                flag = true;
            }
            if (flag)
                Teleporter.teleport(par1, -999, par1.posX, par1.posY, par1.posZ, par1.rotationYaw, par1.rotationPitch, 0, 0, 0);
        }
    }

    public void writeToNBT(NBTTagCompound par1)
    {
        par1.setInteger("dimension", dimension);
        par1.setDouble("x", x);
        par1.setDouble("y", y);
        par1.setDouble("z", z);
        par1.setFloat("yaw", yaw);
        par1.setFloat("pitch", pitch);
    }

}
