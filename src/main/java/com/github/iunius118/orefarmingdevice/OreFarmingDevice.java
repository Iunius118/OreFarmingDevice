package com.github.iunius118.orefarmingdevice;

import com.github.iunius118.orefarmingdevice.client.ClientProxy;
import com.github.iunius118.orefarmingdevice.common.RegistryEventHandler;
import com.github.iunius118.orefarmingdevice.common.ServerProxy;
import com.github.iunius118.orefarmingdevice.data.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
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

        // Register event handlers
        RegistryEventHandler.registerGameObjects(modEventBus);
        modEventBus.addListener(this::gatherData);
    }

    private void setup(FMLCommonSetupEvent event) {
        proxy.setup(event);
    }

    // Generate Data
    public void gatherData(GatherDataEvent event) {
        var dataGenerator = event.getGenerator();
        var existingFileHelper = event.getExistingFileHelper();

        // Server
        boolean includesServer = event.includeServer();
        dataGenerator.addProvider(includesServer, new ModBlockTagsProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(includesServer, new ModLootTableProvider(dataGenerator));
        dataGenerator.addProvider(includesServer, new ModRecipeProvider(dataGenerator));

        // Client
        boolean includesClient = event.includeClient();
        dataGenerator.addProvider(includesClient, new ModBlockStateProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(includesClient, new ModItemModelProvider(dataGenerator, existingFileHelper));
        ModLanguageProvider.addProviders(includesClient, dataGenerator);
    }
}
