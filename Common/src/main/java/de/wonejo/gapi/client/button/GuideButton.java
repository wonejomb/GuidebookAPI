package de.wonejo.gapi.client.button;

import de.wonejo.gapi.CommonGapiMod;
import de.wonejo.gapi.api.util.Accessible;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.api.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.NotNull;

public class GuideButton extends Button implements Accessible {

    protected static final ResourceLocation WIDGETS_LOCATION = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/gui/widgets.png");
    private final ButtonType type;

    public GuideButton(int pX, int pY, ButtonType pType, OnPress pOnPress) {
        super(pX, pY, pType.width(), pType.height(), pType.message(), pOnPress, DEFAULT_NARRATION);
        this.type = pType;
    }

    protected void renderWidget(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

        if ( this.visible ) {

            if (RenderUtils.isMouseBetween(pMouseX, pMouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
                pGuiGraphics.blit(WIDGETS_LOCATION, this.getX(), this.getY(),
                        this.type.hoverXOffset(), this.type.hoverYOffset(),
                        this.type.width(), this.type.height(),
                        42, 31);
                pGuiGraphics.renderTooltip(Minecraft.getInstance().font, this.getMessage(), pMouseX, pMouseY);
            } else {
                pGuiGraphics.blit(WIDGETS_LOCATION, this.getX(), this.getY(),
                        this.type.xOffset(), this.type.yOffset(),
                        this.type.width(), this.type.height(), 42, 31);
            }

        }

    }

    public void playDownSound(@NotNull SoundManager $$0) {
        CommonGapiMod.CLIENT_PROXY.playSound(SoundEvents.BOOK_PAGE_TURN);
    }

    public enum ButtonType {
        HOME(Component.translatable("gapi.book.button.home"), 12, 11, 30, 0, 30, 12),
        CATEGORY_HOME(Component.translatable("gapi.book.button.category.home"), 12, 11, 30, 0, 30, 12),
        INFORMATION(Component.translatable("gapi.book.button.information"), 10, 11, 0, 20, 11, 20),
        NEXT (Component.translatable("gapi.book.button.next"), 14, 9,0, 0, 15, 0),
        BACK (Component.translatable("gapi.book.button.back"), 14, 9,0, 10, 15, 10),
        ;

        private final Component message;
        private final int width;
        private final int height;
        private final int xOffset;
        private final int yOffset;
        private final int hoverXOffset;
        private final int hoverYOffset;

        ButtonType(Component pMessage, int pWidth, int pHeight, int pXOffset, int pYOffset, int pHoverXOffset, int pHoverYOffset) {
            this.message = pMessage;
            this.width = pWidth;
            this.height = pHeight;
            this.xOffset = pXOffset;
            this.yOffset = pYOffset;
            this.hoverXOffset = pHoverXOffset;
            this.hoverYOffset = pHoverYOffset;
        }

        public Component message () {
            return this.message;
        }

        public int width () {
            return this.width;
        }

        public int height () {
            return this.height;
        }

        public int xOffset () {
            return this.xOffset;
        }

        public int yOffset () {
            return this.yOffset;
        }

        public int hoverXOffset ()  {
            return this.hoverXOffset;
        }

        public int hoverYOffset ()  {
            return this.hoverYOffset;
        }
    }
}
