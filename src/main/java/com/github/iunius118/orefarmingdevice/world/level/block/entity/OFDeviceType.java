package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import net.minecraft.resources.ResourceLocation;

public enum OFDeviceType {
    MOD_0("device_0"),
    MOD_1("device_1"),
    MOD_2("device_2"),
    ;

    private final String name;

    OFDeviceType(String name) {
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

    public boolean contains(OFDeviceBlockEntity device) {
        return device != null && device.type == this;
    }

    public boolean isDeviceInShallowLayer(OFDeviceBlockEntity device) {
        return contains(device) && device.getBlockPos().getY() > 0;
    }

    public boolean isDeviceInDeepLayer(OFDeviceBlockEntity device) {
        return contains(device) && device.getBlockPos().getY() <= 0;
    }
}
