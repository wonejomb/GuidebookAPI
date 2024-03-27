package de.wonejo.gapi.api.book;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public interface IBook {

    void initializeContent ();

    boolean shouldSpawnWithBook ();

    ResourceLocation id ();

    ResourceLocation topTexture ();
    ResourceLocation pagesTexture ();
    ResourceLocation modelLocation ();

    Component title ();
    Component header ();
    Component subHeader ();
    Component itemName ();
    Component author ();

    Color color ();

}
