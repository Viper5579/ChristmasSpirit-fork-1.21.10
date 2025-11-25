package com.tm.cspirit.item.base;

import net.minecraft.world.item.Item;

public class ItemBase extends Item implements IItemTag {

    private String[] tags = new String[] {};

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase() {
        super(new Item.Properties());
    }

    public ItemBase addTag(String tag) {
        tags = new String[tags.length + 1];
        tags[tags.length - 1] = tag;
        return this;
    }

    @Override
    public String[] getItemTags() {
        return tags;
    }
}
