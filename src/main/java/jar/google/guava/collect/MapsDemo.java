package jar.google.guava.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import knowledge.data.structure.collections.framework.map.properties.PropertiesUtil;
import l.demo.Demo;
import l.demo.Person;
import org.junit.Test;

import java.util.Map;
import java.util.Objects;

/**
 * Map
 * https://guava.dev/releases/snapshot-jre/api/docs/index.html?com/google/common/collect/Maps.html
 * http://www.ibloger.net/article/3314.html
 * <p>
 * static ConcurrentMap<K, V>   newConcurrentMap()                                  创建 ConcurrentMap
 * static TreeMap<K, V>         newTreeMap([SortedMap<K, ? extends V> map])         创建 TreeMap
 * static TreeMap<K, V>         newTreeMap(@Nullable Comparator<C> comparator)      创建 TreeMap，根据 Comparator
 * static EnumMap<K, V>         newEnumMap(Map<K, ? extends V>/Class<K>)            创建 EnumMap
 * static IdentityHashMap<K, V> newIdentityHashMap()                                创建 IdentityHashMap
 * <p>
 * static ImmutableMap<K, V>    immutableEnumMap(Map<K, ? extends V> map)           Map → immutableEnumMap
 * static XXXMap<K, V>          unmodifiableXXXMap(XXXMap<K, ? extends V> map)      XXXMap → unmodifiableXXXMap
 * static XXXMap<K, V>          synchronizedXXXMap(XXXMap<K, V> map)                XXXMap → synchronizedXXXMap
 * static Entry<K, V>           immutableEntry(@Nullable K key, @Nullable V value)  key + value → Entry
 * static Converter<A, B>       asConverter(BiMap<A, B> bimap)                      BiMap → Converter
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class MapsDemo extends Demo {

    @Test
    public void testMaps() {
        // static <K, V> HashMap<K, V>          newHashMapWithExpectedSize(int expectedSize)
        // static <K, V> HashMap<K, V>          newLinkedHashMapWithExpectedSize(int expectedSize)
        // 创建 HashMap/LinkedHashMap，有足够的初始容量来容纳元素，而不需要 growth
        Map<Integer, String> guavaMap = Maps.newHashMapWithExpectedSize(map.size());

        // static <K, V> MapDifference<K, V>    difference(Map<? extends K, ? extends V> left, Map<? extends K, ? extends V> right)
        // 返回两个 map 之间的差异
        MapDifference<Integer, Object> difference = Maps.difference(map, map2);
        p(difference.entriesInCommon());    // {1=A, 2=B, 3=C}
        p(difference.entriesOnlyOnRight()); // {4=D, 5=E, 6=F, 7=G, 8=H, 9=I}
    }

    /**
     * 函数式接口相关
     * <p>
     * static <K, V1, V2> Map<K, V2>    transformEntries(Map<K, V1> fromMap, Maps.EntryTransformer<? s K, ? s V1, V2> transformer)  ???
     */
    @Test
    public void testFunctionalInterface() {
        // static <K,V> ImmutableMap<K,V>       uniqueIndex(Iterable<V>/Iterator<V>, Function<? super V,K> keyFunction)
        // List<Bean> → Map<field, Bean>
        ImmutableMap<Integer, Person> intPersonMap = Maps.uniqueIndex(personList.iterator(), Person::getId);
        p(intPersonMap); // {1=Person{id=1, name='张三'}, 2=Person{id=2, name='李四'}, 3=Person{id=3, name='王五'}}

        // static <K,V1,V2> Map<K,V2>	        transformValues(Map<K,V1> fromMap, Function<? super V1,V2> function)
        // Map<field, Bean> → List<field2>
        p(Maps.transformValues(intPersonMap, Person::getName));         // {1=张三, 2=李四, 3=王五}

        // static <K,V> Map<K,V>	            apMap(Set<K> set, Function<? super K,V> function)
        // Set<Bean> → Map<Bean, field>
        p(Maps.asMap(Sets.newHashSet(personList), Person::getId));      // {Person{id=1, name='张三'}=1, Person{id=2, name='李四'}=2, Person{id=3, name='王五'}=3}

        // static <K,V> ImmutableMap<K,V>       toMap(Iterable<K>Iterator<K>, Function<? super K,V> valueFunction)
        // Iterable/Iterator<K> → Map<K, V>，经过 Function 处理返回
        p(Maps.toMap(repeatList, i -> Objects.requireNonNull(i) * 2));  // {1=2, 2=4, 3=6, 4=8, 5=10}

        // static <K,V> Map<K,V>	            filterEntries(Map<K,V> unfiltered, Predicate<? super Map.Entry<K,V>> entryPredicate)
        // static <K,V> Map<K,V>	            filterKeys(Map<K,V> unfiltered, Predicate<? super K> keyPredicate)
        // static <K,V> Map<K,V>	            filterValues(Map<K,V> unfiltered, Predicate<? super V> valuePredicate)
        // 经过 Predicate 判断返回
        p(Maps.filterEntries(map2, (entry -> Objects.requireNonNull(entry).getKey() >= 2))); // {2=B, 3=C, 4=D, 5=E, 6=F, 7=G, 8=H, 9=I}

        // static ImmutableMap<String,String>	fromProperties(Properties properties)
        // Properties  →  ImmutableMap
        p(Maps.fromProperties(PropertiesUtil.loadProps(JDBC_PROP_FILENAME)));
    }

}
