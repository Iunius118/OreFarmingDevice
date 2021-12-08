package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import net.minecraft.resources.ResourceLocation;

public enum OFDeviceType {
    MOD_0("device_0"),
    MOD_1("device_1"),
    MOD_2("device_2"),
    ;

    private final String name;

    private OFDeviceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getContainerTranslationKey() {
        return "container." + OreFarmingDevice.MOD_ID + "." + name;
    }

    public ResourceLocation getID() {
        return new ResourceLocation(OreFarmingDevice.MOD_ID, name);
    }
}
