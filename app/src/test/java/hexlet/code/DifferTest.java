package hexlet.code;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class DifferTest {
    @Test
    public void testDiff() {
        Map<String, Object> map1 = Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false);
        Map<String, Object> map2 = Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io");
        var actual = Differ.diff(map1, map2);
        var expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        assertEquals(expected, actual);
    }
}
