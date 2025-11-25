package com.tm.cspirit.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ItemDisc extends RecordItem {

    public ItemDisc(Supplier<SoundEvent> soundSupplier) {
        super(15, soundSupplier, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 0);
    }
}
