package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.util.BookAccessible;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IBookEntry extends BookAccessible {

    ResourceLocation entryId ();
    Component name ();
    List<IBookPage> pages ();


    void init ();
    void addPage ( IBookPage pPage );
    void addPages (List<IBookPage> pPages);
    void addPages ( IBookPage... pPages );

    IBookPage getPage (int pIndex);
}
