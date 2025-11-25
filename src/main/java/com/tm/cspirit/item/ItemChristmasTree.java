package com.tm.cspirit.item;

import com.tm.cspirit.entity.EntityChristmasTree;
import com.tm.cspirit.item.base.IItemSpiritSupplier;
import com.tm.cspirit.item.base.ItemBase;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class ItemChristmasTree extends ItemBase implements IItemSpiritSupplier {

    private final boolean isWhite;

    public ItemChristmasTree(boolean isWhite) {
        super(new Properties().stacksTo(1));
        this.isWhite = isWhite;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null) {
            EntityChristmasTree tree = new EntityChristmasTree(context.getLevel(), context.getClickLocation(), context.getRotation() + 180, isWhite);
            context.getLevel().addFreshEntity(tree);
            context.getItemInHand().shrink(1);

            if (!context.getLevel().isClientSide) {
                context.getPlayer().playSound(SoundEvents.WOOD_PLACE, 1, 1);
                context.getPlayer().playSound(SoundEvents.GRASS_PLACE, 1, 1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    @Override
    public int getMaxStacks() {
        return 2;
    }
}
