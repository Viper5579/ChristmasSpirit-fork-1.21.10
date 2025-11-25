package com.tm.cspirit.item;

import com.tm.cspirit.entity.EntityCandyCaneProjectile;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.item.base.ItemBase;
import com.tm.cspirit.main.CSConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ItemCandyCaneCannon extends ItemBase {

    private static final int MAX_CHARGE_TIME = 20;

    public ItemCandyCaneCannon() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        if (entity instanceof Player player) {
            if (getUseDuration(stack, entity) - player.getUseItemRemainingTicks() == MAX_CHARGE_TIME) {
                player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1, 1);
            }
        }
        super.inventoryTick(stack, world, entity, itemSlot, isSelected);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (hand == InteractionHand.MAIN_HAND) {
            ItemStack candyCaneStack = ItemStack.EMPTY;
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack stackInSlot = player.getInventory().getItem(i);
                if (stackInSlot.getItem() instanceof ItemCandyCane) {
                    candyCaneStack = stackInSlot;
                    break;
                }
            }
            if (!candyCaneStack.isEmpty()) {
                player.startUsingItem(hand);
            }
        }
        return InteractionResultHolder.fail(itemstack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            if (getUseDuration(stack, entityLiving) - timeLeft >= MAX_CHARGE_TIME) {
                ItemStack candyCaneStack = ItemStack.EMPTY;
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack stackInSlot = player.getInventory().getItem(i);
                    if (stackInSlot.getItem() instanceof ItemCandyCane) {
                        candyCaneStack = stackInSlot;
                        break;
                    }
                }
                if (!candyCaneStack.isEmpty()) {
                    byte candyType = 0;
                    if (candyCaneStack.getItem() == InitItems.CANDY_CANE_GREEN.get()) {
                        candyType = 1;
                    } else if (candyCaneStack.getItem() == InitItems.CANDY_CANE_BLUE.get()) {
                        candyType = 2;
                    }
                    EntityCandyCaneProjectile entity = new EntityCandyCaneProjectile(world, player, candyType);
                    entity.setIsCritical(true);
                    entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3, 1.0F);
                    world.addFreshEntity(entity);
                    candyCaneStack.shrink(1);
                    player.playSound(SoundEvents.TRIDENT_THROW, 1, 1);
                    player.playSound(SoundEvents.CROSSBOW_SHOOT, 1, 1);
                }
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public String[] getItemTags() {
        return new String[] {CSConfig.misc.naughtyItems.get() ? "naughty" : "disabled"};
    }
}
