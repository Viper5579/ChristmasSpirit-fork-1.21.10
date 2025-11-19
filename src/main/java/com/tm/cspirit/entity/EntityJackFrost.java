package com.tm.cspirit.entity;

import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.util.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EntityJackFrost extends PathfinderMob implements NeutralMob {

    private int attackTimer;
    private int tradeCooldown;

    private static final TimeUtil.ValueRange randomTime = TimeUtil.rangeOfSeconds(20, 39);
    private int angerTime;
    private UUID angerTarget;

    public EntityJackFrost(EntityType<? extends PathfinderMob> type, Level world) {
        super(type, world);
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InitItems.FROSTMOURNE.get()));
    }

    public EntityJackFrost(Level world, int x, int y, int z) {
        super(InitEntityTypes.JACK_FROST.get(), world);
        moveTo(x, y, z, 0, 0);
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InitItems.FROSTMOURNE.get()));
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 5);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (level().isDay()) addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 4));
        else {
            addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20));
            addEffect(new MobEffectInstance(MobEffects.GLOWING, 20));
        }

        if (getY() > 300) {
            discard();
        }

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }

        if (!level().isClientSide) {

            if (getPersistentAngerTarget() != null && level().getPlayerByUUID(getPersistentAngerTarget()) != null) {
                if (NaughtyListFile.isOnNaughtyList(Objects.requireNonNull(level().getPlayerByUUID(getPersistentAngerTarget())))) {
                    setPersistentAngerTarget(null);
                }
            }

            int radius = 35;

            List<Player> closePlayers = level().getEntitiesOfClass(Player.class, new AABB(blockPosition().getX() - radius, blockPosition().getY() - radius, blockPosition().getZ() - radius, blockPosition().getX() + radius, blockPosition().getY() + radius, blockPosition().getZ() + radius));

            for (Player player : closePlayers) {

                if (!NaughtyListFile.isOnNaughtyList(player)) {
                    setPersistentAngerTarget(player.getUUID());
                    break;
                }
            }
        }
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {

        if (!level().isClientSide) {

            if (hand == InteractionHand.OFF_HAND) {
                return InteractionResult.FAIL;
            }

            if (NaughtyListFile.isOnNaughtyList(player)) {

                if (getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {

                    ItemStack heldStack = player.getMainHandItem();

                    if (heldStack.getItem() == InitItems.LUMP_OF_COAL.get()) {

                        if (heldStack.getCount() >= 5) {

                            ItemHelper.spawnStack(level(), getX(), getY(), getZ(), (level().random.nextDouble() / 2) - 0.25D, 0.2D, (level().random.nextDouble() / 2) - 0.25D, new ItemStack(InitItems.FROST_INGOT.get()));

                            heldStack.shrink(5);

                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return angerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int time) {
        angerTime = time;
    }

    @Override
    public UUID getPersistentAngerTarget() {
        return angerTarget;
    }

    @Override
    public void setPersistentAngerTarget(UUID target) {
        angerTarget = target;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(randomTime.sample(this.random));
    }

    @Override
    public boolean isAngryAt(LivingEntity entity) {
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity)) {
            return false;
        }
        else {
            return entity.getType() == EntityType.PLAYER && this.isAngryAtAllPlayers(entity.level()) || entity.getUUID().equals(this.getPersistentAngerTarget());
        }
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return net.minecraft.sounds.SoundEvents.PILLAGER_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(net.minecraft.world.damagesource.DamageSource damageSourceIn) {
        return net.minecraft.sounds.SoundEvents.PILLAGER_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return net.minecraft.sounds.SoundEvents.PILLAGER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(net.minecraft.sounds.SoundEvents.ZOMBIE_STEP, 0.15F, 1);
    }

    @Override
    protected int getExperienceReward() {
        return 40;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }
}
