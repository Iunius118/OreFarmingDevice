package com.github.iunius118.orefarmingdevice.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CobblestoneDeviceMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;
    protected final Level level;

    public CobblestoneDeviceMenu(int containerCounter, Inventory playerInventory) {
        this(containerCounter, playerInventory, new SimpleContainer(1), new SimpleContainerData(0));
    }

    public CobblestoneDeviceMenu(int containerCounter, Inventory playerInventory, Container inventory, ContainerData dataAccess) {
        super(ModMenuTypes.COBBLESTONE_DEVICE, containerCounter);
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
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
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
