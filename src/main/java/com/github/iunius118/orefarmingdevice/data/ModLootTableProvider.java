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
                ModBlocks.DEVICE_2
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
            consumer.accept(ModLootTables.DEVICE_0.getID(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(Blocks.STONE).setWeight(916))
                            .add(ItemLootEntry.lootTableItem(Blocks.COAL_ORE).setWeight(50))
                            .add(ItemLootEntry.lootTableItem(Blocks.IRON_ORE).setWeight(30))
                            .add(ItemLootEntry.lootTableItem(Blocks.LAPIS_ORE).setWeight(4))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_0_NETHER.getID(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(Items.NETHER_BRICK).setWeight(96))
                            .add(ItemLootEntry.lootTableItem(Blocks.NETHER_QUARTZ_ORE).setWeight(3))
                            .add(ItemLootEntry.lootTableItem(Blocks.NETHER_GOLD_ORE).setWeight(1))
                    )
            );

            // OF Device Mod 1
            consumer.accept(ModLootTables.DEVICE_1.getID(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(Blocks.STONE).setWeight(887))
                            .add(ItemLootEntry.lootTableItem(Blocks.COAL_ORE).setWeight(50))
                            .add(ItemLootEntry.lootTableItem(Blocks.IRON_ORE).setWeight(30))
                            .add(ItemLootEntry.lootTableItem(Blocks.REDSTONE_ORE).setWeight(20))
                            .add(ItemLootEntry.lootTableItem(Blocks.GOLD_ORE).setWeight(6))
                            .add(ItemLootEntry.lootTableItem(Blocks.LAPIS_ORE).setWeight(4))
                            .add(ItemLootEntry.lootTableItem(Blocks.DIAMOND_ORE).setWeight(3))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_1_NETHER.getID(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(Items.NETHER_BRICK).setWeight(96))
                            .add(ItemLootEntry.lootTableItem(Blocks.NETHER_QUARTZ_ORE).setWeight(3))
                            .add(ItemLootEntry.lootTableItem(Blocks.NETHER_GOLD_ORE).setWeight(1))
                    )
            );

            // OF Device Mod 2
            consumer.accept(ModLootTables.DEVICE_2.getID(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(Blocks.STONE).setWeight(882))
                            .add(ItemLootEntry.lootTableItem(Blocks.COAL_ORE).setWeight(50))
                            .add(ItemLootEntry.lootTableItem(Blocks.IRON_ORE).setWeight(30))
                            .add(ItemLootEntry.lootTableItem(Blocks.REDSTONE_ORE).setWeight(20))
                            .add(ItemLootEntry.lootTableItem(Blocks.GOLD_ORE).setWeight(10))
                            .add(ItemLootEntry.lootTableItem(Blocks.LAPIS_ORE).setWeight(4))
                            .add(ItemLootEntry.lootTableItem(Blocks.DIAMOND_ORE).setWeight(3))
                            .add(ItemLootEntry.lootTableItem(Blocks.EMERALD_ORE).setWeight(1))
                    )
            );

            consumer.accept(ModLootTables.DEVICE_2_NETHER.getID(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(Items.NETHER_BRICK).setWeight(959))
                            .add(ItemLootEntry.lootTableItem(Blocks.NETHER_QUARTZ_ORE).setWeight(30))
                            .add(ItemLootEntry.lootTableItem(Blocks.NETHER_GOLD_ORE).setWeight(10))
                            .add(ItemLootEntry.lootTableItem(Blocks.ANCIENT_DEBRIS).setWeight(1))
                    )
            );
        }
    }
}
