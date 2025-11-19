package com.tm.cspirit.block.base;

import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class for Blocks.
 */
public class BlockBase extends Block {

    /**
     * @param properties The specific properties for the Block. (Creative Tab, hardness, material, etc.)
     */
    public BlockBase (Properties properties) {
        super(properties);
    }

    public BlockBase (SoundType sound) {
        super(BlockBehaviour.Properties.of().sound(sound).strength(1));
    }

    public void addDrops(BlockState state, Level world, BlockPos pos, List<ItemStack> list) {
        list.add(new ItemStack(asItem()));
    }

    @Override
    public void onRemove (BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {

            if (isMoving && PistonBaseBlock.isPushable(state, world, pos, Direction.NORTH, false, Direction.NORTH)) {
                return;
            }

            List<ItemStack> drops = new ArrayList<>();
            addDrops(state, world, pos, drops);

            for (ItemStack stack : drops) {
                ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, stack);
            }

            super.onRemove(state, world, pos, newState, isMoving);
        }
    }
}
