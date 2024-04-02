package de.wonejo.gapi.example;

import de.wonejo.gapi.api.GuidebookAPI;
import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.impl.book.BookBuilder;
import de.wonejo.gapi.api.impl.book.BookInformationBuilder;
import de.wonejo.gapi.book.BookCategory;
import de.wonejo.gapi.book.BookEntry;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.api.util.GuideTexture;
import de.wonejo.gapi.client.render.category.ImageCategoryRender;
import de.wonejo.gapi.client.render.category.ItemCategoryRender;
import de.wonejo.gapi.client.render.entry.ImageEntryRender;
import de.wonejo.gapi.client.render.entry.ItemEntryRender;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@GuidebookAPI
public class ExampleBook implements IGuidebook {

    public IBookBuilder build() {
        return BookBuilder.of(new ResourceLocation(Constants.MOD_ID, "example_book"))
                .information(BookInformationBuilder.of()
                        .title(Component.literal("Title test"))
                        .credits(Component.literal("WonejoMB"))
                        .description(Component.literal("This is just a example and test book. So: TEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEST."))
                        .modName(Component.literal("GuidebookAPI (GAPI)"))
                        .build())
                .color(new Color(155, 12, 24))
                .author(Component.literal("WonejoMB"))
                .header(Component.literal("-- Test Header --"))
                .subHeader(Component.literal("Test SubHeader"))
                .contentProvider(this::contentProvider);
    }

    private void contentProvider(List<IBookCategory> pBookCategories) {
        List<IBookCategory> categories = new ArrayList<>();

        BookCategory category = new BookCategory(new ItemCategoryRender(Component.literal("Test ItemRender category"), new ItemStack(Items.CARROT)));
        category.addEntry(new ResourceLocation("test"), new BookEntry(new ItemEntryRender(Component.literal("Test entry"), new ItemStack(Items.CARROT))));
        category.addEntry(new ResourceLocation("test1"), new BookEntry(new ImageEntryRender(Component.literal("Test image entry"), new ResourceLocation("textures/block/acacia_door_bottom.png"))));

        categories.add(category);
        categories.add(new BookCategory(new ImageCategoryRender(Component.literal("Test ImageRender Category"), new ResourceLocation("textures/item/acacia_chest_boat.png"))));

        pBookCategories.addAll(categories);
    }

}
