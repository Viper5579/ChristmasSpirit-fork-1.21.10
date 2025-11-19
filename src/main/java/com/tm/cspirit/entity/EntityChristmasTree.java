package com.tm.cspirit.entity;

import com.tm.cspirit.entity.data.CSDataSerializers;
import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.EffectHelper;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.NonNullList;
import net.minecraft.world.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class EntityChristmasTree extends Entity {

    private static final EntityDataAccessor<NonNullList<ItemStack>> INVENTORY = SynchedEntityData.defineId(EntityChristmasTree.class, CSDataSerializers.ITEMSTACK_ARRAY_4);
    private static final EntityDataAccessor<Boolean> WHITE = SynchedEntityData.defineId(EntityChristmasTree.class, EntityDataSerializers.BOOLEAN);

    public EntityChristmasTree(EntityType<? extends EntityChristmasTree> type, Level level) {
        super(type, level);
    }

    public EntityChristmasTree(Level level, Vec3 position, float yaw, boolean isWhite) {
        this(InitEntityTypes.CHRISTMAS_TREE.get(), level);
        absMoveTo(position.x, position.y, position.z, yaw, 0);
        entityData.set(WHITE, isWhite);
    }

    public boolean isWhite() {
        return entityData.get(WHITE);
    }

    public ItemStack getItemBySlot(EquipmentSlot slotIn) {

        if (slotIn.getType() == EquipmentSlot.Type.ARMOR) {
            return entityData.get(INVENTORY).get(slotIn.getIndex());
        }

        return ItemStack.EMPTY;
    }

    public ItemStack getItemStackFromID (int id) {
        return getItemBySlot(getSlotTypeFromID(id));
    }

    public EquipmentSlot getSlotTypeFromID(int id) {

        switch (id) {
            case 0: return EquipmentSlot.FEET;
            case 1: return EquipmentSlot.LEGS;
            case 2: return EquipmentSlot.CHEST;
        }

        return EquipmentSlot.HEAD;
    }

    public void setItemSlot(EquipmentSlot slotIn, ItemStack stack) {

        if (slotIn.getType() == EquipmentSlot.Type.ARMOR) {

            NonNullList<ItemStack> newInv = NonNullList.create();
            newInv.addAll(entityData.get(INVENTORY));
            newInv.set(slotIn.getIndex(), stack);
            entityData.set(INVENTORY, newInv);
        }
    }

    public boolean hasStar() {
        return !getItemBySlot(EquipmentSlot.HEAD).isEmpty();
    }

    private boolean addDecoration(ItemStack stack) {

        ItemStack copiedStack = stack.copy();
        copiedStack.setCount(1);

        boolean added = false;

        if (!hasStar()) {
            setItemSlot(EquipmentSlot.HEAD, copiedStack);
            return true;
        }

        for (int i = 0; i < 4; i++) {

            if (getItemStackFromID(i).isEmpty()) {
                setItemSlot(getSlotTypeFromID(i), copiedStack);
                added = true;
                break;
            }
        }

        return added;
    }

    private ItemStack removeDecoration() {

        for (int i = 2; i >= 0; i--) {

            ItemStack removedStack = getItemStackFromID(i);

            if (!removedStack.isEmpty()) {
                setItemSlot(getSlotTypeFromID(i), ItemStack.EMPTY);
                return removedStack;
            }
        }

        if (hasStar()) {
            ItemStack removedStack = getItemBySlot(EquipmentSlot.HEAD);
            setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
            return removedStack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();

        int radius = 10;

        int stack = 1;

        if (hasStar() && !getItemBySlot(EquipmentSlot.CHEST).isEmpty() && !getItemBySlot(EquipmentSlot.LEGS).isEmpty() && !getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
            stack = 2;
        }

        List<Player> closePlayers = level().getEntitiesOfClass(Player.class, new AABB(blockPosition().getX() - radius, blockPosition().getY() - radius, blockPosition().getZ() - radius, blockPosition().getX() + radius, blockPosition().getY() + radius, blockPosition().getZ() + radius));

        for (Player player : closePlayers) {
            EffectHelper.giveHolidaySpiritStackEffect(player, stack, 30);
        }
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (!stack.isEmpty()) {

            boolean added = addDecoration(stack);

            if (added) {
                stack.shrink(1);
            }
        }

        else {

            ItemStack removedStack = removeDecoration();

            if (!removedStack.isEmpty()) {
                ItemHelper.spawnStackAtEntity(level(), player, removedStack);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {

        Location location = new Location(level(), blockPosition());

        if (source.getDirectEntity() instanceof Player) {

            Player player = (Player) source.getDirectEntity();

            if (player.getMainHandItem().getItem() instanceof AxeItem || player.isCreative()) {

                Item item = InitItems.CHRISTMAS_TREE.get();

                if (isWhite()) {
                    item = InitItems.CHRISTMAS_TREE_WHITE.get();
                }

                ItemHelper.spawnStackAtLocation(level(), location, new ItemStack(item));

                ItemHelper.spawnStackAtLocation(level(), location, getItemBySlot(EquipmentSlot.HEAD));
                ItemHelper.spawnStackAtLocation(level(), location, getItemBySlot(EquipmentSlot.CHEST));
                ItemHelper.spawnStackAtLocation(level(), location, getItemBySlot(EquipmentSlot.LEGS));
                ItemHelper.spawnStackAtLocation(level(), location, getItemBySlot(EquipmentSlot.FEET));

                if (!level().isClientSide) {
                    playSound(SoundEvents.WOOD_BREAK, 1, 1);
                    playSound(SoundEvents.GRASS_BREAK, 1, 1);
                }

                discard();
            }

            return true;
        }

        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(INVENTORY, NonNullList.withSize(4, ItemStack.EMPTY));
        builder.define(WHITE, false);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {

        NonNullList<ItemStack> newInv = NonNullList.withSize(4, ItemStack.EMPTY);

        newInv.set(0, ItemStack.parseOptional(level().registryAccess(), nbt.getCompound("Head")));
        newInv.set(1, ItemStack.parseOptional(level().registryAccess(), nbt.getCompound("Chest")));
        newInv.set(2, ItemStack.parseOptional(level().registryAccess(), nbt.getCompound("Legs")));
        newInv.set(3, ItemStack.parseOptional(level().registryAccess(), nbt.getCompound("Feet")));

        entityData.set(INVENTORY, newInv);

        entityData.set(WHITE, nbt.getBoolean("White"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {

        CompoundTag headTag = new CompoundTag();
        CompoundTag chestTag = new CompoundTag();
        CompoundTag legsTag = new CompoundTag();
        CompoundTag feetTag = new CompoundTag();
        entityData.get(INVENTORY).get(0).save(level().registryAccess(), headTag);
        entityData.get(INVENTORY).get(1).save(level().registryAccess(), chestTag);
        entityData.get(INVENTORY).get(2).save(level().registryAccess(), legsTag);
        entityData.get(INVENTORY).get(3).save(level().registryAccess(), feetTag);
        nbt.put("Head", headTag);
        nbt.put("Chest", chestTag);
        nbt.put("Legs", legsTag);
        nbt.put("Feet", feetTag);

        nbt.putBoolean("White", entityData.get(WHITE));
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    public void push(Entity entityIn) {
        super.push(entityIn);
    }

    @Override
    public float getPickRadius() {
        return 0.0F;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
