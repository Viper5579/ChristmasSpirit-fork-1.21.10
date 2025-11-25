package com.tm.cspirit.tileentity;

import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.inventory.ContainerPresentUnwrapped;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityPresentUnwrapped extends TileEntityInventoryBase {

    public TileEntityPresentUnwrapped(BlockPos pos, BlockState state) {
        super(InitTileEntityTypes.PRESENT_UNWRAPPED.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Unwrapped Present");
    }

    @Override
    public void tick () {

    }

    @Override
    public int getSizeInventory () {
        return 1;
    }

    @Override
    public AbstractContainerMenu getTileContainer(int windowId, Inventory playerInv) {
        return new ContainerPresentUnwrapped(windowId, playerInv, this);
    }
}
