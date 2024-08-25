package de.wonejo.wuidebook.api.util;

public class WuidebookUtils {

    public static boolean isMouseBetween ( double pMouseX, double pMouseY, int pX, int pY, int pWidth, int pHeight ) {
        int widthSize = pX + pWidth;
        int heightSize = pY + pHeight;

        return (pMouseX >= pX && pMouseX <= widthSize) && (pMouseY >= pY && pMouseY <= heightSize);
    }

}
