package com.github.iunius118.orefarmingdevice.world.level.block;

import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class OFDeviceBlock extends AbstractFurnaceBlock {
    public final OFDeviceType type;

    public OFDeviceBlock(Properties properties, OFDeviceType offDeviceType) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.FALSE));
        type = offDeviceType;
    }
    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
        return new OFDeviceBlockEntity(type);
    }


    @Override
    protected void openContainer(World level, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = level.getBlockEntity(pos);
        if (tileentity instanceof OFDeviceBlockEntity) {
            player.openMenu((OFDeviceBlockEntity) tileentity);
        }
    }

    @Override
    public void setPlacedBy(World level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            TileEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof OFDeviceBlockEntity) {
                ((OFDeviceBlockEntity)tileentity).setCustomName(stack.getHoverName());
            }
        }
    }

    @Override
    public void onRemove(BlockState state, World level, BlockPos pos, BlockState oldState, boolean p_196243_5_) {
        if (!state.is(oldState.getBlock())) {
            TileEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof OFDeviceBlockEntity) {
                OFDeviceBlockEntity ofDeviceBlockEntity = (OFDeviceBlockEntity) tileentity;
                InventoryHelper.dropContents(level, pos, ofDeviceBlockEntity);
                ofDeviceBlockEntity.getRecipesToAwardAndPopExperience(level, Vector3d.atCenterOf(pos));
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, oldState, p_196243_5_);
        }
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
