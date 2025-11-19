package com.tm.cspirit.tab;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.main.CSReference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CSTabDecoration extends ItemGroup {

    public CSTabDecoration() {
        super(CSReference.MOD_ID + ".tabDecoration");
    }

    @Override
    public ItemStack createIcon () {
        return new ItemStack(InitItems.GINGERBREAD_HOUSE.get());
    }
}
