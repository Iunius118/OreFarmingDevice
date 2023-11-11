package com.github.iunius118.orefarmingdevice.loot;

import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.function.Predicate;

public enum OFDeviceLootCondition {
    IS_MOD_0(isType(OFDeviceType.MOD_0)),
    IS_MOD_1(isType(OFDeviceType.MOD_1)),
    IS_MOD_2(isType(OFDeviceType.MOD_2)),
    NOT_APPLICABLE(c -> false),
    ;

    private final Predicate<OFDeviceBlockEntity> predicate;

    OFDeviceLootCondition(Predicate<OFDeviceBlockEntity> lootPredicate) {
        predicate = lootPredicate;
    }

    public boolean test(OFDeviceBlockEntity deviceCondition) {
        return predicate.test(deviceCondition);
    }

    public static OFDeviceLootCondition find(OFDeviceBlockEntity deviceCondition) {
        return Arrays.stream(values()).filter(c -> c.test(deviceCondition)).findFirst().orElse(NOT_APPLICABLE);
    }

    public int toInt() {
        return ordinal();
    }

    public static OFDeviceLootCondition fromInt(int i) {
        OFDeviceLootCondition[] values = values();
        return values[MathHelper.clamp(i, 0, values.length - 1)];
    }

    private static Predicate<OFDeviceBlockEntity> isType(OFDeviceType deviceType) {
        return device -> device.type == deviceType;
    }

    public Predicate<OFDeviceBlockEntity> isDeviceInShallowLayer() {
        return  device -> device.getBlockPos().getY() > 0;
    }

    public Predicate<OFDeviceBlockEntity> isDeviceInDeepLayer() {
        return device -> device.getBlockPos().getY() <= 0;
    }
}
