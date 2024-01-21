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

public class CategoryRender implements ICategoryRender {

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookCategory pCategory, IModScreen pScreen, Font pFont) {
        if (ScreenUtils.isMouseBetween(pMouseX, pMouseY, pCategory.getX(), pCategory.getY(), pCategory.getWidth(), pCategory.getHeight()) ) {
            String msg = ComponentUtils.parse("guidebook.category.select", pCategory.getName().getString());

            pGraphics.drawString(pFont, msg,
                    pScreen.getXOffset() + pScreen.getWidthSize() / 2 - pFont.width(msg) / 2,
                    pScreen.getYOffset() - 15,
                    ChatFormatting.WHITE.getColor(),
                    false);
        }
    }

    public void init() {

    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }
}
