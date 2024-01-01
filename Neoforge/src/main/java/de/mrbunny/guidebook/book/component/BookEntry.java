package de.mrbunny.guidebook.book.component;

import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IEntryRender;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class BookEntry implements IBookEntry {

    private final List<IPage> pages;
    private final Component name;

    private final IEntryRender render;

    private int x;
    private int y;
    private int width;
    private int height;

    public BookEntry ( Component pName, IEntryRender pRender, List<IPage> pPages ) {
        this.name = pName;
        this.render = pRender;
        this.pages = pPages;
    }

    public BookEntry( Component pName, IEntryRender pRender ) {
        this ( pName, pRender, Lists.newArrayList() );
    }

    public void init() {
        this.render.init();
    }

    public void render (@NotNull GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {
        this.render.render(pGraphics, pMouseX, pMouseY, pFont);
    }

    public void renderExtras (@NotNull GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        this.render.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, this, pScreen, pFont);
    }

    public void addPage(IPage pPage) {
        this.pages.add(pPage);
    }

    public void addPages(List<IPage> pPages) {
        this.pages.addAll(pPages);
    }

    public void addPages(IPage... pPages) {
        this.pages.addAll(Arrays.asList(pPages));
    }

    public void removePage(IPage pPage) {
        this.pages.remove(pPage);
    }

    public void removePages(List<IPage> pPages) {
        this.pages.removeAll(pPages);
    }

    public void removePages(IPage... pPages) {
        this.pages.removeAll(Arrays.asList(pPages));
    }

    public IPage getPage(int pIndex) {
        return this.pages.get(pIndex);
    }

    public Component getName() {
        return this.name;
    }

    public List<IPage> getPages() {
        return this.pages;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int pX) {
        this.x = pX;
    }

    public void setY(int pY) {
        this.y = pY;
    }

    public void setHeight(int pHeight) {
        this.height = pHeight;
    }

    public void setWidth(int pWidth) {
        this.width = pWidth;
    }
}
