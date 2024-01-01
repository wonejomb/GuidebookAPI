package de.mrbunny.guidebook.api.book.component;

import de.mrbunny.guidebook.api.client.book.ICategoryRender;
import de.mrbunny.guidebook.api.client.util.Clickable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public interface IBookCategory extends Clickable {

    Component getName ();

    void setX ( int pX );
    void setY ( int pY );
    int getX ();
    int getY ();

    void setWidth ( int pWidth );
    void setHeight ( int pHeight );
    int getWidth ();
    int getHeight ();

    void addEntry ( ResourceLocation pId, IBookEntry pEntry );
    void addEntries ( Map<ResourceLocation, IBookEntry> pEntries );

    void removeEntry ( ResourceLocation pId );
    void removeEntries ( List<ResourceLocation> pIds );
    void removeEntries ( ResourceLocation... pIds );

    IBookEntry getEntry ( ResourceLocation pId );

    IBookEntry getEntry ( String pID );

    void setModId ( String pId );

    ICategoryRender getRenderer ();
    Map<ResourceLocation, IBookEntry> getEntries ();
}
