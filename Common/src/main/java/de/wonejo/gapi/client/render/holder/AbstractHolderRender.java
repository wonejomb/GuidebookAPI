package de.wonejo.gapi.client.render.holder;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IHolder;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.IHolderRender;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.cfg.ModConfigurations;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

public abstract class AbstractHolderRender implements IHolderRender, IExtraRender {

    private static final ResourceLocation HOLDER_WIDGET_LOC = new ResourceLocation(Constants.MOD_ID, "textures/gui/holders.png");

    private final Component text;

    public AbstractHolderRender(Component pText) {
        this.text = pText;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pHolderX, int pHolderY, int pHolderIndex, int pMouseX, int pMouseY, IHolder pHolder, IBook pBook, IModScreen pScreen, Font pFont) {
        FormattedText text = FormattedText.of(this.text.getString());

        int strWidth = pFont.width(text);
        int ellipsisWidth = pFont.width("...");

        if ( pHolderIndex <= 6 ) {
            if (strWidth > pScreen.xOffset() - 79 && strWidth > ellipsisWidth) {
                text = pFont.substrByWidth(text, pScreen.screenWidth() / 2 - 25 - ellipsisWidth);
                text = FormattedText.composite(text, FormattedText.of("..."));
            }

            FormattedCharSequence sequence = Language.getInstance().getVisualOrder(text);
            pGraphics.drawString(pFont, sequence, pHolderX - pFont.width(text) / 2, pHolderY, ModConfigurations.CLIENT_PROVIDER.getBookTextColors().get(pBook).get().getRGB(), false);
        }

        if ( pHolderIndex > 6){
            if ( strWidth > pScreen.xOffset() + pScreen.screenWidth() + 79 && strWidth > ellipsisWidth ) {
                text = pFont.substrByWidth(text, pScreen.xOffset() + pScreen.screenWidth() + 75 - ellipsisWidth);
                text = FormattedText.composite(text, FormattedText.of("..."));
            }

            FormattedCharSequence sequence = Language.getInstance().getVisualOrder(text);
            pGraphics.drawString(pFont, sequence, pHolderX - pFont.width(text) / 2, pHolderY, ModConfigurations.CLIENT_PROVIDER.getBookTextColors().get(pBook).get().getRGB(), false);

        }

        if ( !RenderUtils.isMouseBetween(pMouseX, pMouseY, pHolderX, pHolderY, 79, 9) ) {

        }

        boolean cutString = strWidth > pScreen.screenWidth() + 79 + strWidth && strWidth > ellipsisWidth;
        if (RenderUtils.isMouseBetween(pMouseX, pMouseY, pHolderX, pHolderY, 79 + strWidth, 9) && cutString)
            pGraphics.renderTooltip(pFont, this.text, pMouseX, pMouseY);

    }

    public Component getText () {
        return text;
    }
}
