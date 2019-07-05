package com.test.utils;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Map;

public class Utils {

    private static final String WHITE_SPACE_REGEX = "[\\s\\u0085\\p{Z}]";

    public static char parseTenthChar(String s) {
        return s.charAt(9);
    }

    public static String parseEveryTenthChar(String s) {
        ArrayList<Character> everyTenthChar = new ArrayList<>();

        int count = s.length() / 10;
        for (int iterator = 1; iterator <= count; iterator++) {
            everyTenthChar.add(s.charAt((10 * iterator) - 1));
        }
        return everyTenthChar.toString();
    }

    public static String parseEveryUniqueChar(String s) {
        ArrayMap<String, Integer> map = new ArrayMap<>();

        String[] array = s.split(WHITE_SPACE_REGEX);

        StringBuilder result = new StringBuilder();
        for (String str :
                array) {
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            result.append(entry.getKey());
            result.append(" ");
            result.append(entry.getValue());
            result.append('\n');
        }
        return result.toString();
    }
}
