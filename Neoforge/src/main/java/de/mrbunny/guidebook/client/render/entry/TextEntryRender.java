package de.mrbunny.guidebook.client.render.entry;

import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.cfg.ModConfigurations;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class TextEntryRender extends EntryRender {

    private final ResourceLocation textureLoc = new ResourceLocation(References.GUIDEBOOKAPI_ID, "textures/gui/book/icons/entry/text_entry.png");

    public TextEntryRender ( ) {
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookEntry pEntry, IModScreen pScreen, Font pFont) {

        if (ScreenUtils.isMouseBetween( pMouseX, pMouseY, pEntry.getX(), pEntry.getY(), pEntry.getWidth(), pEntry.getHeight() )) {
            Color color = new Color(ModConfigurations.CLIENT.entryHoverColor.get());

            float red = color.getRed() / 255.0F;
            float green = color.getGreen() / 255.0F;
            float blue = color.getBlue() / 255.0F;

            pGraphics.setColor(red, green, blue, 1.0F);
            ScreenUtils.drawImage(pGraphics, this.textureLoc, pEntry.getX(), pEntry.getY() + 1, 16, 16);
            pGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        } else {
            pGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            ScreenUtils.drawImage(pGraphics, this.textureLoc, pEntry.getX(), pEntry.getY(), 16, 16);
        }

        super.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pEntry, pScreen, pFont);
    }
}
