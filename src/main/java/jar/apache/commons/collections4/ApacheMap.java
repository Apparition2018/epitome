package jar.apache.commons.collections4;

import l.demo.Demo;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.map.*;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/map/package-summary.html">Apache Map</a>
 * <pre>
 * FixedSizeMap             修饰另一个 Map，使其大小固定
 * FixedSizeSortedMap       修饰另一个 SortedMap，使其大小固定
 * LazyMap                  修饰另一个 Map，使其根据需要在 Map 中创建对象
 * LazySortedMap            修饰另一个 SortedMap，使其根据需要在 Map 中创建对象
 * PredicatedMap            修饰另一个 Map，验证所有添加项是否与指定谓词匹配
 * PredicatedSortedMap      修饰另一个 SortedMap，验证所有添加项是否与指定谓词匹配
 * TransformedMap           修饰另一个 Map，在添加的时候对键值进行转换
 * TransformedSortedMap     修饰另一个 SortedMap，在添加的时候对键值进行转换
 * UnmodifiableMap          修饰另一个 Map，使 Map 不允许被修改
 * UnmodifiableSortedMap    修饰另一个 SortedMap，使 Map 不允许被修改
 * UnmodifiableOrderedMap   修饰另一个 OrderedMap，使 Map 不允许被修改
 * Flat3Map                 一个 Map 的实现，将数据存储在简单字段中，直到大小大于3为止。为性能而设计的 Map，甚至超过 HashMap。且具有良好的垃圾收集特性。
 * ReferenceMap             一个 Map 的实现，使用软引用的方式维护数据，允许垃圾收集器删除映射
 * ReferenceIdentityMap     一个 Map 的实现，允许垃圾收集器删除映射，基于 == 而不是 equals() 匹配键和值
 * SingletonMap             一个 Map 的实现，保存单个项且大小固定
 * StaticBucketMap          一个 Map 的高效、线程安全的实现
 * </pre>
 * 除了 UnmodifiableXXXMap，SingletonMap，StaticBucketMap 都不是线程同步的，且线程不安全的
 *
 * @author ljh
 * @since 2020/11/14 14:19
 */
public class ApacheMap extends Demo {

    /**
     * CaseInsensitiveMap           键大小写不敏感
     */
    @Test
    public void testCaseInsensitiveMap() {
        Map<String, Integer> caseInsensitiveMap = new CaseInsensitiveMap<>();
        caseInsensitiveMap.put("a", 1);
        p(caseInsensitiveMap.get("A")); // 1
    }

    /**
     * DefaultedMap                 修饰 Map，使其取值为 null 时返回默认值
     */
    @Test
    public void testDefaultedMap() {
        DefaultedMap<Integer, String> defaultedMap = DefaultedMap.defaultedMap(map, "-");
        p(defaultedMap.get(4)); // -
    }

    /**
     * <pre>
     * OrderedMap                   维持顺序 Map，允许向前向后迭代
     * LinkedMap                    维持插入顺序 Map
     * ListOrderedMap               修饰 Map，使其保持插入顺序
     * </pre>
     */
    @Test
    public void testListOrderedMap() {
        LinkedMap<Integer, String> linkedMap = new LinkedMap<>();
        linkedMap.put(3, "C");
        linkedMap.put(2, "B");
        linkedMap.put(1, "A");
        p(linkedMap);                   // {3=C, 2=B, 1=A}
        p(linkedMap.firstKey());        // 3
        p(linkedMap.previousKey(1));    // 2

        ListOrderedMap<Integer, String> listOrderedMap = ListOrderedMap.listOrderedMap(map);
        listOrderedMap.put(5, "F");
        listOrderedMap.put(4, "E");
        p(listOrderedMap);              // {1=A, 2=B, 3=C, 5=F, 4=E}
        p(listOrderedMap.lastKey());    // 4
        p(listOrderedMap.nextKey(1));   // 2
    }

    /**
     * MultiKeyMap                  多键-1值 Map
     */
    @Test
    public void testMultiKeyMap() {
        MultiKeyMap<Integer, String> multiKeyMap = new MultiKeyMap<>();
        multiKeyMap.put(1, 2, 3, "abc");
        multiKeyMap.put(2, 3, 4, "bcd");
        p(multiKeyMap);             // {MultiKey[2, 3, 4]=bcd, MultiKey[1, 2, 3]=abc}
        p(multiKeyMap.get(2, 3));   // null
        p(multiKeyMap.get(2, 3, 4));// bcd
    }

    /**
     * MultiValuedMap               1键-多值 Map
     */
    @Test
    public void testMultiValuedMap() {
        MultiValuedMap<Integer, String> multiValuedMap = new ArrayListValuedHashMap<>(map);
        multiValuedMap.put(3, "C");
        p(multiValuedMap); // {1=[A], 2=[B], 3=[C, C]}
    }

    /**
     * LRUMap                       最近最少使用 Map，大小固定
     */
    @Test
    public void testLRUMap() {
        LRUMap<Integer, String> lruMap = new LRUMap<>(map);
        p(lruMap); // {1=A, 2=B, 3=C}
        lruMap.get(1);
        p(lruMap); // {2=B, 3=C, 1=A}
        lruMap.put(4, "D");
        lruMap.put(5, "E");
        p(lruMap); // {1=A, 4=D, 5=E}
    }

    /**
     * PassiveExpiringMap           到期驱除 Map
     */
    @Test
    public void testPassiveExpiringMap() {
        PassiveExpiringMap<Integer, String> passiveExpiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
        passiveExpiringMap.put(1, "A");
        passiveExpiringMap.put(2, "B");
        passiveExpiringMap.put(3, "C");
        p(passiveExpiringMap.get(1));   // A
        sleep(2, TimeUnit.SECONDS);
        p(passiveExpiringMap.get(1));   // null
    }

    /**
     * CompositeMap
     */
    @Test
    public void testCompositeMap() {
    }

    /**
     * StaticBucketMap
     */
    @Test
    public void testStaticBucketMap() {
    }
}
