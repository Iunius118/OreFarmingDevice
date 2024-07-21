package com.github.iunius118.orefarmingdevice.common;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.inventory.CobblestoneDeviceMenu;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceMenu;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederItem;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederType;
import com.github.iunius118.orefarmingdevice.world.item.ModCreativeModeTabs;
import com.github.iunius118.orefarmingdevice.world.item.crafting.DeviceProcessingRecipe;
import com.github.iunius118.orefarmingdevice.world.item.crafting.ModRecipeTypes;
import com.github.iunius118.orefarmingdevice.world.level.block.CobblestoneDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.CobblestoneDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.CobblestoneDeviceType;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RegisterEventHandler {
    public static void registerGameObjects(IEventBus modEventBus) {
        registerBlocks(modEventBus);
        registerItems(modEventBus);
        registerTileEntityTypes(modEventBus);
        registerRecipeTypes(modEventBus);
        registerRecipeSerializers(modEventBus);
        registerMenuTypes(modEventBus);
    }

    private static void registerBlocks(IEventBus modEventBus) {
        DeferredRegister<Block> blockRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, OreFarmingDevice.MOD_ID);
        Supplier<BlockBehaviour.Properties> properties = () -> BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F);

        blockRegister.register(OFDeviceType.MOD_0.getName(), () -> new OFDeviceBlock(properties.get(), OFDeviceType.MOD_0));
        blockRegister.register(OFDeviceType.MOD_1.getName(), () -> new OFDeviceBlock(properties.get(), OFDeviceType.MOD_1));
        blockRegister.register(OFDeviceType.MOD_2.getName(), () -> new OFDeviceBlock(properties.get(), OFDeviceType.MOD_2));
        blockRegister.register(CobblestoneDeviceType.BASIC.getName(), () -> new CobblestoneDeviceBlock(properties.get(), CobblestoneDeviceType.BASIC));

        blockRegister.register(modEventBus);
    }

    private static void registerItems(IEventBus modEventBus) {
        DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, OreFarmingDevice.MOD_ID);
        Supplier<Item.Properties> properties = () -> new Item.Properties().tab(ModCreativeModeTabs.MAIN);

        itemRegister.register(OFDeviceType.MOD_0.getName(), () -> new BlockItem(ModBlocks.DEVICE_0, properties.get()));
        itemRegister.register(OFDeviceType.MOD_1.getName(), () -> new BlockItem(ModBlocks.DEVICE_1, properties.get()));
        itemRegister.register(OFDeviceType.MOD_2.getName(), () -> new BlockItem(ModBlocks.DEVICE_2, properties.get()));
        itemRegister.register(CobblestoneDeviceType.BASIC.getName(), () -> new BlockItem(ModBlocks.COBBLESTONE_DEVICE_0, properties.get()));
        itemRegister.register(CobblestoneFeederType.BASIC.getName(), () -> new CobblestoneFeederItem(CobblestoneFeederType.BASIC, properties.get()));
        itemRegister.register(CobblestoneFeederType.UPGRADED.getName(), () -> new CobblestoneFeederItem(CobblestoneFeederType.UPGRADED, properties.get()));

        itemRegister.register(modEventBus);
    }

    private static void registerTileEntityTypes(IEventBus modEventBus) {
        DeferredRegister<BlockEntityType<?>> tileEntityTypeRegister = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, OreFarmingDevice.MOD_ID);

        tileEntityTypeRegister.register(OFDeviceType.MOD_0.getName(), () -> BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_0), ModBlocks.DEVICE_0).build(null));
        tileEntityTypeRegister.register(OFDeviceType.MOD_1.getName(), () -> BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_1), ModBlocks.DEVICE_1).build(null));
        tileEntityTypeRegister.register(OFDeviceType.MOD_2.getName(), () -> BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_2), ModBlocks.DEVICE_2).build(null));
        tileEntityTypeRegister.register(CobblestoneDeviceType.BASIC.getName(), () -> BlockEntityType.Builder.of((pos, state) -> new CobblestoneDeviceBlockEntity(pos, state, CobblestoneDeviceType.BASIC), ModBlocks.COBBLESTONE_DEVICE_0).build(null));

        tileEntityTypeRegister.register(modEventBus);
    }

    private static void registerRecipeTypes(IEventBus modEventBus) {
        DeferredRegister<RecipeType<?>> recipeTypeRegister = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, OreFarmingDevice.MOD_ID);

        recipeTypeRegister.register("device_processing", () -> ModRecipeTypes.DEVICE_PROCESSING);

        recipeTypeRegister.register(modEventBus);
    }

    private static void registerRecipeSerializers(IEventBus modEventBus) {
        DeferredRegister<RecipeSerializer<?>> recipeSerializerRegister = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OreFarmingDevice.MOD_ID);

        recipeSerializerRegister.register("device_processing", DeviceProcessingRecipe.Serializer::new);

        recipeSerializerRegister.register(modEventBus);
    }

    private static void registerMenuTypes(IEventBus modEventBus) {
        DeferredRegister<MenuType<?>> menuTypeRegister = DeferredRegister.create(ForgeRegistries.CONTAINERS, OreFarmingDevice.MOD_ID);

        menuTypeRegister.register("device", () -> new MenuType<>(OFDeviceMenu::new));
        menuTypeRegister.register("cobblestone_device", () -> new MenuType<>(CobblestoneDeviceMenu::new));

        menuTypeRegister.register(modEventBus);
    }
}
