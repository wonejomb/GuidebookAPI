package de.wonejo.gapi.example;

import de.wonejo.gapi.api.GuidebookAPI;
import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.book.components.IPage;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.client.render.category.ItemCategoryRender;
import de.wonejo.gapi.client.render.entry.ItemEntryRender;
import de.wonejo.gapi.client.render.page.*;
import de.wonejo.gapi.impl.book.builder.BookBuilder;
import de.wonejo.gapi.impl.book.builder.BookInformationBuilder;
import de.wonejo.gapi.impl.book.component.Category;
import de.wonejo.gapi.impl.book.component.Entry;
import de.wonejo.gapi.impl.book.component.Page;
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

    public IBookBuilder builder() {
        return BookBuilder.newBuilder(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "example_book"))
                .information(BookInformationBuilder.of()
                        .title(Component.literal("Example Information Title"))
                        .description(Component.literal("Just a example information description example."))
                        .build())
                .header(Component.literal("-- Example Header --"))
                .subHeader(Component.literal("-- Example SubHeader --"))
                .itemName(Component.literal("Example Book"))
                .author(Component.literal("WonejoMB"))
                .bookColor(new Color(140, 18, 32))
                .contentProvider(this::contentProvider);
    }

    private void contentProvider(List<ICategory> pBookCategories) {
        Category category = new Category(new ItemCategoryRender(Component.literal("Example category"), new ItemStack(Items.POTATO)));
        category.addEntries(Map.of(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test"), this.exampleEntry()));

        pBookCategories.add(category);
    }

    private IEntry exampleEntry ()  {
        Entry entry = new Entry(new ItemEntryRender(Component.literal("Example Entry"), new ItemStack(Items.CARROT)));
        entry.addPages(this.exampleEntryPages());
        return entry;
    }

    private List<IPage> exampleEntryPages ( ) {
        List<IPage> pages = new ArrayList<>();

        pages.add(new Page(new TextPageRender(Component.literal("This is an example text page."))));
        pages.add(new Page(new ImagePageRender(ResourceLocation.withDefaultNamespace("textures/block/amethyst_block.png"), 16, 16)));
        pages.add(new Page(new TextImagePageRender(Component.literal("Just an example image page render with text"), ResourceLocation.withDefaultNamespace("textures/block/amethyst_block.png"), 16, 16)));
        pages.add(new Page(new EntityPageRender(EntityType.ZOMBIE::create)));
        pages.add(new Page(new EntityTextPageRender(EntityType.ZOMBIE::create, Component.literal("This is the example of a entity page with text."))));
        pages.add(new Page(new JsonRecipePageRender(ResourceLocation.withDefaultNamespace("iron_block"))));

        return pages;
    }

}
