package com.tm.cspirit.entity;

import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class EntityCandyCaneProjectile extends AbstractArrow {

    private static final EntityDataAccessor<Byte> CANDY_TYPE = SynchedEntityData.defineId(AbstractArrow.class, EntityDataSerializers.BYTE);

    public EntityCandyCaneProjectile(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public EntityCandyCaneProjectile(Level level, Player shooter, byte candyCaneType) {
        super(InitEntityTypes.CANDY_CANE_PROJECTILE.get(), shooter, level, ItemStack.EMPTY, ItemStack.EMPTY);
        entityData.set(CANDY_TYPE, candyCaneType);
        setBaseDamage(2.5F);
        tickCount -= (20 * 60);
    }

    public byte getCandyType() {
        return entityData.get(CANDY_TYPE);
    }

    public ItemStack getCandyCaneStack() {
        ItemStack cane = new ItemStack(InitItems.CANDY_CANE_RED.get());

        if (getCandyType() == 1) cane = new ItemStack(InitItems.CANDY_CANE_GREEN.get());
        else if (getCandyType() == 2) cane = new ItemStack(InitItems.CANDY_CANE_BLUE.get());

        return cane;
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.LANTERN_BREAK;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("candy_type", entityData.get(CANDY_TYPE));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        entityData.set(CANDY_TYPE, compound.getByte("candy_type"));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CANDY_TYPE, (byte)0);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return getCandyCaneStack();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
