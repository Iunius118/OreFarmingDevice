package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.config.OreFarmingDeviceConfig;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceMenu;
import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.AABB;

import java.util.Arrays;
import java.util.List;

public class OFDeviceBlockEntity extends AbstractFurnaceBlockEntity {
    public static final String KEY_EFFICIENCY = "Efficiency";
    public static final float MAX_EFFICIENCY = 3F;
    public final OFDeviceType type;

    private float farmingEfficiency = 0F;

    public OFDeviceBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, OFDeviceType ofDeviceType) {
        super(blockEntityType, blockPos, blockState, RecipeType.SMELTING);
        type = ofDeviceType;
    }

    public OFDeviceBlockEntity(BlockPos blockPos, BlockState blockState, OFDeviceType type) {
        this(getBlockEntityType(type), blockPos, blockState, type);
    }

    public static BlockEntityType<OFDeviceBlockEntity> getBlockEntityType(OFDeviceType type) {
        return switch (type) {
            case MOD_0 -> ModBlockEntityTypes.DEVICE_0;
            case MOD_1 -> ModBlockEntityTypes.DEVICE_1;
            case MOD_2 -> ModBlockEntityTypes.DEVICE_2;
        };

    }

    public int getTotalProcessingTime() {
        return OreFarmingDeviceConfig.SERVER.canAccelerateProcessingSpeedByMod()
                ? type.getTotalProcessingTime()
                : OFDeviceType.MOD_0.getTotalProcessingTime();
    }

    public int getFuelConsumption() {
        return OreFarmingDeviceConfig.SERVER.canIncreaseFuelConsumptionByMod()
                ? type.getFuelConsumption()
                : OFDeviceType.MOD_0.getFuelConsumption();
    }

    public float getFarmingEfficiency() {
        return OreFarmingDeviceConfig.SERVER.isFarmingEfficiencyEnabled()
                ? farmingEfficiency
                : 0F;
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        farmingEfficiency = Mth.clamp(compoundTag.getFloat(KEY_EFFICIENCY), 0F, MAX_EFFICIENCY);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putFloat(KEY_EFFICIENCY, Mth.clamp(farmingEfficiency, 0F, MAX_EFFICIENCY));
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, OFDeviceBlockEntity device) {
        boolean isLitOld = device.isLit();
        boolean hasChanged = false;

        if (isLitOld) {
            device.litTime -= device.getFuelConsumption();
        }

        ItemStack fuelStack = device.items.get(1);
        ItemStack materialStack = device.items.get(0);

        if ((device.isLit() || !fuelStack.isEmpty()) && !materialStack.isEmpty()) {
            ModLootTables productLootTable = device.findLootTable(materialStack);
            boolean canProcess = device.canProcess(productLootTable);

            if (!device.isLit() && canProcess) {
                // Burn new fuel stack
                device.litTime = device.getBurnDuration(fuelStack);
                device.litDuration = device.litTime;

                if (device.isLit()) {
                    // Handle fuel
                    hasChanged = true;

                    if (fuelStack.hasCraftingRemainingItem()) {
                        device.items.set(1, fuelStack.getCraftingRemainingItem());
                    } else if (!fuelStack.isEmpty()) {
                        fuelStack.shrink(1);

                        if (fuelStack.isEmpty()) {
                            device.items.set(1, fuelStack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (device.isLit() && device.litTime == device.litDuration) {
                // When device is refueled
                device.updateFarmingEfficiency(level, blockPos);
                device.cookingTotalTime = device.getTotalProcessingTime();
            }

            if (device.isLit() && canProcess) {
                // Handle material stack
                ++device.cookingProgress;

                if (device.cookingProgress == device.cookingTotalTime) {
                    // Process completion
                    device.cookingProgress = 0;
                    device.cookingTotalTime = device.getTotalProcessingTime();
                    device.process(productLootTable);
                    hasChanged = true;
                }
            } else {
                device.cookingProgress = 0;
            }
        } else if (!device.isLit() && device.cookingProgress > 0) {
            // Fire went out before the process was complete
            device.cookingProgress = Mth.clamp(device.cookingProgress - 2, 0, device.cookingTotalTime);
        }

        if (isLitOld != device.isLit()) {
            // Switch on/off LIT of Device block
            level.setBlock(device.worldPosition, level.getBlockState(device.worldPosition).setValue(AbstractFurnaceBlock.LIT, device.isLit()), 3);
            hasChanged = true;
        }

        if (hasChanged) {
            device.setChanged();
        }
    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    public void updateFarmingEfficiency(Level level, BlockPos blockPos) {
        AABB aabb = new AABB(blockPos).inflate(2.0, 1.0, 2.0);
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, aabb);
        int size = entities.size();
        farmingEfficiency = Mth.clamp(size, 0F, MAX_EFFICIENCY);
    }

    public ModLootTables findLootTable(ItemStack stack) {
        return Arrays.stream(ModLootTables.values()).filter(t -> t.canProcess(this, stack)).findFirst().orElse(null);
    }

    private boolean canProcess(ModLootTables lootTableID) {
        return !items.get(0).isEmpty() && lootTableID != null;
    }

    private void process(ModLootTables productLootTable) {
        if (canProcess(productLootTable)) {
            ItemStack materialStack = items.get(0);
            ItemStack productStack = getRandomItemFromLootTable(productLootTable);
            insertToProductSlot(productStack);

            if (!materialStack.is(ModItems.COBBLESTONE_FEEDER)) {
                materialStack.shrink(1);
            }
        }
    }

    private ItemStack getRandomItemFromLootTable(ModLootTables productLootTable) {
        if (level == null)
            return ItemStack.EMPTY;

        var server = level.getServer();
        if (server == null)
            return ItemStack.EMPTY;

        var lootTable = server.getLootData().getLootTable(productLootTable.getId());
        float luck = getFarmingEfficiency();

        /* DEBUG: log selected loot table
        OreFarmingDevice.LOGGER.debug("Device ({}), loot table: {}, efficiency: {}", this.getBlockPos(), lootTable.getLootTableId(), farmingEfficiency);
        // */

        List<ItemStack> randomItems = lootTable.getRandomItems(new LootParams.Builder((ServerLevel) level).withLuck(luck).create(LootContextParamSets.EMPTY));
        return randomItems.size() > 0 ? randomItems.get(0) : ItemStack.EMPTY;
    }

    private void insertToProductSlot(ItemStack productStack) {
        if (productStack.isEmpty())
            return;

        ItemStack productSlotStack = items.get(2);

        if (productSlotStack.isEmpty()) {
            items.set(2, productStack.copy());
        } else if (productSlotStack.is(productStack.getItem())) {
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
        if (level == null) return;

        BlockPos pos = getBlockPos();
        ItemStack productSlotStack = items.get(2);
        // Eject old item stack
        level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, productSlotStack));
        // Set new item stack in product slot
        items.set(2, newStack);
    }

    @Override
    public void setItem(int slot, ItemStack newStack) {
        ItemStack oldStack = items.get(slot);
        boolean flag = !newStack.isEmpty() && ItemStack.isSameItemSameTags(newStack, oldStack);
        items.set(slot, newStack);

        if (newStack.getCount() > getMaxStackSize()) {
            newStack.setCount(getMaxStackSize());
        }

        if (slot == 0 && !flag) {
            cookingTotalTime = getTotalProcessingTime();
            cookingProgress = 0;
            setChanged();
        }

    }

    @Override
    protected MutableComponent getDefaultName() {
        String translationKey = type.getContainerTranslationKey();
        return Component.translatable(translationKey);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new OFDeviceMenu(containerId, playerInventory, this, dataAccess);
    }
}
