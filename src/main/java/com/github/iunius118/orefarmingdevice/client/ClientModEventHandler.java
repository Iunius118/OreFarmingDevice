package com.github.iunius118.orefarmingdevice.client;

import com.github.iunius118.orefarmingdevice.client.gui.CobblestoneDeviceScreen;
import com.github.iunius118.orefarmingdevice.client.gui.OFDeviceScreen;
import com.github.iunius118.orefarmingdevice.inventory.ModMenuTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class ClientModEventHandler {
    @SubscribeEvent
    public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.DEVICE, OFDeviceScreen::new);
        event.register(ModMenuTypes.COBBLESTONE_DEVICE, CobblestoneDeviceScreen::new);
    }
}
