package de.wonejo.gapi.wrapper;

import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.api.wrapper.IWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public class CategoryWrapper implements IWrapper<ICategory> {

    private final ICategory category;
    private final int x, y;

    public CategoryWrapper (ICategory pCategory, int pX, int pY ) {
        this.category = pCategory;
        this.x = pX;
        this.y = pY;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {
        this.category.getRender().render(pGraphics, pAccess, this.x, this.y, pMouseX, pMouseY, this.category, pScreen, Minecraft.getInstance().font);

        if ( this.category.getRender() instanceof IExtraRender extraRender )
            extraRender.renderExtras(pGraphics, pMouseX, pMouseY, pScreen, Minecraft.getInstance().font);
    }

    public void tick() {
        this.category.getRender().tick();
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return RenderUtils.isMouseBetween(pMouseX, pMouseY, this.x, this.y, 16, 16);
    }

    public ICategory get() {
        return this.category;
    }
}
