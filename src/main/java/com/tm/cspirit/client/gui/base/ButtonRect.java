package com.tm.cspirit.client.gui.base;

import com.tm.cspirit.main.CSReference;
import com.tm.cspirit.util.helper.ScreenHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

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

        this.setWidth(width);
        this.setHeight(16);

        rect = new ScreenRect(x, y, width, 16);
    }

    public void setPosition (int x, int y) {
        rect.x = x;
        this.setX(x);
        rect.y = y;
        this.setY(y);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {

        if (visible) {

            if (rect.contains(mouseX, mouseY)) {
                GL11.glColor4f(1F, 1F, 1F, 1F);
                isHovered = true;
            }

            else {
                GL11.glColor4f(0.8F, 0.8F, 0.8F, 8F);
                isHovered = false;
            }

            if (!active) {
                GL11.glColor4f(0.5F, 0.5F, 0.5F, 1F);
            }

            Minecraft.getInstance().getTextureManager().bind(CSReference.GUI_TEXTURES);
            ScreenHelper.drawCappedRect(rect.x, rect.y, 0, 240, 5, rect.width, rect.height, 256, 16);

            GL11.glColor4f(1, 1, 1, 1);

            ScreenHelper.drawCenteredString(guiGraphics, getMessage().getString(), rect.x + (rect.width / 2), rect.y + (rect.height - 8) / 2, 100, 0xFFFFFF);
        }
    }
}
