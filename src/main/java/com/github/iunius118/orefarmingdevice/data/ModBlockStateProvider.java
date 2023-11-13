package com.github.iunius118.orefarmingdevice.data;

import com.github.iunius118.orefarmingdevice.OreFarmingDevice;
import com.github.iunius118.orefarmingdevice.world.level.block.ModBlocks;
import com.github.iunius118.orefarmingdevice.world.level.block.OFDeviceBlock;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, OreFarmingDevice.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ofDeviceBlock(ModBlocks.DEVICE_0, OFDeviceType.MOD_0.getName());
        ofDeviceBlock(ModBlocks.DEVICE_1, OFDeviceType.MOD_1.getName());
        ofDeviceBlock(ModBlocks.DEVICE_2, OFDeviceType.MOD_2.getName());
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
}
