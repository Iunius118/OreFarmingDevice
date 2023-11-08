package com.github.iunius118.orefarmingdevice;

import com.github.iunius118.orefarmingdevice.client.ClientProxy;
import com.github.iunius118.orefarmingdevice.common.RegistryEventHandler;
import com.github.iunius118.orefarmingdevice.common.ServerProxy;
import com.github.iunius118.orefarmingdevice.config.OreFarmingDeviceConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OreFarmingDevice.MOD_ID)
public class OreFarmingDevice {
    public static final String MOD_ID = "orefarmingdevice";
    public static final String MOD_NAME = "O.F.Device";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ServerProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public OreFarmingDevice() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register mod lifecycle event handlers
        modEventBus.addListener(this::setup);

        // Register config handlers
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, OreFarmingDeviceConfig.serverSpec);

        // Register event handlers
        modEventBus.register(RegistryEventHandler.class);
    }

    private void setup(FMLCommonSetupEvent event) {
        proxy.setup(event);
    }
}
