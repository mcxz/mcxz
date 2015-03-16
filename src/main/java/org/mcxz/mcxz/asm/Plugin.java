package org.mcxz.mcxz.asm;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("mcxz.asm")
@IFMLLoadingPlugin.MCVersion("1.8")
@IFMLLoadingPlugin.TransformerExclusions("org.mcxz.mcxz.asm.")
public class Plugin implements IFMLLoadingPlugin
{

    public static boolean runtimeDeobfuscationEnabled = false;
    public static boolean isDevelopmentEnvironment = false;

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] { "org.mcxz.mcxz.asm.ASMTransformer" };
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getSetupClass()
    {
        return "org.mcxz.mcxz.asm.Setup";
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
        runtimeDeobfuscationEnabled = (Boolean) data.get("runtimeDeobfuscationEnabled");
        isDevelopmentEnvironment = (getClass().getResource("/binpatches.pack.lzma") == null);
    }

}
