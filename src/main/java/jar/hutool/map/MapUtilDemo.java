package jar.hutool.map;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * <a href="https://doc.hutool.cn/pages/MapUtil/">MapUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/map/MapUtil.html">MapUtil api</a>
 * <pre>{@code
 * static <K,V> Map<K,V>                createMap(Class<?> mapType)                         创建 Map
 * static <K,V> HashMap<K,V>            newHashMap([[int size][, boolean isOrder]])         新建 HashMap
 * static <K,V> TreeMap<K,V>            newTreeMap([Map<K,V> map, ]Comparator<? super K>)   新建 TreeMap
 * static <K,V> Map<K,V>                newIdentityMap(int size)                            新建不重复 Map
 * static <K,V> ConcurrentHashMap<K,V>  newConcurrentHashMap([int size/Map<K,V> map])       新建 ConcurrentHashMap
 *
 * static <K,V> TreeMap<K,V>            sort(Map<K,V> map[, Comparator<? super K>])         Map 排序
 * static <T> Map<T, T>                 reverse(Map<T, T> map)                              反转 Map
 * static <T> Map<T, T>                 inverse(Map<T, T> map)                              key value 互换
 * static <K, V> Map<K, V>              renameKey(Map<K, V> map, K oldKey, K newKey)        重命名指定 key
 * static <K, V> Map<K, V>              getAny(Map<K, V> map, K... keys)                    获取 Map 的指定 key 生成新的 Map
 * static <K, V> Map<K, V>              removeAny(Map<K, V> map, K... keys)                 去除 Map 的指定 key，修改原 Map
 * static <K, V> Map<K, V>              removeNullValue(Map<K, V> map)                      去除 null，修改原 Map
 * static <T> T                         get(Map<?, ?> map, Object key, Class<T> type)       获取指定 key 的值，并转换为指定类型
 * static XXX                           getXXX(Map<?, ?>, Object key[, XXX defaultValue])   获取指定 key 的值
 * static void                          clear(Map<?, ?>... maps)                            清除一个或多个 Map 集合内的元素，每个 Map 调用clear()
 *
 * static boolean                       isEmpty(Map<?, ?> map)                              是否位空
 * static boolean                       isNotEmpty(Map<?, ?> map)                           是否不为空
 * static <K, V> Map<K, V>              emptyIfNull(Map<K, V> map)                          如果为空则返回空 map
 * static <T extends Map<K,V>,K,V> T    defaultIfEmpty(T map, T defaultMap)                 如果为空则返回空默认 map}
 * </pre>
 *
 * @author ljh
 * @since 2020/10/29 2:36
 */
public class MapUtilDemo extends Demo {

    @Test
    public void testMapUtil() {
        map = MapUtil.unmodifiable(map);
        map = MapUtil.builder(map).build();
        Map<Object, Object> buildMap = MapBuilder.create().put(1, "A").put(2, "B").put(3, "C").build();
        Map<Object, Object> empty = MapUtil.empty();

        // edit(Map, Editor)                                    经过 Editor 处理返回
        p(MapUtil.edit(map2, intStrEntry -> {
            if (intStrEntry.getKey() % 2 == 1) {
                return intStrEntry;
            }
            return null;
        })); // {1=A, 3=C, 5=E, 7=G, 9=I}

        // Map<K, V> getAny(Map<K, V> map, K... keys)           获取 Map 的指定 key 生成新的 Map
        p(MapUtil.getAny(map2, 1, 3, 5));       // {1=A, 3=C, 5=E}

        // filter(Map, Filter)                                  经过 Filter 判断返回
        p(MapUtil.filter(map2, entry -> entry.getKey() % 2 == 1));
        // {1=A, 3=C, 5=E, 7=G, 9=I}

        // toListMap(Iterable<? extends Map<K, V>> mapList)     合并相同的键，键的值合并为列表
        Map<Integer, List<String>> integerListMap = MapUtil.toListMap(ListUtil.of(map, map, map));
        p(integerListMap);                      // {1=[A, A, A], 2=[B, B, B], 3=[C, C, C]}

        // toMapList(Map<K,? extends Iterable<V>> listMap)      toListMap() 的逆方法
        p(MapUtil.toMapList(integerListMap));   // [{1=A, 2=B, 3=C}, {1=A, 2=B, 3=C}, {1=A, 2=B, 3=C}]
    }

    /**
     * 转换
     * <p>
     * static <K,V> Map<K,V>	    toCamelCaseMap(Map<K,V> map)            转换为 key 为驼峰风格的 Map
     */
    @Test
    public void convert() {

        // join(Map, separator, kvSeparator[, isIgnoreNull, String... otherParams]) Map → String
        p(MapUtil.join(map, "|", "-", true));       // 1-A|2-B|3-C
        p(MapUtil.sortJoin(map, "|", "-", true));   // 1-A|2-B|3-C
        p(MapUtil.joinIgnoreNull(map, "|", "-"));   // 1-A|2-B|3-C

        // toObjectArray(Map<?,?> map)                                              Map → Object[][]
        Object[][] objectArray = MapUtil.toObjectArray(map);
        for (Object[] objects : objectArray) {
            p(objects);
            // [1, A]
            // [2, B]
            // [3, C]
        }
    }
}
