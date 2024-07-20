package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.CobblestoneDeviceContainer;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederItem;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederType;
import com.github.iunius118.orefarmingdevice.world.item.ModItemGroups;
import com.github.iunius118.orefarmingdevice.world.item.crafting.DeviceProcessingRecipe;
import com.github.iunius118.orefarmingdevice.world.level.block.CobblestoneDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.CobblestoneDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.CobblestoneDeviceType;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RegisterEventHandler {
    public static void registerGameObjects(IEventBus modEventBus) {
        registerBlocks(modEventBus);
        registerItems(modEventBus);
        registerTileEntityTypes(modEventBus);
        registerRecipeSerializers(modEventBus);
        registerContainerTypes(modEventBus);
    }

    private static void registerBlocks(IEventBus modEventBus) {
        DeferredRegister<Block> blockRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, OreFarmingDevice.MOD_ID);
        Supplier<AbstractBlock.Properties> properties = () -> AbstractBlock.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(3.5F);

        blockRegister.register(OFDeviceType.MOD_0.getName(), () -> new OFDeviceBlock(properties.get(), OFDeviceType.MOD_0));
        blockRegister.register(OFDeviceType.MOD_1.getName(), () -> new OFDeviceBlock(properties.get(), OFDeviceType.MOD_1));
        blockRegister.register(OFDeviceType.MOD_2.getName(), () -> new OFDeviceBlock(properties.get(), OFDeviceType.MOD_2));
        blockRegister.register(CobblestoneDeviceType.BASIC.getName(), () -> new CobblestoneDeviceBlock(properties.get(), CobblestoneDeviceType.BASIC));

        blockRegister.register(modEventBus);
    }

    private static void registerItems(IEventBus modEventBus) {
        DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, OreFarmingDevice.MOD_ID);
        Supplier<Item.Properties> properties = () -> new Item.Properties().tab(ModItemGroups.MAIN);

        itemRegister.register(OFDeviceType.MOD_0.getName(), () -> new BlockItem(ModBlocks.DEVICE_0, properties.get()));
        itemRegister.register(OFDeviceType.MOD_1.getName(), () -> new BlockItem(ModBlocks.DEVICE_1, properties.get()));
        itemRegister.register(OFDeviceType.MOD_2.getName(), () -> new BlockItem(ModBlocks.DEVICE_2, properties.get()));
        itemRegister.register(CobblestoneDeviceType.BASIC.getName(), () -> new BlockItem(ModBlocks.COBBLESTONE_DEVICE_0, properties.get()));
        itemRegister.register(CobblestoneFeederType.BASIC.getName(), () -> new CobblestoneFeederItem(CobblestoneFeederType.BASIC, properties.get()));
        itemRegister.register(CobblestoneFeederType.UPGRADED.getName(), () -> new CobblestoneFeederItem(CobblestoneFeederType.UPGRADED, properties.get()));

        itemRegister.register(modEventBus);
    }

    private static void registerTileEntityTypes(IEventBus modEventBus) {
        DeferredRegister<TileEntityType<?>> tileEntityTypeRegister = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, OreFarmingDevice.MOD_ID);

        tileEntityTypeRegister.register(OFDeviceType.MOD_0.getName(), () -> TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_0), ModBlocks.DEVICE_0).build(null));
        tileEntityTypeRegister.register(OFDeviceType.MOD_1.getName(), () -> TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_1), ModBlocks.DEVICE_1).build(null));
        tileEntityTypeRegister.register(OFDeviceType.MOD_2.getName(), () -> TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_2), ModBlocks.DEVICE_2).build(null));
        tileEntityTypeRegister.register(CobblestoneDeviceType.BASIC.getName(), () -> TileEntityType.Builder.of(() -> new CobblestoneDeviceBlockEntity(CobblestoneDeviceType.BASIC), ModBlocks.COBBLESTONE_DEVICE_0).build(null));

        tileEntityTypeRegister.register(modEventBus);
    }

    private static void registerRecipeSerializers(IEventBus modEventBus) {
        DeferredRegister<IRecipeSerializer<?>> recipeSerializerRegister = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OreFarmingDevice.MOD_ID);

        recipeSerializerRegister.register("device_processing", DeviceProcessingRecipe.Serializer::new);

        recipeSerializerRegister.register(modEventBus);
    }

    private static void registerContainerTypes(IEventBus modEventBus) {
        DeferredRegister<ContainerType<?>> containerTypeRegister = DeferredRegister.create(ForgeRegistries.CONTAINERS, OreFarmingDevice.MOD_ID);

        containerTypeRegister.register("device", () -> new ContainerType<>(OFDeviceContainer::new));
        containerTypeRegister.register("cobblestone_device", () -> new ContainerType<>(CobblestoneDeviceContainer::new));

        containerTypeRegister.register(modEventBus);
    }
}
