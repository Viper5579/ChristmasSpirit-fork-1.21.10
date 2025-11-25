package com.tm.cspirit.item;

import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.item.base.ItemFoodBase;
import com.tm.cspirit.util.helper.ChatHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class ItemSantaCookie extends ItemFoodBase {

    private static final FoodProperties SANTA_COOKIE = new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(1.2F)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(InitEffects.HOLIDAY_SPIRIT.get(), 400, 4), 1.0F).build();

    public ItemSantaCookie() {
        super(new Item.Properties().food(SANTA_COOKIE).rarity(Rarity.RARE), 5, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving) {
        if (entityLiving instanceof Player player) {
            player.removeEffect(InitEffects.NAUGHTY.get());
            player.addEffect(new MobEffectInstance(InitEffects.HOLIDAY_SPIRIT.get(), 20 * 60, 4));
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 60, 2));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 60, 2));

            if (NaughtyListFile.isOnNaughtyList(player)) {
                NaughtyListFile.removeFromNaughtyList(player);
                ChatHelper.printModMessage(ChatFormatting.GREEN,"You've been removed from the Naughty List!", player);
            }
        }
        return this.isEdible() ? entityLiving.eat(world, stack) : stack;
    }
}
