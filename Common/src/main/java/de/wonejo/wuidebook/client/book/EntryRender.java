package de.wonejo.wuidebook.client.book;

import de.wonejo.wuidebook.api.book.BookInformation;
import de.wonejo.wuidebook.api.book.BookContent;
import de.wonejo.wuidebook.api.client.book.BookIcon;
import de.wonejo.wuidebook.api.client.book.BookRender;
import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.api.util.WuidebookUtils;
import de.wonejo.wuidebook.config.WuidebookConfig;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.HolderLookup;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Optional;

public class EntryRender implements BookRender<BookContent.Entry> {

    private final BookIcon icon;

    public EntryRender ( BookIcon pIcon ) {
        this.icon = pIcon;
    }

    public void render(GuiGraphics pGraphics, HolderLookup.Provider pProvider, BookContent.@NotNull Entry pElement, @NotNull BookInformation pInformation, @NotNull Font pFont, int pX, int pY, int pMouseX, int pMouseY, float pFrameTime) {
        Color entryColor = WuidebookConfig.get().getValue(McEnvironment.CLIENT, "default.entryColor");
        Optional<Color> entryBookColor = WuidebookConfig.get().getOptValue(McEnvironment.CLIENT, "color.entry.%s.%s".formatted(pInformation.id().getNamespace(), pInformation.id().getPath()));
        if  ( entryBookColor.isPresent() ) entryColor = entryBookColor.get();

        Color entryAboveColor = WuidebookConfig.get().getValue(McEnvironment.CLIENT, "default.entryAboveColor");
        Optional<Color> entryAboveBookColor = WuidebookConfig.get().getOptValue(McEnvironment.CLIENT, "color.entry.above.%s.%s".formatted(pInformation.id().getNamespace(), pInformation.id().getPath()));
        if ( entryAboveBookColor.isPresent() ) entryAboveColor = entryAboveBookColor.get();

        FormattedText displayName = pElement.displayName();

        int displayNameWidth = pFont.width(displayName);
        int ellipsisWidth = pFont.width("...");

        if ( displayNameWidth >= pX + 27 & displayNameWidth > ellipsisWidth ) {
            displayName = pFont.substrByWidth(displayName, pX + 27 - ellipsisWidth );
            displayName = FormattedText.composite(displayName, FormattedText.of("..."));
        }

        FormattedCharSequence sequence = Language.getInstance().getVisualOrder(displayName);
        boolean cutString = ((displayNameWidth > pX + 25) && (displayNameWidth > ellipsisWidth));

        if ( WuidebookUtils.isMouseBetween(pMouseX, pMouseY, pX, pY, 35, 10) ) {
            pGraphics.drawString(pFont, sequence, pX, pY, entryAboveColor.getRGB(), false);

            if ( cutString ) {
                pGraphics.renderTooltip(pFont, pElement.displayName(), pMouseX, pMouseY);
            }
        } else {
            pGraphics.drawString(pFont, sequence, pX, pY, entryColor.getRGB(), false);
        }

        this.icon.render(pGraphics, pX, pY);
    }

    public BookContent.ContentElement.TypeProvider type() {
        return BookContent.ContentElement.Type.ENTRY;
    }
}
