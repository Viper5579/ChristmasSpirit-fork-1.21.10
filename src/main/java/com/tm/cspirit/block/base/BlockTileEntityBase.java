package com.tm.cspirit.block.base;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

/**
 * The base class for Blocks that have Block Entities
 */
public abstract class BlockTileEntityBase extends BaseEntityBlock {

    /**
     * @param properties The specific properties for the Block. (Creative Tab, hardness, material, etc.)
     */
    public BlockTileEntityBase(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape (BlockState state) {
        return RenderShape.MODEL;
    }
}
