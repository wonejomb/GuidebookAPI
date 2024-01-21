package de.mrbunny.guidebook.util;

public class StringUtils {

    public static boolean equalsOrContains ( String pFirstString, String pSecondString ) {
        return pFirstString.equals(pSecondString) || pFirstString.contains(pSecondString);
    }
}
