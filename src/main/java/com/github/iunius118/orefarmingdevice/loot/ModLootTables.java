package com.github.iunius118.orefarmingdevice.loot;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import net.minecraft.util.ResourceLocation;

public enum ModLootTables {
    DEVICE_0("device_0"),
    DEVICE_0_NETHER("device_0_n"),

    DEVICE_1("device_1"),
    DEVICE_1_NETHER("device_1_n"),

    DEVICE_2("device_2"),
    DEVICE_2_NETHER("device_2_n"),
    ;

    private final ResourceLocation id;

    ModLootTables(String key) {
        id = new ResourceLocation(OreFarmingDevice.MOD_ID, key);
    }

    public ResourceLocation getID() {
        return id;
    }
}
