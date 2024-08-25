package de.wonejo.wuidebook.api.book.content;

import de.wonejo.wuidebook.wrapper.PositionedWrapper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public interface BookContent {

    List<PositionedWrapper<?>> mainPageContent ();

    Category getCategory ( ResourceLocation pId );
    Map<ResourceLocation, Category> getCategories ();

    interface Category extends ContentElement {
        ResourceLocation id ();
        int index ();
        Component displayName ();
        boolean canBeOpen ();

        Map<ResourceLocation, Entry> getEntries ();
        Entry getEntry ( ResourceLocation pId );


        default Type type() {
            return Type.CATEGORY;
        }
    }

    interface Entry extends ContentElement{
        ResourceLocation id ();
        int index ();
        Component displayName ();
        boolean canBeOpen ();

        List<Page> pages ();
        Page getPage ( int pIndex );


        default Type type() {
            return Type.ENTRY;
        }
    }

    interface Page extends ContentElement {
        int index ();
        List<PositionedWrapper<?>> getContent ();

        default Type type() {
            return Type.PAGE;
        }
    }

    interface ContentElement {
        Type type ();

        enum Type implements TypeProvider {
            CATEGORY,
            ENTRY,
            PAGE
        }

        interface TypeProvider {}
    }

}
