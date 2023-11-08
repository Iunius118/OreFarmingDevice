package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceMenu;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederItem;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederType;
import com.github.iunius118.orefarmingdevice.world.item.ModCreativeModeTabs;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterEventHandler {
    public static void registerGameObjects(IEventBus modEventBus) {
        registerBlocks(modEventBus);
        registerItems(modEventBus);
        registerTileEntityTypes(modEventBus);
        registerContainerTypes(modEventBus);
    }

    private static void registerBlocks(IEventBus modEventBus) {
        DeferredRegister<Block> blockDeferredRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, OreFarmingDevice.MOD_ID);
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F);

        blockDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> new OFDeviceBlock(properties, OFDeviceType.MOD_0));
        blockDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> new OFDeviceBlock(properties, OFDeviceType.MOD_1));
        blockDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> new OFDeviceBlock(properties, OFDeviceType.MOD_2));

        blockDeferredRegister.register(modEventBus);
    }

    private static void registerItems(IEventBus modEventBus) {
        DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(ForgeRegistries.ITEMS, OreFarmingDevice.MOD_ID);
        Item.Properties properties = new Item.Properties().tab(ModCreativeModeTabs.MAIN);

        itemDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> new BlockItem(ModBlocks.DEVICE_0, properties));
        itemDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> new BlockItem(ModBlocks.DEVICE_1, properties));
        itemDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> new BlockItem(ModBlocks.DEVICE_2, properties));
        itemDeferredRegister.register("cobblestone_feeder", () -> new CobblestoneFeederItem(CobblestoneFeederType.BASIC, properties));
        itemDeferredRegister.register("cobblestone_feeder_2", () -> new CobblestoneFeederItem(CobblestoneFeederType.UPGRADED, properties));

        itemDeferredRegister.register(modEventBus);
    }

    private static void registerTileEntityTypes(IEventBus modEventBus) {
        DeferredRegister<BlockEntityType<?>> tileEntityTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, OreFarmingDevice.MOD_ID);

        tileEntityTypeDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_0), ModBlocks.DEVICE_0).build(null));
        tileEntityTypeDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_1), ModBlocks.DEVICE_1).build(null));
        tileEntityTypeDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_2), ModBlocks.DEVICE_2).build(null));

        tileEntityTypeDeferredRegister.register(modEventBus);
    }

    private static void registerContainerTypes(IEventBus modEventBus) {
        DeferredRegister<MenuType<?>> containerTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.CONTAINERS, OreFarmingDevice.MOD_ID);

        containerTypeDeferredRegister.register("device", () -> new MenuType<>(OFDeviceMenu::new));

        containerTypeDeferredRegister.register(modEventBus);
    }
}
