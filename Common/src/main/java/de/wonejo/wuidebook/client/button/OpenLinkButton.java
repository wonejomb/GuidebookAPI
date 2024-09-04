package de.wonejo.wuidebook.client.button;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class OpenLinkButton extends Button {

    protected OpenLinkButton(String pLink, Component message, int x, int y ) {
        super(x, y, Minecraft.getInstance().font.width(message), 10, message, (button) -> Util.getPlatform().openUri(pLink), DEFAULT_NARRATION);
    }

    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int i = this.active ? 16777215 : 10526880;
        this.renderString(guiGraphics, Minecraft.getInstance().font, i | Mth.ceil(this.alpha * 255.0F) << 24);
    }

}
