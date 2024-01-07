package com.github.iunius118.orefarmingdevice.world.item.crafting;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import net.minecraft.item.crafting.IRecipeType;

public class ModRecipeTypes {
    public static final IRecipeType<DeviceRecipe> DEVICE = IRecipeType.register(OreFarmingDevice.MOD_ID + ":device");
}
