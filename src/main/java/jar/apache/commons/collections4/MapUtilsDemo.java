package jar.apache.commons.collections4;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

import static l.demo.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/MapUtils.html">MapUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class MapUtilsDemo {

    private static final Map<Integer, Object> map = new HashMap<>(Map.of(1, "A", 2, "B", 3, "C"));

    /**
     * static <K> XXX	getXXX(Map<? super K,?> map, K key[, Boolean defaultValue])
     */
    public static void main(String[] args) {
        // static <K> Xxx           getXxx(Map<? super K, ?> map, K key[, Boolean defaultValue])
        p(MapUtils.getString(map, 4, "D")); // D
        p(MapUtils.getString(map, 1));      // A
        p(MapUtils.getIntValue(map, 1));    // 0

        // static <K, V> Map<V, K>  invertMap(Map<K, V> map)
        p(MapUtils.invertMap(map));         // {A=1, B=2, C=3}

        // static <K, V> Map<K, V>  putAll(Map<K, V> map, Object[] array)
        p(MapUtils.putAll(map, new Object[]{4, "D", 5, "E"}));
        // {1=A, 2=B, 3=C, 4=D, 5=E}

        // static <K> void          safeAddToMap(Map<? super K, Object> map, K key, Object value)
        // 如果 value 是 null，用 "" 代替
        MapUtils.safeAddToMap(map, 4, null);
        map.put(5, null);
        p(map); // {1=A, 2=B, 3=C, 4=, 5=null}
    }
}
