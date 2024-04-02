package de.wonejo.gapi.wrapper;

import de.wonejo.gapi.api.book.components.IBookPage;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.wrapper.IWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public class PageWrapper implements IWrapper<IBookPage> {

    private final IBookPage page;

    public PageWrapper ( IBookPage pPage ) {
        this.page = pPage;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {
        this.page.render().render(pGraphics, pAccess, pMouseX, pMouseY, pScreen, Minecraft.getInstance().font);

        if ( this.page.render() instanceof IExtraRender extraRender )
            extraRender.renderExtras(pGraphics, pMouseX, pMouseY, pScreen, Minecraft.getInstance().font);
    }

    public void tick() {
        this.page.render().tick();
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return true;
    }

    public IBookPage get() {
        return this.page;
    }

}
