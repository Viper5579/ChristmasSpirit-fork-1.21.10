package com.tm.cspirit.client.gui;

import com.tm.cspirit.client.gui.base.ContainerScreenBase;
import com.tm.cspirit.inventory.ContainerPresentUnwrapped;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenCookieTray extends ContainerScreenBase<ContainerPresentUnwrapped> {

    public ScreenCookieTray(ContainerPresentUnwrapped container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
        inventoryLabelY = 38;
    }

    @Override
    protected void drawGuiBackground(GuiGraphics guiGraphics, int mouseX, int mouseY) {}

    @Override
    protected void drawGuiForeground(GuiGraphics guiGraphics, int mouseX, int mouseY) {}

    @Override
    protected String getGuiTextureName() {
        return "cookie_tray";
    }

    @Override
    public int getGuiSizeY () {
        return 132;
    }
}
