package de.wonejo.gapi.api.impl.book.component;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.client.render.ICategoryRender;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class BookCategory implements IBookCategory {

    private final Map<ResourceLocation, IBookEntry> entries = Maps.newHashMap();

    private final ResourceLocation id;
    private final Component name;
    private final ICategoryRender render;

    public BookCategory ( ResourceLocation pId, Component pName, ICategoryRender pRender ) {
        this.id = pId;
        this.name = pName;
        this.render = pRender;
    }

    public Component name() {
        return this.name;
    }

    public ResourceLocation categoryId() {
        return this.id;
    }

    public ICategoryRender render() {
        return this.render;
    }

    public Map<ResourceLocation, IBookEntry> entries() {
        return this.entries;
    }

    public void addEntry(ResourceLocation pId, IBookEntry pEntry) {
        this.entries.putIfAbsent(pId, pEntry);
    }

    public void addEntries(Map<ResourceLocation, IBookEntry> pEntries) {
        this.entries.putAll(pEntries);
    }

    public IBookEntry getEntry(ResourceLocation pId) {
        return this.entries.get(pId);
    }
}
