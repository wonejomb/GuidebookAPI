package de.wonejo.wuidebook.client.render;

import de.wonejo.wuidebook.api.client.PositionableRender;
import de.wonejo.wuidebook.client.button.OpenLinkButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public final class PositionableLinkRender implements PositionableRender {

    private final OpenLinkButton linkButton;

    public PositionableLinkRender( OpenLinkButton pLinkButton ) {
        this.linkButton = pLinkButton;
    }

    public void render(GuiGraphics pGraphics, int pX, int pY) {
        int i = this.linkButton.isActive() ? 16777215 : 10526880;
        this.linkButton.renderString(pGraphics, Minecraft.getInstance().font, i);
    }

}
