package com.github.iunius118.orefarmingdevice.loot;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.config.OreFarmingDeviceConfig;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederItem;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public enum ModLootTables {
    DEVICE_0("device_0", Blocks.COBBLESTONE, OFDeviceType.MOD_0::contains),
    DEVICE_0_NETHER("device_0_n", Blocks.NETHERRACK, OFDeviceType.MOD_0::contains),
    DEVICE_0_FEED("device_0", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_0::contains),
    DEVICE_0_FEED_2("device_0", ModItems.COBBLESTONE_FEEDER_2, OFDeviceType.MOD_0::contains),

    DEVICE_1("device_1", Blocks.COBBLESTONE, OFDeviceType.MOD_1::contains),
    DEVICE_1_NETHER("device_1_n", Blocks.NETHERRACK, OFDeviceType.MOD_1::contains),
    DEVICE_1_FEED("device_1", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_1::contains),
    DEVICE_1_FEED_2("device_1", ModItems.COBBLESTONE_FEEDER_2, OFDeviceType.MOD_1::contains),

    DEVICE_2("device_2", Blocks.COBBLESTONE, OFDeviceType.MOD_2::contains),
    DEVICE_2_NETHER("device_2_n", Blocks.NETHERRACK, OFDeviceType.MOD_2::contains),
    DEVICE_2_FEED("device_2", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_2::contains),
    DEVICE_2_FEED_2("device_2", ModItems.COBBLESTONE_FEEDER_2, OFDeviceType.MOD_2::contains),
    ;

    private final ResourceLocation id;
    private final ItemStack material;
    private final Function<OFDeviceBlockEntity, Boolean> canProcess;

    ModLootTables(String key, IItemProvider item, Function<OFDeviceBlockEntity, Boolean> canProcess) {
        id = new ResourceLocation(OreFarmingDevice.MOD_ID, key);
        this.material = new ItemStack(item);
        this.canProcess = canProcess;
    }

    public ResourceLocation getId() {
        return id;
    }

    public boolean canProcess(OFDeviceBlockEntity device, ItemStack stack) {
        if (material.getItem() instanceof CobblestoneFeederItem
                && !OreFarmingDeviceConfig.SERVER.isCobblestoneFeederAvailable()) {
            // OF Cobblestone Feeders are not available for OF Devices by config
            return false;
        }

        return canProcess.apply(device) && material.sameItem(stack);
    }
}
