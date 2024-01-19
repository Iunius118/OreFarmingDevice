package com.github.iunius118.orefarmingdevice.gametest;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.loot.OFDeviceLootCondition;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.gametest.GameTestHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@GameTestHolder(value = OreFarmingDevice.MOD_ID)
public class ModGameTests {
    private static final String TEST_STRUCTURE = "orefarmingdevice:device_test";

    /**
     * Test that {@link ModLootTables} returns the correct loot table for the specified device and coordinates.
     * @return List of test functions for each loot table
     */
    @GameTestGenerator
    public static Collection<TestFunction> generateLootTableLookupTests() {
        return  Arrays.stream(ModLootTables.values()).map(ModGameTests::createLootTableLookupTestFunction).toList();
    }

    private static final List<OFDeviceBlock> DEVICE_BLOCKS = new ArrayList<>(Arrays.asList(ModBlocks.DEVICE_0, ModBlocks.DEVICE_1, ModBlocks.DEVICE_2));

    private static TestFunction createLootTableLookupTestFunction(ModLootTables lootTable) {
        return new TestFunction(
                OreFarmingDevice.MOD_ID,
                "%s.loot_table_test.%s".formatted(OreFarmingDevice.MOD_ID, lootTable.name().toLowerCase()),
                TEST_STRUCTURE,
                810, 0, true,
                helper -> {
                    OFDeviceLootCondition lootCondition = lootTable.getLootCondition();
                    helper.assertFalse(lootCondition == OFDeviceLootCondition.NOT_APPLICABLE, "Device loot condition was not found.");

                    // Get device block pos
                    BlockPos absPos = helper.absolutePos(BlockPos.ZERO);
                    int deviceAbsY = lootCondition.isDeviceInShallowLayer() ? 1 : 0;
                    BlockPos devicePos = BlockPos.ZERO.offset(1, deviceAbsY - absPos.getY(), 1);

                    // Place device
                    helper.destroyBlock(devicePos);
                    OFDeviceType type = lootCondition.getType();
                    helper.setBlock(devicePos, DEVICE_BLOCKS.get(type.ordinal()));
                    helper.assertTrue(helper.getBlockState(devicePos).is(DEVICE_BLOCKS.get(type.ordinal())), "Failed to place device block.");
                    BlockEntity blockEntity = helper.getBlockEntity(devicePos);
                    helper.assertTrue(blockEntity instanceof OFDeviceBlockEntity, "Failed to get device block entity.");
                    OFDeviceBlockEntity device = (OFDeviceBlockEntity) blockEntity;

                    // Set 8 material items to device
                    device.setItem(0, new ItemStack(lootTable.getMaterial().getItem(), 8));
                    // Set fuel for 4 material items to device
                    device.setItem(1, new ItemStack(Items.OAK_SIGN, 4));

                    // Test
                    final int expectedProductCount = lootTable.getMaterial().is(ModItems.COBBLESTONE_FEEDER) ? 2 : 4;
                    int tick = device.getTotalProcessingTime() * expectedProductCount;
                    helper.runAfterDelay(tick, () -> {
                                // Check products
                                helper.assertTrue(device.getLastProcessedLootTable() == lootTable, "Loot table did not match: exp = %s, act = %s.".formatted(lootTable, device.getLastProcessedLootTable()));
                                helper.assertTrue(device.getProductCount() == expectedProductCount, "Product count was incorrect: exp = %d, act = %d".formatted(expectedProductCount, device.getProductCount()));

                                // Clean up if successful
                                device.setItem(0, ItemStack.EMPTY);
                                device.setItem(1, ItemStack.EMPTY);
                                device.setItem(2, ItemStack.EMPTY);
                                helper.destroyBlock(devicePos);
                                helper.succeed();
                            }
                    );
                }
        );
    }
}
