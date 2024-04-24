package de.wonejo.gapi.api.client.render;

import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;

public interface IEntryRender extends CanView, ITick {

    void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pEntryX, int pEntryY, int pMouseX, int pMouseY, IBookEntry pCategory, IModScreen pScreen, Font pFont);
    void init ();

    Component name ();

}
