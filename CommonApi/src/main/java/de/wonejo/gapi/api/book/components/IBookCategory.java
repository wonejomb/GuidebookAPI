package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.client.render.ICategoryRender;
import de.wonejo.gapi.api.util.BookAccessible;
import de.wonejo.gapi.api.util.Clickable;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public interface IBookCategory extends BookAccessible, Clickable {

    ICategoryRender render ();
    Map<ResourceLocation, IBookEntry> entries ();


    void addEntry ( ResourceLocation pId, IBookEntry pEntry );
    void addEntries (Map<ResourceLocation, IBookEntry> pEntries);

    IBookEntry getEntry ( ResourceLocation pId );

}
