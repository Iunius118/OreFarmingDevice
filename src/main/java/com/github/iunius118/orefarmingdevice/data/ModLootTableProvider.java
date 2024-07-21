package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootContextParamSets.BLOCK),
                Pair.of(ModDeviceLootTables::new, LootContextParamSets.EMPTY)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }

    @Override
    public String getName() {
        return super.getName() + ": " +  OreFarmingDevice.MOD_ID;
    }

    private static class ModBlockLootTables extends BlockLoot {
        private final List<Block> ofDeviceBlocks = Stream.of(
                ModBlocks.DEVICE_0,
                ModBlocks.DEVICE_1,
                ModBlocks.DEVICE_2,
                ModBlocks.COBBLESTONE_DEVICE_0
        ).collect(ImmutableList.toImmutableList());

        @Override
        protected void addTables() {
            ofDeviceBlocks.forEach(b -> add(b, BlockLoot::createNameableBlockEntityTable));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ofDeviceBlocks;
        }
    }

    private static class ModDeviceLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            // OF Device
            consumer.accept(ModLootTables.DEVICE_0.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Blocks.STONE).setWeight(892).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.COAL_ORE).setWeight(50))
                            .add(LootItem.lootTableItem(Blocks.COPPER_ORE).setWeight(24))
                            .add(LootItem.lootTableItem(Blocks.LAPIS_ORE).setWeight(4))
                            .add(LootItem.lootTableItem(Blocks.IRON_ORE).setWeight(30))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_0_DEEP.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE).setWeight(976).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_COAL_ORE).setWeight(3))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_COPPER_ORE).setWeight(6))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_LAPIS_ORE).setWeight(4))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_IRON_ORE).setWeight(11))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_0_NETHER.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Items.NETHER_BRICK).setWeight(960).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.NETHER_QUARTZ_ORE).setWeight(30))
                            .add(LootItem.lootTableItem(Blocks.NETHER_GOLD_ORE).setWeight(10))
                    )
            );

            // OF Device Mod 1
            consumer.accept(ModLootTables.DEVICE_1.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Blocks.STONE).setWeight(882).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.COAL_ORE).setWeight(50))
                            .add(LootItem.lootTableItem(Blocks.COPPER_ORE).setWeight(24))
                            .add(LootItem.lootTableItem(Blocks.LAPIS_ORE).setWeight(4))
                            .add(LootItem.lootTableItem(Blocks.IRON_ORE).setWeight(30))
                            .add(LootItem.lootTableItem(Blocks.GOLD_ORE).setWeight(4))
                            .add(LootItem.lootTableItem(Blocks.REDSTONE_ORE).setWeight(5))
                            .add(LootItem.lootTableItem(Blocks.DIAMOND_ORE).setWeight(1))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_1_DEEP.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE).setWeight(944).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_COAL_ORE).setWeight(3))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_COPPER_ORE).setWeight(6))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_LAPIS_ORE).setWeight(4))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_IRON_ORE).setWeight(11))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_GOLD_ORE).setWeight(6))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_REDSTONE_ORE).setWeight(20))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_DIAMOND_ORE).setWeight(6))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_1_NETHER.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Items.NETHER_BRICK).setWeight(960).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.NETHER_QUARTZ_ORE).setWeight(30))
                            .add(LootItem.lootTableItem(Blocks.NETHER_GOLD_ORE).setWeight(10))
                    )
            );

            // OF Device Mod 2
            consumer.accept(ModLootTables.DEVICE_2.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Blocks.STONE).setWeight(839).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.COAL_ORE).setWeight(50))
                            .add(LootItem.lootTableItem(Blocks.COPPER_ORE).setWeight(47))
                            .add(LootItem.lootTableItem(Blocks.LAPIS_ORE).setWeight(4))
                            .add(LootItem.lootTableItem(Blocks.IRON_ORE).setWeight(30))
                            .add(LootItem.lootTableItem(Blocks.GOLD_ORE).setWeight(20))
                            .add(LootItem.lootTableItem(Blocks.REDSTONE_ORE).setWeight(5))
                            .add(LootItem.lootTableItem(Blocks.DIAMOND_ORE).setWeight(1))
                            .add(LootItem.lootTableItem(Blocks.EMERALD_ORE).setWeight(2))
                            .add(LootItem.lootTableItem(Items.AMETHYST_SHARD).setWeight(2))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_2_DEEP.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE).setWeight(934).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_COAL_ORE).setWeight(3))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_COPPER_ORE).setWeight(11))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_LAPIS_ORE).setWeight(4))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_IRON_ORE).setWeight(11))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_GOLD_ORE).setWeight(6))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_REDSTONE_ORE).setWeight(20))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_DIAMOND_ORE).setWeight(6))
                            .add(LootItem.lootTableItem(Blocks.DEEPSLATE_EMERALD_ORE).setWeight(1))
                            .add(LootItem.lootTableItem(Items.AMETHYST_SHARD).setWeight(4))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_2_NETHER.getId(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Items.NETHER_BRICK).setWeight(959).setQuality(-240))
                            .add(LootItem.lootTableItem(Blocks.NETHER_QUARTZ_ORE).setWeight(30))
                            .add(LootItem.lootTableItem(Blocks.NETHER_GOLD_ORE).setWeight(10))
                            .add(LootItem.lootTableItem(Blocks.ANCIENT_DEBRIS).setWeight(1))
                    )
            );
        }
    }
}
