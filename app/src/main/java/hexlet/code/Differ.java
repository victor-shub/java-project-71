package hexlet.code;

import java.util.*;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filePath1, String filePath2) {
        return "";
    }

    public static String diff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeySet = new TreeSet<>();
        allKeySet.addAll(map1.keySet());
        allKeySet.addAll(map2.keySet());
        return allKeySet.stream()
                .map(key -> {
                    var line1 = "";
                    var line2 = "";
                    var line12 = "";
                    if (map1.containsKey(key)) {
                        line1 =  " " + key + ":" + " " + map1.get(key);
                        line12 += "-" + line1;
                    }
                    if (map2.containsKey(key)) {
                        line2 =  " " + key + ":" + " " + map2.get(key);
                        if (map1.containsKey(key)) {
                            line12 += "\n  ";
                        }
                        line12 += "+" + line2;
                    }
                    if (line1.equals(line2)) {
                        return " " + line1;
                    }
                    return line12;
                })
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }
}
