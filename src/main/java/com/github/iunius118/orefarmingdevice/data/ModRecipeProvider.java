package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.DEVICE_0)
                .pattern("#F#")
                .pattern("#P#")
                .pattern("#x#")
                .define('#', Blocks.STONE)
                .define('F', Blocks.FURNACE)
                .define('P', Blocks.PISTON)
                .define('x', Items.STONE_PICKAXE)
                .unlockedBy("has_furnace", has(Blocks.FURNACE)).save(consumer);

        SmithingRecipeBuilder.smithing(Ingredient.of(ModBlocks.DEVICE_0), Ingredient.of(Items.IRON_PICKAXE), Item.BY_BLOCK.get(ModBlocks.DEVICE_1))
                .unlocks("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer, ModBlocks.DEVICE_1.getRegistryName());

        SmithingRecipeBuilder.smithing(Ingredient.of(ModBlocks.DEVICE_1), Ingredient.of(Items.DIAMOND_PICKAXE), Item.BY_BLOCK.get(ModBlocks.DEVICE_2))
                .unlocks("has_device_1", has(ModBlocks.DEVICE_1))
                .save(consumer, ModBlocks.DEVICE_2.getRegistryName());
    }

    @Override
    public String getName() {
        return super.getName() + ": " +  OreFarmingDevice.MOD_ID;
    }
}