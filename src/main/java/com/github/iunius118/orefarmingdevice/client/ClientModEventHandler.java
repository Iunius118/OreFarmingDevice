package com.github.iunius118.orefarmingdevice.client;

import com.github.iunius118.orefarmingdevice.client.gui.OFDeviceScreen;
import com.github.iunius118.orefarmingdevice.inventory.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientModEventHandler {
    public static void setup(FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.DEVICE, OFDeviceScreen::new);
    }
}
