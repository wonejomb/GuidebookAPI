package de.mrbunny.guidebook.client.button;

import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public final class NextButton extends GuideButton {

    public NextButton(OnPress pPress, IModScreen pScreen, int pX, int pY) {
        super(Component.translatable("guidebook.button.next"), pPress, pScreen, pX, pY, 16, 12);
    }

    @Environment(EnvType.CLIENT)
    protected void renderWidget(GuiGraphics pGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if ( this.visible ) {
            if (ScreenUtils.isMouseBetween(pMouseX, pMouseY, this.getX(), this.getY(), this.width, this.height)) {
                this.renderImage(pGraphics, this.getX(), this.getY() + 1, 16, 12, 16, 12);
            } else {
                this.renderImage(pGraphics, this.getX(), this.getY(), 0, 12, 16, 12);
            }
        }
    }
}
