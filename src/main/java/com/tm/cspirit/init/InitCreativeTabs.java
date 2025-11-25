package com.tm.cspirit.init;

import com.tm.cspirit.main.CSReference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class InitCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CSReference.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB_MAIN = CREATIVE_MODE_TABS.register("tab_main",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + CSReference.MOD_ID + ".tabMain"))
            .icon(() -> new ItemStack(InitItems.CHRISTMAS_HAT.get()))
            .displayItems((parameters, output) -> {
                // Add all items from the main tab
                InitItems.ITEMS.getEntries().stream()
                    .map(RegistryObject::get)
                    .forEach(output::accept);
            })
            .build()
    );

    public static final RegistryObject<CreativeModeTab> TAB_DECORATION = CREATIVE_MODE_TABS.register("tab_decoration",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + CSReference.MOD_ID + ".tabDecoration"))
            .icon(() -> new ItemStack(InitItems.GINGERBREAD_HOUSE.get()))
            .displayItems((parameters, output) -> {
                // Items will be added via the item registration
            })
            .build()
    );

    public static final RegistryObject<CreativeModeTab> TAB_BAKING = CREATIVE_MODE_TABS.register("tab_baking",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + CSReference.MOD_ID + ".tabBaking"))
            .icon(() -> new ItemStack(InitItems.SUGAR_COOKIE_CIRCLE.get()))
            .displayItems((parameters, output) -> {
                // Items will be added via the item registration
            })
            .build()
    );
}
