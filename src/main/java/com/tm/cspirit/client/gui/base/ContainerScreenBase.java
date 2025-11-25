package com.tm.cspirit.client.gui.base;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tm.cspirit.inventory.base.ContainerBase;
import com.tm.cspirit.main.CSReference;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public abstract class ContainerScreenBase<T extends ContainerBase> extends AbstractContainerScreen<T> {

    protected static final int TEXT_COLOR_GRAY = 0x555555;

    protected final Inventory playerInventory;
    protected final Player player;
    private final T container;

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
    protected abstract void drawGuiBackground (GuiGraphics graphics, int mouseX, int mouseY);

    /**
     * Used to render anything in the foreground layer.
     */
    protected abstract void drawGuiForeground (GuiGraphics graphics, int mouseX, int mouseY);

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
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        drawGuiForeground(graphics, mouseX, mouseY);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        ResourceLocation texture = new ResourceLocation(CSReference.MOD_ID + ":textures/gui/" + getGuiTextureName() + ".png");
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, texture);
        graphics.blit(texture, getScreenX(), getScreenY(), 0, 0, getGuiSizeX(), getGuiSizeY());

        drawGuiBackground(graphics, mouseX, mouseY);
    }
}
