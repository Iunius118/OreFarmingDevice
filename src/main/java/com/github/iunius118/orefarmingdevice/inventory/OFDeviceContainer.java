package com.github.iunius118.orefarmingdevice.inventory;

import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.loot.OFDeviceLootCondition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OFDeviceContainer extends Container {
    private final IInventory container;
    private final IIntArray data;
    protected final World level;

    public OFDeviceContainer(int containerCounter, PlayerInventory playerInventory) {
        this(containerCounter, playerInventory, new Inventory(3), new IntArray(5));
    }

    public OFDeviceContainer(int containerCounter, PlayerInventory playerInventory, IInventory inventory, IIntArray dataAccess) {
        super(ModContainerTypes.DEVICE, containerCounter);
        this.container = inventory;
        this.data = dataAccess;
        this.level = playerInventory.player.level;
        this.addSlot(new Slot(inventory, 0, 108, 17));
        this.addSlot(new FuelSlot(this, inventory, 1, 56, 35));
        this.addSlot(new FurnaceResultSlot(playerInventory.player, inventory, 2, 108, 53));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
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

            if (slotIndex == 2) {
                if (!this.moveItemStackTo(srcStack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(srcStack, returnStack);
            } else if (slotIndex != 1 && slotIndex != 0) {
                if (this.canSmelt(srcStack)) {
                    if (!this.moveItemStackTo(srcStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(srcStack)) {
                    if (!this.moveItemStackTo(srcStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex < 30) {
                    if (!this.moveItemStackTo(srcStack, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex < 39 && !this.moveItemStackTo(srcStack, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(srcStack, 3, 39, false)) {
                return ItemStack.EMPTY;
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

    protected boolean canSmelt(ItemStack stack) {
        int i = this.data.get(4);
        OFDeviceLootCondition condition = OFDeviceLootCondition.fromInt(i);
        return ModLootTables.find(condition, stack).isPresent();
    }

    protected boolean isFuel(ItemStack stack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, IRecipeType.SMELTING) > 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnProgress() {
        int i = this.data.get(2);
        int j = this.data.get(3);
        return j != 0 && i != 0 ? i * 16 / j : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getLitProgress() {
        int i = this.data.get(1);

        if (i == 0) {
            i = 200;
        }

        return this.data.get(0) * 30 / i;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isLit() {
        return this.data.get(0) > 0;
    }

    private static class FuelSlot extends Slot {
        private final OFDeviceContainer menu;

        public FuelSlot(OFDeviceContainer ofDeviceContainer, IInventory inventory, int slotIndex, int x, int y) {
            super(inventory, slotIndex, x, y);
            this.menu = ofDeviceContainer;
        }

        public boolean mayPlace(ItemStack stack) {
            return this.menu.isFuel(stack) || isBucket(stack);
        }

        public int getMaxStackSize(ItemStack stack) {
            return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
        }

        public boolean isBucket(ItemStack stack) {
            return stack.getItem() == Items.BUCKET;
        }
    }
}
