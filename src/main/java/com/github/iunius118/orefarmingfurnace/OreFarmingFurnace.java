package com.github.iunius118.orefarmingfurnace;

import com.github.iunius118.orefarmingfurnace.common.RegistryEventHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OreFarmingFurnace.MOD_ID)
public class OreFarmingFurnace {
    public static final String MOD_ID = "orefarmingfurnace";
    public static final String MOD_NAME = "Ore Farming Furnace";

    public static final Logger LOGGER = LogManager.getLogger();

    public OreFarmingFurnace() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register event handlers
        modEventBus.register(RegistryEventHandler.class);
    }
}
