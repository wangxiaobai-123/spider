package org.example;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtil {

    public static void printMap(Map<String, List<String>> stringListMap) {
        Set<Map.Entry<String, List<String>>> entries = stringListMap.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            System.out.println();
            System.out.println(key + ":" + value);
        }
    }
}
