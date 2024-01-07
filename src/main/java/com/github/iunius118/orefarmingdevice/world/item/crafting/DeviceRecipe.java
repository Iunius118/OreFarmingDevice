package com.github.iunius118.orefarmingdevice.world.item.crafting;

import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.loot.OFDeviceLootCondition;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class DeviceRecipe extends AbstractCookingRecipe {
    public DeviceRecipe(ResourceLocation recipeId) {
        super(ModRecipeTypes.DEVICE, recipeId, "", Ingredient.EMPTY, ItemStack.EMPTY, 0, 200);
    }

    @Override
    public boolean matches(IInventory inventory, World level) {
        ItemStack stack = inventory.getItem(0);
        return ModLootTables.find(OFDeviceLootCondition.MOD_0_IN_SHALLOW_LAYER, stack).isPresent();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.DEVICE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DeviceRecipe> {
        @Override
        public DeviceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            return new DeviceRecipe(recipeId);
        }

        @Nullable
        @Override
        public DeviceRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            return new DeviceRecipe(recipeId);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, DeviceRecipe recipe) {
        }
    }
}
