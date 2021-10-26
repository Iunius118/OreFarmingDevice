package com.github.iunius118.orefarmingfurnace.world.level.block;

import com.github.iunius118.orefarmingfurnace.world.level.block.entity.OFFDeviceBlockEntity;
import com.github.iunius118.orefarmingfurnace.world.level.block.entity.OFFDeviceType;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class OFFDeviceBlock extends AbstractFurnaceBlock {
    public final OFFDeviceType type;

    protected OFFDeviceBlock(Properties properties, OFFDeviceType offDeviceType) {
        super(properties);
        type = offDeviceType;
    }
    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
        return new OFFDeviceBlockEntity(type);
    }


    @Override
    protected void openContainer(World level, BlockPos pos, PlayerEntity player) {

    }

    @Override
    public void setPlacedBy(World level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            TileEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof OFFDeviceBlockEntity) {
                ((AbstractFurnaceTileEntity)tileentity).setCustomName(stack.getHoverName());
            }
        }
    }

    @Override
    public void onRemove(BlockState state, World level, BlockPos pos, BlockState oldState, boolean p_196243_5_) {
        if (!state.is(oldState.getBlock())) {
            TileEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof OFFDeviceBlockEntity) {
                OFFDeviceBlockEntity offDeviceBlockEntity = (OFFDeviceBlockEntity) tileentity;
                InventoryHelper.dropContents(level, pos, offDeviceBlockEntity);
                offDeviceBlockEntity.getRecipesToAwardAndPopExperience(level, Vector3d.atCenterOf(pos));
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, oldState, p_196243_5_);
        }
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

}
