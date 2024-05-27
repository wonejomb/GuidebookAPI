package de.wonejo.gapi.wrapper;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.book.components.IHolder;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.api.wrapper.IWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;

public class HolderWrapper implements IWrapper<IHolder> {

    private final IBook book;
    private final IHolder holder;
    private final int index;
    private final int x;
    private final int y;

    public HolderWrapper ( IBook pBook, IHolder pHolder, int pIndex, int pX, int pY ) {
        this.book = pBook;
        this.holder = pHolder;
        this.index = pIndex;
        this.x = pX;
        this.y = pY;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen) {
        this.holder.getRender().render(pGraphics, pAccess, this.x, this.y, this.index, pMouseX, pMouseY, this.holder, this.book, pScreen, Minecraft.getInstance().font);

        if ( this.holder.getRender() instanceof IExtraRender extra )
            extra.renderExtras(pGraphics, pMouseX, pMouseY, pScreen, Minecraft.getInstance().font);
    }

    public void onClick(IBook pBook, Player pPlayer, ICategory pCategory, double pMouseX, double pMouseY, int pClickType) {
        IWrapper.super.onClick(pBook, pPlayer, pMouseX, pMouseY, pClickType);

        this.holder.onClick(pBook, pPlayer, pCategory, pMouseX, pMouseY, pClickType);
    }

    public boolean isMouseOnWrapper(double pMouseX, double pMouseY) {
        return RenderUtils.isMouseBetween(pMouseX, pMouseY, this.x, this.y, 79, 9);
    }

    public IHolder get() {
        return this.holder;
    }
}
