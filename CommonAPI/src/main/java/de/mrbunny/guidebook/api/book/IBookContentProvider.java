package de.mrbunny.guidebook.api.book;

import de.mrbunny.guidebook.api.book.component.ICategory;
import de.mrbunny.guidebook.api.book.component.IEntry;
import de.mrbunny.guidebook.api.book.component.IPage;

import java.util.List;

public interface IBookContentProvider {

    IBookContentProvider createCategory ( ICategory pCategory );
    IBookContentProvider createCategories ( List<ICategory> pCategories );
    IBookContentProvider createCategories ( ICategory... pCategories );


    IBookContentProvider createEntryToCategory (ICategory pCategory, String name, IEntry pEntry);
    IBookContentProvider createEntriesForCategory ( ICategory pCategory, List<IEntry> pEntries );
    IBookContentProvider createEntriesForCategory ( ICategory pCategory, IEntry... pEntries);


    IBookContentProvider createPageToEntry (IEntry pEntry, IPage pPage);
    IBookContentProvider createPagesForEntry ( IEntry pEntry, List<IPage> pPages );
    IBookContentProvider createPagesForEntry ( IEntry pEntry, IPage... pPages );

    List<ICategory> buildContent ();
    List<ICategory> getCategories ();

}
