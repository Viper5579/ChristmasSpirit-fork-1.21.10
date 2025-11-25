package com.tm.cspirit.item;

import com.tm.cspirit.item.base.IItemTag;
import com.tm.cspirit.main.CSConfig;
import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class ItemFrostmourne extends SwordItem implements IItemTag {

    public ItemFrostmourne() {
        super(Tiers.DIAMOND, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.4F)).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Randomly freezes enemies").withStyle(ChatFormatting.BLUE));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        if (random.nextInt(3) == 0) {
            EffectHelper.giveFrozenEffect(target, 2);
        }
        return true;
    }

    @Override
    public String[] getItemTags() {
        return new String[] {CSConfig.misc.naughtyItems.get() ? "naughty" : "disabled"};
    }
}
