package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockInventoryBase;
import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.tileentity.TileEntityCookieTray;
import com.tm.cspirit.tileentity.base.CSItemHandler;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockCookieTray extends BlockInventoryBase {

    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 1, 14);

    public BlockCookieTray() {
        super(Properties.of().strength(0.5F).sound(SoundType.METAL).noOcclusion());
    }

    public void takeNextCookie(Level world, Player player, CSItemHandler inv) {

        for (int i = 0; i < inv.getSlots(); i++) {

            if (inv.getStackInSlot(i).getCount() > 0) {

                ItemStack stack = inv.getStackInSlot(i).copy();
                stack.setCount(1);

                ItemHelper.spawnStackAtEntity(world, player, stack);
                inv.getStackInSlot(i).shrink(1);
                break;
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult result) {

        if (!player.isCrouching()) {

            Location location = new Location(world, pos);

            if (location.getTileEntity() instanceof TileEntityCookieTray cookieTray) {
                takeNextCookie(world, player, cookieTray.getInventory());
            }

            return InteractionResult.SUCCESS;
        }

        else return super.useWithoutItem(state, world, pos, player, result);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return InitTileEntityTypes.COOKIE_TRAY.get().create(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }
}
