package de.wonejo.gapi.wrapper;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.api.wrapper.IWrapper;
import de.wonejo.gapi.impl.book.component.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public class EntryWrapper implements IWrapper<IEntry> {

    private final IEntry entry;
    private final IBook book;
    private final int x;
    private final int y;

    public EntryWrapper ( IBook pBook, IEntry pEntry, int pX, int pY ) {
        this.book = pBook;
        this.entry = pEntry;
        this.x = pX;
        this.y = pY;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {
        this.entry.getRender().render(pGraphics, pAccess, this.x, this.y, pMouseX, pMouseY, this.entry, pScreen, this.book, Minecraft.getInstance().font);

        if ( this.entry.getRender() instanceof IExtraRender extraRender )
            extraRender.renderExtras(pGraphics, pMouseX, pMouseY, pScreen, Minecraft.getInstance().font);
    }

    public void tick() {
        this.entry.getRender().tick();
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return RenderUtils.isMouseBetween(pMouseX, pMouseY, this.x, this.y, 121, 12);
    }

    public IEntry get() {
        return this.entry;
    }
}
