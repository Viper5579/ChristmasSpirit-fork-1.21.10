package com.tm.cspirit.tileentity.base;

import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.UnitChatMessage;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public abstract class TileEntityBase extends TileEntity implements ITickableTileEntity {

    public TileEntityBase (final TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public abstract UnitChatMessage getUnitName (PlayerEntity player);

    @Override
    public void tick () {

    }

    public Location getLocation () {
        return new Location(world, pos);
    }

    public void markForUpdate () {

        if (world != null) {
            markDirty();
            world.markAndNotifyBlock(getPos(), world.getChunkAt(getPos()), getBlockState(), getBlockState(), 0, 1);
            world.addBlockEvent(getPos(), getBlockState().getBlock(), 1, 1);
            world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 0);
            world.notifyNeighborsOfStateChange(getPos(), getBlockState().getBlock());
        }
    }

    @Override
    public void onDataPacket (NetworkManager net, SUpdateTileEntityPacket pkt) {
        read(getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        read(state, tag);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write (CompoundNBT nbt) {
        return super.write(nbt);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket () {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        int tileEntityType = 64;
        return new SUpdateTileEntityPacket(getPos(), tileEntityType, nbtTagCompound);
    }

    @Override
    public CompoundNBT getUpdateTag () {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return nbt;
    }

    @Override
    public CompoundNBT getTileData () {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return nbt;
    }
}
