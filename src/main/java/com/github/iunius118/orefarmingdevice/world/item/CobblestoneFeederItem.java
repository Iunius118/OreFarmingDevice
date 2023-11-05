package com.github.iunius118.orefarmingdevice.world.item;

import net.minecraft.item.Item;

public class CobblestoneFeederItem extends Item {
    public final CobblestoneFeederType type;

    public CobblestoneFeederItem(CobblestoneFeederType cobblestoneFeederType, Properties properties) {
        super(properties);
        type = cobblestoneFeederType;
    }
}
