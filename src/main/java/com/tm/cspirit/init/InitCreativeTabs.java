package com.tm.cspirit.init;

import com.tm.cspirit.main.CSReference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class InitCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CSReference.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB_MAIN = CREATIVE_TABS.register("tab_main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + CSReference.MOD_ID + ".tabMain"))
                    .icon(() -> new ItemStack(InitItems.CHRISTMAS_HAT.get()))
                    .displayItems((parameters, output) -> {
                        // Main items
                        output.accept(InitItems.CHRISTMAS_HAT.get());
                        output.accept(InitItems.BEANIE_BLACK.get());
                        output.accept(InitItems.BEANIE_RED.get());
                        output.accept(InitItems.BEANIE_GREEN.get());
                        output.accept(InitItems.SWEATER_BLACK.get());
                        output.accept(InitItems.SWEATER_RED.get());
                        output.accept(InitItems.SWEATER_GREEN.get());
                        output.accept(InitItems.WINTER_JEANS.get());
                        output.accept(InitItems.WINTER_BOOTS.get());
                        output.accept(InitItems.ICE_SKATES.get());

                        output.accept(InitItems.SLEIGH.get());
                        output.accept(InitItems.CHRISTMAS_TREE.get());
                        output.accept(InitItems.CHRISTMAS_TREE_WHITE.get());

                        output.accept(InitItems.PRESENT_UNWRAPPED.get());
                        output.accept(InitItems.PRESENT_WRAPPED_RED_ITEM.get());
                        output.accept(InitItems.PRESENT_WRAPPED_GREEN_ITEM.get());
                        output.accept(InitItems.PRESENT_WRAPPED_BLUE_ITEM.get());
                        output.accept(InitItems.PRESENT_WRAPPED_ORANGE_ITEM.get());
                        output.accept(InitItems.PRESENT_WRAPPED_PINK_ITEM.get());

                        output.accept(InitItems.SPAWN_EGG_JACK_FROST.get());
                        output.accept(InitItems.SPAWN_EGG_REINDEER.get());

                        output.accept(InitItems.DISC_WISHBACKGROUND.get());
                        output.accept(InitItems.DISC_MCCHRISTMAS.get());
                        output.accept(InitItems.DISC_JARED.get());

                        // Naughty items
                        output.accept(InitItems.LUMP_OF_COAL.get());
                        output.accept(InitItems.FROST_INGOT.get());
                        output.accept(InitItems.FROST_HELMET.get());
                        output.accept(InitItems.FROST_CHESTPLATE.get());
                        output.accept(InitItems.FROST_LEGGINGS.get());
                        output.accept(InitItems.FROST_BOOTS.get());
                        output.accept(InitItems.FROSTMOURNE.get());
                        output.accept(InitItems.CANDY_CANE_CANNON.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> TAB_DECORATION = CREATIVE_TABS.register("tab_decoration",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + CSReference.MOD_ID + ".tabDecoration"))
                    .icon(() -> new ItemStack(InitItems.STAR.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(InitItems.SNOWY_PATH.get());
                        output.accept(InitItems.FROSTED_GLASS.get());
                        output.accept(InitItems.FROSTED_GLASS_PANE.get());

                        output.accept(InitItems.CANDY_CANE_BLOCK_RED.get());
                        output.accept(InitItems.CANDY_CANE_BLOCK_GREEN.get());
                        output.accept(InitItems.CANDY_CANE_BLOCK_BLUE.get());
                        output.accept(InitItems.CANDY_CANE_BRICK_RED.get());
                        output.accept(InitItems.CANDY_CANE_BRICK_GREEN.get());
                        output.accept(InitItems.CANDY_CANE_BRICK_BLUE.get());

                        output.accept(InitItems.CHRISTMAS_LIGHTS_MULTICOLOR.get());
                        output.accept(InitItems.CHRISTMAS_LIGHTS_MULTICOLOR_FLICKERING.get());
                        output.accept(InitItems.CHRISTMAS_LIGHTS_WHITE.get());
                        output.accept(InitItems.CHRISTMAS_LIGHTS_WHITE_FLICKERING.get());
                        output.accept(InitItems.CHRISTMAS_LIGHTS_RED.get());
                        output.accept(InitItems.CHRISTMAS_LIGHTS_GREEN.get());
                        output.accept(InitItems.CHRISTMAS_LIGHTS_BLUE.get());

                        output.accept(InitItems.ORNAMENT_RED.get());
                        output.accept(InitItems.ORNAMENT_GREEN.get());
                        output.accept(InitItems.ORNAMENT_BLUE.get());
                        output.accept(InitItems.STAR.get());

                        output.accept(InitItems.STOCKING_RED.get());
                        output.accept(InitItems.STOCKING_GREEN.get());
                        output.accept(InitItems.STOCKING_BLUE.get());

                        output.accept(InitItems.CHIMNEY.get());
                        output.accept(InitItems.ICICLES.get());
                        output.accept(InitItems.SNOW_GLOBE.get());
                        output.accept(InitItems.GINGERBREAD_HOUSE.get());
                        output.accept(InitItems.COOKIE_TRAY.get());
                        output.accept(InitItems.REEF.get());
                        output.accept(InitItems.GARLAND.get());
                        output.accept(InitItems.MISTLETOE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> TAB_BAKING = CREATIVE_TABS.register("tab_baking",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + CSReference.MOD_ID + ".tabBaking"))
                    .icon(() -> new ItemStack(InitItems.SUGAR_COOKIE_MAN.get()))
                    .displayItems((parameters, output) -> {
                        // Ingredients
                        output.accept(InitItems.FLOUR.get());
                        output.accept(InitItems.ICING.get());
                        output.accept(InitItems.FOOD_DYE_RED.get());
                        output.accept(InitItems.FOOD_DYE_GREEN.get());
                        output.accept(InitItems.FOOD_DYE_BLUE.get());
                        output.accept(InitItems.PEPPERMINT_LEAF.get());
                        output.accept(InitItems.PEPPERMINT_CANDY_MIX.get());
                        output.accept(InitItems.SUGAR_COOKIE_DOUGH.get());
                        output.accept(InitItems.SUGAR_COOKIE_SHEET.get());
                        output.accept(InitItems.GINGER_GROUND.get());
                        output.accept(InitItems.GINGERBREAD_DOUGH.get());
                        output.accept(InitItems.GINGERBREAD_SHEET.get());

                        // Cookie cutters
                        output.accept(InitItems.COOKIE_CUTTER_CIRCLE.get());
                        output.accept(InitItems.COOKIE_CUTTER_ORNAMENT.get());
                        output.accept(InitItems.COOKIE_CUTTER_STAR.get());
                        output.accept(InitItems.COOKIE_CUTTER_MAN.get());
                        output.accept(InitItems.COOKIE_CUTTER_SNOWMAN.get());

                        // Christmas lights
                        output.accept(InitItems.CHRISTMAS_LIGHT_WHITE.get());
                        output.accept(InitItems.CHRISTMAS_LIGHT_RED.get());
                        output.accept(InitItems.CHRISTMAS_LIGHT_GREEN.get());
                        output.accept(InitItems.CHRISTMAS_LIGHT_BLUE.get());

                        // Food
                        output.accept(InitItems.CHOCOLATE_BAR.get());
                        output.accept(InitItems.PEPPERMINT_BARK.get());
                        output.accept(InitItems.PEPPERMINT_CANDY_RED.get());
                        output.accept(InitItems.PEPPERMINT_CANDY_GREEN.get());
                        output.accept(InitItems.PEPPERMINT_CANDY_BLUE.get());
                        output.accept(InitItems.CANDY_CANE_RED.get());
                        output.accept(InitItems.CANDY_CANE_GREEN.get());
                        output.accept(InitItems.CANDY_CANE_BLUE.get());

                        output.accept(InitItems.SUGAR_COOKIE_SANTA.get());
                        output.accept(InitItems.SUGAR_COOKIE_CIRCLE.get());
                        output.accept(InitItems.SUGAR_COOKIE_ORNAMENT.get());
                        output.accept(InitItems.SUGAR_COOKIE_STAR.get());
                        output.accept(InitItems.SUGAR_COOKIE_MAN.get());
                        output.accept(InitItems.SUGAR_COOKIE_SNOWMAN.get());

                        output.accept(InitItems.GINGERBREAD_COOKIE_CIRCLE.get());
                        output.accept(InitItems.GINGERBREAD_COOKIE_ORNAMENT.get());
                        output.accept(InitItems.GINGERBREAD_COOKIE_STAR.get());
                        output.accept(InitItems.GINGERBREAD_COOKIE_MAN.get());
                        output.accept(InitItems.GINGERBREAD_COOKIE_SNOWMAN.get());

                        output.accept(InitItems.MUG_MILK.get());
                        output.accept(InitItems.MUG_HOT_CHOCOLATE.get());
                        output.accept(InitItems.MUG_EGGNOG.get());

                        output.accept(InitItems.SODA_COLA.get());
                        output.accept(InitItems.SODA_GINGER_ALE.get());
                        output.accept(InitItems.SODA_SPRITE_CRANBERRY.get());

                        // Crops and blocks
                        output.accept(InitItems.GINGER.get());
                        output.accept(InitItems.PEPPERMINT.get());
                        output.accept(InitItems.FRUITCAKE.get());
                    })
                    .build());
}
