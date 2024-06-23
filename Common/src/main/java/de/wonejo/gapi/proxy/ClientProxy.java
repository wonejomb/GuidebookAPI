package de.wonejo.gapi.proxy;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.client.screen.CategoryGuideScreen;
import de.wonejo.gapi.client.screen.EntryGuideScreen;
import de.wonejo.gapi.client.screen.HomeGuideScreen;
import de.wonejo.gapi.core.ModDataComponents;
import de.wonejo.gapi.impl.service.Services;
import de.wonejo.gapi.item.GuideItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ClientProxy implements IProxy {

    public void openGuide(Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack) {
        if (!pStack.isEmpty() && pStack.getItem() instanceof GuideItem)
            pBook.initializeContent();

        try {
            if ( pStack.has(ModDataComponents.ENTRY) && pStack.has(ModDataComponents.CATEGORY) ) {

                ICategory category = pBook.categories().get(pStack.get(ModDataComponents.CATEGORY));
                IEntry entry = category.getEntry(pStack.get(ModDataComponents.ENTRY));
                int pageNumber = pStack.getOrDefault(ModDataComponents.PAGE, 0);
                EntryGuideScreen entryGuideScreen = new EntryGuideScreen(pPlayer, pBook, category, entry);
                entryGuideScreen.pageId = pageNumber;
                Minecraft.getInstance().setScreen(entryGuideScreen);
                return;

            } else if ( pStack.has(ModDataComponents.CATEGORY) ) {

                ICategory category = pBook.categories().get(pStack.get(ModDataComponents.CATEGORY));
                int entryPage  = pStack.getOrDefault(ModDataComponents.PAGE, 0);
                CategoryGuideScreen categoryGuideScreen = new CategoryGuideScreen(pPlayer, pBook, category);
                categoryGuideScreen.entryPage = entryPage;
                Minecraft.getInstance().setScreen(categoryGuideScreen);
                return;

            } else {

                int categoryNumber = pStack.getOrDefault(ModDataComponents.PAGE, 0);
                HomeGuideScreen homeGuideScreen = new HomeGuideScreen(pPlayer, pBook);
                homeGuideScreen.categoryPage = categoryNumber;
                Minecraft.getInstance().setScreen(homeGuideScreen);
                return;

            }
        } catch ( Exception ignored ) {}

        Minecraft.getInstance().setScreen(new HomeGuideScreen( pPlayer, pBook));
    }

    public void playSound(SoundEvent pEvent) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(pEvent, 1.0F));
    }
}
