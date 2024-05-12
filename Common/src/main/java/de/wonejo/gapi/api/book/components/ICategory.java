package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.client.render.ICategoryRender;
import de.wonejo.gapi.api.util.Accessible;
import de.wonejo.gapi.api.util.Clickable;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public interface ICategory extends Accessible, Clickable {
    ICategoryRender getRender ();
    Map<ResourceLocation, IEntry> entries ();

    void addEntry ( ResourceLocation pId, IEntry pEntry );
    void addEntries ( Map<ResourceLocation, IEntry> pEntries );

    IEntry getEntry ( ResourceLocation pId );
}
