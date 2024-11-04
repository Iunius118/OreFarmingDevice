package com.github.iunius118.orefarmingdevice.gametest;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.loot.OFDeviceLootCondition;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.CobblestoneDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.CobblestoneDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.neoforged.neoforge.gametest.GameTestHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

@GameTestHolder(value = OreFarmingDevice.MOD_ID)
public class ModGameTests {
    private static final String TEST_STRUCTURE = "%s:empty3x3x3".formatted(OreFarmingDevice.MOD_ID);

    /**
     * Test that {@link ModLootTables} returns the correct loot table for the specified device and coordinates.
     * @return List of test functions for each loot table
     */
    @GameTestGenerator
    public static Collection<TestFunction> generateLootTableLookupTests() {
        return  Arrays.stream(ModLootTables.values()).map(ModGameTests::createLootTableLookupTestFunction).toList();
    }

    /**
     * Test that OF C Device generates the correct products.
     * @return List of test functions for OF C Devices
     */
    @GameTestGenerator
    public static Collection<TestFunction> generateCobblestoneDeviceTests() {
        return  new ArrayList<>(Arrays.asList(createCobblestoneDeviceTestFunction(true), createCobblestoneDeviceTestFunction(false)));
    }

    private static final List<OFDeviceBlock> DEVICE_BLOCKS = List.of(ModBlocks.DEVICE_0, ModBlocks.DEVICE_1, ModBlocks.DEVICE_2);

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
                    final int tick = device.getTotalProcessingTime() * expectedProductCount;
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

    private static TestFunction createCobblestoneDeviceTestFunction(boolean isInShallowLayer) {
        return new TestFunction(
                OreFarmingDevice.MOD_ID,
                "%s.c_device_test.%s".formatted(OreFarmingDevice.MOD_ID, isInShallowLayer ? "shallow" : "deep"),
                TEST_STRUCTURE,
                224, 0, true,
                helper -> {
                    final CobblestoneDeviceBlock deviceBlock = ModBlocks.COBBLESTONE_DEVICE_0;
                    final Item cobblestone = isInShallowLayer ? Items.COBBLESTONE : Items.COBBLED_DEEPSLATE;

                    // Get device block pos
                    BlockPos absPos = helper.absolutePos(BlockPos.ZERO);
                    int deviceAbsY = isInShallowLayer ? 1 : 0;
                    BlockPos devicePos = BlockPos.ZERO.offset(1, deviceAbsY - absPos.getY(), 1);

                    // Place device
                    helper.destroyBlock(devicePos);
                    helper.setBlock(devicePos, deviceBlock);
                    helper.assertTrue(helper.getBlockState(devicePos).is(deviceBlock), "Failed to place device block.");
                    BlockEntity blockEntity = helper.getBlockEntity(devicePos);
                    helper.assertTrue(blockEntity instanceof CobblestoneDeviceBlockEntity, "Failed to get device block entity.");
                    CobblestoneDeviceBlockEntity device = (CobblestoneDeviceBlockEntity) blockEntity;

                    // A hopper below the device
                    BlockPos hopperPos = devicePos.below();

                    // Test 1: Generate cobblestone
                    final int expectedProductCountTest1 = 3;
                    final int tickTest1 = deviceBlock.type.getIntervalTicks() * expectedProductCountTest1;
                    helper.runAfterDelay(tickTest1, () -> {
                                // Check products in the device
                                final ItemStack product = device.getItem(0);
                                helper.assertTrue(product.is(cobblestone), "[Test 1] Product did not match: exp = %s, act = %s.".formatted(cobblestone, product.getItem()));
                                helper.assertTrue(product.getCount() == expectedProductCountTest1, "[Test 1] Product count was incorrect: exp = %d, act = %d".formatted(expectedProductCountTest1, product.getCount()));

                                // Prepare for Test 2
                                device.setItemForGameTest(0, new ItemStack(cobblestone, cobblestone.getDefaultMaxStackSize() - expectedProductCountTest1));
                            }
                    );

                    // Test 2: Stop generating cobblestone
                    final int expectedProductCountTest2 = cobblestone.getDefaultMaxStackSize();
                    final int tickTest2 = tickTest1 + deviceBlock.type.getIntervalTicks() * (expectedProductCountTest1 + 1);
                    helper.runAfterDelay(tickTest2, () -> {
                                // Check products in the device
                                final ItemStack product = device.getItem(0);
                                helper.assertTrue(product.getCount() == expectedProductCountTest2, "[Test 2] Product count was incorrect: exp = %d, act = %d".formatted(expectedProductCountTest2, product.getCount()));

                                // Prepare for Test 3
                                device.setItemForGameTest(0, new ItemStack(cobblestone, cobblestone.getDefaultMaxStackSize() - expectedProductCountTest1));
                                helper.setBlock(hopperPos, Blocks.HOPPER);
                                helper.assertTrue(helper.getBlockState(hopperPos).is(Blocks.HOPPER), "[Pre Test 3] Failed to place hopper block.");

                                if (helper.getBlockEntity(hopperPos) instanceof HopperBlockEntity hopper) {
                                    hopper.setItem(0, new ItemStack(cobblestone, cobblestone.getDefaultMaxStackSize() - expectedProductCountTest1));
                                    IntStream.range(1, 5).forEach(i -> hopper.setItem(i, new ItemStack(Items.STICK)));
                                } else {
                                    helper.fail("[Pre Test 3] Failed to get hopper block entity.");
                                }
                            }
                    );

                    // Test 3: Hopper
                    final int expectedProductCountTest3 = cobblestone.getDefaultMaxStackSize();
                    final int tickTest3 = tickTest2 + deviceBlock.type.getIntervalTicks() * expectedProductCountTest1;
                    helper.runAfterDelay(tickTest3, () -> {
                                // Check products in the hopper
                                if (helper.getBlockEntity(hopperPos) instanceof HopperBlockEntity hopper) {
                                    final ItemStack product = hopper.getItem(0);
                                    helper.assertTrue(product.getCount() == expectedProductCountTest3, "[Test 3] Product count was incorrect: exp = %d, act = %d".formatted(expectedProductCountTest3, product.getCount()));
                                } else {
                                    helper.fail("[Test 3] Failed to get hopper block entity.");
                                }
                            }
                    );

                    // Test 4: Stop generating cobblestone with hopper
                    final int expectedProductCountTest4 = cobblestone.getDefaultMaxStackSize();
                    final int tickTest4 = tickTest3 + deviceBlock.type.getIntervalTicks() * (expectedProductCountTest1 + 1);
                    helper.runAfterDelay(tickTest4, () -> {
                                // Check products in the device
                                final ItemStack product = device.getItem(0);
                                helper.assertTrue(product.getCount() == expectedProductCountTest4, "[Test 4] Product count was incorrect: exp = %d, act = %d".formatted(expectedProductCountTest4, product.getCount()));

                                // Clean up if successful
                                device.setItemForGameTest(0, ItemStack.EMPTY);
                                helper.destroyBlock(devicePos);

                                if (helper.getBlockEntity(hopperPos) instanceof HopperBlockEntity hopper) {
                                    IntStream.range(0, 5).forEach(i -> hopper.setItem(i, ItemStack.EMPTY));
                                } else {
                                    helper.fail("[Clean Up] Failed to get hopper block entity.");
                                }

                                helper.destroyBlock(hopperPos);
                                helper.succeed();
                            }
                    );
                }
        );
    }
}
