package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.ModMenuTypes;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
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

        itemDeferredRegister.register(modEventBus);
    }

    private static void registerBlockEntityTypes(IEventBus modEventBus) {
        var blockEntityTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OreFarmingDevice.MOD_ID);

        blockEntityTypeDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> ModBlockEntityTypes.DEVICE_0);
        blockEntityTypeDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> ModBlockEntityTypes.DEVICE_1);
        blockEntityTypeDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> ModBlockEntityTypes.DEVICE_2);

        blockEntityTypeDeferredRegister.register(modEventBus);
    }

    private static void registerMenuTypes(IEventBus modEventBus) {
        var menuTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.MENU_TYPES, OreFarmingDevice.MOD_ID);

        menuTypeDeferredRegister.register("device", () -> ModMenuTypes.DEVICE);

        menuTypeDeferredRegister.register(modEventBus);
    }
}
