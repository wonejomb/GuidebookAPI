package de.wonejo.gapi.api.book;

import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.util.GuideTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public interface IBookBuilder {

    IBookBuilder spawnWithBook ();

    IBookBuilder header (Component pHeader);
    IBookBuilder subHeader (Component pSubHeader);
    IBookBuilder itemName (Component pItemName);
    IBookBuilder author (Component pAuthor);

    IBookBuilder bookColor (Color pBookColor);
    IBookBuilder pagesColor (Color pPagesColor);
    IBookBuilder textColor (Color pTextColor);
    IBookBuilder entryColor (Color pEntryColor);
    IBookBuilder entryAboveColor (Color pEntryAboveColor);

    IBookBuilder topTexture (GuideTexture pTopTexture);
    IBookBuilder pagesTexture (GuideTexture pPagesTexture);
    IBookBuilder infoTexture (GuideTexture pInfoTexture);

    IBookBuilder modelLocation (ResourceLocation pModelLocation);

    IBookBuilder information ( IBookInformation pInformation );

    IBookBuilder contentProvider (Consumer<List<ICategory>> pContentProvider);

    IBook build ();

}
