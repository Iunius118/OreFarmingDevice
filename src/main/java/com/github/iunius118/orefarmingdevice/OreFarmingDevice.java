package com.github.iunius118.orefarmingdevice;

import com.github.iunius118.orefarmingdevice.common.RegistryEventHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OreFarmingDevice.MOD_ID)
public class OreFarmingDevice {
    public static final String MOD_ID = "orefarmingdevice";
    public static final String MOD_NAME = "O.F.Device";

    public static final Logger LOGGER = LogManager.getLogger();

    public OreFarmingDevice() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register event handlers
        modEventBus.register(RegistryEventHandler.class);
    }
}
