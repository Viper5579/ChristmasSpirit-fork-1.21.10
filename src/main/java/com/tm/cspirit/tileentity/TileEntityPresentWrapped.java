package com.tm.cspirit.tileentity;

import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.inventory.ContainerPresentWrapped;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityPresentWrapped extends TileEntityInventoryBase {

    private PresentConstructor presentConstructor;

    public TileEntityPresentWrapped(BlockPos pos, BlockState state) {
        super(InitTileEntityTypes.PRESENT_WRAPPED.get(), pos, state);
        presentConstructor = new PresentConstructor();
    }

    public PresentConstructor getConstructor() {
        return presentConstructor;
    }

    public void setConstructor(PresentConstructor presentConstructor) {
        this.presentConstructor = presentConstructor;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Wrapped Present");
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
        return new ContainerPresentWrapped(windowId, playerInv, this);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        presentConstructor = PresentConstructor.fromNBT(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        presentConstructor.toNBT(nbt);
        super.saveAdditional(nbt);
    }
}
