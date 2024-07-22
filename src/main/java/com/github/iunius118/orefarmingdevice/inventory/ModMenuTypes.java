package com.github.iunius118.orefarmingdevice.inventory;

import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<OFDeviceMenu> DEVICE = new MenuType<>(OFDeviceMenu::new);
    public static final MenuType<CobblestoneDeviceMenu> COBBLESTONE_DEVICE = new MenuType<>(CobblestoneDeviceMenu::new);
}
