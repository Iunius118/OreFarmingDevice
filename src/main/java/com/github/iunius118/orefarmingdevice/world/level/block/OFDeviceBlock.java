package com.github.iunius118.orefarmingdevice.world.level.block;

import com.github.iunius118.orefarmingdevice.state.properties.ModBlockStateProperties;
import com.github.iunius118.orefarmingdevice.state.properties.OFDeviceCasing;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
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
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new OFDeviceBlockEntity(blockPos, blockState, type);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, OFDeviceBlockEntity.getBlockEntityType(type), OFDeviceBlockEntity::serverTick);
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity tileentity = level.getBlockEntity(pos);
        if (tileentity instanceof OFDeviceBlockEntity) {
            player.openMenu((OFDeviceBlockEntity) tileentity);
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof OFDeviceBlockEntity deviceBlockEntity) {
                deviceBlockEntity.setCustomName(stack.getHoverName());
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean p_196243_5_) {
        if (!state.is(oldState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof OFDeviceBlockEntity deviceBlockEntity) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, pos, deviceBlockEntity);
                }

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, oldState, p_196243_5_);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CASING);
    }
}
