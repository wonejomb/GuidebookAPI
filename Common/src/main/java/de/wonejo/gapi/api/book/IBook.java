package de.wonejo.gapi.api.book;

import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.util.GuideTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.List;

public interface IBook {

    ResourceLocation id ();

    List<ICategory> categories ();

    IBookInformation information ();

    boolean shouldSpawnWithBook ();

    boolean useCustomBookModel ();
    boolean useCustomBookTexture ();
    boolean useCustomPagesTexture ();
    boolean useCustomInfoPagesTexture ();

    GuideTexture topTexture ();
    GuideTexture pagesTexture ();
    GuideTexture infoTexture ();

    ResourceLocation modelLocation ();

    Component title ();
    Component header ();
    Component subHeader ();
    Component itemName ();
    Component author ();

    Color bookColor ();
    Color textColor ();
    Color pagesColor ();
    Color entryColor ();
    Color entryAboveColor ();

    void initializeContent ();

}
