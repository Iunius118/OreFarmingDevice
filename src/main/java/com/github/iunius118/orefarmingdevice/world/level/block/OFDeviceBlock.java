package com.github.iunius118.orefarmingdevice.world.level.block;

import com.github.iunius118.orefarmingdevice.state.properties.ModBlockStateProperties;
import com.github.iunius118.orefarmingdevice.state.properties.OFDeviceCasing;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class OFDeviceBlock extends AbstractFurnaceBlock {
    public static final EnumProperty<OFDeviceCasing> CASING = ModBlockStateProperties.CASING;

    public final OFDeviceType type;

    public OFDeviceBlock(Properties properties, OFDeviceType offDeviceType) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(LIT, Boolean.FALSE).setValue(CASING, OFDeviceCasing.NORMAL));
        type = offDeviceType;
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
        return new OFDeviceBlockEntity(type);
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (level.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack stack = player.getItemInHand(hand);

            if (Tags.Items.RODS_WOODEN.contains(stack.getItem())) {
                OFDeviceCasing casing = state.getValue(CASING);
                level.setBlock(pos, state.setValue(CASING, casing.getNext()), 3);
            } else {
                openContainer(level, pos, player);
            }

            return ActionResultType.CONSUME;
        }
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
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, oldState, p_196243_5_);
        }
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CASING);
    }
}
