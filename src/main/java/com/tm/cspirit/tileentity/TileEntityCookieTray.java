package com.tm.cspirit.tileentity;

import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.inventory.ContainerCookieTray;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

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
    public AbstractContainerMenu getTileContainer(int windowId, Inventory playerInv) {
        return new ContainerCookieTray(windowId, playerInv, this);
    }
}
