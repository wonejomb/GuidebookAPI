package de.wonejo.gapi.impl.book.component;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.book.components.IHolder;
import de.wonejo.gapi.api.client.render.IHolderRender;
import de.wonejo.gapi.client.screen.EntryGuideScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class Holder implements IHolder {

    private final IEntry entryReference;
    private final int pageIndex;
    private final IHolderRender render;

    public Holder ( IEntry pEntryReference, int pPageIndex, IHolderRender pRender ) {
        this.entryReference = pEntryReference;
        this.pageIndex = pPageIndex;
        this.render = pRender;
    }

    public void onClick(IBook pBook, Player pPlayer, ICategory pCategory, double pMouseX, double pMouseY, int pClickType) {
        IHolder.super.onClick(pBook, pPlayer, pMouseX, pMouseY, pClickType);

        EntryGuideScreen screen = new EntryGuideScreen(pPlayer, pBook, pCategory, this.entryReference);
        screen.setPageId(pageIndex);
        Minecraft.getInstance().setScreen(screen);
    }

    public IEntry entryReference() {
        return this.entryReference;
    }

    public int pageIndex() {
        return this.pageIndex;
    }

    public IHolderRender getRender() {
        return this.render;
    }
}
