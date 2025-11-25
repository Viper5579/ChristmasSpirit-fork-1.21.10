package com.tm.cspirit.item;

import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.item.base.ItemFoodBase;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemSoda extends ItemFoodBase {

    public ItemSoda(int hunger, float saturation) {
        super(hunger, saturation, 1, 2, true);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.literal(isOpened(stack) ? "Opened" : "Unopened"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        if (!isOpened(stack)) {
            CompoundTag nbt = ItemHelper.getNBT(stack);
            nbt.putBoolean("Opened", true);

            entityLiving.playSound(InitSounds.CAN_OPEN.get(), 1, 1);
            return stack;
        }

        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

    private boolean isOpened(ItemStack stack) {
        CompoundTag nbt = ItemHelper.getNBT(stack);
        return nbt.getBoolean("Opened");
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return isOpened(stack) ? UseAnim.DRINK : UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return isOpened(stack) ? 40 : 20;
    }
}
