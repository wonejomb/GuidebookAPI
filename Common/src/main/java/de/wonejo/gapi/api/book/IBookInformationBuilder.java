package de.wonejo.gapi.api.book;

import net.minecraft.network.chat.Component;

public interface IBookInformationBuilder {

    IBookInformationBuilder title ( Component pTitle );
    IBookInformationBuilder description ( Component pDescription );

    IBookInformation build ();
}
