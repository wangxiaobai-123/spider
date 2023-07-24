package org.example;

public class RegexUtil {
    public static boolean isChineseCharacter (String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
                return true;
            }
        }
        return false;
    }

}
