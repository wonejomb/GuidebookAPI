package de.mrbunny.guidebook.api.book.component;

import net.minecraft.network.chat.Component;

import java.util.List;

public interface IBookEntry {

    void addPage ( IPage pPage );
    void addPages ( List<IPage> pPages );
    void addPages ( IPage... pPages );


    void removePage ( IPage pPage );
    void removePages ( List<IPage> pPages );
    void removePages ( IPage... pPages );

    IPage getPage ( int pIndex );

    Component getName ();
    List<IPage> getPages ();
}
