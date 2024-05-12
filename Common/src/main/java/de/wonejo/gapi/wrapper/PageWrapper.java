package de.wonejo.gapi.wrapper;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IPage;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.wrapper.IWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public class PageWrapper implements IWrapper<IPage> {


    private final IBook book;
    private final IPage page;

    public PageWrapper ( IBook pBook, IPage pPage ) {
        this.book = pBook;
        this.page = pPage;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {
        this.page.getRender().render(pGraphics, pAccess, pMouseX, pMouseY, pScreen, this.book, Minecraft.getInstance().font);

        if ( this.page.getRender() instanceof IExtraRender extraRender )
            extraRender.renderExtras(pGraphics, pMouseX, pMouseY, pScreen, Minecraft.getInstance().font);
    }

    public void tick() {
        this.page.getRender().tick();
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return true;
    }

    public IPage get() {
        return this.page;
    }

}
