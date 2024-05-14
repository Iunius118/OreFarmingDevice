package com.github.iunius118.orefarmingdevice.world.level.block;

import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class ModBlocks {
    private static final Supplier<BlockBehaviour.Properties> devicePropertiesSupplier = () -> BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F);

    public static final OFDeviceBlock DEVICE_0 = new OFDeviceBlock(devicePropertiesSupplier.get(), OFDeviceType.MOD_0);
    public static final OFDeviceBlock DEVICE_1 = new OFDeviceBlock(devicePropertiesSupplier.get(), OFDeviceType.MOD_1);
    public static final OFDeviceBlock DEVICE_2 = new OFDeviceBlock(devicePropertiesSupplier.get(), OFDeviceType.MOD_2);
}
