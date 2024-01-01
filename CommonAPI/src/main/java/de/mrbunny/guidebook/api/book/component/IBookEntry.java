package de.mrbunny.guidebook.api.book.component;

import net.minecraft.network.chat.Component;

import java.util.List;

public interface IBookEntry {

    void init ();

    void addPage ( IPage pPage );
    void addPages ( List<IPage> pPages );
    void addPages ( IPage... pPages );


    void removePage ( IPage pPage );
    void removePages ( List<IPage> pPages );
    void removePages ( IPage... pPages );

    IPage getPage ( int pIndex );

    int getX ();
    int getY ();
    int getWidth ();
    int getHeight ();

    void setX(int pX);
    void setY ( int pY );
    void setWidth ( int pWidth );
    void setHeight ( int pHeight );

    Component getName ();
    List<IPage> getPages ();

}
