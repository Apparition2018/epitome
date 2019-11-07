package jar.google.guava.collect;

import com.google.common.collect.*;
import knowledge.api.util.properties.PropertiesUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Maps
 * <p>
 * http://www.ibloger.net/article/3314.html
 * https://guava.dev/releases/snapshot-jre/api/docs/index.html?com/google/common/collect/Maps.html
 */
public class MapsDemo {

    private Person p1;
    private Person p2;
    private Map<String, Person> map;

    {
        p1 = new Person("001", "张三");
        p2 = new Person("002", "李四");
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Person {
        private String Id;
        private String name;
    }

    /**
     * static <K,V> NavigableMap<K,V>	    asMap(NavigableSet<K> set, Function<? super K,V> function)
     * static <K,V> Map<K,V>	            asMap(Set<K> set, Function<? super K,V> function)
     * static <K,V> SortedMap<K,V>	        asMap(SortedSet<K> set, Function<? super K,V> function)
     * 返回一个 Map，键为 set 中的元素，值为 Function 返回的值
     * <p>
     * List<Person>  →  Map<Person, Object>
     */
    @Test
    public void asMap() {
        Set<Person> set = Sets.newHashSet(p1, p2);

        Map<Person, String> map = Maps.asMap(set, Person::getId);
        System.out.println(map);
        // {jar.google.guava.collect.MapsDemo$Person@5e025e70=001, jar.google.guava.collect.MapsDemo$Person@1fbc7afb=002}
    }

    /**
     * static <K,V> ImmutableMap<K,V>	    uniqueIndex(Iterable<V> values, Function<? super V,K> keyFunction)
     * static <K,V> ImmutableMap<K,V>	    uniqueIndex(Iterator<V> values, Function<? super V,K> keyFunction)
     * 返回一个 Map，键为 Function 返回的值，值为 Iterable/Iterator 中相应的元素
     * <p>
     * List<Person>  →  Map<Object, Person>
     */
    @Test
    public void uniqueIndex() {
        List<Person> list = Lists.newArrayList(p1, p2);

        map = Maps.uniqueIndex(list.iterator(), Person::getId);
        System.out.println(map);
        // {001=jar.google.guava.collect.MapsDemo$Person@4590c9c3, 002=jar.google.guava.collect.MapsDemo$Person@32e6e9c3}
    }

    /**
     * static <K,V1,V2> Map<K,V2>	        transformEntries(Map<K,V1> fromMap, Maps.EntryTransformer<? super K,? super V1,V2> transformer)
     * static <K,V1,V2> Map<K,V2>	        transformValues(Map<K,V1> fromMap, Function<? super V1,V2> function)
     * 转换给定 fromMap 的 value/entry
     */
    @Test
    public void transform() {
        uniqueIndex();

        Map<String, String> transMap = Maps.transformValues(map, Person::getName);
        System.out.println(transMap); // {001=张三, 002=李四}
    }

    /**
     * static <K,V> ImmutableMap<K,V>	    toMap(Iterable<K> keys, Function<? super K,V> valueFunction)
     * static <K,V> ImmutableMap<K,V>	    toMap(Iterator<K> keys, Function<? super K,V> valueFunction)
     * 返回一个 Map，其键为给定 keys 去除重复值后的值，其值为 Function 返回的值
     */
    @Test
    public void toMap() {
        List<Integer> list = Lists.newArrayList(1, 2, 2, 3, 4, 4, 5);

        ImmutableMap<Integer, Integer> map = Maps.toMap(list, i -> Objects.requireNonNull(i) * 3);
        System.out.println(map); // {1=3, 2=6, 3=9, 4=12, 5=15}
    }

    /**
     * static <K,V> MapDifference<K,V>	        difference(Map<? extends K,? extends V> left, Map<? extends K,? extends V> right)
     * static <K,V> MapDifference<K,V>	        difference(Map<? extends K,? extends V> left, Map<? extends K,? extends V> right, Equivalence<? super V> valueEquivalence)
     * static <K,V> SortedMapDifference<K,V>	difference(SortedMap<K,? extends V> left, Map<? extends K,? extends V> right)
     * 返回一个 MapDifference，返回两个给定 map 之间的差异
     */
    @Test
    public void difference() {
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("1", "壹");
        map1.put("2", "贰");

        Map<String, String> map2 = Maps.newHashMap(map1);
        map2.put("3", "叁");

        MapDifference<String, Object> difference = Maps.difference(map1, map2);
        System.out.println(difference.entriesInCommon());       // {1=壹, 2=贰}
        System.out.println(difference.entriesOnlyOnRight());    // {3=叁}
    }

    /**
     * static <K,V> Map<K,V>	            filterEntries(Map<K,V> unfiltered, Predicate<? super Map.Entry<K,V>> entryPredicate)
     * static <K,V> Map<K,V>	            filterKeys(Map<K,V> unfiltered, Predicate<? super K> keyPredicate)
     * static <K,V> Map<K,V>	            filterValues(Map<K,V> unfiltered, Predicate<? super V> valuePredicate)
     * 过滤
     */
    @Test
    public void filter() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "壹");
        map.put("2", "贰");
        map.put("3", "叁");

        Map<String, String> filterMap = Maps.filterEntries(map, kvEntry -> Integer.parseInt(Objects.requireNonNull(kvEntry).getKey()) >= 2);
        System.out.println(filterMap); // {2=贰, 3=叁}
    }

    /**
     * static ImmutableMap<String,String>	fromProperties(Properties properties)
     * Properties  →  ImmutableMap
     */
    @Test
    public void fromProperties() throws IOException {
        Properties capitals = PropertiesUtil.loadProps("src/main/java/jar/google/guava/collect/capitals.properties");

        ImmutableMap<String, String> map = Maps.fromProperties(capitals);
        System.out.println(map); // {广东=广州, 江苏=南京, 山东=济南}
    }

}