package com.github.iunius118.orefarmingdevice.client;

import com.github.iunius118.orefarmingdevice.client.gui.OFDeviceScreen;
import com.github.iunius118.orefarmingdevice.common.ServerProxy;
import com.github.iunius118.orefarmingdevice.inventory.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy extends ServerProxy {
    @Override
    public void setup(FMLCommonSetupEvent event) {
        MenuScreens.register(ModMenuTypes.DEVICE, OFDeviceScreen::new);
    }
}
