package de.wonejo.gapi.api.book;

import net.minecraft.network.chat.Component;

public interface IBookInformationBuilder {

    IBookInformationBuilder title ( Component pTitle );
    IBookInformationBuilder modName ( Component pModName );
    IBookInformationBuilder description (Component pDescription);
    IBookInformationBuilder credits ( Component pCredits );

    IBookInformation build ();

}
