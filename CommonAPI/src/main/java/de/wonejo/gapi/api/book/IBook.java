package de.wonejo.gapi.api.book;

import net.minecraft.network.chat.Component;

import java.awt.*;

public interface IBook {

    Component title ();
    Component header ();
    Component subHeader ();
    Component itemName ();
    Component author ();

    Color color ();

}
