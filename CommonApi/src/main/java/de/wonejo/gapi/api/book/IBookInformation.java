package de.wonejo.gapi.api.book;

import net.minecraft.network.chat.Component;

public interface IBookInformation {

    Component title ();
    Component modName ();
    Component description ();
    Component credits ();

}
