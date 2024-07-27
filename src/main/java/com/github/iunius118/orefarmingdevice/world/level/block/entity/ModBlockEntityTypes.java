package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypes {
    public static final BlockEntityType<OFDeviceBlockEntity> DEVICE_0 = BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_0), ModBlocks.DEVICE_0).build(null);
    public static final BlockEntityType<OFDeviceBlockEntity> DEVICE_1 = BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_1), ModBlocks.DEVICE_1).build(null);
    public static final BlockEntityType<OFDeviceBlockEntity> DEVICE_2 = BlockEntityType.Builder.of((pos, state) -> new OFDeviceBlockEntity(pos, state, OFDeviceType.MOD_2), ModBlocks.DEVICE_2).build(null);
    public static final BlockEntityType<CobblestoneDeviceBlockEntity> COBBLESTONE_DEVICE_0 = BlockEntityType.Builder.of((pos, state) -> new CobblestoneDeviceBlockEntity(pos, state, CobblestoneDeviceType.BASIC), ModBlocks.COBBLESTONE_DEVICE_0).build(null);
}
