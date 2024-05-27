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

    }

    public void renderExtras(GuiGraphics pGraphics, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {

    }

    public Component getText () {
        return text;
    }
}
