package com.tm.cspirit.client.gui.base;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tm.cspirit.inventory.base.ContainerBase;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import com.tm.cspirit.util.helper.ScreenHelper;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public abstract class ContainerScreenBase<T extends ContainerBase> extends AbstractContainerScreen<AbstractContainerMenu> {

    protected static final int TEXT_COLOR_GRAY = 0x555555;

    protected final Inventory playerInventory;
    protected final Player player;
    private final AbstractContainerMenu container;

    protected ContainerScreenBase(AbstractContainerMenu container, Inventory playerInventory, Component title) {
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
    protected abstract void drawGuiBackground (PoseStack matrix, int mouseX, int mouseY);

    /**
     * Used to render anything in the foreground layer.
     */
    protected abstract void drawGuiForeground (PoseStack matrix, int mouseX, int mouseY);

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
        return (this.width - getGuiSizeX()) / 2;
    }

    /**
     * Used to determine the top of the GUI.
     */
    public int getScreenY () {
        return (this.height - getGuiSizeY()) / 2;
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
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        renderBackground(matrixStack);

        super.render(matrixStack, mouseX, mouseY, partialTicks);

        drawGuiForeground(matrixStack, mouseX, mouseY);

        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {

        ScreenHelper.bindTexture(getGuiTextureName());
        ScreenHelper.drawRect(getScreenX(), getScreenY(), 0, 0, 0, getGuiSizeX(), getGuiSizeY());

        drawGuiBackground(matrixStack, mouseX, mouseY);
    }
}
