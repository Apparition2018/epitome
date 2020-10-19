package jar.google.guava.collect;

import com.google.common.collect.*;
import knowledge.数据结构.集合框架.map.properties.PropertiesUtil;
import l.demo.Demo;
import l.demo.Person;
import org.junit.Test;

import java.util.*;

/**
 * Maps
 * <p>
 * http://www.ibloger.net/article/3314.html
 * https://guava.dev/releases/snapshot-jre/api/docs/index.html?com/google/common/collect/Maps.html
 */
public class MapsDemo extends Demo {

    private Person p1 = new Person(1, "张三");
    private Person p2 = new Person(2, "李四");
    private Map<Integer, Person> pMap;
    
    @Test
    public void testMaps() {
        Map<Integer, String> map2 = Maps.newHashMapWithExpectedSize(map.size());
        p(map2.size());
    }

    /**
     * List<Person>  →  Map<Object, Person>
     * <p>
     * static <K,V> ImmutableMap<K,V>	    uniqueIndex(Iterable<V> values, Function<? super V,K> keyFunction)
     * static <K,V> ImmutableMap<K,V>	    uniqueIndex(Iterator<V> values, Function<? super V,K> keyFunction)
     * 返回一个 Map，键为 Function 返回的值，值为 Iterable/Iterator 中相应的元素
     */
    @Test
    public void uniqueIndex() {
        List<Person> list = Lists.newArrayList(p1, p2);

        pMap = Maps.uniqueIndex(list.iterator(), Person::getId);
        p(pMap); // {1=Person{id=1, name='张三'}, 2=Person{id=2, name='李四'}}
    }

    /**
     * List<Person>  →  Map<Person, Object>
     * <p>
     * static <K,V> NavigableMap<K,V>	    apMap(NavigableSet<K> set, Function<? super K,V> function)
     * static <K,V> Map<K,V>	            apMap(Set<K> set, Function<? super K,V> function)
     * static <K,V> SortedMap<K,V>	        apMap(SortedSet<K> set, Function<? super K,V> function)
     * 返回一个 Map，键为 set 中的元素，值为 Function 返回的值
     */
    @Test
    public void apMap() {
        Set<Person> set = Sets.newHashSet(p1, p2);

        Map<Person, Integer> map = Maps.asMap(set, Person::getId);
        p(map); // {Person{id=1, name='张三'}=1, Person{id=2, name='李四'}=2}
    }

    /**
     * static <K,V1,V2> Map<K,V2>	        transformEntries(Map<K,V1> fromMap, Maps.EntryTransformer<? super K,? super V1,V2> transformer)
     * static <K,V1,V2> Map<K,V2>	        transformValues(Map<K,V1> fromMap, Function<? super V1,V2> function)
     * 转换给定 fromMap 的 value/entry
     */
    @Test
    public void transform() {
        uniqueIndex();

        Map<Integer, String> tranpMap = Maps.transformValues(pMap, Person::getName);
        p(tranpMap); // {1=张三, 2=李四}
    }

    /**
     * static <K,V> ImmutableMap<K,V>	    toMap(Iterable<K> keys, Function<? super K,V> valueFunction)
     * static <K,V> ImmutableMap<K,V>	    toMap(Iterator<K> keys, Function<? super K,V> valueFunction)
     * 返回一个 Map，其键为给定 keys 去除重复值后的值，其值为 Function 返回的值
     */
    @Test
    public void toMap() {
        p(repeatList); // [1, 1, 2, 2, 3, 3, 4, 5]

        ImmutableMap<Integer, Integer> map = Maps.toMap(repeatList, i -> Objects.requireNonNull(i) * 3);
        p(map); // {1=3, 2=6, 3=9, 4=12, 5=15}
    }

    /**
     * static <K,V> MapDifference<K,V>	        difference(Map<? extends K,? extends V> left, Map<? extends K,? extends V> right)
     * static <K,V> MapDifference<K,V>	        difference(Map<? extends K,? extends V> left, Map<? extends K,? extends V> right, Equivalence<? super V> valueEquivalence)
     * static <K,V> SortedMapDifference<K,V>    difference(SortedMap<K,? extends V> left, Map<? extends K,? extends V> right)
     * 返回一个 MapDifference，返回两个给定 map 之间的差异
     */
    @Test
    public void difference() {
        p(map); // {1=A, 2=B, 3=C}
        p(map2);// {1=A, 2=B, 3=C, 4=E, 5=D}

        MapDifference<Integer, Object> difference = Maps.difference(map, map2);
        p(difference.entriesInCommon());    // {1=A, 2=B, 3=C}
        p(difference.entriesOnlyOnRight()); // {4=E, 5=D}
    }

    /**
     * static <K,V> Map<K,V>	            filterEntries(Map<K,V> unfiltered, Predicate<? super Map.Entry<K,V>> entryPredicate)
     * static <K,V> Map<K,V>	            filterKeys(Map<K,V> unfiltered, Predicate<? super K> keyPredicate)
     * static <K,V> Map<K,V>	            filterValues(Map<K,V> unfiltered, Predicate<? super V> valuePredicate)
     * 过滤
     */
    @Test
    public void filter() {
        p(map); // {1=A, 2=B, 3=C}
        Map<Integer, String> filterMap = Maps.filterEntries(map, kvEntry -> Integer.parseInt(String.valueOf(Objects.requireNonNull(kvEntry).getKey())) >= 2);
        p(filterMap); // {2=B, 3=C}
    }

    /**
     * Properties  →  ImmutableMap
     * static ImmutableMap<String,String>	fromProperties(Properties properties)
     */
    @Test
    public void fromProperties() {
        Properties capitals = PropertiesUtil.loadProps("jdbc.properties");

        ImmutableMap<String, String> map = Maps.fromProperties(capitals);
        p(map); // {jdbc.url=jdbc:mysql://127.0.0.1:3306/epitome?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC, jdbc.username=root, jdbc.driver=com.mysql.jdbc.Driver, jdbc.password=root}
    }

}