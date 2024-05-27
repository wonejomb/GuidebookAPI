package de.wonejo.gapi.client.render.holder;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IHolder;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.Constants;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class HolderColorRender extends AbstractHolderRender{

    private static final ResourceLocation COLOR_WIDGET = new ResourceLocation(Constants.MOD_ID, "textures/gui/holder/color_holder.png");

    private final Color color;

    public HolderColorRender(Component pText, Color pColor) {
        super(pText);
        this.color = pColor;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pHolderX, int pHolderY, int pHolderIndex, int pMouseX, int pMouseY, IHolder pHolder, IBook pBook, IModScreen pScreen, Font pFont) {
        super.render(pGraphics, pAccess, pHolderX, pHolderY, pHolderIndex, pMouseX, pMouseY, pHolder, pBook, pScreen, pFont);
    }
}
