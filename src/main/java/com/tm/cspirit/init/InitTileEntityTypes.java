package com.tm.cspirit.init;

import com.tm.cspirit.main.CSReference;
import com.tm.cspirit.tileentity.TileEntityCookieTray;
import com.tm.cspirit.tileentity.TileEntityPresentUnwrapped;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitTileEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CSReference.MOD_ID);

    public static final RegistryObject<BlockEntityType<TileEntityPresentUnwrapped>> PRESENT_UNWRAPPED = TILE_ENTITY_TYPES.register(
            "present_unwrapped", () -> BlockEntityType.Builder.of(TileEntityPresentUnwrapped::new, InitItems.PRESENT_UNWRAPPED.get()).build(null));

    public static final RegistryObject<BlockEntityType<TileEntityPresentWrapped>> PRESENT_WRAPPED = TILE_ENTITY_TYPES.register(
            "present_wrapped", () -> BlockEntityType.Builder.of(TileEntityPresentWrapped::new,
                    InitItems.PRESENT_WRAPPED_RED.get(),
                    InitItems.PRESENT_WRAPPED_GREEN.get(),
                    InitItems.PRESENT_WRAPPED_BLUE.get(),
                    InitItems.PRESENT_WRAPPED_ORANGE.get(),
                    InitItems.PRESENT_WRAPPED_PINK.get()).build(null));

    public static final RegistryObject<BlockEntityType<TileEntityCookieTray>> COOKIE_TRAY = TILE_ENTITY_TYPES.register(
            "cookie_tray", () -> BlockEntityType.Builder.of(TileEntityCookieTray::new, InitItems.COOKIE_TRAY.get()).build(null));
}
