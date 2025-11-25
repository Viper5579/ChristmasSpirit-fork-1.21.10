package com.tm.cspirit.inventory;

import com.tm.cspirit.init.InitContainerTypes;
import com.tm.cspirit.inventory.base.ContainerBase;
import com.tm.cspirit.inventory.base.SlotLimitedStack;
import com.tm.cspirit.tileentity.TileEntityCookieTray;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;

public class ContainerCookieTray extends ContainerBase {

    public ContainerCookieTray(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, (TileEntityCookieTray) getTileEntity(playerInventory, data));
    }

    public ContainerCookieTray(final int windowId, final Inventory playerInventory, final TileEntityCookieTray tileEntity) {
        super(InitContainerTypes.COOKIE_TRAY.get(), windowId, playerInventory, tileEntity, 8, 50);
        this.addSlot(new SlotLimitedStack(tileEntity.getInventory(), 0, 62, 18, 3));
        this.addSlot(new SlotLimitedStack(tileEntity.getInventory(), 1, 80, 18, 3));
        this.addSlot(new SlotLimitedStack(tileEntity.getInventory(), 2, 98, 18, 3));
    }

    @Override
    public ItemStack clicked(int slotId, int dragType, ClickType clickTypeIn, Player player) {
        ItemStack stack = super.clicked(slotId, dragType, clickTypeIn, player);
        tileEntity.markForUpdate();
        return stack;
    }
}
