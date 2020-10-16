package jar.google.guava.collect;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import l.demo.Demo;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

/**
 * Multimap
 * ListMultimap
 * SetMultimap
 * <p>
 * 1个 key 可以映射多个 value
 * <p>
 * boolean	            equals(@Nullable Object obj)
 * boolean	            isEmpty()
 * Multiset<K>	        keys()
 * Set<K>	            keySet()
 * Collection<V>	    values()
 * default void	        forEach(BiConsumer<? super K,? super V> action)     since: 21.0
 * <p>
 * http://www.ibloger.net/article/3316.html
 * https://blog.csdn.net/yaomingyang/article/details/80955872
 * https://guava.dev/releases/snapshot-jre/api/docs/index.html?com/google/common/collect/Multimap.html
 */
public class MultimapDemo extends Demo {

    private Multimap<Integer, Integer> map;

    public MultimapDemo() {
        // 相当于 Map<String, List<Integer>>
        map = ArrayListMultimap.create();
        map.put(1, 2);
        map.put(1, 2);
        map.put(1, 3);
        map.put(1, 4);
        map.put(2, 3);
        map.put(3, 3);
        map.put(4, 3);
        map.put(5, 3);
        p(map); // {1=[2, 2, 3, 4], 2=[3], 3=[3], 4=[3], 5=[3]}

    }

    /**
     * Map<K,Collection<V>>         asMap()
     * 把 multimap 映射成 Map<K,Collection<V>>，支持 remove 和修改操作，但是不支持 put 和 putAll
     */
    @Test
    public void asMap() {
        Map<Integer, Collection<Integer>> map2 = map.asMap();
        p(map2);
    }

    /**
     * void	                        clear()
     * 清空 multimap
     */
    @Test
    public void clear() {
        map.clear();
        p(map); // {}
    }

    /**
     * boolean	                    containsEntry(@Nullable Object key, @Nullable Object value)
     * boolean	                    containsKey(@Nullable Object key)
     * boolean	                    containsValue(@Nullable Object value)
     * 判断是否包含指定 key - value
     */
    @Test
    public void contains() {
        p(map.containsEntry(3, 3)); // true
        p(map.containsKey(3));         // true
        p(map.containsValue(3));       // true
    }

    /**
     * Collection<Map.Entry<K,V>>	entries()
     */
    @Test
    public void entries() {
        for (Map.Entry<Integer, Integer> entry : map.entries()) {
            p(entry);
            // 1=2
            // 1=2
            // 1=3
            // 1=4
            // 2=3
            // 3=3
            // 4=3
            // 5=3
        }
    }

    /**
     * boolean	                    putAll(K key, Iterable<? extends V> values)
     * boolean	                    putAll(Multimap<? extends K,? extends V> multimap)
     */
    @Test
    public void putAll() {
        map.putAll(6, Lists.newArrayList(4, 5));
        p(map); // {1=[2, 2, 3, 4], 2=[3], 3=[3], 4=[3], 5=[3], 6=[4, 5]}

        Multimap<Integer, Integer> map2 = ArrayListMultimap.create();
        map2.put(7, 1);
        map2.put(7, 7);
        map.putAll(map2);
        p(map); // {1=[2, 2, 3, 4], 2=[3], 3=[3], 4=[3], 5=[3], 6=[4, 5], 7=[1, 7]}
    }

    /**
     * boolean	                    remove(@Nullable Object key, @Nullable Object value)
     * Collection<V>                removeAll(@Nullable Object key)
     */
    @Test
    public void removeAll() {
        map.remove(1, 2);
        p(map); // {1=[2, 3, 4], 2=[3], 3=[3], 4=[3], 5=[3]}

        map.removeAll(1);
        p(map); // {2=[3], 3=[3], 4=[3], 5=[3]}
    }

    /**
     * Collection<V>	            replaceValues(K key, Iterable<? extends V> values)
     */
    @Test
    public void replaceValues() {
        map.replaceValues(1, Lists.newArrayList(3, 3, 3));
        p(map); // {1=[3, 3, 3], 2=[3], 3=[3], 4=[3], 5=[3]}
    }
}