package com.github.iunius118.orefarmingdevice.world.item.crafting;

import com.github.iunius118.orefarmingdevice.loot.ModLootTables;
import com.github.iunius118.orefarmingdevice.loot.OFDeviceLootCondition;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class DeviceProcessingRecipe extends AbstractCookingRecipe {
    public DeviceProcessingRecipe() {
        super(ModRecipeTypes.DEVICE_PROCESSING, "", CookingBookCategory.MISC, Ingredient.EMPTY, ItemStack.EMPTY, 0, 200);
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
        private static final Codec<DeviceProcessingRecipe> CODEC = RecordCodecBuilder.create(
                (instance) -> instance.group(
                        Codec.STRING.optionalFieldOf("dummy").forGetter(recipe -> Optional.empty())
                ).apply(instance, (dummy) -> new DeviceProcessingRecipe()));

        @Override
        public Codec<DeviceProcessingRecipe> codec() {
            return CODEC;
        }

        @Override
        public DeviceProcessingRecipe fromNetwork(FriendlyByteBuf buffer) {
            return new DeviceProcessingRecipe();
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, DeviceProcessingRecipe recipe) {
        }
    }
}
