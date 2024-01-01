package de.mrbunny.guidebook.api.client.book;

import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.render.IRenderable;
import de.mrbunny.guidebook.api.client.util.Clickable;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface IEntryRender extends IRenderable, Clickable {

    void renderExtras (GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookEntry pEntry, IModScreen pScreen, Font pFont);

    void init ();

}
