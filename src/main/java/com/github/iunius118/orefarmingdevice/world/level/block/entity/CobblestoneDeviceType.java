package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import net.minecraft.resources.ResourceLocation;

public enum CobblestoneDeviceType {
    BASIC("cobblestone_device_0", 8),
    ;

    private final String name;
    private final int intervalTicks;

    CobblestoneDeviceType(String name, int intervalTicks) {
        this.name = name;
        this.intervalTicks = intervalTicks;
    }

    public String getName() {
        return name;
    }

    public int getIntervalTicks() {
        return intervalTicks;
    }

    public String getContainerTranslationKey() {
        return "container." + OreFarmingDevice.MOD_ID + "." + name;
    }

    public ResourceLocation getId() {
        return new ResourceLocation(OreFarmingDevice.MOD_ID, name);
    }
}
