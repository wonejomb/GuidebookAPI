package de.wonejo.gapi.client.render.entry;

import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.IEntryRender;
import de.wonejo.gapi.api.util.ComponentUtils;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.config.ModConfigurations;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;

public abstract class EntryRender implements IEntryRender {

    private final Component name;

    public EntryRender ( Component pName ) {
        this.name = pName;
    }

    public void init() {

    }

    public void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pEntryX, int pEntryY, int pMouseX, int pMouseY, IBookEntry pCategory, IModScreen pScreen, Font pFont) {
        FormattedText name = FormattedText.of(this.name.getString());

        int strWidth = pFont.width(name);
        int ellipsisWidth = pFont.width("...");

        if (strWidth > pScreen.widthSize() / 2 - 30 && strWidth > ellipsisWidth ) {
            name = pFont.substrByWidth(name, pScreen.widthSize() / 2 - 25 - ellipsisWidth );
            name = FormattedText.composite(name, FormattedText.of("..."));
        }

        int entryColor = ModConfigurations.CLIENT.entryColor.get().getRGB();
        int entryBetweenColor = ModConfigurations.CLIENT.entryBetweenColor.get().getRGB();

        FormattedCharSequence sequence = Language.getInstance().getVisualOrder(name);
        if (RenderUtils.isMouseBetween(pMouseX, pMouseY, pEntryX - 16, pEntryY, 116, 12)) {
            pGraphics.drawString(pFont, sequence, pEntryX, pEntryY, entryBetweenColor, false);
        } else {
            pGraphics.drawString(pFont, sequence, pEntryX, pEntryY, entryColor, false);
        }

        boolean cutString = strWidth > pScreen.widthSize() / 2 - 20 && strWidth > ellipsisWidth;

        if ( RenderUtils.isMouseBetween(pMouseX, pMouseY, pEntryX - 16, pEntryY, 115, 12) && cutString ) {
            String msg = ComponentUtils.parseEffect("gapi.category.entry.select", this.name.getString());

            pGraphics.drawString(pFont, msg, pScreen.xOffset() + pScreen.widthSize() / 2 - pFont.width(msg) / 2,
                    pScreen.yOffset() - 15,
                    ChatFormatting.WHITE.getColor(),
                    false);
        }


    }

    public Component name() {
        return this.name;
    }
}
