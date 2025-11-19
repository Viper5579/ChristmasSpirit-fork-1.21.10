package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockInventoryBase;
import com.tm.cspirit.init.InitTileEntityTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockPresentUnwrapped extends BlockInventoryBase {

    public BlockPresentUnwrapped() {
        super(Block.Properties.create(Material.WOOL).hardnessAndResistance(0.5F).sound(SoundType.CLOTH).notSolid());
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.PRESENT_UNWRAPPED.get().create();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
}
