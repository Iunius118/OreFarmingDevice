package com.github.iunius118.orefarmingdevice.world.item;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModItemGroups {
    public static final CreativeModeTab MAIN = new CreativeModeTab(OreFarmingDevice.MOD_ID) {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.DEVICE_2);
        }
    };
}
