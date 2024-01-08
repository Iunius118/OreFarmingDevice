package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.ModMenuTypes;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.item.crafting.ModRecipeSerializers;
import com.github.iunius118.orefarmingdevice.world.item.crafting.ModRecipeTypes;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.ModBlockEntityTypes;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterEventHandler {
    public static void registerGameObjects(IEventBus modEventBus) {
        registerBlocks(modEventBus);
        registerItems(modEventBus);
        registerBlockEntityTypes(modEventBus);
        registerRecipeTypes(modEventBus);
        registerRecipeSerializers(modEventBus);
        registerMenuTypes(modEventBus);
    }

    private static void registerBlocks(IEventBus modEventBus) {
        var blockDeferredRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, OreFarmingDevice.MOD_ID);

        blockDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> ModBlocks.DEVICE_0);
        blockDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> ModBlocks.DEVICE_1);
        blockDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> ModBlocks.DEVICE_2);

        blockDeferredRegister.register(modEventBus);
    }

    private static void registerItems(IEventBus modEventBus) {
        var itemDeferredRegister = DeferredRegister.create(ForgeRegistries.ITEMS, OreFarmingDevice.MOD_ID);

        itemDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> ModItems.DEVICE_0);
        itemDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> ModItems.DEVICE_1);
        itemDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> ModItems.DEVICE_2);
        itemDeferredRegister.register("cobblestone_feeder", () -> ModItems.COBBLESTONE_FEEDER);
        itemDeferredRegister.register("cobblestone_feeder_2", () -> ModItems.COBBLESTONE_FEEDER_2);


        itemDeferredRegister.register(modEventBus);
    }

    private static void registerBlockEntityTypes(IEventBus modEventBus) {
        var blockEntityTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OreFarmingDevice.MOD_ID);

        blockEntityTypeDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> ModBlockEntityTypes.DEVICE_0);
        blockEntityTypeDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> ModBlockEntityTypes.DEVICE_1);
        blockEntityTypeDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> ModBlockEntityTypes.DEVICE_2);

        blockEntityTypeDeferredRegister.register(modEventBus);
    }

    private static void registerRecipeTypes(IEventBus modEventBus) {
        var recipeTypeRegister = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, OreFarmingDevice.MOD_ID);

        recipeTypeRegister.register("device_processing", () -> ModRecipeTypes.DEVICE_PROCESSING);

        recipeTypeRegister.register(modEventBus);
    }

    private static void registerRecipeSerializers(IEventBus modEventBus) {
        var recipeSerializerDeferredRegister = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OreFarmingDevice.MOD_ID);

        recipeSerializerDeferredRegister.register("device_processing", () -> ModRecipeSerializers.DEVICE_PROCESSING);

        recipeSerializerDeferredRegister.register(modEventBus);
    }

    private static void registerMenuTypes(IEventBus modEventBus) {
        var menuTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.MENU_TYPES, OreFarmingDevice.MOD_ID);

        menuTypeDeferredRegister.register("device", () -> ModMenuTypes.DEVICE);

        menuTypeDeferredRegister.register(modEventBus);
    }
}
