package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.level.block.CobblestoneDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.CobblestoneDeviceType;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper) {
        super(packOutput, OreFarmingDevice.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ofDeviceBlock(ModBlocks.DEVICE_0, OFDeviceType.MOD_0.getName());
        ofDeviceBlock(ModBlocks.DEVICE_1, OFDeviceType.MOD_1.getName());
        ofDeviceBlock(ModBlocks.DEVICE_2, OFDeviceType.MOD_2.getName());
        cobblestoneDeviceBlock(ModBlocks.COBBLESTONE_DEVICE_0, CobblestoneDeviceType.BASIC.getName());
    }

    public void ofDeviceBlock(OFDeviceBlock block, final String baseName) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            String lit = state.getValue(OFDeviceBlock.LIT) ? "on" : "off";
            int casing = state.getValue(OFDeviceBlock.CASING).ordinal();
            String name = String.join("_", baseName, lit, String.valueOf(casing));
            ModelFile model = models().cubeAll(name, new ResourceLocation(OreFarmingDevice.MOD_ID, "block/" + name));
            return ConfiguredModel.builder().modelFile(model).build();
        }, OFDeviceBlock.FACING);
    }

    public void cobblestoneDeviceBlock(CobblestoneDeviceBlock block, final String baseName) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            int casing = state.getValue(CobblestoneDeviceBlock.CASING).ordinal();
            String name = String.join("_", baseName, String.valueOf(casing));
            ModelFile model = models().cubeAll(name, new ResourceLocation(OreFarmingDevice.MOD_ID, "block/" + name));
            return ConfiguredModel.builder().modelFile(model).build();
        });
    }
}
