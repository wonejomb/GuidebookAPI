package de.wonejo.gapi.book;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.client.render.ICategoryRender;
import de.wonejo.gapi.api.util.ClickType;
import de.wonejo.gapi.client.screen.CategoryGuideScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class BookCategory implements IBookCategory {

    private final Map<ResourceLocation, IBookEntry> entries = Maps.newHashMap();

    private final ICategoryRender render;

    public BookCategory ( ICategoryRender pRender ) {
        this.render = pRender;
    }

    public ICategoryRender render() {
        return this.render;
    }

    public void onClick(IBook pBook, double pMouseX, double pMouseY, int pClickType) {
        if ( pClickType == ClickType.LEFT_CLICK.getId() )
            Minecraft.getInstance().setScreen(new CategoryGuideScreen(pBook, this));
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
