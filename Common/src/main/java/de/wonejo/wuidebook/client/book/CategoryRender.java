package de.wonejo.wuidebook.client.book;

import de.wonejo.wuidebook.api.book.BookInformation;
import de.wonejo.wuidebook.api.book.BookContent;
import de.wonejo.wuidebook.api.client.book.BookIcon;
import de.wonejo.wuidebook.api.client.book.BookRender;
import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.config.WuidebookConfig;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Optional;

public class CategoryRender implements BookRender<BookContent.Category> {

    private final BookIcon icon;

    public CategoryRender ( BookIcon pIcon ) {
        this.icon = pIcon;
    }

    public void render( GuiGraphics pGraphics, HolderLookup.Provider pProvider, BookContent.Category pElement, @NotNull BookInformation pInformation, Font pFont, int pX, int pY, int pMouseX, int pMouseY, float pFrameTime ) {
        Color textColor = WuidebookConfig.get().getValue(McEnvironment.CLIENT, "default.textColor");
        Optional<Color> configBookTextColor = WuidebookConfig.get().getOptValue(McEnvironment.CLIENT, "color.text.%s.%s".formatted(pInformation.id().getNamespace(), pInformation.id().getPath()));

        if (  configBookTextColor.isPresent() ) textColor = configBookTextColor.get();

        this.icon.render(pGraphics, (pX + pFont.width(pElement.displayName())) / 2 + (pFont.width(pElement.displayName()) / 2), pY);
        pGraphics.drawString(pFont, pElement.displayName(), (pX + pFont.width(pElement.displayName())) / 2, pY + 18, textColor.getRGB(), false);
    }

    public BookContent.ContentElement.TypeProvider type () {
        return BookContent.ContentElement.Type.CATEGORY;
    }

}
