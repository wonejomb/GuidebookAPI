package de.wonejo.gapi.impl.book.component;

import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.book.components.IPage;
import de.wonejo.gapi.api.client.render.IEntryRender;
import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;

public class Entry implements IEntry {

    private final List<IPage> pages = Lists.newArrayList();

    private final IEntryRender render;

    public Entry(IEntryRender pRender ) {
        this.render = pRender;
    }

    public void addPage(IPage pPage) {
        this.pages.add(pPage);
    }

    public void addPages(List<IPage> pPages) {
        this.pages.addAll(pPages);
    }

    public void addPages(IPage... pPages) {
        this.pages.addAll(Arrays.asList(pPages));
    }

    public IPage getPageById (int pIndex) {
        return this.pages.get(pIndex);
    }

    public List<IPage> getPages() {
        return this.pages;
    }

    public IEntryRender getRender () {
        return this.render;
    }

}
