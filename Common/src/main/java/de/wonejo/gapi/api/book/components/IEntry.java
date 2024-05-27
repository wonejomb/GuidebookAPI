package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.render.IEntryRender;
import de.wonejo.gapi.api.util.Accessible;
import de.wonejo.gapi.api.util.Clickable;
import de.wonejo.gapi.client.screen.EntryGuideScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.List;


public interface IEntry extends Accessible, Clickable {
    IEntryRender getRender ();
    List<IPage> getPages ();

    void addPage ( IPage pPage );
    void addPages ( List<IPage> pPages );
    void addPages ( IPage... pPages );

    IPage getPageById ( int pIndex );

    default void onClick(IBook pBook, Player pPlayer, ICategory pCategory, double pMouseX, double pMouseY, int pClickType) {
        Clickable.super.onClick(pBook, pPlayer, pMouseX, pMouseY, pClickType);
        Minecraft.getInstance().setScreen(new EntryGuideScreen(pPlayer, pBook, pCategory, this));
    }
}
