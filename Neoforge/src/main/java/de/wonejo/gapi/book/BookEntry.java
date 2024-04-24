package de.wonejo.gapi.book;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.book.components.IBookPage;
import de.wonejo.gapi.api.client.render.IEntryRender;
import de.wonejo.gapi.client.screen.EntryGuideScreen;
import net.minecraft.client.Minecraft;
import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;

public class BookEntry implements IBookEntry {

    private final List<IBookPage> pages = Lists.newArrayList();

    private final IEntryRender render;

    public BookEntry ( IEntryRender pRender ) {
        this.render = pRender;
    }

    public void onClick(IBook pBook, IBookCategory pCategory, double pMouseX, double pMouseY, int pClickType) {
        IBookEntry.super.onClick(pBook, pCategory, pMouseX, pMouseY, pClickType);

        Minecraft.getInstance().setScreen(new EntryGuideScreen(pBook, pCategory, this));
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
