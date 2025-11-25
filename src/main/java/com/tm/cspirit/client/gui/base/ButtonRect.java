package com.tm.cspirit.client.gui.base;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tm.cspirit.util.helper.ScreenHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButtonRect extends Button {

    public final ScreenRect rect;

    /**
     * Used as the basic button for anything in the mod.
     * @param text The text rendered on the button.
     * @param pressable Called when the button is pressed.
     */
    public ButtonRect(int x, int y, int width, String text, OnPress pressable) {
        super(x, y, width, 16, Component.literal(text), pressable, DEFAULT_NARRATION);
        rect = new ScreenRect(x, y, width, 16);
    }

    public void setPosition (int x, int y) {
        rect.x = x;
        this.setX(x);
        rect.y = y;
        this.setY(y);
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {

        if (visible) {

            boolean hovered = rect.contains(mouseX, mouseY);
            this.isHovered = hovered;

            float color = active ? (hovered ? 1F : 0.8F) : 0.5F;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(color, color, color, 1F);

            ScreenHelper.bindGuiTextures();
            ScreenHelper.drawCappedRect(graphics, rect.x, rect.y, 0, 240, 5, rect.width, rect.height, 256, 16);

            RenderSystem.setShaderColor(1F, 1F, 1F, 1F);

            ScreenHelper.drawCenteredString(graphics, getMessage().getString(), rect.x + (rect.width / 2), rect.y + (rect.height - 8) / 2, 0xFFFFFF);
        }
    }
}
