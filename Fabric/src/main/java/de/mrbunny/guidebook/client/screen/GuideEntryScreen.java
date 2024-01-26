package de.mrbunny.guidebook.client.screen;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.client.button.BackButton;
import de.mrbunny.guidebook.client.button.NextButton;
import de.mrbunny.guidebook.client.button.PreviousButton;
import de.mrbunny.guidebook.client.button.SearchButton;
import de.mrbunny.guidebook.config.ModConfigManager;
import de.mrbunny.guidebook.config.ModConfigurations;
import de.mrbunny.guidebook.ext.IScreenRenderablesAccessor;
import de.mrbunny.guidebook.wrapper.PageWrapper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuideEntryScreen extends GuideScreen {

    private final ResourceLocation borderTexture;
    private final ResourceLocation pagesTexture;
    private final IBook book;
    private final IBookCategory category;
    private final IBookEntry entry;
    private final List<PageWrapper> pageWrappers = new ArrayList<>();
    private int pageNumber;

    public GuideEntryScreen (IBook pBook, IBookCategory pCategory, IBookEntry pEntry, Player pPlayer, ItemStack pStack) {
        super(pEntry.getName(), pPlayer, pStack);
        this.book = pBook;
        this.category = pCategory;
        this.entry = pEntry;
        this.borderTexture = pBook.getOutlineTexture();
        this.pagesTexture = pBook.getPagesTexture();
        this.pageNumber = 0;
    }

    protected void init() {
        entry.init();
        this.pageWrappers.clear();

        this.xOffset = (this.width - this.widthSize) / 2;
        this.yOffset = (this.height - this.heightSize) / 2;

        this.addRenderableWidget(new NextButton((btn) -> {
            if ( this.pageNumber + 1 < this.pageWrappers.size() )
                nextPage();
        }, this, this.xOffset + this.widthSize / 2 + 15, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new PreviousButton((btn) -> {
            if ( this.pageNumber > 0 )
                this.previousPage();
        }, this, this.xOffset + this.widthSize / 2 - 35, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new BackButton((btn) -> {
            Minecraft.getInstance().setScreen(new GuideCategoryScreen(this.book, this.category, this.getPlayer(), this.getBookStack(), null));
        }, this, this.xOffset + this.widthSize / 2 - 55, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new SearchButton((btn) -> {
            this.minecraft.setScreen(new GuideSearchScreen(this.book, this.getPlayer(), this.getBookStack(), this));
        }, this, this.xOffset + this.widthSize / 2 + 40, this.yOffset + this.heightSize - 27));

        for (IPage page : this.entry.getPages()) {
            page.init();
            this.pageWrappers.add(
                    new PageWrapper(this, this.book, this.category, this.entry, page, this.getPlayer(), this.font, this.getBookStack(), this.xOffset, this.yOffset)
            );
        }
    }

    public boolean mouseClicked(double pMouseX, double pMouseY, int pTypeOfClick) {
        if ( !super.mouseClicked(pMouseX, pMouseY, pTypeOfClick) ) {
            for ( PageWrapper wrapper : this.pageWrappers ) {
                if ( wrapper.isMouseOnWrapper(pMouseX, pMouseY) && wrapper.canView() ) {
                    if ( pTypeOfClick == 0 ) {
                        this.pageWrappers.get(this.pageNumber).getPage().getRender().leftClick(this.book, pMouseX, pMouseY, this.getPlayer(), this.getBookStack());
                        return true;
                    }

                    if ( pTypeOfClick == 1 ) {
                        this.pageWrappers.get(this.pageNumber).getPage().getRender().rightClick(this.book, pMouseX, pMouseY, this.getPlayer(), this.getBookStack());
                        return true;
                    }
                }
            }

            return false;
        }

        return true;
    }

    public void tick() {
        if ( this.pageNumber < this.pageWrappers.size() )
            if ( this.pageWrappers.get(this.pageNumber).canView() )
                this.pageWrappers.get(this.pageNumber).getPage().getRender().tick();
    }

    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        float red = new Color(Integer.parseInt(String.valueOf(ModConfigManager.CLIENT.bookColors.get(this.book).get()))).getRed() / 255.0F;
        float green = new Color(Integer.parseInt(String.valueOf(ModConfigManager.CLIENT.bookColors.get(this.book).get()))).getGreen() / 255.0F;
        float blue = new Color(Integer.parseInt(String.valueOf(ModConfigManager.CLIENT.bookColors.get(this.book).get()))).getBlue() / 255.0F;

        pGuiGraphics.blit(this.pagesTexture, this.xOffset, this.yOffset, 0, 0, this.widthSize, this.heightSize, this.widthSize, this.heightSize);
        pGuiGraphics.setColor(red, green, blue, 1.0F);
        pGuiGraphics.blit(this.borderTexture, this.xOffset, this.yOffset, 0, 0, this.widthSize, this.heightSize, this.widthSize, this.heightSize);
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        pageNumber = Mth.clamp(pageNumber, 0, this.pageWrappers.size() - 1);

        if ( this.pageNumber < this.pageWrappers.size())
            if ( this.pageWrappers.get(this.pageNumber).canView() )
                this.pageWrappers.get(this.pageNumber).render(pGuiGraphics, Minecraft.getInstance().level.registryAccess(), pMouseX, pMouseY, this);

        pGuiGraphics.drawString(this.font, "%d/%d".formatted(this.pageNumber + 1, this.pageWrappers.size()),
                this.xOffset + this.widthSize / 2 - 10,
                this.yOffset + this.heightSize - 23, ChatFormatting.DARK_GRAY.getColor(),
                false);

        for ( Renderable renderable : ((IScreenRenderablesAccessor) this).getRenderables() )
            renderable.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    private void nextPage () {
        if ( this.pageNumber != this.pageWrappers.size() - 1 && !this.pageWrappers.isEmpty() )
            this.pageNumber++;
    }

    private void previousPage () {
        if ( this.pageNumber != 0 )
            this.pageNumber--;
    }
}
