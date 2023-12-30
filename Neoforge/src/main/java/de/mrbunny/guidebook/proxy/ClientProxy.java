package de.mrbunny.guidebook.proxy;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBookItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    @OnlyIn(Dist.CLIENT)
    public void initColors() {
        for (Supplier<ItemStack> bookStack : GuidebookAPI.getBookToStack().values()) {
            Minecraft.getInstance().getItemColors().register((stack, tint) -> {
                IBookItem item = (IBookItem) stack.getItem();
                if ( item.getBook(stack) != null && tint == 0 )
                    return item.getBook(stack).getColor().getRGB();

                return -1;
            }, bookStack.get().getItem());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void playSound(SoundEvent pEvent) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(pEvent, 1));
    }

}
