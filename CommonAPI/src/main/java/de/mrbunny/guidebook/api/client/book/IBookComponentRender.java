package de.mrbunny.guidebook.api.client.book;

import de.mrbunny.guidebook.api.client.render.IRenderable;
import de.mrbunny.guidebook.api.client.util.Clickable;
import de.mrbunny.guidebook.api.client.util.Lockable;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface IBookComponentRender extends IRenderable, Clickable, Lockable {

    void renderExtras (GuiGraphics pGraphics, RegistryAccess pAccess, int pXOffset, int pYOffset, Font pFont);

    void init ();

}
