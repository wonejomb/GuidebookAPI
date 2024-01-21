package de.mrbunny.guidebook.book;

import de.mrbunny.guidebook.api.book.IBookContentProvider;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IPage;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;

public class BookContentProvider implements IBookContentProvider {

    private final List<IBookCategory> categories = Lists.newArrayList();
    private final String modId;

    public BookContentProvider ( String pModId ) {
        this.modId = pModId;
    }

    public void buildContent(List<IBookCategory> pCategories) {
        pCategories.addAll(categories);
    }

    public BookContentProvider createCategory(IBookCategory pCategory) {
        this.categories.add(pCategory);
        return this;
    }

    public BookContentProvider createCategories(List<IBookCategory> pCategories) {
        this.categories.addAll(pCategories);
        return this;
    }

    public BookContentProvider createCategories(IBookCategory... pCategories) {
        this.categories.addAll(Arrays.asList(pCategories));
        return this;
    }

    public BookContentProvider createEntryToCategory(IBookCategory pCategory, ResourceLocation pId, IBookEntry pEntry) {
        if ( this.categories.contains(pCategory) )
            pCategory.addEntry(pId, pEntry);

        return this;
    }

    public BookContentProvider createEntryToCategory(IBookCategory pCategory, String pName, IBookEntry pEntry) {
        ResourceLocation id = new ResourceLocation(this.modId, pName);
        return this.createEntryToCategory(pCategory, id, pEntry);
    }

    public BookContentProvider createPageToEntry(IBookEntry pEntry, IPage pPage) {
        pEntry.addPage(pPage);
        return this;
    }

    public BookContentProvider createPagesForEntry(IBookEntry pEntry, List<IPage> pPages) {
        pEntry.addPages(pPages);
        return this;
    }

    public BookContentProvider createPagesForEntry(IBookEntry pEntry, IPage... pPages) {
        pEntry.addPages(pPages);
        return this;
    }

    public List<IBookCategory> getContent() {
        return this.categories;
    }
}
