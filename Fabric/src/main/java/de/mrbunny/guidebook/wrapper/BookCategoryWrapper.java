package de.mrbunny.guidebook.wrapper;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.wrapper.IWrapper;
import de.mrbunny.guidebook.book.component.BookCategory;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class BookCategoryWrapper implements IWrapper {

    private final IBook book;
    private final BookCategory category;
    private final int x, y, width, height;
    private Player player;
    private Font font;

    public ItemStack bookStack;

    public BookCategoryWrapper ( IBook pBook, BookCategory pCategory, Player pPlayer, Font pFont, ItemStack pBookStack, int pX, int pY, int pWidth, int pHeight ) {
        this.book = pBook;
        this.category = pCategory;
        this.player = pPlayer;
        this.font = pFont;
        this.bookStack = pBookStack;
        this.x = pX;
        this.y = pY;
        this.width = pWidth;
        this.height = pHeight;

        this.category.setX(pX);
        this.category.setY(pY);
        this.category.setWidth(pWidth);
        this.category.setHeight(pHeight);
    }

    public boolean canView() {
        return this.category.getRenderer().canView();
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {

        this.category.render(pGraphics, pMouseX, pMouseY, this.font);
        this.category.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pScreen, this.font);

    }

    public void tick () {
        this.category.getRenderer().tick();
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return ScreenUtils.isMouseBetween(pMouseX, pMouseY, this.x, this.y, this.width, this.height);
    }

    public void onHoverOver(int pMouseX, int pMouseY) {

    }

    public IBook getBook() {
        return book;
    }

    public BookCategory getCategory() {
        return category;
    }

    public Font getFont() {
        return font;
    }

    public ItemStack getBookStack() {
        return bookStack;
    }

    public Player getPlayer() {
        return player;
    }

}
