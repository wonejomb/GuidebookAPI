package de.mrbunny.guidebook.api.book;

import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IPage;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IBookContentProvider {

    IBookContentProvider createCategory ( IBookCategory pCategory );
    IBookContentProvider createCategories ( List<IBookCategory> pCategories );
    IBookContentProvider createCategories ( IBookCategory... pCategories );


    IBookContentProvider createEntryToCategory (IBookCategory pCategory, String pName, IBookEntry pEntry);
    IBookContentProvider createEntryToCategory (IBookCategory pCategory, ResourceLocation pId, IBookEntry pEntry);


    IBookContentProvider createPageToEntry (IBookEntry pEntry, IPage pPage);
    IBookContentProvider createPagesForEntry (IBookEntry pEntry, List<IPage> pPages );
    IBookContentProvider createPagesForEntry (IBookEntry pEntry, IPage... pPages );

    List<IBookCategory> getContent ();

}
