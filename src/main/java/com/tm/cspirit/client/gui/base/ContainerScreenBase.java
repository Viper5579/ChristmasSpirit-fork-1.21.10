package com.tm.cspirit.client.gui.base;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tm.cspirit.inventory.base.ContainerBase;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Menu;

public abstract class ContainerScreenBase<T extends Menu> extends AbstractContainerScreen<T> {

    protected static final int TEXT_COLOR_GRAY = 0x555555;

    protected final Inventory playerInventory;
    protected final Player player;
    private final Menu container;

    protected ContainerScreenBase (T container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = getGuiSizeX();
        this.imageHeight = getGuiSizeY();
        this.container = container;
        this.playerInventory = playerInventory;
        this.player = playerInventory.player;
    }

    /**
     * Used to obtain the GUI's texture so it can render it.
     */
    protected abstract String getGuiTextureName ();

    /**
     * Used to render anything in the background layer.
     */
    protected abstract void drawGuiBackground (GuiGraphics guiGraphics, int mouseX, int mouseY);

    /**
     * Used to render anything in the foreground layer.
     */
    protected abstract void drawGuiForeground (GuiGraphics guiGraphics, int mouseX, int mouseY);

    /**
     * Used to determine the width of the GUI.
     */
    public int getGuiSizeX () {
        return 176;
    }

    /**
     * Used to determine the height of the GUI.
     */
    public int getGuiSizeY () {
        return 176;
    }

    /**
     * Used to determine the left of the GUI.
     */
    public int getScreenX () {
        return this.leftPos;
    }

    /**
     * Used to determine the top of the GUI.
     */
    public int getScreenY () {
        return this.topPos;
    }

    /**
     * @return The Tile Entity connected to the GUI.
     */
    public TileEntityInventoryBase getTileEntity () {

        if (container instanceof ContainerBase) {

            ContainerBase containerBase = (ContainerBase) container;

            if (containerBase.tileEntity != null) {
                return containerBase.tileEntity;
            }
        }

        return null;
    }

    /**
     * The base render method. Handles ALL rendering.
     */
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {

        renderBackground(guiGraphics);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        drawGuiForeground(guiGraphics, mouseX, mouseY);

        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        ResourceLocation texture = new ResourceLocation(com.tm.cspirit.main.CSReference.MOD_ID, "textures/gui/" + getGuiTextureName() + ".png");
        guiGraphics.blit(texture, getScreenX(), getScreenY(), 0, 0, getGuiSizeX(), getGuiSizeY());

        drawGuiBackground(guiGraphics, mouseX, mouseY);
    }
}
