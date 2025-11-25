package com.tm.cspirit.init;

import com.tm.cspirit.main.CSReference;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class InitArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
        DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, CSReference.MOD_ID);

    // Helper method to create armor material with vanilla leather stats
    private static ArmorMaterial createLeatherLike(String name, int durabilityMultiplier) {
        return new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 2);
                map.put(ArmorItem.Type.CHESTPLATE, 3);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 3);
            }),
            15, // enchantability
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Items.LEATHER),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(CSReference.MOD_ID, name))),
            0.0F, // toughness
            0.0F  // knockback resistance
        );
    }

    // Helper method to create armor material with diamond stats
    private static ArmorMaterial createDiamondLike(String name, Supplier<Ingredient> repairIngredient, float toughness, float knockbackResistance) {
        return new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }),
            10, // enchantability
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            repairIngredient,
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(CSReference.MOD_ID, name))),
            toughness,
            knockbackResistance
        );
    }

    public static final RegistryObject<ArmorMaterial> CHRISTMAS_HAT = ARMOR_MATERIALS.register("christmas_hat",
        () -> createLeatherLike("christmas_hat", 100));

    public static final RegistryObject<ArmorMaterial> BEANIE_BLACK = ARMOR_MATERIALS.register("beanie_black",
        () -> createLeatherLike("beanie_black", 100));

    public static final RegistryObject<ArmorMaterial> BEANIE_RED = ARMOR_MATERIALS.register("beanie_red",
        () -> createLeatherLike("beanie_red", 100));

    public static final RegistryObject<ArmorMaterial> BEANIE_GREEN = ARMOR_MATERIALS.register("beanie_green",
        () -> createLeatherLike("beanie_green", 100));

    public static final RegistryObject<ArmorMaterial> SWEATER_BLACK = ARMOR_MATERIALS.register("sweater_black",
        () -> createLeatherLike("sweater_black", 100));

    public static final RegistryObject<ArmorMaterial> SWEATER_RED = ARMOR_MATERIALS.register("sweater_red",
        () -> createLeatherLike("sweater_red", 150));

    public static final RegistryObject<ArmorMaterial> SWEATER_GREEN = ARMOR_MATERIALS.register("sweater_green",
        () -> createLeatherLike("sweater_green", 150));

    public static final RegistryObject<ArmorMaterial> WINTER_JEANS = ARMOR_MATERIALS.register("winter_jeans",
        () -> createLeatherLike("winter_jeans", 150));

    public static final RegistryObject<ArmorMaterial> WINTER_BOOTS = ARMOR_MATERIALS.register("winter_boots",
        () -> createLeatherLike("winter_boots", 150));

    public static final RegistryObject<ArmorMaterial> ICE_SKATES = ARMOR_MATERIALS.register("ice_skates",
        () -> createLeatherLike("ice_skates", 200));

    public static final RegistryObject<ArmorMaterial> FROST = ARMOR_MATERIALS.register("frost",
        () -> createDiamondLike("frost", () -> Ingredient.of(InitItems.FROST_INGOT.get()), 1.0F, 1.0F));
}
