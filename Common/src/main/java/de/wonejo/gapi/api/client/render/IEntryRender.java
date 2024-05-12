package de.wonejo.gapi.api.client.render;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.Clickable;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;

public interface IEntryRender extends IExtraRender, Clickable, CanView, ITick {

    void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pEntryX, int pEntryY, int pMouseX, int pMouseY, IEntry pEntry, IModScreen pScreen, IBook pBook, Font pFont );
    default void init () {}

    Component name ();
}
