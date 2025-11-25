package com.tm.cspirit.entity;

import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.main.CSConfig;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.packet.PacketReindeerJump;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class EntityReindeer extends Horse implements FlyingAnimal {

    public static final EntityDataAccessor<Boolean> JUMP_KEY = SynchedEntityData.defineId(EntityReindeer.class, EntityDataSerializers.BOOLEAN);

    public EntityReindeer(EntityType<? extends Horse> type, Level worldIn) {
        super(type, worldIn);
    }

    public EntityReindeer(Level world, double x, double y, double z) {
        this(InitEntityTypes.REINDEER.get(), world);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xOld = x;
        this.yOld = y;
        this.zOld = z;
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED, 1.2D)
                .add(Attributes.JUMP_STRENGTH, 1);
    }

    @Override
    public void tick() {

        if (CSConfig.misc.reindeerFlying.get()) {

            addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0, true, false));

            if (level().isClientSide) {

                if (getControllingPassenger() != null) {

                    if (getControllingPassenger() instanceof LocalPlayer) {

                        LocalPlayer player = (LocalPlayer) getControllingPassenger();

                        if (isSaddled()) {

                            if (player.input.jumping && !entityData.get(JUMP_KEY)) {
                                ChristmasSpirit.network.sendToServer(new PacketReindeerJump(true));
                                entityData.set(JUMP_KEY, true);
                            }

                            else if (entityData.get(JUMP_KEY)) {
                                ChristmasSpirit.network.sendToServer(new PacketReindeerJump(false));
                                entityData.set(JUMP_KEY, false);
                            }
                        }
                    }
                }

                else if (entityData.get(JUMP_KEY)) {
                    ChristmasSpirit.network.sendToServer(new PacketReindeerJump(false));
                    entityData.set(JUMP_KEY, false);
                }
            }

            else {

                if (getControllingPassenger() == null) {

                    if (entityData.get(JUMP_KEY)) {
                        entityData.set(JUMP_KEY, false);
                    }
                }
            }

            if (entityData.get(JUMP_KEY)) {
                push(0, 0.2F, 0);
            }
        }

        super.tick();
    }

    @Override
    public void travel(Vec3 travelVector) {

        if (CSConfig.misc.reindeerFlying.get()) {

            if (!onGround()) {
                setSpeed((float) getAttributeValue(Attributes.MOVEMENT_SPEED) * 5);
            }

            else setSpeed((float) getAttributeValue(Attributes.MOVEMENT_SPEED));
        }

        super.travel(travelVector);
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, net.minecraft.world.damagesource.DamageSource source) {

        if (CSConfig.misc.reindeerFlying.get()) {

            if (distance > 1.0F) {
                playSound(SoundEvents.HORSE_LAND, 0.4F, 1.0F);
            }

            return false;
        }

        else return super.causeFallDamage(distance, damageMultiplier, source);
    }

    @Override
    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
        AbstractHorse reindeer = InitEntityTypes.REINDEER.get().create(world);
        setOffspringAttributes(mate, reindeer);
        return reindeer;
    }

    @Override
    public boolean canJump() {

        if (!CSConfig.misc.reindeerFlying.get()) {
            return super.canJump();
        }

        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(JUMP_KEY, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        entityData.set(JUMP_KEY, nbt.getBoolean("Jump"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Jump", entityData.get(JUMP_KEY));
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isFlying() {
        return !onGround();
    }
}

