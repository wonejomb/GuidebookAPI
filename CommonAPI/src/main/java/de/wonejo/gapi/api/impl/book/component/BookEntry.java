package de.wonejo.gapi.api.impl.book.component;

import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.book.components.IBookPage;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class BookEntry implements IBookEntry {
    @Override
    public ResourceLocation entryId() {
        return null;
    }

    @Override
    public Component name() {
        return null;
    }

    @Override
    public List<IBookPage> pages() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void addPage(IBookPage pPage) {

    }

    @Override
    public void addPages(List<IBookPage> pPages) {

    }

    @Override
    public void addPages(IBookPage... pPages) {

    }

    @Override
    public IBookPage getPage(int pIndex) {
        return null;
    }
}
