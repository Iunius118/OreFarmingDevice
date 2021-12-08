package com.github.iunius118.orefarmingdevice.loot;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Function;

public enum ModLootTables {
    DEVICE_0("device_0", Blocks.COBBLESTONE, OFDeviceType.MOD_0::equals),
    DEVICE_0_NETHER("device_0_n", Blocks.NETHERRACK, OFDeviceType.MOD_0::equals),

    DEVICE_1("device_1", Blocks.COBBLESTONE, OFDeviceType.MOD_1::equals),
    DEVICE_1_NETHER("device_1_n", Blocks.NETHERRACK, OFDeviceType.MOD_1::equals),

    DEVICE_2("device_2", Blocks.COBBLESTONE, OFDeviceType.MOD_2::equals),
    DEVICE_2_NETHER("device_2_n", Blocks.NETHERRACK, OFDeviceType.MOD_2::equals),
    ;

    private final ResourceLocation id;
    private final ItemStack material;
    private final Function<OFDeviceType, Boolean> canProcess;

    ModLootTables(String key, ItemLike item, Function<OFDeviceType, Boolean> canProcess) {
        id = new ResourceLocation(OreFarmingDevice.MOD_ID, key);
        this.material = new ItemStack(item);
        this.canProcess = canProcess;
    }

    public ResourceLocation getID() {
        return id;
    }

    public boolean canProcess(OFDeviceType type, ItemStack stack) {
        return canProcess.apply(type) && material.sameItem(stack);
    }
}
