package com.tm.cspirit.entity;

import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.init.InitItems;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class EntitySleigh extends Boat {

    private static final int MAX_PASSENGERS = 4;

    public EntitySleigh(EntityType<? extends Boat> type, Level level) {
        super(type, level);
        maxUpStep = 1.0F;
    }

    public EntitySleigh(Level level, double x, double y, double z) {
        this(InitEntityTypes.SLEIGH.get(), level);
        this.moveTo(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xOld = x;
        this.yOld = y;
        this.zOld = z;
    }

    @Override
    public void tick() {

        if (this.isControlledByLocalInstance()) {
            this.move(MoverType.SELF, this.getDeltaMovement().multiply(4, 1, 4));
        }

        else {
            this.setDeltaMovement(Vec3.ZERO);
        }

        super.tick();

        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));

        if (!list.isEmpty()) {

            boolean flag = !this.level().isClientSide && !(this.getControllingPassenger() instanceof Player);

            for (Entity entity : list) {

                if (!entity.isPassenger(this)) {

                    if (flag && this.getPassengers().size() < MAX_PASSENGERS && !entity.isPassenger() && entity.getBbWidth() < this.getBbWidth() && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
                        entity.startRiding(this);
                    }

                    else this.push(entity);
                }
            }
        }
    }

    protected void positionRider(Entity passenger, MoveFunction moveFunction) {

        if (this.hasPassenger(passenger)) {

            double passengerOffsetX = 0.0F;
            double passengerOffsetZ = 0.0F;
            float f1 = (float)((this.isRemoved() ? (double)0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());

            if (getPassengers().indexOf(passenger) == 0) passengerOffsetX = -0.39D;
            if (getPassengers().size() > 1 && getPassengers().indexOf(passenger) == 1) passengerOffsetX = 0.39D;

            if (getPassengers().size() > 2 && getPassengers().indexOf(passenger) == 2) {
                passengerOffsetX = -0.39D;
                f1 += 0.4D;
                passengerOffsetZ = -1.0D;
            }
            if (getPassengers().size() > 3 && getPassengers().indexOf(passenger) == 3) {
                passengerOffsetX = 0.39D;
                f1 += 0.4D;
                passengerOffsetZ = -1.0D;
            }

            Vec3 vector3d = (new Vec3(-0.5D + passengerOffsetZ, 0D, passengerOffsetX)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
            moveFunction.accept(passenger, this.getX() + vector3d.x, this.getY() + (double)f1, this.getZ() + vector3d.z);

            passenger.setYRot(passenger.getYRot() + (getYRot() - yRotO));
            passenger.setYHeadRot(passenger.getYHeadRot() + (getYRot() - yRotO));

            this.clampRotation(passenger);
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.85D;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {

        if (!this.isPassenger()) {

            if (onGroundIn) {
                this.fallDistance = 0.0F;
            }

            else if (!this.level().getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance = (float)((double)this.fallDistance - y);
            }
        }
    }

    @Override
    public Item getDropItem() {
        return InitItems.SLEIGH.get();
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().size() < MAX_PASSENGERS && !this.isEyeInFluid(FluidTags.WATER);
    }

    @Override
    public float getGroundFriction() {
        return 0.8F;
    }

    @Override
    public boolean isPaddling() {
        return false;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
