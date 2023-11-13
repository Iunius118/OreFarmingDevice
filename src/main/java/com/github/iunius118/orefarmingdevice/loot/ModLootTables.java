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

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum ModLootTables {
    DEVICE_0("device_0", Blocks.COBBLESTONE, OFDeviceLootCondition.is(OFDeviceType.MOD_0)),
    DEVICE_0_NETHER("device_0_n", Blocks.NETHERRACK, OFDeviceLootCondition.is(OFDeviceType.MOD_0)),
    DEVICE_0_FEED("device_0", ModItems.COBBLESTONE_FEEDER, OFDeviceLootCondition.is(OFDeviceType.MOD_0)),
    DEVICE_0_FEED_2("device_0", ModItems.COBBLESTONE_FEEDER_2, OFDeviceLootCondition.is(OFDeviceType.MOD_0)),

    DEVICE_1("device_1", Blocks.COBBLESTONE, OFDeviceLootCondition.is(OFDeviceType.MOD_1)),
    DEVICE_1_NETHER("device_1_n", Blocks.NETHERRACK, OFDeviceLootCondition.is(OFDeviceType.MOD_1)),
    DEVICE_1_FEED("device_1", ModItems.COBBLESTONE_FEEDER, OFDeviceLootCondition.is(OFDeviceType.MOD_1)),
    DEVICE_1_FEED_2("device_1", ModItems.COBBLESTONE_FEEDER_2, OFDeviceLootCondition.is(OFDeviceType.MOD_1)),

    DEVICE_2("device_2", Blocks.COBBLESTONE, OFDeviceLootCondition.is(OFDeviceType.MOD_2)),
    DEVICE_2_NETHER("device_2_n", Blocks.NETHERRACK, OFDeviceLootCondition.is(OFDeviceType.MOD_2)),
    DEVICE_2_FEED("device_2", ModItems.COBBLESTONE_FEEDER, OFDeviceLootCondition.is(OFDeviceType.MOD_2)),
    DEVICE_2_FEED_2("device_2", ModItems.COBBLESTONE_FEEDER_2, OFDeviceLootCondition.is(OFDeviceType.MOD_2)),
    ;

    private final ResourceLocation id;
    private final ItemStack material;
    private final Predicate<OFDeviceLootCondition> canProcess;

    ModLootTables(String key, IItemProvider item, Predicate<OFDeviceLootCondition> canProcess) {
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

        OFDeviceLootCondition lootCondition = OFDeviceLootCondition.find(device);
        return canProcess.test(lootCondition) && material.sameItem(stack);
    }

    public boolean canProcess(OFDeviceLootCondition lootCondition, ItemStack stack) {
        if (material.getItem() instanceof CobblestoneFeederItem
                && !OreFarmingDeviceConfig.SERVER.isCobblestoneFeederAvailable()) {
            // OF Cobblestone Feeders are not available for OF Devices by config
            return false;
        }

        return canProcess.test(lootCondition) && material.sameItem(stack);
    }
}
