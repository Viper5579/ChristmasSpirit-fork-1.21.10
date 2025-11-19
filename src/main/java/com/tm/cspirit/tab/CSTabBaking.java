package com.tm.cspirit.tab;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.main.CSReference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CSTabBaking extends ItemGroup {

    public CSTabBaking() {
        super(CSReference.MOD_ID + ".tabBaking");
    }

    @Override
    public ItemStack createIcon () {
        return new ItemStack(InitItems.SUGAR_COOKIE_CIRCLE.get());
    }
}
