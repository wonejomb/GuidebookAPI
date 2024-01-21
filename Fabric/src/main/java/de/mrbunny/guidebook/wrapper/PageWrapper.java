package de.mrbunny.guidebook.wrapper;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.wrapper.IWrapper;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PageWrapper implements IWrapper {

    private final IModScreen screen;
    private final IBook book;
    private final IBookCategory category;
    private final IBookEntry entry;
    private final IPage page;
    private final Player player;
    private final Font font;
    private final ItemStack bookStack;
    private final int xOffset;
    private final int yOffset;

    public PageWrapper ( IModScreen pScreen, IBook pBook, IBookCategory pCategory, IBookEntry pEntry, IPage pPage, Player pPlayer, Font pFont, ItemStack pBookStack, int pXOffset, int pYOffset) {
        this.screen = pScreen;
        this.book = pBook;
        this.category = pCategory;
        this.entry = pEntry;
        this.page = pPage;
        this.player = pPlayer;
        this.font= pFont;
        this.bookStack = pBookStack;
        this.xOffset = pXOffset;
        this.yOffset = pYOffset;
    }

    public boolean canView() {
        return this.page.getRender().canView();
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {
        this.page.getRender().render(pGraphics, pMouseX, pMouseY, this.font);
        this.page.getRender().renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pScreen, this.font);
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return true;
    }

    public void onHoverOver(int pMouseX, int pMouseY) {

    }

    public IPage getPage() {
        return page;
    }
}
