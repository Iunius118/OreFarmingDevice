package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // Devise 0
        ShapedRecipeBuilder.shaped(ModBlocks.DEVICE_0)
                .pattern("#F#")
                .pattern("#L#")
                .pattern("ixi")
                .define('#', Blocks.STONE)
                .define('F', Blocks.FURNACE)
                .define('L', Blocks.LEVER)
                .define('i', Items.IRON_INGOT)
                .define('x', Items.STONE_PICKAXE)
                .unlockedBy("has_furnace", has(Blocks.FURNACE)).save(consumer);

        // Devise 1
        ShapelessRecipeBuilder.shapeless(ModBlocks.DEVICE_1)
                .requires(ModBlocks.DEVICE_0)
                .requires(Items.IRON_PICKAXE)
                .unlockedBy("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer);

        UpgradeRecipeBuilder.smithing(Ingredient.of(ModBlocks.DEVICE_0), Ingredient.of(Items.IRON_PICKAXE), Item.BY_BLOCK.get(ModBlocks.DEVICE_1))
                .unlocks("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer, ModBlocks.DEVICE_1.getRegistryName() + "_smithing");

        // Devise 2
        ShapelessRecipeBuilder.shapeless(ModBlocks.DEVICE_2)
                .requires(ModBlocks.DEVICE_1)
                .requires(Items.DIAMOND_PICKAXE)
                .unlockedBy("has_device_1", has(ModBlocks.DEVICE_1))
                .save(consumer);

        UpgradeRecipeBuilder.smithing(Ingredient.of(ModBlocks.DEVICE_1), Ingredient.of(Items.DIAMOND_PICKAXE), Item.BY_BLOCK.get(ModBlocks.DEVICE_2))
                .unlocks("has_device_1", has(ModBlocks.DEVICE_1))
                .save(consumer, ModBlocks.DEVICE_2.getRegistryName() + "_smithing");
    }

    @Override
    public String getName() {
        return super.getName() + ": " +  OreFarmingDevice.MOD_ID;
    }
}
