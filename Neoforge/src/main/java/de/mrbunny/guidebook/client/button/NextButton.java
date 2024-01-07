package de.mrbunny.guidebook.client.button;

import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public final class NextButton extends GuideButton {

    public NextButton(OnPress pPress, IModScreen pScreen, int pX, int pY) {
        super(Component.translatable("guidebook.button.next"), pPress, pScreen, pX, pY, 16, 12);
    }

    @OnlyIn(Dist.CLIENT)
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
