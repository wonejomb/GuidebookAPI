package de.wonejo.gapi.wrapper;

import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.api.wrapper.IWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public class EntryWrapper implements IWrapper<IBookEntry> {

    private final IBookEntry entry;
    private final int x;
    private final int y;

    public EntryWrapper ( IBookEntry pEntry, int pX, int pY ) {
        this.entry = pEntry;
        this.x = pX;
        this.y = pY;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {
        this.entry.render().render(pGraphics, pAccess, this.x, this.y, pMouseX, pMouseY, this.entry, pScreen, Minecraft.getInstance().font);

        if ( this.entry.render() instanceof IExtraRender extraRender )
            extraRender.renderExtras(pGraphics, pMouseX, pMouseY, pScreen, Minecraft.getInstance().font);
    }

    public void tick() {
        this.entry.render().tick();
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return RenderUtils.isMouseBetween(pMouseX, pMouseY, this.x, this.y, 121, 12);
    }

    public IBookEntry get() {
        return this.entry;
    }
}
