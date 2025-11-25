package com.tm.cspirit.block.base;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block, Item.Properties properties) {
        super(block, properties);
    }

    public BlockItemBase(Block block) {
        super(block, new Item.Properties());
    }
}
