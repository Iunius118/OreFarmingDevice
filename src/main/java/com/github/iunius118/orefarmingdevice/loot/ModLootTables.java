package com.github.iunius118.orefarmingdevice.loot;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.config.OreFarmingDeviceConfig;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederItem;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.Optional;

public enum ModLootTables {
    DEVICE_0("device_0", Blocks.COBBLESTONE, OFDeviceLootCondition.IS_MOD_0),
    DEVICE_0_NETHER("device_0_n", Blocks.NETHERRACK, OFDeviceLootCondition.IS_MOD_0),
    DEVICE_0_FEED("device_0", ModItems.COBBLESTONE_FEEDER, OFDeviceLootCondition.IS_MOD_0),
    DEVICE_0_FEED_2("device_0", ModItems.COBBLESTONE_FEEDER_2, OFDeviceLootCondition.IS_MOD_0),

    DEVICE_1("device_1", Blocks.COBBLESTONE, OFDeviceLootCondition.IS_MOD_1),
    DEVICE_1_NETHER("device_1_n", Blocks.NETHERRACK, OFDeviceLootCondition.IS_MOD_1),
    DEVICE_1_FEED("device_1", ModItems.COBBLESTONE_FEEDER, OFDeviceLootCondition.IS_MOD_1),
    DEVICE_1_FEED_2("device_1", ModItems.COBBLESTONE_FEEDER_2, OFDeviceLootCondition.IS_MOD_1),

    DEVICE_2("device_2", Blocks.COBBLESTONE, OFDeviceLootCondition.IS_MOD_2),
    DEVICE_2_NETHER("device_2_n", Blocks.NETHERRACK, OFDeviceLootCondition.IS_MOD_2),
    DEVICE_2_FEED("device_2", ModItems.COBBLESTONE_FEEDER, OFDeviceLootCondition.IS_MOD_2),
    DEVICE_2_FEED_2("device_2", ModItems.COBBLESTONE_FEEDER_2, OFDeviceLootCondition.IS_MOD_2),
    ;

    private final ResourceLocation id;
    private final ItemStack material;
    private final OFDeviceLootCondition canProcess;

    ModLootTables(String key, IItemProvider item, OFDeviceLootCondition canProcess) {
        id = new ResourceLocation(OreFarmingDevice.MOD_ID, key);
        this.material = new ItemStack(item);
        this.canProcess = canProcess;
    }

    public static Optional<ModLootTables> find(OFDeviceBlockEntity device, ItemStack stack) {
        return Arrays.stream(values()).filter(t -> t.canProcess(device, stack)).findFirst();
    }

    public static Optional<ModLootTables> find(OFDeviceLootCondition lootCondition, ItemStack stack) {
        return Arrays.stream(values()).filter(t -> t.canProcess(lootCondition, stack)).findFirst();
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

        return canProcess.test(device) && material.sameItem(stack);
    }

    public boolean canProcess(OFDeviceLootCondition lootCondition, ItemStack stack) {
        if (material.getItem() instanceof CobblestoneFeederItem
                && !OreFarmingDeviceConfig.SERVER.isCobblestoneFeederAvailable()) {
            // OF Cobblestone Feeders are not available for OF Devices by config
            return false;
        }

        return lootCondition == canProcess && material.sameItem(stack);
    }
}
