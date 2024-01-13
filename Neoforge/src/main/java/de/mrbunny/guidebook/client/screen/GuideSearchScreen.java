package de.mrbunny.guidebook.client.screen;

import com.google.common.collect.Lists;
import de.mrbunny.guidebook.GuidebookMod;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.client.button.BackButton;
import de.mrbunny.guidebook.client.button.NextButton;
import de.mrbunny.guidebook.client.button.PreviousButton;
import de.mrbunny.guidebook.client.button.SearchButton;
import de.mrbunny.guidebook.util.ComponentUtils;
import de.mrbunny.guidebook.util.ScreenUtils;
import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.List;
import java.util.Locale;

public class GuideSearchScreen extends GuideScreen {

    private final IBook book;
    private final ResourceLocation bordersLocation;
    private final ResourceLocation pagesLocation;
    private final int renderXOffset = 25;
    private final int renderYOffset = 15;
    private final Screen parent;
    private EditBox searchField;
    private List<List<Pair<IBookEntry, IBookCategory>>> searchResults;
    private int currentPage = 0;
    private String lastQuery = "";

    public GuideSearchScreen ( IBook pBook, Player pPlayer, ItemStack pStack, Screen pParent ) {
        super(pBook.getTitle(), pPlayer, pStack);
        this.book = pBook;
        this.bordersLocation = pBook.getOutlineTexture();
        this.pagesLocation = pBook.getPagesTexture();
        this.parent = pParent;
        this.searchResults = getMatches ( pBook, null);
    }

    protected void init() {
        this.xOffset = ( this.width - this.widthSize ) / 2;
        this.yOffset = ( this.height - this.heightSize ) / 2;

        this.addRenderableWidget(new NextButton((btn) -> {
            if ( this.currentPage <= this.searchResults.size() - 1 )
                this.currentPage++;
        }, this, this.xOffset + this.widthSize / 2 + 45, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new PreviousButton((btn) -> {
            if ( this.currentPage > 0 )
                this.currentPage--;
        }, this, this.xOffset + this.widthSize / 2 - 65, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new BackButton((btn) -> {
            Minecraft.getInstance().setScreen(this.parent);
        }, this, this.xOffset + this.widthSize / 2 - 85, this.yOffset + this.heightSize - 25));
        this.addRenderableWidget(new SearchButton((btn) -> {
            this.minecraft.setScreen(new GuideSearchScreen(this.book, this.getPlayer(), this.getBookStack(), this));
    }, this, this.xOffset + this.widthSize / 2 + 70, this.yOffset + this.heightSize - 27));

        this.searchField = new EditBox(this.font, this.xOffset + this.widthSize / 2 - 40,
                this.yOffset + this.heightSize - 25,
                75,
                10, Component.translatable("guidebook.button.search"));
        this.searchField.setBordered(false);
        this.searchField.setFocused(true);
        this.searchResults = getMatches(book, null);
    }

    public boolean charTyped(char pCodePoint, int pModifiers) {
        if ( this.searchField.charTyped(pCodePoint, pModifiers) ) {
            this.updateSearch();
            return true;
        }

        return super.charTyped(pCodePoint, pModifiers);
    }

    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if ( !this.searchField.isFocused() )
            return super.keyPressed(pKeyCode, pScanCode, pModifiers);

        if ( pKeyCode == GLFW.GLFW_KEY_ESCAPE )
            this.searchField.setFocused(false);
        if ( this.searchField.keyPressed(pKeyCode, pScanCode, pModifiers) )
            this.updateSearch();

        return true;
    }

    public boolean mouseClicked(double pMouseX, double pMouseY, int pTypeOfClick) {
        if ( !super.mouseClicked(pMouseX, pMouseY, pTypeOfClick) ) {
            if ( pTypeOfClick == 0 ) {
                int entryX = this.xOffset + this.renderXOffset;
                int entryY = this.yOffset + this.renderYOffset;

                if ( !this.searchResults.isEmpty() && this.currentPage >= 0 && this.currentPage < this.searchResults.size() ) {
                    List<Pair<IBookEntry, IBookCategory>> pageResults = this.searchResults.get(this.currentPage);

                    for ( Pair<IBookEntry, IBookCategory> pair : pageResults ) {
                        if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, entryX, entryY, pair.getLeft().getWidth(), pair.getLeft().getHeight()) )
                            GuidebookMod.CLIENT_PROXY.openEntry(this.book, pair.getRight(), pair.getLeft(), this.getPlayer(), this.getBookStack());

                        entryY += 15;
                    }
                }

                return this.searchField.mouseClicked(pMouseX, pMouseY, pTypeOfClick);
            } else if ( pTypeOfClick == 1 )  {
                if  ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, this.searchField.getX(), this.searchField.getY(), this.searchField.getInnerWidth(), this.searchField.getHeight() )) {
                    this.searchField.setValue("");
                    this.lastQuery = "";
                    this.searchResults = getMatches(this.book, "");
                    return true;
                }
            }
        }
        return true;
    }

    public void render(@NotNull GuiGraphics pGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGraphics, pMouseX, pMouseY, pPartialTick);

        pGraphics.blit(this.pagesLocation, this.getXOffset(), this.getYOffset(), 0, 0, this.getWidthSize(), this.getHeightSize(), this.getWidthSize(), this.getHeightSize());
        pGraphics.setColor(this.book.getColor().getRed() / 255F, this.book.getColor().getGreen() / 255F, this.book.getColor().getBlue() / 255F, 1.0F);
        pGraphics.blit(this.bordersLocation, this.getXOffset(), this.getYOffset(), 0, 0, this.getWidthSize(), this.getHeightSize(), this.getWidthSize(), this.getHeightSize());
        pGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        pGraphics.fill(searchField.getX() - 1, searchField.getY() - 1, searchField.getX() + searchField.getInnerWidth() + 1, searchField.getY() + searchField.getHeight() + 1, new Color(166, 126, 50, 128).getRGB());
        this.searchField.render(pGraphics, pMouseX, pMouseY, pPartialTick);

        int entryX = this.xOffset + this.renderXOffset;
        int entryY = this.yOffset + this.renderYOffset;

        if (!searchResults.isEmpty() && currentPage >= 0 && currentPage < searchResults.size()) {
            List<Pair<IBookEntry, IBookCategory>> pageResults = searchResults.get(currentPage);
            for (Pair<IBookEntry, IBookCategory> entry : pageResults) {
                entry.getLeft().setX(entryX);
                entry.getLeft().setY(entryY);

                entry.getLeft().getRender().render(pGraphics, pMouseX, pMouseY, this.font);
                entry.getLeft().getRender().renderExtras(pGraphics, Minecraft.getInstance().level.registryAccess(), pMouseX, pMouseY, entry.getLeft(), this, this.font);

                if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, entryX, entryY, entry.getLeft().getWidth(), entry.getLeft().getHeight())) {
                    if( GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
                        String msg = ComponentUtils.parseEffect("guidebook.entry.category", entry.getRight().getName().getString());
                        pGraphics.renderComponentTooltip(this.font, List.of(Component.literal(msg)), pMouseX, pMouseY);
                    }
                }

                entryY += 15;
            }
        }

        for ( Renderable renderable : this.renderables )
            renderable.render(pGraphics, pMouseX, pMouseY, pPartialTick);
    }

    private void updateSearch ( ) {
        if ( !this.searchField.getValue().equalsIgnoreCase(this.lastQuery) ) {
            lastQuery = searchField.getValue();
            searchResults = getMatches(this.book, searchField.getValue());
            if ( this.currentPage > searchResults.size() ) {
                currentPage = searchResults.size() - 1;
            }
        }
    }

    private static List<List<Pair<IBookEntry, IBookCategory>>> getMatches ( IBook pBook, @Nullable String pQuery ) {
        List<Pair<IBookEntry, IBookCategory>> discovered = Lists.newArrayList();

        for ( IBookCategory category : pBook.getCategories() ) {
            if ( !category.getRenderer().canView() ) continue;

            for ( IBookEntry entry : category.getEntries().values() ) {
                if ( !entry.getRender().canView() ) continue;

                if (Strings.isNullOrEmpty(pQuery) || entry.getName().getString().toLowerCase(Locale.ENGLISH).contains(pQuery.toLowerCase(Locale.ENGLISH)))
                    discovered.add(Pair.of(entry, category));
            }
        }

        return Lists.partition ( discovered, 10 );
    }
}
