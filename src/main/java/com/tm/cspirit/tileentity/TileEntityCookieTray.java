package com.tm.cspirit.tileentity;

import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.inventory.ContainerCookieTray;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;

public class TileEntityCookieTray extends TileEntityInventoryBase {

    public TileEntityCookieTray(BlockPos pos, BlockState state) {
        super(InitTileEntityTypes.COOKIE_TRAY.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Cookie Tray");
    }

    @Override
    public int getSizeInventory () {
        return 3;
    }

    @Override
    public AbstractContainerMenu getTileContainer (int windowId, PlayerInventory playerInv) {
        return new ContainerCookieTray(windowId, playerInv, this);
    }
}
