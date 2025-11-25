package com.tm.cspirit.tileentity.base;

import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.UnitChatMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public abstract class TileEntityBase extends BlockEntity {

    public TileEntityBase(final BlockEntityType<?> tileEntityType, final BlockPos pos, final BlockState state) {
        super(tileEntityType, pos, state);
    }

    public abstract UnitChatMessage getUnitName(Player player);

    public void tick() {

    }

    public Location getLocation() {
        return new Location(level, worldPosition);
    }

    public void markForUpdate() {

        if (level != null) {
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
    }
}
