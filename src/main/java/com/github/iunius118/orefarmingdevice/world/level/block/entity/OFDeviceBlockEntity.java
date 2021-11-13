package com.github.iunius118.orefarmingdevice.world.level.block.entity;

import com.github.iunius118.orefarmingdevice.inventory.OFDeviceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class OFDeviceBlockEntity extends AbstractFurnaceTileEntity {
    public final OFDeviceType type;
    public final String containerTranslationKey;

    public OFDeviceBlockEntity(OFDeviceType ofDeviceType) {
        super(TileEntityType.FURNACE, IRecipeType.SMELTING);
        type = ofDeviceType;
        containerTranslationKey = ofDeviceType.getContainerTranslationKey();
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent(containerTranslationKey);
    }

    @Override
    protected Container createMenu(int containerId, PlayerInventory playerInventory) {
        return new OFDeviceContainer(containerId, playerInventory, this, dataAccess);
    }
}
