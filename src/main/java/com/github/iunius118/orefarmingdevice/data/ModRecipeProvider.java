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
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

// Suppress removal warning from LegacyUpgradeRecipeBuilder only during 1.19.4
@SuppressWarnings("removal")
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Devise 0
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DEVICE_0)
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.DEVICE_1)
                .requires(ModBlocks.DEVICE_0)
                .requires(Items.IRON_PICKAXE)
                .unlockedBy("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer);

        LegacyUpgradeRecipeBuilder.smithing(Ingredient.of(ModBlocks.DEVICE_0), Ingredient.of(Items.IRON_PICKAXE), RecipeCategory.DECORATIONS, Item.BY_BLOCK.get(ModBlocks.DEVICE_1))
                .unlocks("has_device_0", has(ModBlocks.DEVICE_0))
                .save(consumer, getItemId(ModBlocks.DEVICE_1.asItem()) + "_smithing");

        // Devise 2
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.DEVICE_2)
                .requires(ModBlocks.DEVICE_1)
                .requires(Items.DIAMOND_PICKAXE)
                .unlockedBy("has_device_1", has(ModBlocks.DEVICE_1))
                .save(consumer);

        LegacyUpgradeRecipeBuilder.smithing(Ingredient.of(ModBlocks.DEVICE_1), Ingredient.of(Items.DIAMOND_PICKAXE), RecipeCategory.DECORATIONS, Item.BY_BLOCK.get(ModBlocks.DEVICE_2))
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

        // Cobblestone Feeder II
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COBBLESTONE_FEEDER_2)
                .requires(ModItems.COBBLESTONE_FEEDER)
                .requires(Items.DIAMOND_PICKAXE)
                .unlockedBy("has_feeder", has(ModItems.COBBLESTONE_FEEDER))
                .save(consumer);

        LegacyUpgradeRecipeBuilder.smithing(Ingredient.of(ModItems.COBBLESTONE_FEEDER), Ingredient.of(Items.DIAMOND_PICKAXE), RecipeCategory.MISC, ModItems.COBBLESTONE_FEEDER_2)
                .unlocks("has_feeder", has(ModItems.COBBLESTONE_FEEDER))
                .save(consumer, getItemId(ModItems.COBBLESTONE_FEEDER_2) + "_smithing");

        // Cobblestone Feeder I -> Lava Bucket
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LAVA_BUCKET)
                .group(OreFarmingDevice.MOD_ID + ":feeders_to_lava_bucket")
                .requires(ModItems.COBBLESTONE_FEEDER)
                .requires(Items.BUCKET)
                .unlockedBy("has_feeder", has(ModItems.COBBLESTONE_FEEDER))
                .save(consumer, OreFarmingDevice.MOD_ID + ":feeder_to_lava_bucket");

        // Cobblestone Feeder II -> Lava Bucket
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LAVA_BUCKET)
                .group(OreFarmingDevice.MOD_ID + ":feeders_to_lava_bucket")
                .requires(ModItems.COBBLESTONE_FEEDER_2)
                .requires(Items.BUCKET)
                .unlockedBy("has_feeder_2", has(ModItems.COBBLESTONE_FEEDER_2))
                .save(consumer, OreFarmingDevice.MOD_ID + ":feeder_2_to_lava_bucket");

        // Cobblestone Feeder II -> Diamond Pickaxe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, Items.DIAMOND_PICKAXE)
                .requires(ModItems.COBBLESTONE_FEEDER_2)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_feeder_2", has(ModItems.COBBLESTONE_FEEDER_2))
                .save(consumer, OreFarmingDevice.MOD_ID + ":feeder_2_to_diamond_pickaxe");

        // Cobblestone Devise
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.COBBLESTONE_DEVICE_0)
                .pattern("###")
                .pattern("#f#")
                .pattern("#R#")
                .define('#', Blocks.STONE)
                .define('f', ModItems.COBBLESTONE_FEEDER_2)
                .define('R', Blocks.REDSTONE_TORCH)
                .unlockedBy("has_feeder_2", has(ModItems.COBBLESTONE_FEEDER_2))
                .save(consumer);

        // Cobblestone Devise -> Cobblestone Feeder II
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COBBLESTONE_FEEDER_2)
                .requires(ModItems.COBBLESTONE_DEVICE_0)
                .unlockedBy("has_c_device", has(ModItems.COBBLESTONE_DEVICE_0))
                .save(consumer, OreFarmingDevice.MOD_ID + ":c_device_to_feeder_2");
    }

    private ResourceLocation getItemId(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }
}
