package com.github.iunius118.orefarmingdevice;

import com.github.iunius118.orefarmingdevice.client.ClientProxy;
import com.github.iunius118.orefarmingdevice.common.RegisterEventHandler;
import com.github.iunius118.orefarmingdevice.common.ServerProxy;
import com.github.iunius118.orefarmingdevice.config.OreFarmingDeviceConfig;
import com.github.iunius118.orefarmingdevice.data.ModDataGenerator;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(OreFarmingDevice.MOD_ID)
public class OreFarmingDevice {
    public static final String MOD_ID = "orefarmingdevice";
    public static final String MOD_NAME = "O.F.Device";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ServerProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public OreFarmingDevice(FMLJavaModLoadingContext context) {
        final IEventBus modEventBus = context.getModEventBus();

        // Register mod lifecycle event handlers
        modEventBus.addListener(this::setup);

        // Register config handlers
        context.registerConfig(ModConfig.Type.SERVER, OreFarmingDeviceConfig.SERVER_SPEC);

        // Register event handlers
        RegisterEventHandler.registerGameObjects(modEventBus);
        modEventBus.addListener(ModDataGenerator::gatherData);
        /* Disable data pack Experimental_1202 since 1.20.2
        // Register optional data pack handlers
        modEventBus.addListener(Experimental1202DataProvider::addPackFinders);
         */
    }

    private void setup(FMLCommonSetupEvent event) {
        proxy.setup(event);
    }

    public static ResourceLocation makeId(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
