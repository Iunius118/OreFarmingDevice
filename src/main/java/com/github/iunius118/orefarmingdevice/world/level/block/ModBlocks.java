package com.github.iunius118.orefarmingdevice.world.level.block;

import com.github.iunius118.orefarmingdevice.world.level.block.entity.CobblestoneDeviceType;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class ModBlocks {
    private static final Supplier<BlockBehaviour.Properties> PROPERTIES = () -> BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F);

    public static final OFDeviceBlock DEVICE_0 = new OFDeviceBlock(PROPERTIES.get(), OFDeviceType.MOD_0);
    public static final OFDeviceBlock DEVICE_1 = new OFDeviceBlock(PROPERTIES.get(), OFDeviceType.MOD_1);
    public static final OFDeviceBlock DEVICE_2 = new OFDeviceBlock(PROPERTIES.get(), OFDeviceType.MOD_2);
    public static final CobblestoneDeviceBlock COBBLESTONE_DEVICE_0 = new CobblestoneDeviceBlock(PROPERTIES.get(), CobblestoneDeviceType.BASIC);
}
