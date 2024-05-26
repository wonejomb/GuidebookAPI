package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.client.render.ICategoryRender;
import de.wonejo.gapi.api.util.Accessible;
import de.wonejo.gapi.api.util.Clickable;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public interface ICategory extends Accessible, Clickable {
    ICategoryRender getRender ();

    List<IHolder> holders ();
    Map<ResourceLocation, IEntry> entries ();

    void addHolder ( IHolder pHolder );
    void addHolders ( List<IHolder> pHolders );
    void addHolders ( IHolder... pHolders );

    void addEntry ( ResourceLocation pId, IEntry pEntry );
    void addEntries ( Map<ResourceLocation, IEntry> pEntries );

    IHolder getHolder (int pIndex);
    IEntry getEntry ( ResourceLocation pId );
}
