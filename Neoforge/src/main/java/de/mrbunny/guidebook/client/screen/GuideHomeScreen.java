package de.mrbunny.guidebook.client.screen;

import com.google.common.collect.HashMultimap;
import com.mojang.logging.LogUtils;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.book.component.BookCategory;
import de.mrbunny.guidebook.client.button.BackButton;
import de.mrbunny.guidebook.client.button.NextButton;
import de.mrbunny.guidebook.client.button.PreviousButton;
import de.mrbunny.guidebook.client.button.SearchButton;
import de.mrbunny.guidebook.util.ComponentUtils;
import de.mrbunny.guidebook.wrapper.BookCategoryWrapper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class GuideHomeScreen extends GuideScreen {

    private final ResourceLocation borderTexture;
    private final ResourceLocation pagesTexture;
    private final IBook book;
    private final HashMultimap<Integer, BookCategoryWrapper> categoryWrappers = HashMultimap.create();
    private int categoryPage;

    public GuideHomeScreen(IBook pBook, Player pPlayer, ItemStack pBookStack) {
        super(pBook.getTitle(), pPlayer, pBookStack);

        this.book = pBook;
        this.borderTexture = pBook.getOutlineTexture();
        this.pagesTexture = pBook.getPagesTexture();
        this.categoryPage = 0;
    }

    protected void init() {
        this.categoryWrappers.clear();

        this.xOffset = ( this.width - this.widthSize ) / 2;
        this.yOffset = ( this.height - this.heightSize ) / 2;

        this.addRenderableWidget(new NextButton((btn) -> {
            if ( this.categoryPage + 1 < this.categoryWrappers.asMap().size() )
                this.nextPage();
        }, this, this.xOffset + this.widthSize / 2 + 15, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new PreviousButton((btn) -> {
            if ( this.categoryPage > 0 )
                this.previousPage();
        }, this, this.xOffset + this.widthSize / 2 - 35, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new BackButton((btn) -> Minecraft.getInstance().setScreen(this), this, this.xOffset + this.widthSize / 2 - 55, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new SearchButton((btn) -> {
             this.minecraft.setScreen(new GuideSearchScreen(this.book, this.getPlayer(), this.getBookStack(), this));
        }, this, this.xOffset + this.widthSize / 2 + 40, this.yOffset + this.heightSize - 27));

        int categoryX = this.getXOffset() + 30;
        int categoryY = this.getYOffset() + 45;
        int i = 0;
        int pageNumber = 0;

        for (IBookCategory icategory : book.getCategories()) {
            BookCategory category = (BookCategory) icategory;

            if ( category.getEntries().isEmpty() ) continue;
            category.getRenderer().init();

            int x = i % 5;
            int y = i / 5;
            this.categoryWrappers.put(pageNumber, new BookCategoryWrapper(this.book, category, this.getPlayer(), this.font, this.getBookStack(),categoryX + x * 27, categoryY + y * 30, 23, 23));
            i++;

            if ( i >= 20 ) {
                i = 0;
                pageNumber++;
            }
        }
    }

    public boolean mouseClicked(double pMouseX, double pMouseY, int pTypeOfClick) {
        if (!super.mouseClicked(pMouseX, pMouseX, pTypeOfClick)) {

            for ( BookCategoryWrapper wrapper : this.categoryWrappers.get(this.categoryPage) ) {
                if ( wrapper.isMouseOnWrapper(pMouseX, pMouseY) ) {
                    if ( pTypeOfClick == 0 )
                        wrapper.getCategory().leftClick(this.book, pMouseX, pMouseY, this.getPlayer(), this.getBookStack());
                    if ( pTypeOfClick == 1 )
                        wrapper.getCategory().rightClick(this.book, pMouseX, pMouseY, this.getPlayer(), this.getBookStack());

                    return true;
                }
            }
            return false;
        }

        return true;
    }

    public void tick() {
        for ( BookCategoryWrapper wrapper : this.categoryWrappers.get(this.categoryPage) )
            if ( wrapper.canView() ) wrapper.tick();
    }

    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        pGuiGraphics.blit(this.pagesTexture, this.getXOffset(), this.getYOffset(), 0, 0, this.getWidthSize(), this.getHeightSize(), this.getWidthSize(), this.getHeightSize());
        pGuiGraphics.setColor(this.book.getColor().getRed() / 255F, this.book.getColor().getGreen() / 255F, this.book.getColor().getBlue() / 255F, 1.0F);
        pGuiGraphics.blit(this.borderTexture, this.getXOffset(), this.getYOffset(), 0, 0, this.getWidthSize(), this.getHeightSize(), this.getWidthSize(), this.getHeightSize());
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawCenteredStringWithoutShadow(pGuiGraphics, this.font, this.book.getHeader(), this.getXOffset() + (this.widthSize / 2), this.getYOffset()  + 15, ChatFormatting.DARK_GRAY.getColor());

        if ( this.book.getSubHeaderText() != null ) {
            this.drawCenteredStringWithoutShadow(pGuiGraphics, this.font, this.book.getSubHeaderText(), this.getXOffset() + (this.widthSize / 2), this.getYOffset() + 25, ChatFormatting.DARK_GRAY.getColor());
        }

        this.categoryPage = Mth.clamp(categoryPage, 0, this.categoryWrappers.size() - 1);

        for ( BookCategoryWrapper wrapper : this.categoryWrappers.get(this.categoryPage) )
            if ( wrapper.canView() )
                wrapper.render(pGuiGraphics, Minecraft.getInstance().level.registryAccess(), pMouseX, pMouseY, this);

        pGuiGraphics.drawString(this.font, "%d/%d".formatted(this.categoryPage + 1, this.categoryWrappers.asMap().size()),
                this.xOffset + this.widthSize / 2 - 10,
                this.yOffset + this.heightSize - 23, ChatFormatting.DARK_GRAY.getColor(),
                false);

        for ( Renderable renderable : this.renderables )
            renderable.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    private void nextPage () {
        if ( this.categoryPage != this.categoryWrappers.asMap().size() - 1 && !this.categoryWrappers.asMap().isEmpty() )
            this.categoryPage++;
    }

    private void previousPage () {
        if  ( this.categoryPage != 0 )
            this.categoryPage--;
    }
}
