package com.tm.cspirit.main;

import com.tm.cspirit.client.gui.ScreenCookieTray;
import com.tm.cspirit.client.gui.ScreenPresentUnwrapped;
import com.tm.cspirit.client.render.*;
import com.tm.cspirit.command.CSCommandBase;
import com.tm.cspirit.data.DailyPresentDataFile;
import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.data.SantaGiftListFile;
import com.tm.cspirit.entity.EntityJackFrost;
import com.tm.cspirit.entity.EntityReindeer;
import com.tm.cspirit.entity.data.CSDataSerializers;
import com.tm.cspirit.event.ItemTooltipOverrideEvent;
import com.tm.cspirit.event.SpawnEggRegisterEvent;
import com.tm.cspirit.init.*;
import com.tm.cspirit.packet.PacketReindeerJump;
import com.tm.cspirit.packet.PacketWrapPresent;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(CSReference.MOD_ID)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ChristmasSpirit {

    public static ChristmasSpirit instance;

    public static IEventBus MOD_EVENT_BUS;
    public static SimpleChannel network;

    public ChristmasSpirit() {

        instance = this;

        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_EVENT_BUS.addListener(this::onCommonSetup);
        MOD_EVENT_BUS.addListener(this::onClientSetup);
        MOD_EVENT_BUS.addListener(this::onLoadComplete);
        MOD_EVENT_BUS.addListener(this::onEntityAttributeCreation);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CSConfig.spec, CSReference.CONFIG_DIR + "/ChristmasSpirit.toml");
        EntityDataSerializers.registerSerializer(CSDataSerializers.ITEMSTACK_ARRAY_4);
        InitArmorMaterials.ARMOR_MATERIALS.register(MOD_EVENT_BUS);
        InitSounds.SOUNDS.register(MOD_EVENT_BUS);
        InitEffects.POTION_TYPES.register(MOD_EVENT_BUS);
        InitTileEntityTypes.TILE_ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitContainerTypes.CONTAINER_TYPES.register(MOD_EVENT_BUS);
        InitEntityTypes.ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitCreativeTabs.CREATIVE_MODE_TABS.register(MOD_EVENT_BUS);
        InitItems.init();
        DailyPresentDataFile.init();
        SantaGiftListFile.init();
        NaughtyListFile.init();
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {

        network = NetworkRegistry.newSimpleChannel(new ResourceLocation(CSReference.MOD_ID, CSReference.MOD_ID), () -> "1.0", s -> true, s -> true);
        network.registerMessage(0, PacketWrapPresent.class, PacketWrapPresent::toBytes, PacketWrapPresent::new, PacketWrapPresent::handle);
        network.registerMessage(1, PacketReindeerJump.class, PacketReindeerJump::toBytes, PacketReindeerJump::new, PacketReindeerJump::handle);

        InitEvents.init();
    }

    private void onEntityAttributeCreation(final EntityAttributeCreationEvent event) {
        event.put(InitEntityTypes.JACK_FROST.get(), EntityJackFrost.setCustomAttributes().build());
        event.put(InitEntityTypes.REINDEER.get(), EntityReindeer.setCustomAttributes().build());
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        InitRenderLayers.init();

        MenuScreens.register(InitContainerTypes.PRESENT_UNWRAPPED.get(), ScreenPresentUnwrapped::new);
        MenuScreens.register(InitContainerTypes.COOKIE_TRAY.get(), ScreenCookieTray::new);

        EntityRenderers.register(InitEntityTypes.JACK_FROST.get(), RenderJackFrost::new);
        EntityRenderers.register(InitEntityTypes.REINDEER.get(), RenderReindeer::new);
        EntityRenderers.register(InitEntityTypes.CANDY_CANE_PROJECTILE.get(), RenderCandyCaneProjectile::new);
        EntityRenderers.register(InitEntityTypes.SLEIGH.get(), RenderSleigh::new);
        EntityRenderers.register(InitEntityTypes.CHRISTMAS_TREE.get(), RenderChristmasTree::new);

        BlockEntityRenderers.register(InitTileEntityTypes.COOKIE_TRAY.get(), RenderCookieTray::new);

        MinecraftForge.EVENT_BUS.register(new SpawnEggRegisterEvent());
        MinecraftForge.EVENT_BUS.register(new ItemTooltipOverrideEvent());
    }

    private void onLoadComplete(final FMLLoadCompleteEvent event) {
        InitFreezeWorld.init();
    }

    @SubscribeEvent
    public void onServerStarting (ServerStartingEvent event) {
        CSCommandBase.register(event.getServer().getCommands().getDispatcher());
    }
}
