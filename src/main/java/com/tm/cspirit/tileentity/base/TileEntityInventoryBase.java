package com.tm.cspirit.tileentity.base;

import com.tm.cspirit.util.UnitChatMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ContainerHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public abstract class TileEntityInventoryBase extends TileEntityBase implements MenuProvider {

    private final CSItemHandler inventory;

    public TileEntityInventoryBase(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state) {
        super(tileEntityType, pos, state);

        this.inventory = new CSItemHandler(getSizeInventory());
    }

    @Override
    public UnitChatMessage getUnitName(Player player) {
        return new UnitChatMessage(getDisplayName().getString(), player);
    }

    public abstract int getSizeInventory();
    public abstract AbstractContainerMenu getTileContainer(int id, PlayerInventory playerInv);

    public CSItemHandler getInventory() {
        return this.inventory;
    }

    @Override
    public <T> LazyOptional<T> getCapability (Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, PlayerInventory playerInv, Player player) {
        return getTileContainer(id, playerInv);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        NonNullList<ItemStack> inv = NonNullList.withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, inv);
        this.inventory.setNonNullList(inv);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {

        ContainerHelper.saveAllItems(nbt, this.inventory.toNonNullList());
        super.saveAdditional(nbt);
    }
}
