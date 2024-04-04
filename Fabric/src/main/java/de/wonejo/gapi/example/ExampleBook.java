package de.wonejo.gapi.example;

import de.wonejo.gapi.api.GuidebookAPI;
import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.book.components.IBookPage;
import de.wonejo.gapi.api.impl.book.BookBuilder;
import de.wonejo.gapi.api.impl.book.BookInformationBuilder;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.book.BookCategory;
import de.wonejo.gapi.book.BookEntry;
import de.wonejo.gapi.book.BookPage;
import de.wonejo.gapi.client.render.category.ItemCategoryRender;
import de.wonejo.gapi.client.render.entry.ItemEntryRender;
import de.wonejo.gapi.client.render.page.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@GuidebookAPI
public class ExampleBook implements IGuidebook {

    public IBookBuilder build() {
        return BookBuilder.of(new ResourceLocation(Constants.MOD_ID, "example_book"))
                .spawnWithBook()
                .information(BookInformationBuilder.of()
                        .title(Component.literal("Example Information Title"))
                        .modName(Component.literal("GuidebookAPI (Gapi)"))
                        .credits(Component.literal("WonejoMB"))
                        .description(Component.literal("Just a example information description example."))
                        .build())
                .header(Component.literal("-- Example Header --"))
                .subHeader(Component.literal("-- Example SubHeader --"))
                .itemName(Component.literal("Example Book"))
                .author(Component.literal("WonejoMB"))
                .color(new Color(228, 18, 120))
                .contentProvider(this::contentProvider);
    }

    private void contentProvider(List<IBookCategory> pBookCategories) {
        BookCategory category = new BookCategory(new ItemCategoryRender(Component.literal("Example category"), new ItemStack(Items.POTATO)));
        category.addEntries(Map.of(new ResourceLocation("test"), this.exampleEntry()));

        pBookCategories.add(category);
    }

    private BookEntry exampleEntry ()  {
        BookEntry entry = new BookEntry(new ItemEntryRender(Component.literal("Example Entry"), new ItemStack(Items.CARROT)));

        entry.addPages(this.exampleEntryPages());

        return entry;
    }

    private List<IBookPage> exampleEntryPages ( ) {
        List<IBookPage> pages = new ArrayList<>();

        pages.add(new BookPage(new TextPageRender(Component.literal("This is an example text page."))));
        pages.add(new BookPage(new ImagePageRender(new ResourceLocation("textures/block/amethyst_block.png"), 16, 16)));
        pages.add(new BookPage(new TextImagePageRender(Component.literal("Just an example image page render with text"), new ResourceLocation("textures/block/amethyst_block.png"), 16, 16)));
        pages.add(new BookPage(new EntityPageRender(EntityType.ZOMBIE::create)));
        pages.add(new BookPage(new EntityTextPageRender(EntityType.ZOMBIE::create, Component.literal("This is the example of a entity page with text."))));
        pages.add(new BookPage(new JsonRecipePageRender(new ResourceLocation("iron_block"))));

        return pages;
    }

}
