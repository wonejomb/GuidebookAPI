package de.wonejo.gapi.client.render.page;

import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.IPageRender;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.config.ModConfigurations;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;

public class TextPageRender implements IPageRender {

    private final Component pageText;

    public TextPageRender ( Component pPageTex ) {
        this.pageText = pPageTex;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        RenderUtils.renderTextInRange(
                pGraphics,
                pFont,
                pageText,
                pScreen.xOffset() + 10,
                pScreen.yOffset() + 10,
                pScreen.widthSize() - 18,
                ModConfigurations.CLIENT.textColor.get()
        );
    }

}
