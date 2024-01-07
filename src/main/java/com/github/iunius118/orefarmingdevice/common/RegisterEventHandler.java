package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederItem;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederType;
import com.github.iunius118.orefarmingdevice.world.item.ModItemGroups;
import com.github.iunius118.orefarmingdevice.world.item.crafting.DeviceProcessingRecipe;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
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

public class RegisterEventHandler {
    public static void registerGameObjects(IEventBus modEventBus) {
        registerBlocks(modEventBus);
        registerItems(modEventBus);
        registerTileEntityTypes(modEventBus);
        registerRecipeSerializers(modEventBus);
        registerContainerTypes(modEventBus);
    }

    private static void registerBlocks(IEventBus modEventBus) {
        DeferredRegister<Block> blockDeferredRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, OreFarmingDevice.MOD_ID);
        AbstractBlock.Properties properties = AbstractBlock.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(3.5F);

        blockDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> new OFDeviceBlock(properties, OFDeviceType.MOD_0));
        blockDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> new OFDeviceBlock(properties, OFDeviceType.MOD_1));
        blockDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> new OFDeviceBlock(properties, OFDeviceType.MOD_2));

        blockDeferredRegister.register(modEventBus);
    }

    private static void registerItems(IEventBus modEventBus) {
        DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(ForgeRegistries.ITEMS, OreFarmingDevice.MOD_ID);
        Item.Properties properties = new Item.Properties().tab(ModItemGroups.MAIN);

        itemDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> new BlockItem(ModBlocks.DEVICE_0, properties));
        itemDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> new BlockItem(ModBlocks.DEVICE_1, properties));
        itemDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> new BlockItem(ModBlocks.DEVICE_2, properties));
        itemDeferredRegister.register("cobblestone_feeder", () -> new CobblestoneFeederItem(CobblestoneFeederType.BASIC, properties));
        itemDeferredRegister.register("cobblestone_feeder_2", () -> new CobblestoneFeederItem(CobblestoneFeederType.UPGRADED, properties));

        itemDeferredRegister.register(modEventBus);
    }

    private static void registerTileEntityTypes(IEventBus modEventBus) {
        DeferredRegister<TileEntityType<?>> tileEntityTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, OreFarmingDevice.MOD_ID);

        tileEntityTypeDeferredRegister.register(OFDeviceType.MOD_0.getName(), () -> TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_0), ModBlocks.DEVICE_0).build(null));
        tileEntityTypeDeferredRegister.register(OFDeviceType.MOD_1.getName(), () -> TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_1), ModBlocks.DEVICE_1).build(null));
        tileEntityTypeDeferredRegister.register(OFDeviceType.MOD_2.getName(), () -> TileEntityType.Builder.of(() -> new OFDeviceBlockEntity(OFDeviceType.MOD_2), ModBlocks.DEVICE_2).build(null));

        tileEntityTypeDeferredRegister.register(modEventBus);
    }

    private static void registerRecipeSerializers(IEventBus modEventBus) {
        DeferredRegister<IRecipeSerializer<?>> recipeSerializerDeferredRegister = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OreFarmingDevice.MOD_ID);

        recipeSerializerDeferredRegister.register("device_processing", DeviceProcessingRecipe.Serializer::new);

        recipeSerializerDeferredRegister.register(modEventBus);
    }

    private static void registerContainerTypes(IEventBus modEventBus) {
        DeferredRegister<ContainerType<?>> containerTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.CONTAINERS, OreFarmingDevice.MOD_ID);

        containerTypeDeferredRegister.register("device", () -> new ContainerType<>(OFDeviceContainer::new));

        containerTypeDeferredRegister.register(modEventBus);
    }
}
