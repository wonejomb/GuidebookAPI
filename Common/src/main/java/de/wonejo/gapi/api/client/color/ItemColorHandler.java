package de.wonejo.gapi.api.client.color;

import de.wonejo.gapi.api.book.item.IBookItem;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.impl.service.Services;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public final class ItemColorHandler {

    public static void submitItems ( ItemColorHandlerConsumer pConsumer ) {

        for (Supplier<ItemStack> bookStack : Services.BOOK_REGISTRY.getBookToStacks().values()) {
            pConsumer.register((itemStack, i) -> {
                IBookItem item = (IBookItem) bookStack.get().getItem();

                if ( item.get() != null && !item.get().useCustomBookModel() && i == 0 )
                    return ModConfigurations.CLIENT_PROVIDER.getBookColors().get(item.get()).get().getRGB();

                return -1;
            }, bookStack.get().getItem());
        }

    }


}
