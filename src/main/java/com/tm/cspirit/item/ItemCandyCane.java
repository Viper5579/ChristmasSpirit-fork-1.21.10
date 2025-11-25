package com.tm.cspirit.item;

import com.tm.cspirit.block.base.BlockItemBase;
import com.tm.cspirit.item.base.IItemSpiritSupplier;
import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ItemCandyCane extends BlockItemBase implements IItemSpiritSupplier {

    public ItemCandyCane(Block block) {
        super(block, new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(0.8F).alwaysEdible().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving) {
        if (entityLiving instanceof Player player) {
            EffectHelper.giveHolidaySpiritStackEffect(player, 2);
        }
        return super.finishUsingItem(stack, world, entityLiving);
    }

    @Override
    public int getMaxStacks() {
        return 2;
    }
}
