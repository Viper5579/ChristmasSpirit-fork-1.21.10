package com.tm.cspirit.client.gui.base;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class TextFieldRect extends EditBox {

    /**
     * Used as the basic text field for anything in the mod.
     * @param defaultString The initial text rendered on the button.
     */
    public TextFieldRect(Font fontRenderer, int x, int y, int width, int stringLimit, String defaultString) {

        super(fontRenderer, x + 1, y + 2, width, 12, Component.literal(defaultString));
        setTextColor(-1);
        setTextColorUneditable(-1);
        setBordered(true);
        setMaxLength(stringLimit);
        setValue(defaultString);
        setCanLoseFocus(true);
    }
}
