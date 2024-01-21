package de.mrbunny.guidebook.util;

import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.page.TextPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static List<IPage> pagesForLongText (FormattedText pText, Ingredient pIngredient) {
        List<FormattedText> pageText = prepareForLongText(pText, 204, 81, 120);
        List<IPage> pageList = new ArrayList<>();
        for ( int i = 0; i < pageText.size(); i++ ) {
            if ( i != 0 ) {
                pageList.add(new TextPage( pageText.get(i) ));
            }
        }

        return pageList;
    }

    public static List<IPage> pagesForLongText ( FormattedText pText ) {
        List<IPage> pageList = new ArrayList<>();
        prepareForLongText(pText, 204, 126, 126).forEach((text) -> pageList.add(new TextPage(text)));
        return pageList;
    }

    public static void drawFormattedText (GuiGraphics pGraphics, int pX, int pY, FormattedText pText) {
        Font font = Minecraft.getInstance().font;

        List<FormattedCharSequence> cutLines = font.split(pText, 204);
        for ( FormattedCharSequence cut : cutLines ) {
            pGraphics.drawString(font, cut, pX, pY, 0xA58573, false);
            pY += 15;
        }
    }

    public static void drawFormattedText ( GuiGraphics pGraphics, FormattedText pText, int pX, int pY, int pLineMaxWidth ) {
        Font font = Minecraft.getInstance().font;

        List<FormattedCharSequence> cutLines = font.split(pText, pLineMaxWidth);
        for ( FormattedCharSequence sequence : cutLines ) {
            pGraphics.drawString(font, sequence, pX, pY, 0xA58573, false);
            pY += 15;
        }
    }

    public static List<IPage> pagesForLongText (FormattedText pText, Item pItem) {
        return pagesForLongText(pText, new ItemStack(pItem));
    }

    public static List<IPage> pagesForLongText (FormattedText pText, Block pBlock) {
        return pagesForLongText(pText, new ItemStack(pBlock));
    }

    public static List<IPage> pagesForLongText (FormattedText pText, ItemStack pStack) {
        return pagesForLongText(pText, Ingredient.of(pStack));
    }

    public static List<FormattedText> prepareForLongText ( FormattedText pText, int pLineWidth, int pFirstHeight, int pSubsequentHeight ) {
        Font font = Minecraft.getInstance().font;
        int firstCount = pFirstHeight / font.lineHeight;
        int count = pSubsequentHeight / font.lineHeight;
        List<FormattedText> lines = new ArrayList<>(font.getSplitter().splitLines(pText, pLineWidth, Style.EMPTY));
        List<FormattedText> pages = new ArrayList<>();

        List<FormattedText> pageLines = lines.size() > firstCount ? lines.subList(0, firstCount) : lines;
        pages.add(combineWithNewLine(pageLines));
        pageLines.clear();
        while (!lines.isEmpty()) {
            pageLines = lines.size() > count ? lines.subList(0, count) : lines;
            pages.add(combineWithNewLine(pageLines));
            pageLines.clear();
        }

        return pages;
    }

    private static FormattedText combineWithNewLine (List<FormattedText> pElements) {
        FormattedText newLine = Component.literal("\n");
        List<FormattedText> copy = new ArrayList<>(pElements.size() * 2);
        for ( int i = 0; i < pElements.size() - 1; i++ ) {
            copy.add(pElements.get(i));
            copy.add(newLine);
        }
        copy.add(pElements.get(pElements.size() - 1));
        return FormattedText.composite(copy);
    }
}
