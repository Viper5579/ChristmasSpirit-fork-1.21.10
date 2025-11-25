package com.tm.cspirit.init;

import com.tm.cspirit.inventory.ContainerCookieTray;
import com.tm.cspirit.inventory.ContainerPresentUnwrapped;
import com.tm.cspirit.inventory.ContainerPresentWrapped;
import com.tm.cspirit.main.CSReference;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitContainerTypes {

    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CSReference.MOD_ID);

    public static final RegistryObject<MenuType<ContainerPresentUnwrapped>> PRESENT_UNWRAPPED = CONTAINER_TYPES.register(
            "present_unwrapped", () -> IForgeMenuType.create(ContainerPresentUnwrapped::new));

    public static final RegistryObject<MenuType<ContainerPresentWrapped>> PRESENT_WRAPPED = CONTAINER_TYPES.register(
            "present_wrapped", () -> IForgeMenuType.create(ContainerPresentWrapped::new));

    public static final RegistryObject<MenuType<ContainerCookieTray>> COOKIE_TRAY = CONTAINER_TYPES.register(
            "cookie_tray", () -> IForgeMenuType.create(ContainerCookieTray::new));
}
