package com.github.iunius118.orefarmingdevice.state.properties;

import net.minecraft.util.StringRepresentable;

public enum OFDeviceCasing implements StringRepresentable {
    NORMAL("normal"),
    STONE("stone"),
    LOG("log"),
    COAL("coal"),
    IRON("iron"),
    COPPER("copper"),
    MACHINE("machine"),
    ;

    private final String name;

    OFDeviceCasing(String typeName) {
        name = typeName;
    }

    public String toString() {
        return name;
    }

    public OFDeviceCasing getNext() {
        final int nextIndex = this.ordinal() + 1;
        OFDeviceCasing[] values = OFDeviceCasing.values();
        return nextIndex < values.length ? values[nextIndex] : values[0] ;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
