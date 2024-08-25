package de.wonejo.wuidebook.api.client.book;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public sealed interface BookIcon permits BookIcon.ImageIcon, BookIcon.ItemStackIcon, BookIcon.TextureIcon {

    @NotNull
    static BookIcon itemStack ( ItemStack pStack ) {
        return new ItemStackIcon(pStack);
    }

    @NotNull
    static BookIcon image ( ResourceLocation pImageLocation, int pImageWidth, int pImageHeight ) {
        return new ImageIcon(pImageLocation, pImageWidth, pImageHeight);
    }

    @NotNull
    static BookIcon texture ( ResourceLocation pImageLocation, int pImageWidth, int pImageHeight, int pXOffset, int pYOffset, int pTextureWidth, int pTextureHeight ) {
        return new TextureIcon(pImageLocation, pImageWidth, pImageHeight, pXOffset, pYOffset, pTextureWidth, pTextureHeight);
    }

    void render ( @NotNull GuiGraphics pGraphics, int pX, int pY );

    final class ItemStackIcon implements BookIcon {

        private final ItemStack item;

        private ItemStackIcon (ItemStack pItem) {
            this.item = pItem;
        }

        public void render(@NotNull GuiGraphics pGraphics, int pX, int pY) {
            pGraphics.renderItem(item, pX, pY);
        }
    }

    final class ImageIcon implements BookIcon {

        private final ResourceLocation imageLocation;
        private final int imageWidth;
        private final int imageHeight;

        public ImageIcon ( ResourceLocation pImageLocation, int pImageWidth, int pImageHeight ) {
            this.imageLocation = pImageLocation;
            this.imageWidth = pImageWidth;
            this.imageHeight = pImageHeight;
        }

        public void render(@NotNull GuiGraphics pGraphics, int pX, int pY) {
            pGraphics.blit(this.imageLocation, pX, pY, 0, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
        }

    }

    final class TextureIcon implements BookIcon {

        private final ResourceLocation imageLocation;
        private final int imageWidth;
        private final int imageHeight;
        private final int xOffset;
        private final int yOffset;
        private final int textureWidth;
        private final int textureHeight;

        public TextureIcon ( ResourceLocation pImageLocation, int pImageWidth, int pImageHeight, int pXOffset, int pYOffset, int pTextureWidth, int pTextureHeight ) {
            this.imageLocation = pImageLocation;
            this.imageWidth = pImageWidth;
            this.imageHeight = pImageHeight;
            this.xOffset =  pXOffset;
            this.yOffset = pYOffset;
            this.textureWidth = pTextureWidth;
            this.textureHeight = pTextureHeight;
        }

        public void render(@NotNull GuiGraphics pGraphics, int pX, int pY) {
            pGraphics.blit(this.imageLocation, pX, pY, 0, this.xOffset, this.yOffset, this.textureWidth, this.textureHeight, this.imageWidth, this.imageHeight);
        }

    }

}
