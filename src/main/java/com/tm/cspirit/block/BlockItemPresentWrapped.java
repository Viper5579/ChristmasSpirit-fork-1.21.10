package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockItemBase;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import com.tm.cspirit.util.helper.TimeHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class BlockItemPresentWrapped extends BlockItemBase {

    public BlockItemPresentWrapped(Block block) {
        super(block);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {

        PresentConstructor constructor = PresentConstructor.fromStack(stack);

        if (!constructor.getToPlayerName().isEmpty()) {
            tooltip.add(Component.literal("From: " + ChatFormatting.GOLD + constructor.getFromPlayerName()));
            tooltip.add(Component.literal("To: " + ChatFormatting.GOLD + constructor.getToPlayerName()));
            tooltip.add(Component.literal("Open on the " + ChatFormatting.GOLD + TimeHelper.getFormattedDay(constructor.getActualDay())));
        }
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, BlockState state) {

        boolean b = super.placeBlock(context, state);

        Location location = new Location(context.getLevel(), context.getClickedPos());

        PresentConstructor constructor = PresentConstructor.fromStack(context.getItemInHand());
        constructor.toBlock(location);

        CompoundTag nbt = ItemHelper.getNBT(context.getItemInHand());
        CompoundTag compoundnbt = nbt.getList("Items", 10).getCompound(0);

        if (location.getTileEntity() instanceof TileEntityPresentWrapped tileEntity) {
            tileEntity.getInventory().setStackInSlot(0, ItemStack.parseOptional(context.getLevel().registryAccess(), compoundnbt));
        }

        return b;
    }
}
