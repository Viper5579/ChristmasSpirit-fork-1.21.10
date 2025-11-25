package com.tm.cspirit.init;

import com.tm.cspirit.entity.*;
import com.tm.cspirit.main.CSReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CSReference.MOD_ID);

    public static final RegistryObject<EntityType<EntityJackFrost>> JACK_FROST = ENTITY_TYPES.register("jack_frost",
            () -> EntityType.Builder.<EntityJackFrost>of(EntityJackFrost::new, MobCategory.MONSTER)
                    .sized(0.8F, 1.8F)
                    .build(ResourceLocation.fromNamespaceAndPath(CSReference.MOD_ID, "jack_frost").toString()));

    public static final RegistryObject<EntityType<EntityReindeer>> REINDEER = ENTITY_TYPES.register("reindeer",
            () -> EntityType.Builder.<EntityReindeer>of(EntityReindeer::new, MobCategory.CREATURE)
                    .sized(0.8F, 1.8F)
                    .build(ResourceLocation.fromNamespaceAndPath(CSReference.MOD_ID, "reindeer").toString()));

    public static final RegistryObject<EntityType<EntityCandyCaneProjectile>> CANDY_CANE_PROJECTILE = ENTITY_TYPES.register("candy_cane_projectile",
            () -> EntityType.Builder.<EntityCandyCaneProjectile>of(EntityCandyCaneProjectile::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .build(ResourceLocation.fromNamespaceAndPath(CSReference.MOD_ID, "candy_cane_projectile").toString()));

    public static final RegistryObject<EntityType<EntitySleigh>> SLEIGH = ENTITY_TYPES.register("sleigh",
            () -> EntityType.Builder.<EntitySleigh>of(EntitySleigh::new, MobCategory.MISC)
                    .sized(1.95F, 1.95F)
                    .build(ResourceLocation.fromNamespaceAndPath(CSReference.MOD_ID, "sleigh").toString()));

    public static final RegistryObject<EntityType<EntityChristmasTree>> CHRISTMAS_TREE = ENTITY_TYPES.register("christmas_tree",
            () -> EntityType.Builder.<EntityChristmasTree>of(EntityChristmasTree::new, MobCategory.MISC)
                    .sized(1.5F, 3F)
                    .build(ResourceLocation.fromNamespaceAndPath(CSReference.MOD_ID, "christmas_tree").toString()));
}
