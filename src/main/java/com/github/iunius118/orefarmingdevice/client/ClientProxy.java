package com.github.iunius118.orefarmingdevice.client;

import com.github.iunius118.orefarmingdevice.client.gui.CobblestoneDeviceScreen;
import com.github.iunius118.orefarmingdevice.client.gui.OFDeviceScreen;
import com.github.iunius118.orefarmingdevice.common.ServerProxy;
import com.github.iunius118.orefarmingdevice.inventory.ModContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy extends ServerProxy {
    @Override
    public void setup(FMLCommonSetupEvent event) {
        ScreenManager.register(ModContainerTypes.DEVICE, OFDeviceScreen::new);
        ScreenManager.register(ModContainerTypes.COBBLESTONE_DEVICE, CobblestoneDeviceScreen::new);
    }
}
