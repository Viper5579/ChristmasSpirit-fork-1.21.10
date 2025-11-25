package com.tm.cspirit.block;

import com.tm.cspirit.item.base.IItemSpiritSupplier;
import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class BlockFruitCake extends CakeBlock implements IItemSpiritSupplier {

    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(1.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.box(3.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.box(5.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.box(7.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.box(9.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.box(11.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.box(13.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D)};

    public BlockFruitCake() {
        super(Properties.of().strength(0.5F).sound(SoundType.WOOL));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("hunger.icon.4"));
        tooltip.add(Component.translatable("saturation.icon.0.3"));
    }

    @Override
    protected InteractionResult eat(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
        player.awardStat(Stats.EAT_CAKE_SLICE);
        if (player.canEat(false)) player.getFoodData().eat(4, 0.3F);
        EffectHelper.giveHolidaySpiritStackEffect(player, 3);

        int i = state.getValue(BITES);

        if (i < 6) world.setBlock(pos, state.setValue(BITES, i + 1), 3);
        else world.removeBlock(pos, false);

        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(BITES)];
    }

    @Override
    public int getMaxStacks() {
        return 3;
    }
}
