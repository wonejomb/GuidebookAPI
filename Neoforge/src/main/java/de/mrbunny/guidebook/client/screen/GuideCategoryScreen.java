package de.mrbunny.guidebook.client.screen;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.book.component.BookEntry;
import de.mrbunny.guidebook.wrapper.BookEntryWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuideCategoryScreen extends GuideScreen {

    private final ResourceLocation outlineTexture;
    private final ResourceLocation pagesTexture;
    private final IBook book;
    private final IBookCategory category;
    private HashMultimap<Integer, BookEntryWrapper> entryWrappers = HashMultimap.create();
    private int entryPage;
    @Nullable
    private IBookEntry startEntry;

    public GuideCategoryScreen (IBook pBook, IBookCategory pCategory, Player pPlayer, ItemStack pStack, @Nullable IBookEntry pStartEntry) {
        super(pCategory.getName(), pPlayer, pStack);
        this.book = pBook;
        this.category = pCategory;
        this.outlineTexture = pBook.getOutlineTexture();
        this.pagesTexture = pBook.getPagesTexture();
        this.entryPage = 0;
        this.startEntry = pStartEntry;
    }

    protected void init() {
        this.entryWrappers.clear();

        this.xOffset = (this.width - this.widthSize) / 2;
        this.yOffset = (this.height - this.heightSize) / 2;

        int entryX = this.xOffset + 25;
        int entryY = this.yOffset + 15;
        int index = 0;
        int pageNumber = 0;
        List<IBookEntry> entries = Lists.newArrayList(this.category.getEntries().values());

        for ( IBookEntry ientry : entries ) {

            BookEntry entry = (BookEntry) ientry;
            entry.init ();

            this.entryWrappers.put(pageNumber, new BookEntryWrapper(this.book, entry, this.getPlayer(), this.font, this.getBookStack(), entryX, entryY, 4 * widthSize / 6, 10));
            if ( entry.equals(this.startEntry) ){
                this.startEntry = null;
                this.entryPage = pageNumber;
            }
            entryY += 20;
            index++;

            if ( index >= 11 ) {
                index = 0;
                entryY = this.yOffset + 15;
                pageNumber++;
            }
        }
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        pGuiGraphics.blit(this.pagesTexture, this.xOffset, this.yOffset, 0, 0, this.getWidthSize(), this.getHeightSize(), this.widthSize, this.heightSize);
        pGuiGraphics.setColor(this.book.getColor().getRed() / 255.0F, this.book.getColor().getGreen() / 255.0F, this.book.getColor().getBlue() / 255.0F, 1.0F);
        pGuiGraphics.blit(this.outlineTexture, this.xOffset, this.yOffset, 0, 0, this.widthSize, this.heightSize, this.widthSize, this.heightSize);
        pGuiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        this.entryPage = Mth.clamp(entryPage, 0, this.entryWrappers.size() - 1);

        for (BookEntryWrapper wrapper : this.entryWrappers.get(this.entryPage)) {
            if ( wrapper.canView() ) {
                wrapper.render(pGuiGraphics, Minecraft.getInstance().level.registryAccess(), pMouseX, pMouseY, this);
                if ( wrapper.isMouseOnWrapper(pMouseX, pMouseY) ) {
                    wrapper.onHoverOver(pMouseX, pMouseY);
                }
            }
        }

        for (Renderable renderable : this.renderables) {
            renderable.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        }
    }
}
