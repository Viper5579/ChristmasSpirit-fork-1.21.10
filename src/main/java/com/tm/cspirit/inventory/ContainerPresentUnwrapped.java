package com.tm.cspirit.inventory;

import com.tm.cspirit.init.InitContainerTypes;
import com.tm.cspirit.inventory.base.ContainerBase;
import com.tm.cspirit.tileentity.TileEntityPresentUnwrapped;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPresentUnwrapped extends ContainerBase {

    public ContainerPresentUnwrapped (final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, (TileEntityPresentUnwrapped) getTileEntity(playerInventory, data));
    }

    public ContainerPresentUnwrapped (final int windowId, final Inventory playerInventory, final TileEntityPresentUnwrapped tileEntity) {
        super(InitContainerTypes.PRESENT_UNWRAPPED.get(), windowId, playerInventory, tileEntity, 8, 112);
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 80, 18));
    }
}
