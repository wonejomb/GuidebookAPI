package de.mrbunny.guidebook.api.book.component;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IEntry {

    void addPage ( IPage pPage );
    void addPages ( List<IPage> pPages );
    void addPages ( IPage... pPages );


    void removePage ( IPage pPage );
    void removePages ( List<IPage> pPages );
    void removePages ( IPage... pPages );

    ResourceLocation getId ();

    List<IPage> getPages ();
}
