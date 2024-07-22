package com.github.iunius118.orefarmingdevice.world.item;

import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModItems {
    private static final Supplier<Item.Properties> PROPERTIES = () -> new Item.Properties().tab(ModCreativeModeTabs.MAIN);

    public static final Item DEVICE_0 = new BlockItem(ModBlocks.DEVICE_0, PROPERTIES.get());
    public static final Item DEVICE_1 = new BlockItem(ModBlocks.DEVICE_1, PROPERTIES.get());
    public static final Item DEVICE_2 = new BlockItem(ModBlocks.DEVICE_2, PROPERTIES.get());
    public static final Item COBBLESTONE_DEVICE_0 = new BlockItem(ModBlocks.COBBLESTONE_DEVICE_0, PROPERTIES.get());
    public static final Item COBBLESTONE_FEEDER = new CobblestoneFeederItem(CobblestoneFeederType.BASIC, PROPERTIES.get());
    public static final Item COBBLESTONE_FEEDER_2 = new CobblestoneFeederItem(CobblestoneFeederType.UPGRADED, PROPERTIES.get());
}
