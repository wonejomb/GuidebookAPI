package de.wonejo.gapi.api.impl.book.component;

import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.book.components.IBookPage;
import de.wonejo.gapi.api.client.render.IEntryRender;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;

public class BookEntry implements IBookEntry {

    private final List<IBookPage> pages = Lists.newArrayList();

    private final IEntryRender render;

    public BookEntry ( IEntryRender pRender ) {
        this.render = pRender;
    }

    public void init() {

    }

    public void addPage(IBookPage pPage) {
        this.pages.add(pPage);
    }

    public void addPages(List<IBookPage> pPages) {
        this.pages.addAll(pPages);
    }

    public void addPages(IBookPage... pPages) {
        this.pages.addAll(Arrays.asList(pPages));
    }

    public IBookPage getPage(int pIndex) {
        return this.pages.get(pIndex);
    }

    public List<IBookPage> pages() {
        return this.pages;
    }

    public IEntryRender render() {
        return this.render;
    }

}
