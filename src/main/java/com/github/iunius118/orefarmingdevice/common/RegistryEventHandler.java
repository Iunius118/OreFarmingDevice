package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.data.*;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import com.github.iunius118.orefarmingdevice.world.item.ModItemGroups;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.function.Supplier;

public class RegistryEventHandler {
    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        Supplier<BlockBehaviour.Properties> ofDevicePropertiesSupplier = () -> BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F);

        // Register OFDeviceBlocks
        event.getRegistry().registerAll(
                new OFDeviceBlock(ofDevicePropertiesSupplier.get(), OFDeviceType.MOD_0).setRegistryName(OFDeviceType.MOD_0.getName()),
                new OFDeviceBlock(ofDevicePropertiesSupplier.get(), OFDeviceType.MOD_1).setRegistryName(OFDeviceType.MOD_1.getName()),
                new OFDeviceBlock(ofDevicePropertiesSupplier.get(), OFDeviceType.MOD_2).setRegistryName(OFDeviceType.MOD_2.getName())
        );
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        Supplier<Item.Properties> ofDevicePropertiesSupplier = () -> new Item.Properties().tab(ModItemGroups.MAIN);

        // Register items of OFDeviceBlocks
        event.getRegistry().registerAll(
                new BlockItem(ModBlocks.DEVICE_0, ofDevicePropertiesSupplier.get()).setRegistryName(OFDeviceType.MOD_0.getName()),
                new BlockItem(ModBlocks.DEVICE_1, ofDevicePropertiesSupplier.get()).setRegistryName(OFDeviceType.MOD_1.getName()),
                new BlockItem(ModBlocks.DEVICE_2, ofDevicePropertiesSupplier.get()).setRegistryName(OFDeviceType.MOD_2.getName()),
                new Item(new Item.Properties().tab(ModItemGroups.MAIN)).setRegistryName("cobblestone_feeder")
        );
    }

    @SubscribeEvent
    public static void onBlockEntityRegistry(RegistryEvent.Register<BlockEntityType<?>> event) {
        // Register BEs of OFDeviceBlocks
        event.getRegistry().registerAll(
                BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_0), ModBlocks.DEVICE_0).build(null).setRegistryName(OFDeviceType.MOD_0.getName()),
                BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_1), ModBlocks.DEVICE_1).build(null).setRegistryName(OFDeviceType.MOD_1.getName()),
                BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_2), ModBlocks.DEVICE_2).build(null).setRegistryName(OFDeviceType.MOD_2.getName())
        );
    }

    @SubscribeEvent
    public static void onContainerRegistry(RegistryEvent.Register<MenuType<?>> event) {
        // Register Container of OFDeviceBlocks
        event.getRegistry().register(new MenuType<>(OFDeviceContainer::new).setRegistryName("device"));
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ForgeBlockTagsProvider blockTagsProvider = new ForgeBlockTagsProvider(dataGenerator, existingFileHelper);

        if (event.includeServer()) {
            dataGenerator.addProvider(new ModLootTableProvider(dataGenerator));
            dataGenerator.addProvider(new ModRecipeProvider(dataGenerator));
        }

        if (event.includeClient()) {
            dataGenerator.addProvider(new ModBlockStateProvider(dataGenerator, existingFileHelper));
            dataGenerator.addProvider(new ModItemModelProvider(dataGenerator, existingFileHelper));
            ModLanguageProvider.addProviders(dataGenerator);
        }
    }
}
