package de.wonejo.gapi.proxy;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.book.components.IEntry;
import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.client.screen.CategoryGuideScreen;
import de.wonejo.gapi.client.screen.EntryGuideScreen;
import de.wonejo.gapi.client.screen.HomeGuideScreen;
import de.wonejo.gapi.impl.service.Services;
import de.wonejo.gapi.item.GuideItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ClientProxy implements IProxy {

    public void openGuide(Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack) {
        if (!pStack.isEmpty() && pStack.getItem() instanceof GuideItem)
            pBook.initializeContent();

        try {
            if ( pStack.has(Services.DATA_COMPONENTS.getEntryDataComponent()) && pStack.has(Services.DATA_COMPONENTS.getCategoryDataComponent()) ) {

                ICategory category = pBook.categories().get(pStack.get(Services.DATA_COMPONENTS.getCategoryDataComponent()));
                IEntry entry = category.getEntry(pStack.get(Services.DATA_COMPONENTS.getEntryDataComponent()));
                int pageNumber = pStack.getOrDefault(Services.DATA_COMPONENTS.getPageDataComponent(), 0);
                EntryGuideScreen entryGuideScreen = new EntryGuideScreen(pPlayer, pBook, category, entry);
                entryGuideScreen.pageId = pageNumber;
                Minecraft.getInstance().setScreen(entryGuideScreen);
                return;

            } else if ( pStack.has(Services.DATA_COMPONENTS.getCategoryDataComponent()) ) {

                ICategory category = pBook.categories().get(pStack.get(Services.DATA_COMPONENTS.getCategoryDataComponent()));
                int entryPage  = pStack.getOrDefault(Services.DATA_COMPONENTS.getPageDataComponent(), 0);
                CategoryGuideScreen categoryGuideScreen = new CategoryGuideScreen(pPlayer, pBook, category);
                categoryGuideScreen.entryPage = entryPage;
                Minecraft.getInstance().setScreen(categoryGuideScreen);
                return;

            } else {

                int categoryNumber = pStack.getOrDefault(Services.DATA_COMPONENTS.getPageDataComponent(), 0);
                HomeGuideScreen homeGuideScreen = new HomeGuideScreen(pPlayer, pBook);
                homeGuideScreen.categoryPage = categoryNumber;
                Minecraft.getInstance().setScreen(homeGuideScreen);
                return;

            }
        } catch ( Exception ignored ) {}

        Minecraft.getInstance().setScreen(new HomeGuideScreen( pPlayer, pBook));
    }

}
