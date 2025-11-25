package com.tm.cspirit.item;

import com.tm.cspirit.item.base.IItemTag;
import com.tm.cspirit.item.base.ItemArmorBase;
import com.tm.cspirit.item.tier.CSArmorTiers;
import com.tm.cspirit.main.CSConfig;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemFrostArmor extends ItemArmorBase implements IItemTag {

    public ItemFrostArmor(ArmorItem.Type slot) {
        super(CSArmorTiers.FROST, slot);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Set Bonus: ").append(Component.literal("Freezes attackers").withStyle(ChatFormatting.BLUE)));
    }

    @Override
    public String[] getItemTags() {
        return new String[] {CSConfig.misc.naughtyItems.get() ? "naughty" : "disabled"};
    }
}
