package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.client.render.IEntryRender;
import de.wonejo.gapi.api.util.BookAccessible;
import de.wonejo.gapi.api.util.Clickable;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IBookEntry extends BookAccessible {

    IEntryRender render ();

    List<IBookPage> pages ();

    void addPage ( IBookPage pPage );
    void addPages (List<IBookPage> pPages);
    void addPages ( IBookPage... pPages );

    IBookPage getPage (int pIndex);
}
