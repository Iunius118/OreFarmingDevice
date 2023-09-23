package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        // Devise 0
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DEVICE_0)
                .pattern("#F#")
                .pattern("#L#")
                .pattern("ixi")
                .define('#', Blocks.STONE)
                .define('F', Blocks.FURNACE)
                .define('L', Blocks.LEVER)
                .define('i', Items.IRON_INGOT)
                .define('x', Items.STONE_PICKAXE)
                .unlockedBy("has_furnace", has(Blocks.FURNACE))
                .save(consumer);

        // Devise 1
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.DEVICE_1)
                .requires(ModBlocks.DEVICE_0)
                .requires(Items.IRON_PICKAXE)
                .unlockedBy("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.PAPER), Ingredient.of(ModBlocks.DEVICE_0), Ingredient.of(Items.IRON_PICKAXE), RecipeCategory.DECORATIONS, Item.BY_BLOCK.get(ModBlocks.DEVICE_1))
                .unlocks("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer, getItemId(ModBlocks.DEVICE_1.asItem()) + "_smithing");

        // Devise 2
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.DEVICE_2)
                .requires(ModBlocks.DEVICE_1)
                .requires(Items.DIAMOND_PICKAXE)
                .unlockedBy("has_device_1", has(ModBlocks.DEVICE_1))
                .save(consumer);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.PAPER), Ingredient.of(ModBlocks.DEVICE_1), Ingredient.of(Items.DIAMOND_PICKAXE), RecipeCategory.DECORATIONS, Item.BY_BLOCK.get(ModBlocks.DEVICE_2))
                .unlocks("has_device_1", has(ModBlocks.DEVICE_1))
                .save(consumer, getItemId(ModBlocks.DEVICE_2.asItem()) + "_smithing");

        // Cobblestone Feeder
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COBBLESTONE_FEEDER)
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

        // Cobblestone Feeder -> Lava Bucket
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LAVA_BUCKET)
                .requires(ModItems.COBBLESTONE_FEEDER)
                .requires(Items.BUCKET)
                .unlockedBy("has_cobblestone_feeder", has(ModItems.COBBLESTONE_FEEDER))
                .save(consumer, OreFarmingDevice.MOD_ID + ":feeder_to_lava_bucket");
    }

    private ResourceLocation getItemId(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }
}
