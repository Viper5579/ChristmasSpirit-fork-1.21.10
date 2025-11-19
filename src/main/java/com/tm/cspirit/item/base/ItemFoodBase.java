package com.tm.cspirit.item.base;

import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ItemFoodBase extends ItemBase implements IItemSpiritSupplier {

    private final int maxSpiritStack;
    private final boolean drink;

    public ItemFoodBase(Properties properties, int maxSpiritStack, boolean drink) {
        super(properties);
        this.drink = drink;
        this.maxSpiritStack = maxSpiritStack;
    }

    public ItemFoodBase(int foodHeal, float saturationHeal, int stackSize, int maxSpiritStack, boolean drink) {
        this(new Item.Properties().stacksTo(stackSize).food(new FoodProperties.Builder().nutrition(foodHeal).saturationMod(saturationHeal).alwaysEat().build()), maxSpiritStack, drink);
    }

    public ItemFoodBase(int foodHeal, float saturationHeal, int maxSpiritStack, boolean drink) {
        this(foodHeal, saturationHeal, 64, maxSpiritStack, drink);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving) {
        EffectHelper.giveHolidaySpiritStackEffect((Player)entityLiving, maxSpiritStack);

        return super.finishUsingItem(stack, world, entityLiving);
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return drink ? UseAnim.DRINK : UseAnim.EAT;
    }

    @Override
    public int getMaxStacks() {
        return maxSpiritStack;
    }
}
