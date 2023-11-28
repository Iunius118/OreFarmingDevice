package com.github.iunius118.orefarmingdevice.world.item;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.data.ModLanguageProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;

public class ModCreativeModeTabs {
    public static CreativeModeTab MAIN;

    public static void onCreativeModeTabRegister(CreativeModeTabEvent.Register event) {
        MAIN = event.registerCreativeModeTab(new ResourceLocation(OreFarmingDevice.MOD_ID, "main"),
                builder -> builder.icon(() -> new ItemStack(ModItems.DEVICE_2))
                        .title(Component.translatable(ModLanguageProvider.MOD_ITEM_GROUP_KEY))
                        .displayItems((params, output) -> {
                            output.accept(ModItems.DEVICE_0);
                            output.accept(ModItems.DEVICE_1);
                            output.accept(ModItems.DEVICE_2);
                            output.accept(ModItems.COBBLESTONE_FEEDER);
                            output.accept(ModItems.COBBLESTONE_FEEDER_2);
                        })
        );
    }
}
