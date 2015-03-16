package org.mcxz.mcxz.asm;

import net.minecraft.launchwrapper.IClassTransformer;

public class ASMTransformer implements IClassTransformer
{

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        return bytes;
    }

}
