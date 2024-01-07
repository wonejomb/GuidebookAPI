package de.mrbunny.guidebook.wrapper;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.wrapper.IWrapper;
import de.mrbunny.guidebook.book.component.BookEntry;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class BookEntryWrapper implements IWrapper {

    private final IBook book;
    private final BookEntry entry;
    private final int x, y, width, height;
    private Player player;
    private Font font;

    private ItemStack bookStack;

    public BookEntryWrapper ( IBook pBook, BookEntry pEntry, Player pPlayer, Font pFont, ItemStack pStack, int pX, int pY, int pWidth, int pHeight ) {
        this.book = pBook;
        this.entry = pEntry;
        this.player = pPlayer;
        this.font = pFont;
        this.bookStack = pStack;
        this.x = pX;
        this.y = pY;
        this.width = pWidth;
        this.height = pHeight;

        this.entry.setX(pX);
        this.entry.setY(pY);
        this.entry.setWidth(pWidth);
        this.entry.setHeight(pHeight);
    }

    public boolean canView() {
        return true;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {
        this.entry.render(pGraphics, pMouseX, pMouseY, Minecraft.getInstance().font);
        this.entry.renderExtras(pGraphics, Minecraft.getInstance().level.registryAccess(), pMouseX, pMouseY, pScreen, Minecraft.getInstance().font);
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return ScreenUtils.isMouseBetween(pMouseX, pMouseY, this.entry.getX(), this.entry.getY(), this.entry.getWidth(), this.entry.getHeight());
    }

    public void onHoverOver(int pMouseX, int pMouseY) {

    }

    public BookEntry getEntry() {
        return entry;
    }
}
