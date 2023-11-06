package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.config.OreFarmingDeviceConfig;
import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederItem;
import com.github.iunius118.orefarmingdevice.world.item.CobblestoneFeederType;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OFDeviceBlockEntity extends AbstractFurnaceTileEntity {
    public static final String KEY_EFFICIENCY = "Efficiency";
    public static final float MAX_EFFICIENCY = 3F;
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_FUEL = 1;
    public static final int SLOT_RESULT = 2;
    public final OFDeviceType type;

    private float farmingEfficiency = 0F;

    public OFDeviceBlockEntity(TileEntityType<?> blockEntityType, OFDeviceType ofDeviceType) {
        super(blockEntityType, IRecipeType.SMELTING);
        type = ofDeviceType;
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

    public int getTotalProcessingTime() {
        return OreFarmingDeviceConfig.SERVER.canAccelerateProcessingSpeedByMod()
                ? type.getTotalProcessingTime() : OFDeviceType.MOD_0.getTotalProcessingTime();
    }

    public int getFuelConsumption(boolean isFuelConsumptionDoubled) {
        int fuel = OreFarmingDeviceConfig.SERVER.canIncreaseFuelConsumptionByMod()
                ? type.getFuelConsumption() : OFDeviceType.MOD_0.getFuelConsumption();
        return isFuelConsumptionDoubled ? fuel * 2 : fuel;
    }

    public float getFarmingEfficiency() {
        return OreFarmingDeviceConfig.SERVER.isFarmingEfficiencyEnabled()
                ? farmingEfficiency : 0F;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        farmingEfficiency = MathHelper.clamp(nbt.getFloat(KEY_EFFICIENCY), 0F, MAX_EFFICIENCY);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        nbt.putFloat(KEY_EFFICIENCY, MathHelper.clamp(farmingEfficiency, 0F, MAX_EFFICIENCY));
        return nbt;
    }

    @Override
    public void tick() {
        boolean isLitOld = isLit();
        boolean hasChanged = false;
        ItemStack fuelStack = items.get(SLOT_FUEL);
        ItemStack materialStack = items.get(SLOT_INPUT);

        if (isLitOld) {
            litTime -= getFuelConsumption(isFuelConsumptionDoubled(materialStack));
        }

        if (!level.isClientSide) {
            if ((isLit() || !fuelStack.isEmpty()) && !materialStack.isEmpty()) {
                ModLootTables productLootTable = findLootTable(materialStack);
                boolean canProcess = canProcess(productLootTable);

                if (!isLit() && canProcess) {
                    // Burn new fuel stack
                    litTime = getBurnDuration(fuelStack);
                    litDuration = litTime;

                    if (isLit()) {
                        // Handle fuel
                        hasChanged = true;

                        if (fuelStack.hasContainerItem()) {
                            items.set(SLOT_FUEL, fuelStack.getContainerItem());
                        } else if (!fuelStack.isEmpty()) {
                            fuelStack.shrink(1);

                            if (fuelStack.isEmpty()) {
                                items.set(SLOT_FUEL, fuelStack.getContainerItem());
                            }
                        }
                    }
                }

                if (isLit() && litTime == litDuration) {
                    // When device is refueled
                    updateFarmingEfficiency(level, worldPosition);
                    cookingTotalTime = getTotalProcessingTime();
                }

                if (isLit() && canProcess) {
                    // Handle material stack
                    ++cookingProgress;

                    if (cookingProgress == cookingTotalTime) {
                        // Process completion
                        cookingProgress = 0;
                        cookingTotalTime = getTotalProcessingTime();
                        process(productLootTable);
                        hasChanged = true;
                    }
                } else {
                    cookingProgress = 0;
                }
            } else if (!isLit() && cookingProgress > 0) {
                // Fire went out before the process was complete
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

    private static boolean isFuelConsumptionDoubled(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof CobblestoneFeederItem) {
            return ((CobblestoneFeederItem) item).type == CobblestoneFeederType.BASIC;
        }

        return false;
    }

    public void updateFarmingEfficiency(World level, BlockPos blockPos) {
        AxisAlignedBB aabb = new AxisAlignedBB(blockPos).inflate(2.0, 1.0, 2.0);
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, aabb);
        int size = entities.size();
        farmingEfficiency = MathHelper.clamp(size, 0F, MAX_EFFICIENCY);
    }

    public ModLootTables findLootTable(ItemStack stack) {
        return Arrays.stream(ModLootTables.values()).filter(t -> t.canProcess(this, stack)).findFirst().orElse(null);
    }

    private boolean canProcess(ModLootTables productLootTable) {
        return !items.get(SLOT_INPUT).isEmpty() && productLootTable != null;
    }

    private void process(ModLootTables productLootTable) {
        if (level == null || level.isClientSide)
            return;

        // Server side
        if (canProcess(productLootTable)) {
            ItemStack materialStack = this.items.get(SLOT_INPUT);
            List<ItemStack> productStacks = getRandomItemsFromLootTable(productLootTable);
            insertToProductSlot(productStacks);

            if (!(materialStack.getItem() instanceof CobblestoneFeederItem)) {
                materialStack.shrink(1);
            }
        }
    }

    private List<ItemStack> getRandomItemsFromLootTable(ModLootTables productLootTable) {
        if (level == null)
            return Collections.emptyList();

        MinecraftServer server = level.getServer();
        if (server == null)
            return Collections.emptyList();

        LootTable lootTable = server.getLootTables().get(productLootTable.getId());
        float luck = getFarmingEfficiency();

        /* DEBUG: log selected loot table
        OreFarmingDevice.LOGGER.debug("Device ({}), loot table: {}, efficiency: {}", this.getBlockPos(), lootTable.getLootTableId(), farmingEfficiency);
        // */

        return lootTable.getRandomItems(new LootContext.Builder((ServerWorld) level).withLuck(luck).withRandom(level.random).create(LootParameterSets.EMPTY));
    }

    private void insertToProductSlot(List<ItemStack> productStacks) {
        for (ItemStack productStack : productStacks) {
            if (productStack.isEmpty())
                continue;

            ItemStack productSlotStack = this.items.get(SLOT_RESULT);

            if (productSlotStack.isEmpty()) {
                // Insert product item stack to empty product slot
                items.set(SLOT_RESULT, productStack.copy());
            } else if (productSlotStack.sameItem(productStack)
                    && (productSlotStack.getCount() + productStack.getCount() <= Math.min(getMaxStackSize(), productSlotStack.getMaxStackSize()))) {
                // Add same product item to item stack in product slot
                productSlotStack.grow(productStack.getCount());
            } else {
                // Replace item stack in product slot with another item stack
                replaceProductStack(productStack.copy());
            }
        }
    }

    private void replaceProductStack(ItemStack newStack) {
        if (level == null)
            return;

        BlockPos pos = getBlockPos();
        ItemStack productSlotStack = this.items.get(SLOT_RESULT);
        // Eject old item stack
        level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, productSlotStack));
        this.items.set(SLOT_RESULT, newStack);
    }

    @Override
    public void setItem(int slot, ItemStack newStack) {
        ItemStack oldStack = items.get(slot);
        boolean flag = !newStack.isEmpty() && ItemStack.matches(newStack, oldStack);
        items.set(slot, newStack);

        if (newStack.getCount() > getMaxStackSize()) {
            newStack.setCount(getMaxStackSize());
        }

        if (slot == SLOT_INPUT && !flag) {
            cookingTotalTime = getTotalProcessingTime();
            cookingProgress = 0;
            setChanged();
        }
    }

    @Override
    protected ITextComponent getDefaultName() {
        String translationKey = type.getContainerTranslationKey();
        return new TranslationTextComponent(translationKey);
    }

    @Override
    protected Container createMenu(int containerId, PlayerInventory playerInventory) {
        return new OFDeviceContainer(containerId, playerInventory, this, dataAccess);
    }
}
