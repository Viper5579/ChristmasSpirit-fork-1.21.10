package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockBase;
import com.tm.cspirit.util.ShapeBundle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockSnowGlobe extends BlockBase {

    public static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.box(3, 0, 3, 13, 1, 13));
        SHAPE.addShape(Block.box(3.5D, 1, 3.5D, 12.5D, 10, 12.5D));
    }

    public BlockSnowGlobe() {
        super(Properties.of().strength(1).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {

        if (!world.isClientSide) ((ServerLevel)world).setWeatherParameters(0, 20 * 60 * 5, true, false);

        else {
            for (int i = 0; i < 10; i++) {
                world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SNOW_BLOCK.defaultBlockState()), pos.getX() + 0.5D, pos.getY() + 0.2D, pos.getZ() + 0.5D, 0, 0, 0);
            }
        }

        return InteractionResult.SUCCESS;
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
    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }
}
