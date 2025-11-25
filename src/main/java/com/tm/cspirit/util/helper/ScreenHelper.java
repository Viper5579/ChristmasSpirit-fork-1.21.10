package com.tm.cspirit.util.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tm.cspirit.main.CSReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class ScreenHelper {

    private static final int TEXTURE_SIZE = 256;
    private static final Minecraft mc = Minecraft.getInstance();

    private ScreenHelper() {}

    public static void bindGuiTextures () {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, CSReference.GUI_TEXTURES);
    }

    public static void bindTexture (String name) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, new ResourceLocation(CSReference.MOD_ID + ":textures/gui/" + name + ".png"));
    }

    public static void drawCappedRect (GuiGraphics graphics, int x, int y, int u, int v, int zLevel, int width, int height, int maxWidth, int maxHeight) {
        //TOP LEFT
        drawRect(graphics, x, y, u, v, zLevel, Math.min(width - 2, maxWidth), Math.min(height - 2, maxHeight));

        //RIGHT
        if (width <= maxWidth) drawRect(graphics, x + width - 2, y, u + maxWidth - 2, v, zLevel, 2, Math.min(height - 2, maxHeight));

        //BOTTOM
        if (height <= maxHeight) drawRect(graphics, x, y + height - 2, u, v + maxHeight - 2, zLevel, Math.min(width - 2, maxWidth), 2);

        //BOTTOM RIGHT
        if (width <= maxWidth && height <= maxHeight) drawRect(graphics, x + width - 2, y + height - 2, u + maxWidth - 2, v + maxHeight - 2, zLevel, 2, 2);
    }

    public static void drawRect (GuiGraphics graphics, int x, int y, int u, int v, int zLevel, int width, int height) {
        graphics.blit(CSReference.GUI_TEXTURES, x, y, zLevel, u, v, width, height, TEXTURE_SIZE, TEXTURE_SIZE);
    }

    public static void drawCenteredString (GuiGraphics graphics, String text, int x, int y, int color) {
        Font font = mc.font;
        graphics.drawString(font, text, x - (font.width(text) / 2), y, color, false);
    }
}
