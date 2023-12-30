package de.mrbunny.guidebook.api.book;

import de.mrbunny.guidebook.api.book.component.ICategory;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.List;

public interface IBook {

    Component getHeader ();

    Component getItemName ();

    Component getAuthor ();

    Color getColor ();

    List<ICategory> getCategories ();

}
