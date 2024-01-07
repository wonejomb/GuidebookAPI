package de.mrbunny.guidebook.client.render.entry;

import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IEntryRender;
import de.mrbunny.guidebook.util.ComponentUtils;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;

public class EntryRender implements IEntryRender {
    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookEntry pEntry, IModScreen pScreen, Font pFont) {

        FormattedText entryName = pEntry.getName();
        int strWidth = pFont.width(entryName);
        int ellipsisWidth = pFont.width("...");

        if ( strWidth > pScreen.getWidthSize() - 80 && strWidth > ellipsisWidth ) {
            entryName = pFont.substrByWidth(entryName, pScreen.getWidthSize() - 80 - ellipsisWidth );
            entryName = FormattedText.composite(entryName, FormattedText.of("..."));
        }

        FormattedCharSequence sequence = Language.getInstance().getVisualOrder(entryName);
        if (ScreenUtils.isMouseBetween(pMouseX, pMouseY, pEntry.getX(), pEntry.getY(), pEntry.getWidth(), pEntry.getHeight())) {
            pGraphics.drawString(pFont, sequence, pEntry.getX() + 22, pEntry.getY() + 5, ChatFormatting.BLUE.getColor(), false);
        } else {
            pGraphics.drawString(pFont, sequence, pEntry.getX() + 22, pEntry.getY() + 4, ChatFormatting.DARK_GRAY.getColor(), false);
        }

        boolean cutString = strWidth > pScreen.getWidthSize() - 80 && strWidth > ellipsisWidth;

        if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, pEntry.getX(), pEntry.getY(), pEntry.getWidth(), pEntry.getHeight()) && cutString ) {
            String msg = ComponentUtils.parseEffect("guidebook.category.entry.select", pEntry.getName().getString());

            pGraphics.drawString(pFont, msg,
                    (pScreen.getXOffset() + pScreen.getWidthSize() / 2) - pFont.width(msg) / 2,
                    pScreen.getYOffset() - 15,
                    ChatFormatting.WHITE.getColor(),
                    false);
        }
    }

    public void init() {

    }
}
