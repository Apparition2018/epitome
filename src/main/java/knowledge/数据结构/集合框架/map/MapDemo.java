package knowledge.数据结构.集合框架.map;

import l.demo.Demo;
import org.junit.Test;

import java.util.*;

/**
 * Map
 * Map          HashMap                 LinkedHashMap           TreeMap
 * 适用场景     快速访问                记录插入顺序              自动排序
 * https://jdk6.net/util/Map.html
 * <p>
 * Map API:
 * void	        putAll(Map<? extends K,? extends V> m)      从指定映射中将所有映射关系复制到此映射中（可选操作）
 * V	        remove(Object key)                          如果存在一个键的映射关系，则将其从此映射中移除（可选操作）
 * void	        clear()                                     从此映射中移除所有映射关系（可选操作）
 * boolean      containsKey(Object key)                     如果此映射包含指定键的映射关系，则返回 true
 * boolean      containsValue(Object value)                 如果此映射将一个或多个键映射到指定值，则返回 true
 * boolean      isEmpty()                                   如果此映射未包含键-值映射关系，则返回 true
 * <p>
 * HashMap API:
 * HashMap([int initialCapacity[, float loadFactor]])       构造一个带指定初始容量和加载因子的空 HashMap
 * HashMap(Map<? extends K,? extends V> m)                  构造一个映射关系与指定 Map 相同的新 HashMap
 */
public class MapDemo extends Demo {

    /**
     * 遍历
     */
    @Test
    public void traversal() {
        // 方法一
        // Set<K>	                keySet()        返回此映射中包含的键的 Set 视图
        for (Integer key : map.keySet()) {
            System.out.println("key = " + key + " and value = " + map.get(key));
        }

        // 方法二
        // Set<Map.Entry<K,V>>	    entrySet()      返回此映射中包含的映射关系的 Set 视图
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + " and value = " + entry.getValue());
        }

        // 方法三
        // Collection<V>	        values()        返回此映射中包含的值的 Collection 视图
        for (String v : map.values()) {
            System.out.println("value = " + v);
        }

        // 方法四
        map.forEach((k, v) -> System.out.println("key = " + k + " and value = " + v));
    }

    /**
     * TreeMap
     * TreeMap → NavigableMap → SortedMap → Map
     * 基于红黑树（Red-Black tree）的 NavigableMap 实现。该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序。
     * https://jdk6.net/util/TreeMap.html
     * <p>
     * PS：大部分 API 类似 TreeSet，可参考 TreeSetDemo
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
     * LRU
     * LRU 是 Least Recently Used 的缩写，即最近最少使用算法。
     * 以下例子为使用 LinkedHashMap 实现一个简易的 LRU 缓存
     * https://blog.csdn.net/Apeopl/article/details/90137398
     */
    @Test
    public void lruByLinkedHashMap() {
        SimpleLRUCache<Integer, String> lru = new SimpleLRUCache<>(5);
        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");
        p(lru); // 1:A 2:B 3:C

        lru.get(1);
        p(lru); // 2:B 3:C 1:A

        lru.put(4, "D");
        lru.put(5, "E");
        p(lru); // 1:A 2:B 3:C 4:D 5:E

        lru.put(6, "F");
        p(lru); // 2:B 3:C 4:D 5:E 6:F

    }

    private static class SimpleLRUCache<K, V> {

        private final int MAX_CACHE_SIZE;
        private final float DEFAULT_LOAD_FACTORY = 0.75f;
        LinkedHashMap<K, V> map;

        public SimpleLRUCache(int cacheSize) {
            MAX_CACHE_SIZE = cacheSize;
            int capacity = (int) Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;
            map = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTORY, true) {
                private static final long serialVersionUID = 1L;

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
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                stringBuilder.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
            }
            return stringBuilder.toString();
        }
    }

}
