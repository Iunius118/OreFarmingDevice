package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemModelProvider  extends ItemModelProvider {
    public ModItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, OreFarmingDevice.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerBlockItemModel(ModBlocks.DEVICE_0);
        registerBlockItemModel(ModBlocks.DEVICE_1);
        registerBlockItemModel(ModBlocks.DEVICE_2);
        registerItemModel(ModItems.COBBLESTONE_FEEDER);
    }

    private void registerItemModel(Item item) {
        String path = getItemId(item).getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", "item/" + path);
    }

    private void registerBlockItemModel(Block block) {
        String path = getItemId(block.asItem()).getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
    }

    private ResourceLocation getItemId(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }
}
