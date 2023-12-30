package de.mrbunny.guidebook.book.component;

import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public class BookCategory implements IBookCategory {

    private final Map<ResourceLocation, IBookEntry> entries;
    private final Component name;
    private String modId;

    public BookCategory(Map<ResourceLocation, IBookEntry> pEntries, Component pName) {
        this.entries = pEntries;
        this.name = pName;
    }

    public void addEntry(ResourceLocation pId, IBookEntry pEntry) {
        this.entries.put(pId, pEntry);
    }

    public void addEntries(Map<ResourceLocation, IBookEntry> pEntries) {
        this.entries.putAll(pEntries);
    }

    public void removeEntry(ResourceLocation pId) {
        this.entries.remove(pId);
    }

    public void removeEntries(ResourceLocation... pIds) {
        for ( ResourceLocation id : pIds )
            this.entries.remove(id);
    }

    public void removeEntries(List<ResourceLocation> pIds) {
        for ( ResourceLocation id : pIds )
            this.entries.remove(id);
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public IBookEntry getEntry(ResourceLocation pId) {
        return this.entries.get(pId);
    }

    public IBookEntry getEntry(String pID) {
        if ( this.modId == null )
            throw new NullPointerException("ModId of category must not be null when call BookCategory#getEntry.");

        return this.getEntry(new ResourceLocation(this.modId, pID));
    }

    public Component getName() {
        return name;
    }

    public Map<ResourceLocation, IBookEntry> getEntries() {
        return entries;
    }
}
