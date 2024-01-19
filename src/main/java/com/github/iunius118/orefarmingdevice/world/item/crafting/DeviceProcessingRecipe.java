package com.github.iunius118.orefarmingdevice.world.item.crafting;

import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.loot.OFDeviceLootCondition;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class DeviceProcessingRecipe extends AbstractCookingRecipe {
    public DeviceProcessingRecipe(ResourceLocation recipeId) {
        super(ModRecipeTypes.DEVICE_PROCESSING, recipeId, "", CookingBookCategory.MISC, Ingredient.EMPTY, ItemStack.EMPTY, 0, 200);
    }

    @Override
    public boolean matches(Container container, Level level) {
        ItemStack stack = container.getItem(0);
        return ModLootTables.find(OFDeviceLootCondition.MOD_0_IN_SHALLOW_LAYER, stack).isPresent();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.DEVICE_PROCESSING;
    }

    public static class Serializer implements RecipeSerializer<DeviceProcessingRecipe> {
        @Override
        public DeviceProcessingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            return new DeviceProcessingRecipe(recipeId);
        }

        @Nullable
        @Override
        public DeviceProcessingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            return new DeviceProcessingRecipe(recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, DeviceProcessingRecipe recipe) {
        }
    }
}
