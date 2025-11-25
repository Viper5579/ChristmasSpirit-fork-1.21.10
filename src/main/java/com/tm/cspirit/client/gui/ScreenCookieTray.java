package com.tm.cspirit.client.gui;

import com.tm.cspirit.client.gui.base.ContainerScreenBase;
import com.tm.cspirit.inventory.ContainerCookieTray;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenCookieTray extends ContainerScreenBase<ContainerCookieTray> {

    public ScreenCookieTray(ContainerCookieTray container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
        inventoryLabelY = 38;
    }

    @Override
    protected void drawGuiBackground(GuiGraphics graphics, int mouseX, int mouseY) {}

    @Override
    protected void drawGuiForeground(GuiGraphics graphics, int mouseX, int mouseY) {}

    @Override
    protected String getGuiTextureName() {
        return "cookie_tray";
    }

    @Override
    public int getGuiSizeY () {
        return 132;
    }
}
