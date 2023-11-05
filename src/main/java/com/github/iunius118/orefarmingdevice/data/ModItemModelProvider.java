package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.item.ModItems;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider  extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, OreFarmingDevice.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerBlockItemModel(ModBlocks.DEVICE_0);
        registerBlockItemModel(ModBlocks.DEVICE_1);
        registerBlockItemModel(ModBlocks.DEVICE_2);
        registerItemModel(ModItems.COBBLESTONE_FEEDER);
        registerItemModel(ModItems.COBBLESTONE_FEEDER_2);
    }

    private void registerItemModel(Item item) {
        String path = item.getRegistryName().getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", "item/" + path);
    }

    private void registerBlockItemModel(Block block) {
        String path = block.getRegistryName().getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
    }
}
