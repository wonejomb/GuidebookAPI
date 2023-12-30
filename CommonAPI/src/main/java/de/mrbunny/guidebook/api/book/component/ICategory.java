package de.mrbunny.guidebook.api.book.component;

import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public interface ICategory {

    void addEntry ( ResourceLocation pId, IEntry pEntry );
    void addEntries ( Map<ResourceLocation, IEntry> pEntries );

    void removeEntry ( ResourceLocation pId );
    void removeEntries ( List<ResourceLocation> pIds );
    void removeEntries ( ResourceLocation... pIds );

    Map<ResourceLocation, IEntry> getEntries ();
}
