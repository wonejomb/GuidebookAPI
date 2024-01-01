package de.mrbunny.guidebook.book.component;

import com.google.common.collect.Maps;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.ICategoryRender;
import de.mrbunny.guidebook.book.Book;
import de.mrbunny.guidebook.client.screen.GuideCategoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class BookCategory implements IBookCategory {

    private final Component name;
    private final ICategoryRender categoryRender;
    private final Map<ResourceLocation, IBookEntry> entries;

    private String modId;

    private int x;
    private int y;
    private int width;
    private int height;

    public BookCategory(Component pName, ICategoryRender pRender, Map<ResourceLocation, IBookEntry> pEntries) {
        this.name = pName;
        this.categoryRender = pRender;
        this.entries = pEntries;
    }

    public BookCategory ( Component pName, ICategoryRender pRender ) {
        this ( pName, pRender, Maps.newHashMap() );
    }


    public void render (@NotNull GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {
        this.categoryRender.render(pGraphics, pMouseX, pMouseY, pFont);
    }

    public void renderExtras (@NotNull GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        this.categoryRender.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, this, pScreen, pFont);
    }

    public void leftClick (IBook pBook, double pMouseX, double pMouseY, Player pPlayer, ItemStack pBookStack ) {
        Minecraft.getInstance().setScreen(new GuideCategoryScreen(pBook, this, pPlayer, pBookStack, null));
    }

    public void addEntry(ResourceLocation pId, IBookEntry pEntry) {
        this.entries.put(pId, pEntry);
    }

    public void addEntries(Map<ResourceLocation, IBookEntry> pEntries) {
        this.entries.putAll(pEntries);
    }

    public void removeEntry(ResourceLocation pId) {
        this.entries.remove(pId);
    }

    public ICategoryRender getRenderer() {
        return this.categoryRender;
    }

    public void removeEntries(ResourceLocation... pIds) {
        for ( ResourceLocation id : pIds )
            this.entries.remove(id);
    }

    public void removeEntries(List<ResourceLocation> pIds) {
        for ( ResourceLocation id : pIds )
            this.entries.remove(id);
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public IBookEntry getEntry(ResourceLocation pId) {
        return this.entries.get(pId);
    }

    public IBookEntry getEntry(String pID) {
        if ( this.modId == null )
            throw new NullPointerException("ModId of category must not be null when call BookCategory#getEntry.");

        return this.getEntry(new ResourceLocation(this.modId, pID));
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Component getName() {
        return name;
    }

    public Map<ResourceLocation, IBookEntry> getEntries() {
        return entries;
    }
}
