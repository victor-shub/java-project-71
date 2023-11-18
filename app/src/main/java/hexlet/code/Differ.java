package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(File file1, File file2) throws Exception {
        if (!file1.isFile() || !file2.isFile()) {
            throw new Exception("Wrong file name(s)!");
        }
        var stringFile1 = Files.readString(file1.toPath());
        var stringFile2 = Files.readString(file2.toPath());
        var map1 = jsonToMap(stringFile1);
        var map2 = jsonToMap(stringFile2);
        return diff(map1, map2);
    }

    public static Map<String, Object> jsonToMap(String stringFile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var map = objectMapper.readValue(stringFile, new TypeReference<Map<String, Object>>() { });
        return map;
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
