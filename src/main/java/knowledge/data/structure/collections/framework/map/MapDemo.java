package knowledge.data.structure.collections.framework.map;

import com.google.common.collect.Maps;
import knowledge.data.structure.collections.framework.collection.SetDemo;
import l.demo.Demo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

import java.io.Serial;
import java.util.*;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Map.html">Map</a>
 * </pre>
 * 阿里编程规约：高度注意 Map 类集合 K / V 能不能存储 null 值的情况
 * <pre>
 * 集合类                  key         value       Super           说明                  底层结构 & 特点
 * Hashtable            not null    not null    Dictionary      线程安全        数组 + 链表（synchronized put/get）
 * TreeMap              not null    null        AbstractMap     线程不安全       红黑树（自平衡的二叉查找树）
 * ConcurrentHashMap    not null    not null    AbstractMap     锁分段技术       数组 + 链表 + 红黑树（CAS + Synchronized）
 * HashMap              null        null        AbstractMap     线程不安全       数组 + 链表 + 红黑树
 * LinkedHashMap        null        null        HashMap         线程不安全       数组 + 链表 + 双向链表（维护插入/访问顺序）
 * 注：①HashMap 怎么解决哈希冲突？
 *      a.落到同一个桶（数组位），链表解决
 *      b.扩容稀释冲突
 *    ②当数组长度 ≥ 64，且链表长度 ≥ 8，则将链表转为红黑树
 *    ③ConcurrentHashMap：空桶用 CAS 插入，非空桶用 synchronized 锁该桶（即链表头或树根节点）
 * </pre>
 * <p>************************************************************
 * <p>HashMap
 * <pre>
 * 1 底层实现是数组链表，JDK8 加上了红黑树
 * 2 允许空键和空值（但空键只有一个，且放在第一位）
 * 3 两个关键因子：初始容量16、加载因子0.75
 *   3.1 扩容总是原来的2倍，即容量始终为2的幂次方
 *   3.2 遍历整个 Map 需要的时间与 桶（数组） 的长度成正比
 *   3.3 加载因子越大，发生冲突的可能性就越大，反之需要频繁 resize，性能降低
 *   3.4 建议在创建 HashMap 的时候指定初始化容量，可使用 guava 工具方法 {@link Maps#newHashMapWithExpectedSize(int)}（阿里编程规约）
 * </pre>
 * <p>************************************************************
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class MapDemo extends Demo {

    /** 返回一个不可修改的 Map，JDK9 引入 */
    @Test
    public void testOf() {
        // Map<K, V>    of(K k1, V v1, ...)
        // Map<K, V>    ofEntries(Entry<? extends K, ? extends V>... entries)
        // 返回一个不可修改的 Map，JDK9 引入
        Map<Integer, String> map = Map.of(1, "A", 2, "B", 3, "C");
        map = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(1, "A"),
            new AbstractMap.SimpleEntry<>(2, "B"),
            new AbstractMap.SimpleEntry<>(3, "C")
        );

        // Map<K, V>    copyOf(Map<? extends K, ? extends V> map)
        // 返回一个不可修改的 Map，JDK10 引入
        map = Map.copyOf(map);
    }

    @Test
    public void testPutIfAbsent() {
        map.put(4, null);
        p(map);                     // {1=A, 2=B, 3=C, 4=null}

        // V    putIfAbsent(K key, V value)         k 不存在，或指定 k 的 v 为 null，才设置值
        p(map.putIfAbsent(3, "c")); // C
        p(map.putIfAbsent(4, "d")); // null
        p(map.putIfAbsent(5, "e")); // null
        p(map);                     // {1=A, 2=B, 3=C, 4=d, 5=e}
    }

    /**
     * put      设置指定值，并返回插入之前的值
     * <p>
     * compute  设置并返回执行 BiFunction 之后的值
     *
     * @see <a href="https://blog.csdn.net/qq_43679056/article/details/104976819">compute</a>
     */
    @Test
    public void testCompute() {
        p(map.put(1, "a")); // A
        // k 存在时，BiFunction 返回值不为 null，则进行更新，否者删除
        p(map.compute(2, (k, v) -> k + v)); // 2B
        // k 不存在时，BiFunction 返回值不为 null，进行新增
        p(map.compute(4, (k, v) -> null));  // null
        // k 不存在时，不会进行新增
        p(map.computeIfPresent(4, (k, v) -> k + v)); // null
        // k 不存在或 v 为 null，BiFunction 才起作用
        p(map.computeIfAbsent(4, (k) -> "D")); // D
        p(map); // {4=D, 1=a, 2=2B, 3=C}
    }

    @Test
    public void testMerge() {
        @Data
        @Accessors(chain = true)
        class Student {
            private int id;
            private String subject;
            private int score;
        }

        List<Student> studentList = List.of(
            new Student().setId(1).setSubject("chinese").setScore(99),
            new Student().setId(1).setSubject("math").setScore(100),
            new Student().setId(1).setSubject("english").setScore(98),
            new Student().setId(2).setSubject("chinese").setScore(85),
            new Student().setId(2).setSubject("math").setScore(60),
            new Student().setId(2).setSubject("english").setScore(100)
        );
        Map<Integer, Integer> studentMap = new HashMap<>();
        studentList.forEach(student -> studentMap.merge(student.getId(), student.getScore(), Integer::sum));
        System.out.println(studentMap); // {1=297, 2=245}
    }

    /**
     * 阿里编程规约：
     * <pre>
     * 1 使用 Map 的方法 keySet() / values() / entrySet() 返回集合对象时，不可以对其进行添加元素操作，否则会抛出 UnsupportedOperationException 异常
     * 2 使用 entrySet 遍历 Map 类集合 KV，而不是 keySet 方式进行遍历
     *   keySet 其实是遍历了 2 次，一次是转为 Iterator 对象，另一次是从 hashMap 中取出 key 所对应的 value。
     *   而 entrySet 只是遍历了一次就把 key 和 value 都放到了 entry 中，效率更高。如果是 JDK8，使用 Map.forEach 方法
     * </pre>
     */
    @Test
    public void traversal() {
        // 方法一
        // Set<Map.Entry<K,V>>	    entrySet()      返回此映射中包含的映射关系的 Set 视图
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            p("key = " + entry.getKey() + " and value = " + entry.getValue());
        }

        // 方法二
        // Set<K>	                keySet()        返回此映射中包含的键的 Set 视图
        for (Integer key : map.keySet()) {
            p("key = " + key + " and value = " + map.get(key));
        }

        // 方法三
        // Collection<V>	        values()        返回此映射中包含的值的 Collection 视图
        for (String v : map.values()) {
            p("value = " + v);
        }

        // 方法四
        map.forEach((k, v) -> p("key = " + k + " and value = " + v));
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/TreeMap.html">TreeMap</a>
     * <pre>
     * TreeMap → NavigableMap → SortedMap → Map
     * 基于红黑树（Red-Black tree）的 NavigableMap 实现。该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序。
     * 大部分 API 类似 TreeSet {@link SetDemo#testTreeSet()}
     * </pre>
     */
    @Test
    public void testTreeMap() {
        map.put(5, "E");
        map.put(4, "D");
        // TreeMap([Comparator<? super K> comparator])  构造一个新的、空的树映射，该映射根据给定比较器进行排序
        // TreeMap(Map<? extends K,? extends V> m)      构造一个与给定映射具有相同映射关系的新的树映射，该映射根据其键的自然顺序 进行排序
        // TreeMap(SortedMap<K,? extends V> m)          构造一个与指定有序映射具有相同映射关系和相同排序顺序的新的树映射
        TreeMap<Integer, String> treeMap = new TreeMap<>(map);
        p(map); // {1=A, 2=B, 3=C, 4=D, 5=E}

        // NavigableSet<K>	    navigableKeySet()       返回此映射中所包含键的 NavigableSet 视图
        NavigableSet<Integer> keySet = treeMap.navigableKeySet();
        for (Integer key : keySet) {
            p(key);
        }
    }

    /**
     * LRU (Least Recently Used)
     * 即最近最少使用算法。
     *
     * @see <a href="https://blog.csdn.net/Apeopl/article/details/90137398">使用 LinkedHashMap 实现一个简易的 LRU 缓存</a>
     */
    @Test
    public void lruByLinkedHashMap() {
        SimpleLRUCache<Integer, String> lru = new SimpleLRUCache<>(3);
        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");
        p(lru); // 1:A 2:B 3:C

        lru.get(1);
        p(lru); // 2:B 3:C 1:A

        lru.put(4, "D");
        lru.put(5, "E");
        p(lru); // 1:A 4:D 5:E
    }

    private static class SimpleLRUCache<K, V> {

        private final int MAX_CACHE_SIZE;
        private final float DEFAULT_LOAD_FACTORY = 0.75F;
        LinkedHashMap<K, V> map;

        public SimpleLRUCache(int cacheSize) {
            MAX_CACHE_SIZE = cacheSize;
            int capacity = (int) Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;
            map = new LinkedHashMap<>(capacity, DEFAULT_LOAD_FACTORY, true) {
                @Serial
                private static final long serialVersionUID = -2584653547800826492L;

                @Override
                protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                    return size() > MAX_CACHE_SIZE;
                }
            };

        }

        public synchronized void put(K key, V value) {
            map.put(key, value);
        }

        public synchronized V get(K key) {
            return map.get(key);
        }

        public synchronized void remove(K key) {
            map.remove(key);
        }

        public synchronized Set<Map.Entry<K, V>> getAll() {
            return map.entrySet();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
            }
            return sb.toString();
        }
    }
}
