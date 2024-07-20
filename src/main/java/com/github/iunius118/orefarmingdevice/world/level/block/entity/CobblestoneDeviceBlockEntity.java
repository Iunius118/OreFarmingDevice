package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.inventory.CobblestoneDeviceContainer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class CobblestoneDeviceBlockEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {
    private static final int[] SLOTS_FOR_DOWN = new int[]{0};
    private static final int CONTAINER_SIZE = 1;
    public static final int SLOT_RESULT = 0;
    protected NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    public int intervalTime;
    public final CobblestoneDeviceType type;
    protected final IIntArray dataAccess = new IntArray(0);

    protected CobblestoneDeviceBlockEntity(TileEntityType<?> blockEntityType, CobblestoneDeviceType feederType) {
        super(blockEntityType);
        type = feederType;
    }

    public CobblestoneDeviceBlockEntity(CobblestoneDeviceType type) {
        this(getBlockEntityType(type), type);
    }

    public static TileEntityType<?> getBlockEntityType(CobblestoneDeviceType type) {
        if (type == CobblestoneDeviceType.BASIC) {
            return ModBlockEntityTypes.COBBLESTONE_DEVICE_0;
        }

        return null;
    }

    public int getIntervalTicks() {
        return type.getIntervalTicks();
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, items);
        intervalTime = nbt.getInt("IntervalTime");
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        nbt.putInt("IntervalTime", intervalTime);
        ItemStackHelper.saveAllItems(nbt, items);
        return nbt;
    }

    /* ITickableTileEntity */

    @Override
    public void tick() {
        if (++intervalTime >= getIntervalTicks()) {
            intervalTime = 0;

            if (level == null || level.isClientSide) {
                return;
            }

            // Server side
            ItemStack productSlotStack = items.get(SLOT_RESULT);
            ItemStack productStack = getProductStack();

            if (productSlotStack.isEmpty()) {
                // Insert product item stack to empty product slot
                items.set(SLOT_RESULT, productStack);
            } else if (productSlotStack.sameItem(productStack)
                    && (productSlotStack.getCount() + productStack.getCount() <= Math.min(getMaxStackSize(), productSlotStack.getMaxStackSize()))) {
                // Add same product item to item stack in product slot
                productSlotStack.grow(productStack.getCount());
            } else {
                // When the result slot stack is full, do nothing.
            }
        }
    }

    private ItemStack getProductStack() {
        return new ItemStack(Blocks.COBBLESTONE);
    }

    /* ISidedInventory */

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return direction == Direction.DOWN ? SLOTS_FOR_DOWN : new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    /* LockableTileEntity */

    @Override
    protected ITextComponent getDefaultName() {
        String translationKey = type.getContainerTranslationKey();
        return new TranslationTextComponent(translationKey);
    }

    @Override
    protected Container createMenu(int containerId, PlayerInventory playerInventory) {
        return new CobblestoneDeviceContainer(containerId, playerInventory, this, dataAccess);
    }

    /* IInventory */

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return index >= 0 && index < items.size() ? items.get(index) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int index, int amount) {
        return ItemStackHelper.removeItem(items, index, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(items, index);
    }

    @Override
    public void setItem(int index, ItemStack newStack) {
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        if (level.getBlockEntity(worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double) worldPosition.getX() + 0.5D, (double) worldPosition.getY() + 0.5D, (double) worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return false;
    }

    /* IClearable */

    @Override
    public void clearContent() {
        items.clear();
    }
}
