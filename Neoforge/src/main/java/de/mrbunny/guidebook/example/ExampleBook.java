package de.mrbunny.guidebook.example;

import de.mrbunny.guidebook.api.Guidebook;
import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBookBuilder;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.book.BookBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.List;

@Guidebook
public class ExampleBook implements IGuidebook {

    public IBookBuilder buildBook() {
        return new BookBuilder(new ResourceLocation(References.GUIDEBOOKAPI_ID, "example_book"))
                .setColor(new Color(250, 50, 70))
                .setTitle(Component.literal("ExampleBook"))
                .setAuthor(Component.literal("ExampleGuy"))
                .contentProvider(this::buildBookContent);
    }

    private void buildBookContent (List<IBookCategory> pCategories) {

    }

}
