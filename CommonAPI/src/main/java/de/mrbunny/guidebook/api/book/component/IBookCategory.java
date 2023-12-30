package de.mrbunny.guidebook.api.book.component;

import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public interface IBookCategory {

    void addEntry ( ResourceLocation pId, IBookEntry pEntry );
    void addEntries ( Map<ResourceLocation, IBookEntry> pEntries );

    void removeEntry ( ResourceLocation pId );
    void removeEntries ( List<ResourceLocation> pIds );
    void removeEntries ( ResourceLocation... pIds );

    IBookEntry getEntry ( ResourceLocation pId );

    IBookEntry getEntry ( String pID );

    void setModId ( String pId );

    Map<ResourceLocation, IBookEntry> getEntries ();
}
