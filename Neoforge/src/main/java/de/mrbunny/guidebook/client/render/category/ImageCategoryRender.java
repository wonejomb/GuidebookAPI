package de.mrbunny.guidebook.client.render.category;

import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.ICategoryRender;
import de.mrbunny.guidebook.util.ComponentUtils;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;

public class ImageCategoryRender extends CategoryRender{

    private final ResourceLocation image;

    public ImageCategoryRender ( ResourceLocation pImage ) {
        this.image = pImage;
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookCategory pCategory, IModScreen pScreen, Font pFont) {
        if (ScreenUtils.isMouseBetween(pMouseX, pMouseY, pCategory.getX(), pCategory.getY(), pCategory.getWidth(), pCategory.getHeight())) {
            ScreenUtils.drawScaledImage(pGraphics, this.image, pCategory.getX(), pCategory.getY() - 2, pCategory.getWidth(), pCategory.getHeight(), 1.1F);
        } else {
            ScreenUtils.drawScaledImage(pGraphics, this.image, pCategory.getX(), pCategory.getY() - 3, pCategory.getWidth(), pCategory.getHeight(), 1.1F);
        }

        super.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pCategory, pScreen, pFont);
    }
}
