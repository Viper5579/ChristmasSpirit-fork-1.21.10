package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockBase;
import com.tm.cspirit.util.ShapeBundle;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockChimney extends BlockBase {

    public static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.box(0, 0, 0, 2, 16, 16));
        SHAPE.addShape(Block.box(14, 0, 0, 16, 16, 16));
        SHAPE.addShape(Block.box(2, 0, 0, 14, 16, 2));
        SHAPE.addShape(Block.box(2, 0, 14, 14, 16, 16));
    }

    public BlockChimney() {
        super(Properties.of().strength(1).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }
}
