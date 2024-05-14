package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.ModMenuTypes;
import com.github.iunius118.orefarmingdevice.world.item.ModCreativeModeTabs;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.item.crafting.ModRecipeSerializers;
import com.github.iunius118.orefarmingdevice.world.item.crafting.ModRecipeTypes;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.ModBlockEntityTypes;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegisterEventHandler {
    public static void registerGameObjects(IEventBus modEventBus) {
        registerBlocks(modEventBus);
        registerItems(modEventBus);
        registerBlockEntityTypes(modEventBus);
        registerRecipeTypes(modEventBus);
        registerRecipeSerializers(modEventBus);
        registerMenuTypes(modEventBus);
        registerCreativeModeTabs(modEventBus);
    }

    private static void registerBlocks(IEventBus modEventBus) {
        var blockRegister = DeferredRegister.createBlocks(OreFarmingDevice.MOD_ID);

        blockRegister.register(OFDeviceType.MOD_0.getName(), () -> ModBlocks.DEVICE_0);
        blockRegister.register(OFDeviceType.MOD_1.getName(), () -> ModBlocks.DEVICE_1);
        blockRegister.register(OFDeviceType.MOD_2.getName(), () -> ModBlocks.DEVICE_2);

        blockRegister.register(modEventBus);
    }

    private static void registerItems(IEventBus modEventBus) {
        var itemRegister = DeferredRegister.createItems(OreFarmingDevice.MOD_ID);

        itemRegister.register(OFDeviceType.MOD_0.getName(), () -> ModItems.DEVICE_0);
        itemRegister.register(OFDeviceType.MOD_1.getName(), () -> ModItems.DEVICE_1);
        itemRegister.register(OFDeviceType.MOD_2.getName(), () -> ModItems.DEVICE_2);
        itemRegister.register("cobblestone_feeder", () -> ModItems.COBBLESTONE_FEEDER);
        itemRegister.register("cobblestone_feeder_2", () -> ModItems.COBBLESTONE_FEEDER_2);

        itemRegister.register(modEventBus);
    }

    private static void registerBlockEntityTypes(IEventBus modEventBus) {
        var blockEntityTypeRegister = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, OreFarmingDevice.MOD_ID);

        blockEntityTypeRegister.register(OFDeviceType.MOD_0.getName(), () -> ModBlockEntityTypes.DEVICE_0);
        blockEntityTypeRegister.register(OFDeviceType.MOD_1.getName(), () -> ModBlockEntityTypes.DEVICE_1);
        blockEntityTypeRegister.register(OFDeviceType.MOD_2.getName(), () -> ModBlockEntityTypes.DEVICE_2);

        blockEntityTypeRegister.register(modEventBus);
    }

    private static void registerRecipeTypes(IEventBus modEventBus) {
        var recipeTypeRegister = DeferredRegister.create(Registries.RECIPE_TYPE, OreFarmingDevice.MOD_ID);

        recipeTypeRegister.register("device_processing", () -> ModRecipeTypes.DEVICE_PROCESSING);

        recipeTypeRegister.register(modEventBus);
    }

    private static void registerRecipeSerializers(IEventBus modEventBus) {
        var recipeSerializerRegister = DeferredRegister.create(Registries.RECIPE_SERIALIZER, OreFarmingDevice.MOD_ID);

        recipeSerializerRegister.register("device_processing", () -> ModRecipeSerializers.DEVICE_PROCESSING);

        recipeSerializerRegister.register(modEventBus);
    }

    private static void registerMenuTypes(IEventBus modEventBus) {
        var menuTypeRegister = DeferredRegister.create(Registries.MENU, OreFarmingDevice.MOD_ID);

        menuTypeRegister.register("device", () -> ModMenuTypes.DEVICE);

        menuTypeRegister.register(modEventBus);
    }

    private static void registerCreativeModeTabs(IEventBus modEventBus) {
        var creativeModeTabRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OreFarmingDevice.MOD_ID);

        creativeModeTabRegister.register("general", () -> ModCreativeModeTabs.MAIN);

        creativeModeTabRegister.register(modEventBus);
    }
}
