package com.github.iunius118.orefarmingdevice.world.level.block;

import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModBlocks {
    private static final BlockBehaviour.Properties ofDevicePropertiesSupplier = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F);

    public static final OFDeviceBlock DEVICE_0 = new OFDeviceBlock(ofDevicePropertiesSupplier, OFDeviceType.MOD_0);
    public static final OFDeviceBlock DEVICE_1 = new OFDeviceBlock(ofDevicePropertiesSupplier, OFDeviceType.MOD_1);
    public static final OFDeviceBlock DEVICE_2 = new OFDeviceBlock(ofDevicePropertiesSupplier, OFDeviceType.MOD_2);
}
