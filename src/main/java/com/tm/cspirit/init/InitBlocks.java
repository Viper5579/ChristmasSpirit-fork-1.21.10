package com.tm.cspirit.init;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class InitBlocks {

    public static final DeferredRegister<Block> BLOCKS = InitItems.BLOCKS;

    public static final RegistryObject<Block> PRESENT_UNWRAPPED = InitItems.PRESENT_UNWRAPPED;
    public static final RegistryObject<Block> PRESENT_WRAPPED_RED = InitItems.PRESENT_WRAPPED_RED;
    public static final RegistryObject<Block> PRESENT_WRAPPED_GREEN = InitItems.PRESENT_WRAPPED_GREEN;
    public static final RegistryObject<Block> PRESENT_WRAPPED_BLUE = InitItems.PRESENT_WRAPPED_BLUE;
    public static final RegistryObject<Block> PRESENT_WRAPPED_ORANGE = InitItems.PRESENT_WRAPPED_ORANGE;
    public static final RegistryObject<Block> PRESENT_WRAPPED_PINK = InitItems.PRESENT_WRAPPED_PINK;
    public static final RegistryObject<Block> COOKIE_TRAY = InitItems.COOKIE_TRAY;

    private InitBlocks() {
    }
}
