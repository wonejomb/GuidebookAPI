package de.wonejo.gapi.example;

import de.wonejo.gapi.api.GuidebookAPI;
import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.impl.book.BookBuilder;
import de.wonejo.gapi.api.util.Constants;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

@GuidebookAPI
public class ExampleBook implements IGuidebook {

    public IBookBuilder build() {
        return BookBuilder.of(new ResourceLocation(Constants.MOD_ID, "example_book"))
                .contentProvider(this::contentProvider);
    }

    private void contentProvider(List<IBookCategory> iBookCategories) {

    }

}
