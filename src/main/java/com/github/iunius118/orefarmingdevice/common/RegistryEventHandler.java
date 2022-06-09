package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.data.*;
import com.github.iunius118.orefarmingdevice.inventory.ModContainerTypes;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.ModBlockEntityTypes;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class RegistryEventHandler {
    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event) {
        if (!event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCKS))
            return;

        event.register(ForgeRegistries.Keys.BLOCKS, OFDeviceType.MOD_0.getId(), () -> ModBlocks.DEVICE_0);
        event.register(ForgeRegistries.Keys.BLOCKS, OFDeviceType.MOD_1.getId(), () -> ModBlocks.DEVICE_1);
        event.register(ForgeRegistries.Keys.BLOCKS, OFDeviceType.MOD_2.getId(), () -> ModBlocks.DEVICE_2);
    }

    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
        if (!event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS))
            return;

        event.register(ForgeRegistries.Keys.ITEMS, OFDeviceType.MOD_0.getId(), () -> ModItems.DEVICE_0);
        event.register(ForgeRegistries.Keys.ITEMS, OFDeviceType.MOD_1.getId(), () -> ModItems.DEVICE_1);
        event.register(ForgeRegistries.Keys.ITEMS, OFDeviceType.MOD_2.getId(), () -> ModItems.DEVICE_2);
        event.register(ForgeRegistries.Keys.ITEMS, new ResourceLocation(OreFarmingDevice.MOD_ID, "cobblestone_feeder"), () -> ModItems.COBBLESTONE_FEEDER);
    }

    @SubscribeEvent
    public static void registerBlockEntityTypes(RegisterEvent event) {
        if (!event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES))
            return;

        event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, OFDeviceType.MOD_0.getId(), () -> ModBlockEntityTypes.DEVICE_0);
        event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, OFDeviceType.MOD_1.getId(), () -> ModBlockEntityTypes.DEVICE_1);
        event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, OFDeviceType.MOD_2.getId(), () -> ModBlockEntityTypes.DEVICE_2);
    }

    @SubscribeEvent
    public static void registerContainerTypes(RegisterEvent event) {
        if (!event.getRegistryKey().equals(ForgeRegistries.Keys.CONTAINER_TYPES))
            return;

        event.register(ForgeRegistries.Keys.CONTAINER_TYPES, new ResourceLocation(OreFarmingDevice.MOD_ID, "device"), () -> ModContainerTypes.DEVICE);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var dataGenerator = event.getGenerator();
        var existingFileHelper = event.getExistingFileHelper();
        var blockTagsProvider = new ForgeBlockTagsProvider(dataGenerator, existingFileHelper);

        // Server
        boolean includesServer = event.includeServer();
        dataGenerator.addProvider(includesServer, new ModLootTableProvider(dataGenerator));
        dataGenerator.addProvider(includesServer, new ModRecipeProvider(dataGenerator));

        // Client
        boolean includesClient = event.includeClient();
        dataGenerator.addProvider(includesClient, new ModBlockStateProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(includesClient, new ModItemModelProvider(dataGenerator, existingFileHelper));
        ModLanguageProvider.addProviders(includesClient, dataGenerator);
    }
}
