package com.github.iunius118.orefarmingfurnace.world.level.block.entity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class OFFDeviceBlockEntity extends AbstractFurnaceTileEntity {
    public final OFFDeviceType type;
    public final String containerTranslationKey;

    public OFFDeviceBlockEntity(OFFDeviceType offDeviceType) {
        super(TileEntityType.FURNACE, IRecipeType.SMELTING);
        type = offDeviceType;
        containerTranslationKey = offDeviceType.getContainerTranslationKey();
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent(containerTranslationKey);
    }

    @Override
    protected Container createMenu(int containerId, PlayerInventory inventory) {
        return null;
    }
}
