package com.tm.cspirit.inventory;

import com.tm.cspirit.init.InitContainerTypes;
import com.tm.cspirit.inventory.base.ContainerBase;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPresentWrapped extends ContainerBase {

    public ContainerPresentWrapped(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, (TileEntityPresentWrapped) getTileEntity(playerInventory, data));
    }

    public ContainerPresentWrapped(final int windowId, final Inventory playerInventory, final TileEntityPresentWrapped tileEntity) {
        super(InitContainerTypes.PRESENT_WRAPPED.get(), windowId, playerInventory, tileEntity, 8, 41);
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 80, 18));
    }
}
