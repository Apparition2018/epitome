package jar.google.guava.collect;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import l.demo.Demo;

/**
 * <a href="https://github.com/google/guava/wiki/NewCollectionTypesExplained#multimap">Multimap</a>
 * <p><a href="https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/collect/Multimap.html">Multimap api</a>
 * <p>1个 key 可以映射多个 value
 * <p>
 * <p><a href="https://github.com/google/guava/wiki/CollectionUtilitiesExplained#multimaps">Multimaps</a>
 * <p>参考：
 * <pre>
 * <a href="http://www.ibloger.net/article/3316.html">Guava Multimaps</a>
 * <a href="https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/collect/Multimaps.html">Multimaps api</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class MultimapDemo extends Demo {

    private static final Multimap<Integer, Integer> map;

    static {
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

    public static void main(String[] args) {
        // Map<K,Collection<V>>         asMap()
        // 把 multimap 映射成 Map<K,Collection<V>>，支持 remove 和修改操作，但是不支持 put 和 putAll
        p(map.asMap());                 // {1=[2, 2, 3, 4], 2=[3], 3=[3], 4=[3], 5=[3]}

        // boolean	                    containsEntry(Object key, Object value)
        // boolean	                    containsKey(Object key)
        // boolean	                    containsValue(Object value)
        p(map.containsEntry(3, 3));     // true
        p(map.containsKey(3));          // true
        p(map.containsValue(3));        // true

        // boolean	                    putAll(K key, Iterable<? extends V> values)
        // boolean	                    putAll(Multimap<? extends K,? extends V> multimap)
        map.putAll(6, Lists.newArrayList(4, 5));
        Multimap<Integer, Integer> map2 = ArrayListMultimap.create();
        map2.put(7, 1);
        map2.put(7, 7);
        map.putAll(map2);
        p(map);                         // {1=[2, 2, 3, 4], 2=[3], 3=[3], 4=[3], 5=[3], 6=[4, 5], 7=[1, 7]}

        // boolean	                    remove(Object key, Object value)
        // Collection<V>                removeAll(Object key)
        map.remove(6, 4);
        map.remove(6, 5);
        map.removeAll(7);
        p(map);                         // {1=[2, 2, 3, 4], 2=[3], 3=[3], 4=[3], 5=[3]}

        // Collection<V>	            replaceValues(K key, Iterable<? extends V> values)
        map.replaceValues(1, Lists.newArrayList(3, 3, 3));
        p(map);                         // {1=[3, 3, 3], 2=[3], 3=[3], 4=[3], 5=[3]}

        // void	                        clear()
        map.clear();
    }
}
