package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.data.ModBlockStateProvider;
import com.github.iunius118.orefarmingdevice.data.ModItemModelProvider;
import com.github.iunius118.orefarmingdevice.data.ModLanguageProvider;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.data.DataGenerator;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.function.Supplier;

public class RegistryEventHandler {
    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        Supplier<AbstractBlock.Properties> ofDevicePropertiesSupplier = () -> AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F);

        // Register OFDeviceBlocks
        event.getRegistry().registerAll(
                new OFDeviceBlock(ofDevicePropertiesSupplier.get(), OFDeviceType.MOD_0).setRegistryName(OFDeviceType.MOD_0.getName()),
                new OFDeviceBlock(ofDevicePropertiesSupplier.get(), OFDeviceType.MOD_1).setRegistryName(OFDeviceType.MOD_1.getName()),
                new OFDeviceBlock(ofDevicePropertiesSupplier.get(), OFDeviceType.MOD_2).setRegistryName(OFDeviceType.MOD_2.getName())
        );
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        Supplier<Item.Properties> ofDevicePropertiesSupplier = () -> new Item.Properties().tab(ItemGroup.TAB_DECORATIONS);

        // Register items of OFDeviceBlocks
        event.getRegistry().registerAll(
                new BlockItem(ModBlocks.DEVICE_0, ofDevicePropertiesSupplier.get()).setRegistryName(OFDeviceType.MOD_0.getName()),
                new BlockItem(ModBlocks.DEVICE_1, ofDevicePropertiesSupplier.get()).setRegistryName(OFDeviceType.MOD_1.getName()),
                new BlockItem(ModBlocks.DEVICE_2, ofDevicePropertiesSupplier.get()).setRegistryName(OFDeviceType.MOD_2.getName())
        );
    }

    @SubscribeEvent
    public static void onBlockEntityRegistry(RegistryEvent.Register<TileEntityType<?>> event) {
        // Register BEs of OFDeviceBlocks
        event.getRegistry().registerAll(
                TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_0), ModBlocks.DEVICE_0).build(null).setRegistryName(OFDeviceType.MOD_0.getName()),
                TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_1), ModBlocks.DEVICE_1).build(null).setRegistryName(OFDeviceType.MOD_1.getName()),
                TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_2), ModBlocks.DEVICE_2).build(null).setRegistryName(OFDeviceType.MOD_2.getName())
        );
    }

    @SubscribeEvent
    public static void onContainerRegistry(RegistryEvent.Register<ContainerType<?>> event) {
        // Register Container of OFDeviceBlocks
        event.getRegistry().register(new ContainerType<>(OFDeviceContainer::new).setRegistryName("device"));
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ForgeBlockTagsProvider blockTagsProvider = new ForgeBlockTagsProvider(dataGenerator, existingFileHelper);

        if (event.includeServer()) {

        }

        if (event.includeClient()) {
            dataGenerator.addProvider(new ModBlockStateProvider(dataGenerator, existingFileHelper));
            dataGenerator.addProvider(new ModItemModelProvider(dataGenerator, existingFileHelper));
            ModLanguageProvider.addProviders(dataGenerator);
        }
    }
}
