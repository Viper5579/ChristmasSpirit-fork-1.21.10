package com.tm.cspirit.item;

import com.tm.cspirit.item.base.ItemBase;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemCookieCutter extends ItemBase {

    public ItemCookieCutter() {
        super(new Item.Properties().stacksTo(1).craftRemainder(null));
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(this);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }
}
