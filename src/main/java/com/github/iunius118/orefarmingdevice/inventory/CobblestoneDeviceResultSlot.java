package com.github.iunius118.orefarmingdevice.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class CobblestoneDeviceResultSlot extends Slot {
    public CobblestoneDeviceResultSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack remove(int amount) {
        return super.remove(amount);
    }

    @Override
    public ItemStack onTake(PlayerEntity player, ItemStack stack) {
        super.onTake(player, stack);
        return stack;
    }
}
