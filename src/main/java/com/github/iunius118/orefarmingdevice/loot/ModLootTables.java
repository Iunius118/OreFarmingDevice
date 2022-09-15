package com.github.iunius118.orefarmingdevice.loot;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.config.OreFarmingDeviceConfig;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Function;

public enum ModLootTables {
    DEVICE_0("device_0", Blocks.COBBLESTONE, OFDeviceType.MOD_0::contains),
    DEVICE_0_DEEP("device_0_d", Blocks.COBBLED_DEEPSLATE, OFDeviceType.MOD_0::contains),
    DEVICE_0_NETHER("device_0_n", Blocks.NETHERRACK, OFDeviceType.MOD_0::contains),
    DEVICE_0_FEED("device_0", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_0::isDeviceInShallowLayer),
    DEVICE_0_FEED_DEEP("device_0_d", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_0::isDeviceInDeepLayer),

    DEVICE_1("device_1", Blocks.COBBLESTONE, OFDeviceType.MOD_1::contains),
    DEVICE_1_DEEP("device_1_d", Blocks.COBBLED_DEEPSLATE, OFDeviceType.MOD_1::contains),
    DEVICE_1_NETHER("device_1_n", Blocks.NETHERRACK, OFDeviceType.MOD_1::contains),
    DEVICE_1_FEED("device_1", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_1::isDeviceInShallowLayer),
    DEVICE_1_FEED_DEEP("device_1_d", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_1::isDeviceInDeepLayer),

    DEVICE_2("device_2", Blocks.COBBLESTONE, OFDeviceType.MOD_2::contains),
    DEVICE_2_DEEP("device_2_d", Blocks.COBBLED_DEEPSLATE, OFDeviceType.MOD_2::contains),
    DEVICE_2_NETHER("device_2_n", Blocks.NETHERRACK, OFDeviceType.MOD_2::contains),
    DEVICE_2_FEED("device_2", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_2::isDeviceInShallowLayer),
    DEVICE_2_FEED_DEEP("device_2_d", ModItems.COBBLESTONE_FEEDER, OFDeviceType.MOD_2::isDeviceInDeepLayer),
    ;

    private final ResourceLocation id;
    private final ItemStack material;
    private final Function<OFDeviceBlockEntity, Boolean> canProcess;

    ModLootTables(String key, ItemLike item, Function<OFDeviceBlockEntity, Boolean> canProcess) {
        id = new ResourceLocation(OreFarmingDevice.MOD_ID, key);
        this.material = new ItemStack(item);
        this.canProcess = canProcess;
    }

    public ResourceLocation getId() {
        return id;
    }

    public boolean canProcess(OFDeviceBlockEntity device, ItemStack stack) {
        if (material.sameItem(new ItemStack(ModItems.COBBLESTONE_FEEDER))
                && !OreFarmingDeviceConfig.SERVER.isCobblestoneFeederAvailable()) {
            // OF Cobblestone Feeder is not available for OF Devices by config
            return false;
        }

        return canProcess.apply(device) && material.sameItem(stack);
    }
}
