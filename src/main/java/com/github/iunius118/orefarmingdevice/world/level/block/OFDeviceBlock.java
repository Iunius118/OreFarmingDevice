package com.github.iunius118.orefarmingdevice.world.level.block;

import com.github.iunius118.orefarmingdevice.state.properties.ModBlockStateProperties;
import com.github.iunius118.orefarmingdevice.state.properties.OFDeviceCasing;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceBlockEntity;
import com.github.iunius118.orefarmingdevice.world.level.block.entity.OFDeviceType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class OFDeviceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<OFDeviceBlock> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    BlockBehaviour.Properties.CODEC.fieldOf("properties").forGetter(BlockBehaviour::properties),
                    ResourceLocation.CODEC.fieldOf("device_type").forGetter(d -> d.type.getId())
            ).apply(instance, OFDeviceBlock::new)
    );
    public static final EnumProperty<OFDeviceCasing> CASING = ModBlockStateProperties.CASING;

    public final OFDeviceType type;

    public OFDeviceBlock(Properties properties, OFDeviceType ofDeviceType) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(LIT, Boolean.FALSE).setValue(CASING, OFDeviceCasing.NORMAL));
        type = ofDeviceType;
    }

    public OFDeviceBlock(Properties properties, ResourceLocation deviceType) {
        this(properties, OFDeviceType.valueOf(deviceType));
    }

    @Override
    public MapCodec<OFDeviceBlock> codec() {
        return CODEC;
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
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        // Server side
        openContainer(level, pos, player);
        return InteractionResult.CONSUME;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return ItemInteractionResult.SUCCESS;
        }

        // Server side
        if (stack.is(Tags.Items.RODS_WOODEN)) {
            OFDeviceCasing casing = state.getValue(CASING);
            level.setBlock(pos, state.setValue(CASING, casing.getNext()), 3);
        } else {
            openContainer(level, pos, player);
        }

        return ItemInteractionResult.CONSUME;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        var blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof OFDeviceBlockEntity) {
            player.openMenu((OFDeviceBlockEntity) blockEntity);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean p_196243_5_) {
        if (!state.is(oldState.getBlock())) {
            var blockEntity = level.getBlockEntity(pos);

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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CASING);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
