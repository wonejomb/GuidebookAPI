package de.mrbunny.guidebook.client.button;

import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public final class SearchButton extends GuideButton{

    public SearchButton(OnPress pPress, IModScreen pScreen, int pX, int pY) {
        super(Component.translatable("guidebook.button.search"), pPress, pScreen, pX, pY, 14, 14);
    }

    @Environment(EnvType.CLIENT)
    protected void renderWidget(@NotNull GuiGraphics pGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if ( this.visible ) {
            if (ScreenUtils.isMouseBetween(pMouseX, pMouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
                this.renderImage(pGraphics, this.getX(), this.getY() + 1, 46, 0, this.getWidth(), this.getHeight());
            } else {
                this.renderImage(pGraphics, this.getX(), this.getY(), 32, 0, this.getWidth(), this.getHeight());
            }
        }
    }
}
