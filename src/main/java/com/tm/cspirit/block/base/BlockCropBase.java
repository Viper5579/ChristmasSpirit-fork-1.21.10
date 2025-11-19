package com.tm.cspirit.block.base;

import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class BlockCropBase extends CropBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 6, 16),
            Block.box(0, 0, 0, 16, 6, 16),
            Block.box(0, 0, 0, 16, 8, 16),
            Block.box(0, 0, 0, 16, 8, 16)};

    private final Supplier<Item> cropItem;

    public BlockCropBase(Supplier<Item> cropItem) {
        super(BlockBehaviour.Properties.copy(Blocks.WHEAT));
        this.cropItem = cropItem;
    }

    public BlockCropBase() {
        super(BlockBehaviour.Properties.copy(Blocks.WHEAT));
        this.cropItem = null;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.asItem();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(getAgeProperty())];
    }

    @Override
    public void onRemove (BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {

            RandomSource random = world.random;

            int seedAmount = 1 + (state.getValue(AGE) == 7 ? random.nextInt(2) : 0);
            int cropAmount = state.getValue(AGE) == 7 ? random.nextInt(2) + 1 : 0;

            if (cropItem == null) {
                seedAmount = 1 + (state.getValue(AGE) == 7 ? random.nextInt(2) + 1 : 0);
            }

            ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack(getBaseSeedId(), seedAmount));
            if (cropItem != null) ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack(cropItem.get(), cropAmount));

            super.onRemove(state, world, pos, newState, isMoving);
        }
    }
}
