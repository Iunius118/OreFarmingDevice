package com.github.iunius118.orefarmingdevice.inventory;

import net.minecraft.world.inventory.MenuType;

public class ModContainerTypes {
    public static final MenuType<OFDeviceContainer> DEVICE = new MenuType<>(OFDeviceContainer::new);
}
