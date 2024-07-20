package com.github.iunius118.orefarmingdevice.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;

public class CobblestoneDeviceContainer extends Container {
    private final IInventory container;
    private final IIntArray data;
    protected final World level;

    public CobblestoneDeviceContainer(int containerCounter, PlayerInventory playerInventory) {
        this(containerCounter, playerInventory, new Inventory(1), new IntArray(0));
    }

    public CobblestoneDeviceContainer(int containerCounter, PlayerInventory playerInventory, IInventory inventory, IIntArray dataAccess) {
        super(ModContainerTypes.COBBLESTONE_DEVICE, containerCounter);
        this.container = inventory;
        this.data = dataAccess;
        this.level = playerInventory.player.level;
        this.addSlot(new CobblestoneDeviceResultSlot(inventory, 0, 80, 20));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 109));
        }

        this.addDataSlots(dataAccess);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int slotIndex) {
        ItemStack returnStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasItem()) {
            ItemStack srcStack = slot.getItem();
            returnStack = srcStack.copy();

            if (slotIndex == 0) {
                if (!this.moveItemStackTo(srcStack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(srcStack, returnStack);
            } else {
                if (slotIndex < 28) {
                    if (!this.moveItemStackTo(srcStack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex < 37 && !this.moveItemStackTo(srcStack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (srcStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (srcStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, srcStack);
        }

        return returnStack;
    }
}
