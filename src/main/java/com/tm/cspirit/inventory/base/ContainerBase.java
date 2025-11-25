package com.tm.cspirit.inventory.base;

import com.tm.cspirit.tileentity.base.CSItemHandler;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class ContainerBase extends AbstractContainerMenu {

    protected final Inventory playerInventory;
    public final TileEntityInventoryBase tileEntity;
    protected int size;

    protected boolean isItemContainer;

    protected ContainerBase (MenuType<?> type, int windowId, Inventory playerInventory, TileEntityInventoryBase tileEntity, int x, int y) {
        super(type, windowId);

        this.playerInventory = playerInventory;
        this.tileEntity = tileEntity;

        addPlayerInv(x, y);
        addPlayerHotbar(x, y + 58);
    }

    /**
     * Gets the connected TileEntity.
     * Throws IllegalStateException if not found.
     */
    protected static TileEntityInventoryBase getTileEntity (final Inventory playerInventory, final FriendlyByteBuf data) {

        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");

        final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());

        if (tileAtPos instanceof TileEntityInventoryBase) {
            return (TileEntityInventoryBase) tileAtPos;
        }

        throw new IllegalStateException("Tile entity is not correct!" + tileAtPos);
    }

    /**
     * Gets the new inventory's slot amount.
     */
    private int getTileEntitySlotAmount () {
        return isItemContainer ? size : tileEntity.getSizeInventory();
    }

    /**
     * Used to add the Player's inventory.
     */
    protected void addPlayerInv (int x, int y) {
        addStorageInv(playerInventory, 9, x, y, 3);
    }

    /**
     * Used to add the Player's hotbar.
     */
    protected void addPlayerHotbar (int x, int y) {
        addStorageInv(playerInventory, 0, x, y, 1);
    }

    /**
     * Used to by addPlayerInv & addPlayerHotbar to add the appropriate slots.
     */
    private void addStorageInv (Container inv, int idOffset, int x, int y, int height) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + idOffset, x + (j * 18), y + (i * 18)));
            }
        }
    }

    /**
     * Used to add the storage slots of a Tile Entity.
     */
    protected void addTileEntityStorageInv (CSItemHandler inv, int idOffset, int x, int y, int height) {

        int id = idOffset;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new SlotItemHandler(inv, id, x + (j * 18), y + (i * 18)));
                id++;
            }
        }
    }

    /**
     * Handles shift-clicking items from slot to slot.
     */
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    /**
     * Checks if the given merge is possible.
     */
    private boolean mergeIfPossible (Slot slot, ItemStack is, ItemStack is2, int id, int maxId) {

        if (!this.moveItemStackTo(is, id, maxId, false) || is.getCount() > slot.getMaxStackSize(is)) {
            return true;
        }

        slot.onQuickCraft(is, is2);
        return false;
    }

    /**
     * Handles Transfers: Player Inventory <-> Hotbar.
     */
    private boolean mergeInvHotbarIfPossible (Slot slot, ItemStack is, ItemStack is2, int id) {

        //Transfers: Player Inventory -> Hotbar.
        if (id < 27) {

            if (mergeIfPossible(slot, is, is2, 27, 35)) {
                return true;
            }
        }

        //Transfers: Hotbar -> Player Inventory.
        else {

            if (mergeIfPossible(slot, is, is2, 0, 26)) {
                return true;
            }
        }

        slot.onSlotChange(is, is2);
        return false;
    }

    @Override
    public boolean stillValid (Player playerIn) {
        return true;
    }
}

