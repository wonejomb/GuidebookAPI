package de.mrbunny.guidebook.book.component;

import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IPage;
import net.minecraft.network.chat.Component;
import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;

public class BookEntry implements IBookEntry {

    private final List<IPage> pages;
    private final Component name;

    public BookEntry(List<IPage> pPages, Component pName ) {
        this.pages = pPages;
        this.name = pName;
    }

    public BookEntry(Component pName ) {
        this ( Lists.newArrayList(), pName );
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

    public void removePage(IPage pPage) {
        this.pages.remove(pPage);
    }

    public void removePages(List<IPage> pPages) {
        this.pages.removeAll(pPages);
    }

    public void removePages(IPage... pPages) {
        this.pages.removeAll(Arrays.asList(pPages));
    }

    public IPage getPage(int pIndex) {
        return this.pages.get(pIndex);
    }

    public Component getName() {
        return this.name;
    }

    public List<IPage> getPages() {
        return this.pages;
    }

}
