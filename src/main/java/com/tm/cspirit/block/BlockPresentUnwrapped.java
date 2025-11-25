package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockInventoryBase;
import com.tm.cspirit.init.InitTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockPresentUnwrapped extends BlockInventoryBase {

    public BlockPresentUnwrapped() {
        super(Properties.of().strength(0.5F).sound(SoundType.WOOL).noOcclusion());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return InitTileEntityTypes.PRESENT_UNWRAPPED.get().create(pos, state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }
}
