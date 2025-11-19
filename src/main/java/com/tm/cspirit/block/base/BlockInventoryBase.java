package com.tm.cspirit.block.base;

import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import com.tm.cspirit.util.helper.InventoryHelper;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * The base class for Blocks that have Inventories.
 */
public abstract class BlockInventoryBase extends BlockTileEntityBase {

    /**
     * @param properties The specific properties for the Block. (Creative Tab, hardness, material, etc.)
     */
    public BlockInventoryBase(Properties properties) {
        super(properties);
    }

    /**
     * Drops all contents when the Block is broken.
     */
    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {

        Location location = new Location(world, pos);
        BlockEntity tileEntity = location.getTileEntity();

        if (tileEntity instanceof TileEntityInventoryBase) {

            TileEntityInventoryBase inv = (TileEntityInventoryBase) tileEntity;
            InventoryHelper.breakInventory(world, inv.getInventory(), location);
        }

        ItemHelper.spawnStackAtLocation(world, location, new ItemStack(asItem()));

        location.setBlockToAir();

        return true;
    }

    /**
     * Opens the gui of the Block.
     */
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {

        //Prevents client side.
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        Location location = new Location(world, pos);
        BlockEntity tileEntity = location.getTileEntity();

        if (player instanceof ServerPlayer && tileEntity instanceof MenuProvider) {
            player.openMenu((MenuProvider) tileEntity);
        }

        return InteractionResult.SUCCESS;
    }
}
