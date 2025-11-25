package com.tm.cspirit.init;

import com.tm.cspirit.block.*;
import com.tm.cspirit.block.base.*;
import com.tm.cspirit.item.*;
import com.tm.cspirit.item.base.ItemArmorBase;
import com.tm.cspirit.item.base.ItemBase;
import com.tm.cspirit.item.base.ItemFoodBase;
import com.tm.cspirit.item.base.ItemSpawnEggBase;
import com.tm.cspirit.item.tier.CSArmorTiers;
import com.tm.cspirit.main.CSReference;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class InitItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CSReference.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CSReference.MOD_ID);

    //------ITEMS------\\

    //INGREDIENTS
    public static final RegistryObject<Item> FLOUR =                       regItem("flour", ItemBase::new);
    public static final RegistryObject<Item> ICING =                       regItem("icing", ItemBase::new);

    public static final RegistryObject<Item> FOOD_DYE_RED =                regItem("food_dye_red", ItemBase::new);
    public static final RegistryObject<Item> FOOD_DYE_GREEN =              regItem("food_dye_green", ItemBase::new);
    public static final RegistryObject<Item> FOOD_DYE_BLUE =               regItem("food_dye_blue", ItemBase::new);

    public static final RegistryObject<Item> PEPPERMINT_LEAF =             regItem("peppermint_leaf", ItemBase::new);
    public static final RegistryObject<Item> PEPPERMINT_CANDY_MIX =        regItem("peppermint_candy_mix", ItemBase::new);

    public static final RegistryObject<Item> SUGAR_COOKIE_DOUGH =          regItem("sugar_cookie_dough", ItemBase::new);
    public static final RegistryObject<Item> SUGAR_COOKIE_SHEET =          regItem("sugar_cookie_sheet", ItemBase::new);
    public static final RegistryObject<Item> GINGER_GROUND =               regItem("ginger_ground", ItemBase::new);
    public static final RegistryObject<Item> GINGERBREAD_DOUGH =           regItem("gingerbread_dough", ItemBase::new);
    public static final RegistryObject<Item> GINGERBREAD_SHEET =           regItem("gingerbread_sheet", ItemBase::new);

    public static final RegistryObject<Item> COOKIE_CUTTER_CIRCLE =        regItem("cookie_cutter_circle", ItemCookieCutter::new);
    public static final RegistryObject<Item> COOKIE_CUTTER_ORNAMENT =      regItem("cookie_cutter_ornament", ItemCookieCutter::new);
    public static final RegistryObject<Item> COOKIE_CUTTER_STAR =          regItem("cookie_cutter_star", ItemCookieCutter::new);
    public static final RegistryObject<Item> COOKIE_CUTTER_MAN =           regItem("cookie_cutter_man", ItemCookieCutter::new);
    public static final RegistryObject<Item> COOKIE_CUTTER_SNOWMAN =       regItem("cookie_cutter_snowman", ItemCookieCutter::new);

    public static final RegistryObject<Item> CHRISTMAS_LIGHT_WHITE =       regItem("christmas_light_white", ItemBase::new);
    public static final RegistryObject<Item> CHRISTMAS_LIGHT_RED =         regItem("christmas_light_red", ItemBase::new);
    public static final RegistryObject<Item> CHRISTMAS_LIGHT_GREEN =       regItem("christmas_light_green", ItemBase::new);
    public static final RegistryObject<Item> CHRISTMAS_LIGHT_BLUE =        regItem("christmas_light_blue", ItemBase::new);

    //FOOD
    public static final RegistryObject<Item> CHOCOLATE_BAR =               regItem("chocolate_bar", () -> new ItemFoodBase(4, 0.4F, 1, false));
    public static final RegistryObject<Item> PEPPERMINT_BARK =             regItem("peppermint_bark", () -> new ItemFoodBase(6, 0.6F, 2, false));

    public static final RegistryObject<Item> PEPPERMINT_CANDY_RED =        regItem("peppermint_candy_red", () -> new ItemFoodBase(4, 0.4F, 2, false));
    public static final RegistryObject<Item> PEPPERMINT_CANDY_GREEN =      regItem("peppermint_candy_green", () -> new ItemFoodBase(4, 0.4F, 2, false));
    public static final RegistryObject<Item> PEPPERMINT_CANDY_BLUE =       regItem("peppermint_candy_blue", () -> new ItemFoodBase(4, 0.4F, 2, false));

    public static final RegistryObject<Block> CANDY_CANE_POST_RED =       regBlock("candy_cane_red", BlockCandyCanePost::new);
    public static final RegistryObject<Block> CANDY_CANE_POST_GREEN =     regBlock("candy_cane_green", BlockCandyCanePost::new);
    public static final RegistryObject<Block> CANDY_CANE_POST_BLUE =      regBlock("candy_cane_blue", BlockCandyCanePost::new);

    public static final RegistryObject<Item> CANDY_CANE_RED =              regItem("candy_cane_red", () -> new ItemCandyCane(CANDY_CANE_POST_RED.get()));
    public static final RegistryObject<Item> CANDY_CANE_GREEN =            regItem("candy_cane_green", () -> new ItemCandyCane(CANDY_CANE_POST_GREEN.get()));
    public static final RegistryObject<Item> CANDY_CANE_BLUE =             regItem("candy_cane_blue", () -> new ItemCandyCane(CANDY_CANE_POST_BLUE.get()));

    public static final RegistryObject<Item> SUGAR_COOKIE_SANTA =          regItem("sugar_cookie_santa", ItemSantaCookie::new);

    public static final RegistryObject<Item> SUGAR_COOKIE_CIRCLE =         regItem("sugar_cookie_circle", () -> new ItemFoodBase(6, 0.6F, 2, false));
    public static final RegistryObject<Item> SUGAR_COOKIE_ORNAMENT =       regItem("sugar_cookie_ornament", () -> new ItemFoodBase(7, 0.7F, 2, false));
    public static final RegistryObject<Item> SUGAR_COOKIE_STAR =           regItem("sugar_cookie_star", () -> new ItemFoodBase(8, 0.8F, 2, false));
    public static final RegistryObject<Item> SUGAR_COOKIE_MAN =            regItem("sugar_cookie_man", () -> new ItemFoodBase(9, 0.9F, 3, false));
    public static final RegistryObject<Item> SUGAR_COOKIE_SNOWMAN =        regItem("sugar_cookie_snowman", () -> new ItemFoodBase(10, 0.10F, 3, false));

    public static final RegistryObject<Item> GINGERBREAD_COOKIE_CIRCLE =   regItem("gingerbread_cookie_circle", () -> new ItemFoodBase(7, 0.7F, 2, false));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE_ORNAMENT = regItem("gingerbread_cookie_ornament", () -> new ItemFoodBase(8, 0.8F, 2, false));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE_STAR =     regItem("gingerbread_cookie_star", () -> new ItemFoodBase(9, 0.9F, 2, false));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE_MAN =      regItem("gingerbread_cookie_man", () -> new ItemFoodBase(10, 0.10F, 3, false));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE_SNOWMAN =  regItem("gingerbread_cookie_snowman", () -> new ItemFoodBase(11, 0.11F, 3, false));

    public static final RegistryObject<Item> MUG_MILK =                    regItem("mug_milk", () -> new ItemFoodBase(4, 0.6F, 2, true));
    public static final RegistryObject<Item> MUG_HOT_CHOCOLATE =           regItem("mug_hot_chocolate", () -> new ItemFoodBase(7, 0.7F, 2, true));
    public static final RegistryObject<Item> MUG_EGGNOG =                  regItem("mug_eggnog", () -> new ItemFoodBase(7, 0.8F, 2, true));

    public static final RegistryObject<Item> SODA_COLA =                   regItem("soda_cola", () -> new ItemSoda(6, 0.6F));
    public static final RegistryObject<Item> SODA_GINGER_ALE =             regItem("soda_ginger_ale", () -> new ItemSoda(7, 0.7F));
    public static final RegistryObject<Item> SODA_SPRITE_CRANBERRY =       regItem("soda_sprite_cranberry", () -> new ItemSoda(8, 0.8F));

    //WEARABLES
    public static final RegistryObject<Item> CHRISTMAS_HAT =               regItem("christmas_hat", () -> new ItemArmorBase(CSArmorTiers.CHRISTMAS_HAT, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> BEANIE_BLACK =                regItem("beanie_black", () -> new ItemArmorBase(CSArmorTiers.BEANIE_BLACK, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> BEANIE_RED =                  regItem("beanie_red", () -> new ItemArmorBase(CSArmorTiers.BEANIE_RED, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> BEANIE_GREEN =                regItem("beanie_green", () -> new ItemArmorBase(CSArmorTiers.BEANIE_GREEN, EquipmentSlot.HEAD));

    public static final RegistryObject<Item> SWEATER_BLACK =               regItem("sweater_black", () -> new ItemArmorBase(CSArmorTiers.SWEATER_BLACK, EquipmentSlot.CHEST));
    public static final RegistryObject<Item> SWEATER_RED =                 regItem("sweater_red", () -> new ItemArmorBase(CSArmorTiers.SWEATER_RED, EquipmentSlot.CHEST));
    public static final RegistryObject<Item> SWEATER_GREEN =               regItem("sweater_green", () -> new ItemArmorBase(CSArmorTiers.SWEATER_GREEN, EquipmentSlot.CHEST));

    public static final RegistryObject<Item> WINTER_JEANS =                regItem("winter_jeans", () -> new ItemArmorBase(CSArmorTiers.WINTER_JEANS, EquipmentSlot.LEGS));

    public static final RegistryObject<Item> WINTER_BOOTS =                regItem("winter_boots", () -> new ItemArmorBase(CSArmorTiers.WINTER_BOOTS, EquipmentSlot.FEET));
    public static final RegistryObject<Item> ICE_SKATES =                  regItem("ice_skates", () -> new ItemArmorBase(CSArmorTiers.ICE_SKATES, EquipmentSlot.FEET));

    //DISCS
    public static final RegistryObject<Item> DISC_WISHBACKGROUND =         regItem("disc_wishbackground", () -> new ItemDisc(InitSounds.WISHBACKGROUND));
    public static final RegistryObject<Item> DISC_MCCHRISTMAS =            regItem("disc_mcchristmas", () -> new ItemDisc(InitSounds.MCCHRISTMAS));
    public static final RegistryObject<Item> DISC_JARED =                  regItem("disc_jared", () -> new ItemDisc(InitSounds.JARED));

    //OTHER
    public static final RegistryObject<Item> SLEIGH =                      regItem("sleigh", ItemSleigh::new);
    public static final RegistryObject<Item> CHRISTMAS_TREE =              regItem("christmas_tree", () -> new ItemChristmasTree(false));
    public static final RegistryObject<Item> CHRISTMAS_TREE_WHITE =        regItem("christmas_tree_white", () -> new ItemChristmasTree(true));

    public static final RegistryObject<Item> SPAWN_EGG_JACK_FROST =        regItem("spawn_egg_jack_frost", () -> new ItemSpawnEggBase(InitEntityTypes.JACK_FROST));
    public static final RegistryObject<Item> SPAWN_EGG_REINDEER =          regItem("spawn_egg_reindeer", () -> new ItemSpawnEggBase(InitEntityTypes.REINDEER));

    //------NAUGHTY ITEMS------\\

    public static final RegistryObject<Item> LUMP_OF_COAL =                regItem("lump_of_coal", () -> new ItemBase().addTag("naughty"));

    public static final RegistryObject<Item> FROST_INGOT =                 regItem("frost_ingot", () -> new ItemBase().addTag("naughty"));
    public static final RegistryObject<Item> FROST_HELMET =                regItem("frost_helmet", () -> new ItemFrostArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> FROST_CHESTPLATE =            regItem("frost_chestplate", () -> new ItemFrostArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> FROST_LEGGINGS =              regItem("frost_leggings", () -> new ItemFrostArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> FROST_BOOTS =                 regItem("frost_boots", () -> new ItemFrostArmor(EquipmentSlot.FEET));

    public static final RegistryObject<Item> FROSTMOURNE =                 regItem("frostmourne", ItemFrostmourne::new);

    public static final RegistryObject<Item> CANDY_CANE_CANNON =           regItem("candy_cane_cannon", ItemCandyCaneCannon::new);

    //------BLOCKS------\\

    public static final RegistryObject<Block> FRUITCAKE =              regBlockAndItem("fruitcake", BlockFruitCake::new);

    public static final RegistryObject<Block> PRESENT_UNWRAPPED =      regBlockAndItem("present_unwrapped", BlockPresentUnwrapped::new);

    public static final RegistryObject<Block> PRESENT_WRAPPED_RED =           regBlock("present_wrapped_red", BlockPresentWrapped::new);
    public static final RegistryObject<Item> PRESENT_WRAPPED_RED_ITEM =        regItem("present_wrapped_red", () -> new BlockItemPresentWrapped(PRESENT_WRAPPED_RED.get()));
    public static final RegistryObject<Block> PRESENT_WRAPPED_GREEN =         regBlock("present_wrapped_green", BlockPresentWrapped::new);
    public static final RegistryObject<Item> PRESENT_WRAPPED_GREEN_ITEM =      regItem("present_wrapped_green", () -> new BlockItemPresentWrapped(PRESENT_WRAPPED_GREEN.get()));
    public static final RegistryObject<Block> PRESENT_WRAPPED_BLUE =          regBlock("present_wrapped_blue", BlockPresentWrapped::new);
    public static final RegistryObject<Item> PRESENT_WRAPPED_BLUE_ITEM =       regItem("present_wrapped_blue", () -> new BlockItemPresentWrapped(PRESENT_WRAPPED_BLUE.get()));
    public static final RegistryObject<Block> PRESENT_WRAPPED_ORANGE =        regBlock("present_wrapped_orange", BlockPresentWrapped::new);
    public static final RegistryObject<Item> PRESENT_WRAPPED_ORANGE_ITEM =     regItem("present_wrapped_orange", () -> new BlockItemPresentWrapped(PRESENT_WRAPPED_ORANGE.get()));
    public static final RegistryObject<Block> PRESENT_WRAPPED_PINK =          regBlock("present_wrapped_pink", BlockPresentWrapped::new);
    public static final RegistryObject<Item> PRESENT_WRAPPED_PINK_ITEM =       regItem("present_wrapped_pink", () -> new BlockItemPresentWrapped(PRESENT_WRAPPED_PINK.get()));

    //CROPS
    public static final RegistryObject<Block> GINGER =                 regBlockAndItem("ginger", BlockCropBase::new);
    public static final RegistryObject<Block> PEPPERMINT =             regBlockAndItem("peppermint", () -> new BlockCropBase(PEPPERMINT_LEAF));

    //DECORATIONS
    public static final RegistryObject<Block> SNOWY_PATH =             regBlockAndItem("snowy_path", BlockSnowyPath::new);
    public static final RegistryObject<Block> FROSTED_GLASS =          regBlockAndItem("frosted_glass", BlockFrostedGlass::new);
    public static final RegistryObject<Block> FROSTED_GLASS_PANE =     regBlockAndItem("frosted_glass_pane", BlockFrostedGlassPane::new);

    public static final RegistryObject<Block> CANDY_CANE_BLOCK_RED =   regBlockAndItem("candy_cane_block_red", () -> new BlockBase(SoundType.NETHERRACK));
    public static final RegistryObject<Block> CANDY_CANE_BLOCK_GREEN = regBlockAndItem("candy_cane_block_green", () -> new BlockBase(SoundType.NETHERRACK));
    public static final RegistryObject<Block> CANDY_CANE_BLOCK_BLUE =  regBlockAndItem("candy_cane_block_blue", () -> new BlockBase(SoundType.NETHERRACK));

    public static final RegistryObject<Block> CANDY_CANE_BRICK_RED =   regBlockAndItem("candy_cane_brick_red", () -> new BlockBase(SoundType.NETHER_BRICK));
    public static final RegistryObject<Block> CANDY_CANE_BRICK_GREEN = regBlockAndItem("candy_cane_brick_green", () -> new BlockBase(SoundType.NETHER_BRICK));
    public static final RegistryObject<Block> CANDY_CANE_BRICK_BLUE =  regBlockAndItem("candy_cane_brick_blue", () -> new BlockBase(SoundType.NETHER_BRICK));

    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_MULTICOLOR =            regBlockAndItem("christmas_lights_multicolor", BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_MULTICOLOR_FLICKERING = regBlockAndItem("christmas_lights_multicolor_flickering", BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_WHITE =                 regBlockAndItem("christmas_lights_white", BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_WHITE_FLICKERING =      regBlockAndItem("christmas_lights_white_flickering", BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_RED =                   regBlockAndItem("christmas_lights_red", BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_GREEN =                 regBlockAndItem("christmas_lights_green", BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_BLUE =                  regBlockAndItem("christmas_lights_blue", BlockChristmasLights::new);

    public static final RegistryObject<Block> ORNAMENT_RED =           regBlockAndItem("ornament_red", BlockOrnament::new);
    public static final RegistryObject<Block> ORNAMENT_GREEN =         regBlockAndItem("ornament_green", BlockOrnament::new);
    public static final RegistryObject<Block> ORNAMENT_BLUE =          regBlockAndItem("ornament_blue", BlockOrnament::new);

    public static final RegistryObject<Block> STAR =                   regBlockAndItem("star", BlockStar::new);

    public static final RegistryObject<Block> STOCKING_RED =           regBlockAndItem("stocking_red", BlockStocking::new);
    public static final RegistryObject<Block> STOCKING_GREEN =         regBlockAndItem("stocking_green", BlockStocking::new);
    public static final RegistryObject<Block> STOCKING_BLUE =          regBlockAndItem("stocking_blue", BlockStocking::new);

    public static final RegistryObject<Block> CHIMNEY =                regBlockAndItem("chimney", BlockChimney::new);
    public static final RegistryObject<Block> ICICLES =                regBlockAndItem("icicles", BlockIcicles::new);
    public static final RegistryObject<Block> SNOW_GLOBE =             regBlockAndItem("snow_globe", BlockSnowGlobe::new);
    public static final RegistryObject<Block> GINGERBREAD_HOUSE =      regBlockAndItem("gingerbread_house", BlockGingerbreadHouse::new);
    public static final RegistryObject<Block> COOKIE_TRAY =            regBlockAndItem("cookie_tray", BlockCookieTray::new);
    public static final RegistryObject<Block> REEF =                   regBlockAndItem("reef", BlockReef::new);
    public static final RegistryObject<Block> GARLAND =                regBlockAndItem("garland", BlockGarland::new);
    public static final RegistryObject<Block> MISTLETOE =              regBlockAndItem("mistletoe", BlockMistletoe::new);


    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static RegistryObject<Item> regItem(String name, final Supplier<? extends Item> sup) {
        return ITEMS.register(name, sup);
    }

    public static RegistryObject<Block> regBlock(String name, final Supplier<? extends Block> sup) {
        return BLOCKS.register(name, sup);
    }

    public static RegistryObject<Block> regBlockAndItem(String name, final Supplier<? extends Block> sup) {
        RegistryObject<Block> registryBlock = BLOCKS.register(name, sup);
        RegistryObject<Item> registryItem = ITEMS.register(name, () -> new BlockItemBase(registryBlock.get()));
        return registryBlock;
    }
}
