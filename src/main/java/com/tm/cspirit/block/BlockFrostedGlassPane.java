package com.tm.cspirit.block;

import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockFrostedGlassPane extends IronBarsBlock {

    public BlockFrostedGlassPane() {
        super(Properties.of().strength(0.5F).sound(SoundType.GLASS).noOcclusion());
    }

    @Override
    public BlockState onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, net.minecraft.world.entity.player.Player player, boolean willHarvest, net.minecraft.world.level.material.FluidState fluid) {

        if (state.getBlock() != world.getBlockState(pos).getBlock()) {
            ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack(asItem()));
        }

        return super.onDestroyedByPlayer(state, world, pos, player, willHarvest, fluid);
    }
}
