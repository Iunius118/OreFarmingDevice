package com.github.iunius118.orefarmingdevice.loot;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public enum ModLootTables {
    DEVICE_0("device_0", Blocks.COBBLESTONE.asItem(), t -> t == OFDeviceType.MOD_0),
    DEVICE_0_NETHER("device_0_n", Blocks.NETHERRACK.asItem(), t -> t == OFDeviceType.MOD_0),

    DEVICE_1("device_1", Blocks.COBBLESTONE.asItem(), t -> t == OFDeviceType.MOD_1),
    DEVICE_1_NETHER("device_1_n", Blocks.NETHERRACK.asItem(), t -> t == OFDeviceType.MOD_1),

    DEVICE_2("device_2", Blocks.COBBLESTONE.asItem(), t -> t == OFDeviceType.MOD_2),
    DEVICE_2_NETHER("device_2_n", Blocks.NETHERRACK.asItem(), t -> t == OFDeviceType.MOD_2),
    ;

    private final ResourceLocation id;
    private final Item item;
    private final Function<OFDeviceType, Boolean> canProcess;

    ModLootTables(String key, Item item, Function<OFDeviceType, Boolean> canProcess) {
        id = new ResourceLocation(OreFarmingDevice.MOD_ID, key);
        this.item = item;
        this.canProcess = canProcess;
    }

    public ResourceLocation getID() {
        return id;
    }

    public boolean canProcess(OFDeviceType type, ItemStack stack) {
        return canProcess.apply(type) && stack.getItem() == item ;
    }
}
