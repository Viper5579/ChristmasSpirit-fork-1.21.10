package com.tm.cspirit.block;

import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import com.tm.cspirit.util.helper.PresentHelper;
import com.tm.cspirit.util.helper.SoundHelper;
import com.tm.cspirit.util.helper.TimeHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockPresentWrapped extends BlockPresentUnwrapped {

    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 13, 15);

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return InitTileEntityTypes.PRESENT_WRAPPED.get().create(pos, state);
    }

    public static void spawnPresent(Location location, PresentConstructor constructor, ItemStack giftStack) {

        ItemStack stack = new ItemStack(constructor.getStyle().getBlock().asItem());

        constructor.toStack(stack);

        CompoundTag nbt = ItemHelper.getNBT(stack);
        NonNullList<ItemStack> giftList = NonNullList.create();
        giftList.add(0, giftStack);
        ContainerHelper.saveAllItems(nbt, giftList);

        ItemHelper.spawnStackAtLocation(location.world, location, stack);
    }

    @Override
    public BlockState onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {

        Location location = new Location(world, pos);
        BlockEntity tileEntity = location.getTileEntity();

        if (!world.isClientSide) {

            if (tileEntity instanceof TileEntityPresentWrapped present) {

                boolean isAnybody = present.getConstructor().getToPlayerName().equalsIgnoreCase("anybody");
                boolean isToPlayer = player.getDisplayName().getString().equalsIgnoreCase(present.getConstructor().getToPlayerName());
                boolean isFromPlayer = player.getDisplayName().getString().equalsIgnoreCase(present.getConstructor().getFromPlayerName());

                if (isAnybody || isToPlayer || isFromPlayer || player.isCreative()) {
                    spawnPresent(location, present.getConstructor(), present.getInventory().getStackInSlot(0));
                    location.setBlockToAir();
                }

                else present.getUnitName(player).printMessage(ChatFormatting.RED, "This present belongs to " + present.getConstructor().getToPlayerName() + "! You can't pick it up!");
            }
        }

        return state;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult result) {

        Location location = new Location(world, pos);
        BlockEntity tileEntity = location.getTileEntity();

        if (!world.isClientSide) {

            if (tileEntity instanceof TileEntityPresentWrapped present) {

                if (player.isCrouching()) {
                    present.getUnitName(player).printMessage(ChatFormatting.WHITE, "From: " + present.getConstructor().getFromPlayerName());
                    present.getUnitName(player).printMessage(ChatFormatting.WHITE, "To: " + present.getConstructor().getToPlayerName());
                    present.getUnitName(player).printMessage(ChatFormatting.WHITE, "Open on the " + TimeHelper.getFormattedDay(present.getConstructor().getActualDay()));
                }

                else if (player.getName().getString().equalsIgnoreCase(present.getConstructor().getToPlayerName()) || present.getConstructor().getToPlayerName().equalsIgnoreCase("anybody")) {

                    if (TimeHelper.getCurrentDay() >= present.getConstructor().getActualDay()) {

                        ItemStack stack = present.getInventory().getStackInSlot(0);

                        if (present.getConstructor().getFromPlayerName().equalsIgnoreCase("santa")) {
                            stack = PresentHelper.getSantaGiftedStack(player, present.getConstructor().getDay());
                        }

                        ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, stack);
                        location.setBlockToAir();

                        player.playSound(InitSounds.PRESENT_UNWRAP.get(), 1, 1);
                        SoundHelper.sendSoundToClient((ServerPlayer) player, InitSounds.PRESENT_UNWRAP.get());
                    }

                    else {
                        present.getUnitName(player).printMessage(ChatFormatting.RED, "You can't open this present yet!");
                        present.getUnitName(player).printMessage(ChatFormatting.RED, "You must wait until the " + TimeHelper.getFormattedDay(present.getConstructor().getActualDay()) + "!");
                    }
                }

                else {
                    present.getUnitName(player).printMessage(ChatFormatting.RED, "This present belongs to " + present.getConstructor().getToPlayerName() + "! You can't open it!");
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
