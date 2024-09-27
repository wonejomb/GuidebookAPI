package de.wonejo.wuidebook.api.book;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public interface BookInfo {

    static Builder builder () {
        return de.wonejo.wuidebook.impl.book.BookInfoImpl.builderImpl();
    }

    ResourceLocation bookId ();

    ResourceLocation modelLocation ();
    ResourceLocation mainBookGUITexture ();
    ResourceLocation pageBookGUITexture ();

    Component title ();
    Component header ();
    Component itemName ();
    Component author ();

    Color bookColor ();

    boolean shouldSpawnWithBook ();

    interface Builder {
        Builder withId ( ResourceLocation pBookId );
        Builder withModelLocation ( ResourceLocation pLocation );
        Builder withMainBookTexture ( ResourceLocation pLocation );
        Builder withPageBookTexture ( ResourceLocation pLocation );
        Builder setTitle ( Component pTitle );
        Builder setHeader ( Component pHeader );
        Builder setItemName ( Component pItemName );
        Builder setAuthor ( Component pAuthor );
        Builder withBookColor ( Color pColor );
        Builder shouldSpawnWithBook ();

        BookInfo build ();
    }

}
