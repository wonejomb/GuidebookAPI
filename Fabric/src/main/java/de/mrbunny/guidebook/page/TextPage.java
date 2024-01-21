package de.mrbunny.guidebook.page;

import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IPageRender;
import de.mrbunny.guidebook.util.PageUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.FormattedText;

public class TextPage implements IPage, IPageRender {

    private final FormattedText text;

    public TextPage ( FormattedText pText ) {
        this.text = pText;
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        PageUtils.drawFormattedText(pGraphics, pScreen.getXOffset() + 26, pScreen.getYOffset() + 14, this.text);
    }
}
