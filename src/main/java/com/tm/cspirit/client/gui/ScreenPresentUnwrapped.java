package com.tm.cspirit.client.gui;

import com.mojang.blaze3d.platform.InputConstants;
import com.tm.cspirit.client.gui.base.ButtonRect;
import com.tm.cspirit.client.gui.base.ContainerScreenBase;
import com.tm.cspirit.client.gui.base.TextFieldRect;
import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.inventory.ContainerPresentUnwrapped;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.packet.PacketWrapPresent;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.present.PresentStyle;
import com.tm.cspirit.tileentity.TileEntityPresentUnwrapped;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Inventory;

    private final TileEntityPresentUnwrapped present;
    private final PresentConstructor constructor;

    private TextFieldRect toPlayerNameField;
    private ButtonRect dayBtn, styleBtn;

    public ScreenPresentUnwrapped(ContainerPresentUnwrapped container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
        inventoryLabelY = 102;
        constructor = new PresentConstructor();
        this.present = (TileEntityPresentUnwrapped) getTileEntity();
    }

    @Override
    protected void init() {
        super.init();

        if (minecraft != null) {

            //To Player Name Field
            toPlayerNameField = new TextFieldRect(minecraft.font, getScreenX() + 41, getScreenY() + 45, 94, 20, constructor.getFromPlayerName());
            addRenderableWidget(toPlayerNameField);

            //Day Button
            dayBtn = addRenderableWidget(new ButtonRect(getScreenX() + 26, getScreenY() + 63, 50, "Day " + constructor.getActualDay(), (btn) -> cycleDays()));

            //Style Button
            styleBtn = addRenderableWidget(new ButtonRect(getScreenX() + 100, getScreenY() + 63, 50, constructor.getStyle().getName(), (btn) -> cycleStyles()));

            //Wrap Button
            addRenderableWidget(new ButtonRect(getScreenX() + 51, getScreenY() + 84, 74, "Wrap Present", (btn) -> wrapPresent()));
        }
    }

    private void cycleDays() {
        constructor.setDay(constructor.getDay() + 1);

        if (constructor.getDay() > 32) {
            constructor.setDay(0);
        }

        dayBtn.setMessage(Component.literal("Day " + constructor.getActualDay()));
    }

    private void cycleStyles() {
        constructor.setStyleIndex(constructor.getStyleIndex() + 1);

        if (constructor.getStyleIndex() > PresentStyle.values().length - 1) {
            constructor.setStyleIndex(0);
        }

        styleBtn.setMessage(Component.literal(constructor.getStyle().getName()));
    }

    private boolean isPresentReady() {

        boolean notEmpty = !present.getInventory().getStackInSlot(0).isEmpty();
        boolean hasToPlayerName = !toPlayerNameField.getText().isEmpty();

        if (!notEmpty) present.getUnitName(player).printMessage(TextColor.fromLegacyFormat(ChatFormatting.RED), "The present is empty!");
        if (!hasToPlayerName) present.getUnitName(player).printMessage(TextColor.fromLegacyFormat(ChatFormatting.RED), "The present needs a player to go to!");

        return notEmpty && hasToPlayerName;
    }

    private void wrapPresent() {

        if (isPresentReady()) {
            onClose();
            constructor.setFromPlayerName(player.getDisplayName().getString());
            constructor.setToPlayerName(toPlayerNameField.getText());
            ChristmasSpirit.network.sendToServer(new PacketWrapPresent(constructor, present.getPos()));
            player.playSound(InitSounds.PRESENT_WRAP.get(), 1, 1);
        }
    }

    @Override
    protected void drawGuiBackground(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        toPlayerNameField.render(guiGraphics, mouseX, mouseY, 0);
    }

    @Override
    protected void drawGuiForeground(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (minecraft != null) guiGraphics.drawString(minecraft.font, "To:", getScreenX() + 24, getScreenY() + 49, TEXT_COLOR_GRAY, false);
    }

    @Override
    protected String getGuiTextureName() {
        return "present_unwrapped";
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        InputConstants.Key key = InputConstants.getKey(keyCode, scanCode);

        if (minecraft != null && minecraft.options.keyInventory.isActiveAndMatches(key)) {

            if (toPlayerNameField.isFocused()) {
                return true;
            }
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public int getGuiSizeY () {
        return 194;
    }
}
