package com.zyao.mwfexplosionfix;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = mwfexplosionfix.MODID, useMetadata = true)
public class mwfexplosionfix
{
    public static final String MODID = "mwfexplosionfix";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("I AM OPEN!");
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
