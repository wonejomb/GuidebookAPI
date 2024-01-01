package de.mrbunny.guidebook.example;

import de.mrbunny.guidebook.api.Guidebook;
import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBookBuilder;
import de.mrbunny.guidebook.api.book.IBookContentProvider;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.book.BookBuilder;
import de.mrbunny.guidebook.book.BookContentProvider;
import de.mrbunny.guidebook.book.component.BookCategory;
import de.mrbunny.guidebook.book.component.BookEntry;
import de.mrbunny.guidebook.client.render.category.ImageCategoryRender;
import de.mrbunny.guidebook.client.render.category.ItemStackCategoryRender;
import de.mrbunny.guidebook.client.render.entry.ImageEntryRender;
import de.mrbunny.guidebook.client.render.entry.ItemStackEntryRender;
import de.mrbunny.guidebook.client.render.entry.TextEntryRender;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Guidebook
public class ExampleBook implements IGuidebook {

    public IBookBuilder buildBook() {
        return new BookBuilder(new ResourceLocation(References.GUIDEBOOKAPI_ID, "example_book"))
                .setColor(new Color(250, 50, 70))
                .setTitle(Component.literal("ExampleBook"))
                .setItemName(Component.literal("ExampleBook Item name"))
                .setHeader(Component.literal("ExampleBook Header"))
                .setSubHeaderText(Component.literal("ExampleBook sub header text"))
                .setAuthor(Component.literal("ExampleGuy"))
                .contentProvider(this::buildBookContent);
    }

    private void buildBookContent(List<IBookCategory> pCategories) {
        IBookContentProvider contentProvider = new BookContentProvider(References.GUIDEBOOKAPI_ID);

        Map<ResourceLocation, IBookEntry> itemStackEntries = new HashMap<>();

        itemStackEntries.put(new ResourceLocation(References.GUIDEBOOKAPI_ID, "items_text_entry"),
                new BookEntry(Component.literal("Text entry"), new TextEntryRender()));

        itemStackEntries.put(new ResourceLocation(References.GUIDEBOOKAPI_ID, "items_image_entry"),
                new BookEntry(Component.literal("Image entry"),
                        new ImageEntryRender(new ResourceLocation(References.GUIDEBOOKAPI_ID, "textures/gui/book/test/test_image_entry.png"))));

        itemStackEntries.put(new ResourceLocation(References.GUIDEBOOKAPI_ID, "item_itemstack_entry"),
                new BookEntry(Component.literal("ItemStack entry"),
                        new ItemStackEntryRender(new ItemStack(Items.DIAMOND))));

        IBookCategory itemStackCategory = new BookCategory(
                Component.literal("ItemStack category"),
                new ItemStackCategoryRender(new ItemStack(Blocks.STONE)),
                itemStackEntries
        );

        Map<ResourceLocation, IBookEntry> imageEntries = new HashMap<>();
        IBookCategory imageCategory = new BookCategory(
                Component.literal("Image category"),
                new ImageCategoryRender(new ResourceLocation(References.GUIDEBOOKAPI_ID, "textures/gui/book/test/hearth_test.png")),
                imageEntries
        );

        contentProvider.createCategories(itemStackCategory, imageCategory);
        contentProvider.buildContent(pCategories);
    }

}
