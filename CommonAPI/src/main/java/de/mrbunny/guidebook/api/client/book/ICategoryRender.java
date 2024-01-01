package de.mrbunny.guidebook.api.client.book;

import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.render.IRenderable;
import de.mrbunny.guidebook.api.client.util.Clickable;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface ICategoryRender extends IRenderable, Clickable {

    void renderExtras (GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookCategory pCategory, IModScreen pScreen, Font pFont);

    void init ();

}
