package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import net.minecraft.util.ResourceLocation;

public enum OFDeviceType {
    MK_1("device_mk_1"),
    MK_2("device_mk_2"),
    MK_3("device_mk_3"),
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
