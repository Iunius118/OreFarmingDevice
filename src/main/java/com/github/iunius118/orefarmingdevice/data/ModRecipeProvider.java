package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        // Devise 0
        ShapedRecipeBuilder.shaped(ModBlocks.DEVICE_0)
                .pattern("#F#")
                .pattern("#L#")
                .pattern("ixi")
                .define('#', Blocks.STONE)
                .define('F', Blocks.FURNACE)
                .define('L', Blocks.LEVER)
                .define('i', Tags.Items.INGOTS_IRON)
                .define('x', Items.STONE_PICKAXE)
                .unlockedBy("has_furnace", has(Blocks.FURNACE))
                .save(consumer);

        // Devise 1
        ShapelessRecipeBuilder.shapeless(ModBlocks.DEVICE_1)
                .requires(ModBlocks.DEVICE_0)
                .requires(Items.IRON_PICKAXE)
                .unlockedBy("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer);

        SmithingRecipeBuilder.smithing(Ingredient.of(ModBlocks.DEVICE_0), Ingredient.of(Items.IRON_PICKAXE), Item.BY_BLOCK.get(ModBlocks.DEVICE_1))
                .unlocks("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer, ModBlocks.DEVICE_1.getRegistryName() + "_smithing");

        // Devise 2
        ShapelessRecipeBuilder.shapeless(ModBlocks.DEVICE_2)
                .requires(ModBlocks.DEVICE_1)
                .requires(Items.DIAMOND_PICKAXE)
                .unlockedBy("has_device_1", has(ModBlocks.DEVICE_1))
                .save(consumer);

        SmithingRecipeBuilder.smithing(Ingredient.of(ModBlocks.DEVICE_1), Ingredient.of(Items.DIAMOND_PICKAXE), Item.BY_BLOCK.get(ModBlocks.DEVICE_2))
                .unlocks("has_device_1", has(ModBlocks.DEVICE_1))
                .save(consumer, ModBlocks.DEVICE_2.getRegistryName() + "_smithing");

        // Cobblestone Feeder I
        ShapedRecipeBuilder.shaped(ModItems.COBBLESTONE_FEEDER)
                .pattern("  L")
                .pattern("RPx")
                .pattern("  W")
                .define('L', Items.LAVA_BUCKET)
                .define('R', Blocks.REPEATER)
                .define('P', Blocks.PISTON)
                .define('x', Items.STONE_PICKAXE)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer);

        // Cobblestone Feeder II
        ShapelessRecipeBuilder.shapeless(ModItems.COBBLESTONE_FEEDER_2)
                .requires(ModItems.COBBLESTONE_FEEDER)
                .requires(Items.DIAMOND_PICKAXE)
                .unlockedBy("has_feeder", has(ModItems.COBBLESTONE_FEEDER))
                .save(consumer);

        SmithingRecipeBuilder.smithing(Ingredient.of(ModItems.COBBLESTONE_FEEDER), Ingredient.of(Items.DIAMOND_PICKAXE), ModItems.COBBLESTONE_FEEDER_2)
                .unlocks("has_feeder", has(ModItems.COBBLESTONE_FEEDER))
                .save(consumer, ModItems.COBBLESTONE_FEEDER_2.getRegistryName() + "_smithing");

        // Cobblestone Feeder I -> Lava Bucket
        ShapelessRecipeBuilder.shapeless(Items.LAVA_BUCKET)
                .group(OreFarmingDevice.MOD_ID + ":feeders_to_lava_bucket")
                .requires(ModItems.COBBLESTONE_FEEDER)
                .requires(Items.BUCKET)
                .unlockedBy("has_feeder", has(ModItems.COBBLESTONE_FEEDER))
                .save(consumer, OreFarmingDevice.MOD_ID + ":feeder_to_lava_bucket");

        // Cobblestone Feeder II -> Lava Bucket
        ShapelessRecipeBuilder.shapeless(Items.LAVA_BUCKET)
                .group(OreFarmingDevice.MOD_ID + ":feeders_to_lava_bucket")
                .requires(ModItems.COBBLESTONE_FEEDER_2)
                .requires(Items.BUCKET)
                .unlockedBy("has_feeder_2", has(ModItems.COBBLESTONE_FEEDER_2))
                .save(consumer, OreFarmingDevice.MOD_ID + ":feeder_2_to_lava_bucket");
    }
}
