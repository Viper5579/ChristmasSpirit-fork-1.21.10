package com.tm.cspirit.item;

import com.tm.cspirit.entity.EntitySleigh;
import com.tm.cspirit.item.base.ItemBase;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class ItemSleigh extends ItemBase {

    public ItemSleigh() {
     super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null) {
            EntitySleigh sleigh = new EntitySleigh(context.getLevel(), context.getClickLocation().x, context.getClickLocation().y, context.getClickLocation().z);
            sleigh.setYRot(context.getPlayer().getYRot());
            context.getLevel().addFreshEntity(sleigh);
            context.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
