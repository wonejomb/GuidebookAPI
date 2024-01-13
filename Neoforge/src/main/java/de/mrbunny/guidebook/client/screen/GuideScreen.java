package de.mrbunny.guidebook.client.screen;

import de.mrbunny.guidebook.api.client.IModScreen;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public abstract class GuideScreen extends Screen implements IModScreen {

    public final int widthSize = 245;
    public final int heightSize = 192;
    private final Player player;
    private final ItemStack bookStack;
    public int xOffset;
    public int yOffset;

    public GuideScreen (Component pTitle, Player pPlayer, ItemStack pBookStack) {
        super(pTitle);

        this.player = pPlayer;
        this.bookStack = pBookStack;
    }

    public boolean isPauseScreen() {
        return false;
    }

    public void drawCenteredStringWithoutShadow(GuiGraphics pGraphics, Font pFont, String pString, int pX, int pY, int pColor) {
        pGraphics.drawString(pFont, pString, pX - pFont.width(pString) / 2, pY, pColor, false);
    }

    public void drawCenteredStringWithoutShadow(GuiGraphics pGraphics, Font pFont, FormattedCharSequence pString, int pX, int pY, int pColor) {
        pGraphics.drawString(pFont, pString, pX - pFont.width(pString) / 2, pY, pColor, false);
    }

    public void drawCenteredStringWithoutShadow(GuiGraphics pGraphics, Font pFont, Component pString, int pX, int pY, int pColor) {
        pGraphics.drawString(pFont, pString, pX - pFont.width(pString) / 2, pY, pColor, false);
    }

    public int getWidthSize() {
        return this.widthSize;
    }

    public int getHeightSize() {
        return this.heightSize;
    }

    public int getXOffset() {
        return this.xOffset;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    public ItemStack getBookStack() {
        return this.bookStack;
    }

    public Player getPlayer() {
        return this.player;
    }

}
