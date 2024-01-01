package de.mrbunny.guidebook.api.book;

import de.mrbunny.guidebook.api.book.component.IBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.List;

public interface IBook {

    void initializeContent();

    Component getTitle ();

    Component getHeader ();
    Component getSubHeaderText ();

    Component getItemName ();

    Component getAuthor ();

    Color getColor ();

    ResourceLocation getId ();

    ResourceLocation getOutlineTexture ();
    ResourceLocation getPagesTexture ();

    boolean shouldSpawnWithBook ();

    List<IBookCategory> getCategories ();

}
