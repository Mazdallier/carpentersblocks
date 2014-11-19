package com.carpentersblocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import com.carpentersblocks.proxy.CommonProxy;
import com.carpentersblocks.util.CarpentersBlocksTab;
import com.carpentersblocks.util.handler.PacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(
        modid = CarpentersBlocks.MODID,
        name = "Carpenter's Blocks",
        version = CarpentersBlocks.VERSION,
        dependencies = "required-after:Forge@[9.11.1.964,)"
        )
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false,
        channels = { CarpentersBlocks.MODID },
        packetHandler = PacketHandler.class
        )
public class CarpentersBlocks {

    public static final String MODID = "CarpentersBlocks";
    public static final String VERSION = "3.2.8.4";
    public static CreativeTabs creativeTab = new CarpentersBlocksTab(MODID);

    @Instance(MODID)
    public static CarpentersBlocks instance;

    @SidedProxy(clientSide = "com.carpentersblocks.proxy.ClientProxy", serverSide = "com.carpentersblocks.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        proxy.preInit(event, config);

        if (config.hasChanged()) {
            config.save();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

}