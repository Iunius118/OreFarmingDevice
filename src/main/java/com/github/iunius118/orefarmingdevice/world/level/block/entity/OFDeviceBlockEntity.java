package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.Arrays;
import java.util.List;

public class OFDeviceBlockEntity extends AbstractFurnaceTileEntity {
    public final OFDeviceType type;
    public final String containerTranslationKey;

    public OFDeviceBlockEntity(TileEntityType<?> blockEntityType, OFDeviceType ofDeviceType) {
        super(blockEntityType, IRecipeType.SMELTING);
        type = ofDeviceType;
        containerTranslationKey = ofDeviceType.getContainerTranslationKey();
    }

    public OFDeviceBlockEntity(OFDeviceType type) {
        this(getBlockEntityType(type), type);
    }

    public static TileEntityType<?> getBlockEntityType(OFDeviceType type) {
        switch(type) {
            case MOD_0:
                return ModBlockEntityTypes.DEVICE_0;
            case MOD_1:
                return ModBlockEntityTypes.DEVICE_1;
            case MOD_2:
                return ModBlockEntityTypes.DEVICE_2;
        }

        return null;
    }

    @Override
    public void tick() {
        boolean isLitOld = isLit();
        boolean hasChanged = false;

        if (isLitOld) {
            --litTime;
        }

        if (!level.isClientSide) {
            ItemStack fuelStack = items.get(1);
            ItemStack materialStack = items.get(0);

            if ((isLit() || !fuelStack.isEmpty()) && !materialStack.isEmpty()) {
                ModLootTables lootTableID = findLootTable(materialStack);
                boolean canProcess = canProcess(lootTableID);

                if (!isLit() && canProcess) {
                    // Burn new fuel stack
                    litTime = getBurnDuration(fuelStack);
                    litDuration = litTime;

                    if (isLit()) {
                        // Handle fuel
                        hasChanged = true;

                        if (fuelStack.hasContainerItem()) {
                            items.set(1, fuelStack.getContainerItem());
                        } else if (!fuelStack.isEmpty()) {
                            fuelStack.shrink(1);

                            if (fuelStack.isEmpty()) {
                                items.set(1, fuelStack.getContainerItem());
                            }
                        }
                    }
                }

                if (isLit() && canProcess) {
                    // Handle material stack
                    ++cookingProgress;

                    if (cookingProgress == cookingTotalTime) {
                        // Process completion
                        cookingProgress = 0;
                        cookingTotalTime = getTotalCookTime();
                        process(lootTableID);
                        hasChanged = true;
                    }
                } else {
                    cookingProgress = 0;
                }
            } else if (!isLit() && cookingProgress > 0) {
                cookingProgress = MathHelper.clamp(cookingProgress - 2, 0, cookingTotalTime);
            }

            if (isLitOld != isLit()) {
                // Switch on/off LIT of Device block
                level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(AbstractFurnaceBlock.LIT, isLit()), 3);
                hasChanged = true;
            }
        }

        if (hasChanged) {
            setChanged();
        }
    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    private ModLootTables findLootTable(ItemStack stack) {
        return Arrays.stream(ModLootTables.values()).filter(t -> t.canProcess(type, stack)).findFirst().orElse(null);
    }

    private boolean canProcess(ModLootTables lootTableID) {
        return !items.get(0).isEmpty() && lootTableID != null;
    }

    private void process(ModLootTables lootTableID) {
        if (!level.isClientSide && canProcess(lootTableID)) {
            // Server side
            ItemStack materialStack = this.items.get(0);
            LootTable lootTable = level.getServer().getLootTables().get(lootTableID.getID());
            List<ItemStack> items = lootTable.getRandomItems(new LootContext.Builder((ServerWorld) level).withRandom(level.random).create(LootParameterSets.EMPTY));
            ItemStack productStack = items.size() > 0 ? items.get(0) : ItemStack.EMPTY;
            insertToProductSlot(productStack);
            materialStack.shrink(1);
        }
    }

    private void insertToProductSlot(ItemStack productStack) {
        if (productStack.isEmpty()) return;

        ItemStack productSlotStack = this.items.get(2);

        if (productSlotStack.isEmpty()) {
            this.items.set(2, productStack.copy());
        } else if (productSlotStack.sameItem(productStack)) {
            if (productSlotStack.getCount() + productStack.getCount() <= Math.min(getMaxStackSize(), productSlotStack.getMaxStackSize())) {
                productSlotStack.grow(productStack.getCount());
            } else {
                replaceProductStack(productStack.copy());
            }
        } else {
            replaceProductStack(productStack.copy());
        }
    }

    private void replaceProductStack(ItemStack newStack) {
        BlockPos pos = getBlockPos();
        ItemStack productSlotStack = this.items.get(2);
        level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, productSlotStack));
        this.items.set(2, newStack);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent(containerTranslationKey);
    }

    @Override
    protected Container createMenu(int containerId, PlayerInventory playerInventory) {
        return new OFDeviceContainer(containerId, playerInventory, this, dataAccess);
    }
}
