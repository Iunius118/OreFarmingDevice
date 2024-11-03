package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.world.level.block.ModBlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public class ModBlockEntityTypes {
    public static final BlockEntityType<OFDeviceBlockEntity> DEVICE_0 = new BlockEntityType<>((p, s) -> new OFDeviceBlockEntity(p, s, OFDeviceType.MOD_0), Set.of(ModBlockRegistry.DEVICE_0.get()));
    public static final BlockEntityType<OFDeviceBlockEntity> DEVICE_1 = new BlockEntityType<>((p, s) -> new OFDeviceBlockEntity(p, s, OFDeviceType.MOD_1), Set.of(ModBlockRegistry.DEVICE_1.get()));
    public static final BlockEntityType<OFDeviceBlockEntity> DEVICE_2 = new BlockEntityType<>((p, s) -> new OFDeviceBlockEntity(p, s, OFDeviceType.MOD_2), Set.of(ModBlockRegistry.DEVICE_2.get()));
    public static final BlockEntityType<CobblestoneDeviceBlockEntity> COBBLESTONE_DEVICE_0 = new BlockEntityType<>((p, s) -> new CobblestoneDeviceBlockEntity(p, s, CobblestoneDeviceType.BASIC), Set.of(ModBlockRegistry.COBBLESTONE_DEVICE_0.get()));
}
