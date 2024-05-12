package de.wonejo.gapi.impl.book.component;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.client.render.ICategoryRender;
import de.wonejo.gapi.client.screen.CategoryGuideScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class Category implements ICategory {

    private final Map<ResourceLocation, IEntry> entries = Maps.newHashMap();

    private final ICategoryRender render;

    public Category(ICategoryRender pRender ) {
        this.render = pRender;
    }

    public ICategoryRender getRender() {
        return this.render;
    }

    public void onClick(IBook pBook, double pMouseX, double pMouseY, int pClickType) {
        if ( pClickType == 0 )
            Minecraft.getInstance().setScreen(new CategoryGuideScreen(pBook, this));
    }

    public Map<ResourceLocation, IEntry> entries() {
        return this.entries;
    }

    public void addEntry(ResourceLocation pId, IEntry pEntry) {
        this.entries.putIfAbsent(pId, pEntry);
    }

    public void addEntries(Map<ResourceLocation, IEntry> pEntries) {
        this.entries.putAll(pEntries);
    }

    public IEntry getEntry(ResourceLocation pId) {
        return this.entries.get(pId);
    }
}
