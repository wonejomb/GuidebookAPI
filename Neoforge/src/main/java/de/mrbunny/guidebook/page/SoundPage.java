package de.mrbunny.guidebook.page;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IPageRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.RegistryAccess;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SoundPage implements IPage, IPageRender {

    private final IPage page;
    private final SoundEvent sound;

    public SoundPage ( IPage pPage, SoundEvent pSound ) {
        this.page = pPage;
        this.sound = pSound;
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        this.page.getRender().renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pScreen, pFont);
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {
        this.page.getRender().render(pGraphics, pMouseX, pMouseY, pFont);
    }

    public void leftClick(IBook pBook, double pMouseX, double pMouseY, Player pPlayer, ItemStack pBookStack) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(this.sound, 1.0F));
        this.page.getRender().leftClick(pBook, pMouseX, pMouseY, pPlayer, pBookStack);
    }

    public void rightClick(IBook pBook, double pMouseX, double pMouseY, Player pPlayer, ItemStack pBookStack) {
        this.page.getRender().rightClick(pBook, pMouseX, pMouseY, pPlayer, pBookStack);
    }
}
