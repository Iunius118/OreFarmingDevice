package com.github.iunius118.orefarmingfurnace.world.level.block.entity;

import com.github.iunius118.orefarmingfurnace.OreFarmingFurnace;
import net.minecraft.util.ResourceLocation;

public enum OFFDeviceType {
    MK_1("device_mk_1"),
    MK_2("device_mk_2"),
    MK_3("device_mk_3"),
    ;

    private final String name;

    private OFFDeviceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getContainerTranslationKey() {
        return "container.orefarmingfurnace." + name;
    }

    public ResourceLocation getID() {
        return new ResourceLocation(OreFarmingFurnace.MOD_ID, name);
    }
}
