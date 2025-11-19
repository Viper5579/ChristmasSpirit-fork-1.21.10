package com.tm.cspirit.item.base;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public class ItemArmorBase extends ArmorItem {

    public ItemArmorBase(ArmorMaterial materialIn, ArmorItem.Type slot) {
        super(materialIn, slot, new Item.Properties());
    }
}
