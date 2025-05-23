package jar.hutool.collection;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://doc.hutool.cn/pages/CollUtil/">CollUtil</a> 集合工具
 * <pre>
 * static <E,T extends Coll<E>> T   empty(Class<?> collectionClass)                                 根据给定的集合类型，返回对应的空集合，支持类型包括： *
 * static <T> Collection<T>         create(Class<?> collectionType)                                 创建新的集合对象
 * static <T> HashSet<T>            newHashSet([boolean isSorted, ]
 *                                      T.../Enumeration/Collection/Iterator)                       新建 HashSet
 * static <T> LinkedHashSet<T>      newLinkedHashSet(T... ts)                                       新建 LinkedHashSet
 * static <T> BlockingQueue<T>      newBlockingQueue(int capacity, boolean isLinked)                新建 BlockingQueue
 * static <T> HashSet<T>            set(boolean isSorted, T... ts)                                  新建 HashSet
 *
 * static <T> Collection<T>         addAll(Collection<T> coll, Object value[, Type elementType])    加入全部
 * static <T> List<T>               addAllIfNotContains(List<T> list, List<T> otherList)            加入全部，除非元素已经存在
 *
 * static <T> T                     get(Collection<T> collection, int index)                        获取集合中指定下标的元素值，支持负数
 * static <T> List<T>               getAny(Collection<T> collection, int... indexes)                获取集合中指定多个下标的元素，支持负数
 * static <T> T                     getLast(Collection<T> collection)                               获取集合的最后一个元素
 *
 * static <T extends Comparable> T  max(Collection<T> coll)                                         最大值
 * static <T extends Comparable> T  min(Collection<T> coll)                                         最小值
 *
 * static <T> Set<T>                emptyIfNull(Set<T> set)                                         如果为空则返回空 set
 * static <T> List<T>               emptyIfNull(List<T> set)                                        如果为空则返回空 list
 * static <T extends Coll<E>,E> T   defaultIfEmpty(T collection, T defaultCollection)               如果为空则返回默认 collection
 *
 * static boolean                   isEmpty(Enumeration/Iterable/Iterator/Map)                      是否为空
 * static boolean                   isNotEmpty(Enumeration/Iterable/Iterator/Map)                   是否不为空
 *
 * static <T> boolean               contains(Collection<T> coll, Predicate<? super T> containFunc)  包含
 * static boolean                   contains(Collection<?> coll, Object value)                      包含
 * static boolean                   containsAll(Collection<?> coll1, Collection<?> coll2)           包含所有
 * static boolean                   containsAny(Collection<?> coll1, Collection<?> coll2)           包含任意
 * <p>
 * static void                      clear(Collection<?>... collections)                             清除一个或多个集合内的元素，每个集合调用 clear()
 * </pre>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/collection/CollUtil.html">CollUtil api</a>
 *
 * @author ljh
 * @since 2020/10/27 20:13
 */
public class CollUtilDemo extends Demo {

    @Test
    public void testIterable() {

        // findOne(Iterable, Filter)                                    查找 Iterable 中第一个匹配 Filter 的元素
        p(CollUtil.findOne(list, i -> i % 2 == 0));                     // 2

        // findOneByField(Iterable, fieldName, fieldValue)              查找 Iterable 中第一个匹配 fileName 和 fileVale 的对象，支持 Bean 与 Map
        p(CollUtil.findOneByField(personList, "name", "李四"));          // Person{id=2, name='李四'}

        // getFieldValues(Iterable, fieldName, ignoreNull)              获取 Iterable 中指定 fileName 值的列表，支持 Bean 与 Map
        p(CollUtil.getFieldValues(personList, "name", true));           // [张三, 李四, 王五]

        // indexOfAll(Collection, Matcher)                              获取满足指定规则所有的元素的位置
        p(CollUtil.indexOfAll(ListUtil.of(1, 2, 3, 2, 1),
                Integer.valueOf(2)::equals));                           // [1, 3]

        // count(Iterable, Matcher)                                     集合中匹配 Matcher 的数量
        p(CollUtil.count(list, i -> i % 2 == 0));                       // 4

        // countMap(Iterator)                                           返回一个 Map，记录集合各元素出现的次数
        p(CollUtil.countMap(list));                                     // {1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 7=1, 8=1, 9=1}

        // group(Collection, Hash32<T> hash)                            分组，根据 Hash32 定义的 hash 算法
        p(CollUtil.group(list, i -> i % 4));                            // [[4, 8], [1, 5, 9], [2, 6], [3, 7]]

        // groupByField(Collection, fieldName)                          分组，根据 fieldName，非 Bean 放在第一个分组
        p(CollUtil.groupByField(personList, "name"));                   // [[Person{id=1, name='张三'}], [Person{id=2, name='李四'}], [Person{id=3, name='王五'}]]
    }

    /**
     * 函数式接口相关
     */
    @Test
    public void testFunctionalInterface() {

        // extract(Iterable<?>, Editor<Object>, ignoreNull)             经过 Editor 处理返回
        p(CollUtil.extract(list, o -> {
            if (o instanceof Integer) {
                if (((Integer) o) % 2 == 1) return o;
            }
            return null;
        }, true));                                                      // [1, 3, 5, 7, 9]

        // map(Iterable, Function, ignoreNull)                          经过 Function 处理返回
        p(CollUtil.map(list, i -> i * 2, true));                        // [2, 4, 6, 8, 10, 12, 14, 16, 18]

        // trans(Collection, Function<? super F,? extends T>)           经过 Function 处理返回
        p(CollUtil.trans(list, i -> i * 2));                             // [2, 4, 6, 8, 10, 12, 14, 16, 18]
    }

    /**
     * 涉及 Map 的相关方法
     * <pre>
     * static <K> Set<K>                keySet(Collection<Map<K,?>> mapCollection)                          获取指定 Map 列表中所有的 Key
     * static <K,V> TreeMap<K,V>        sort(Map<K,V> map, Comparator<? super K> comparator)                Map 排序
     * static <K,V> LinkedHashMap<K,V>  sortToMap(Collection<Map.Entry<K,V>>, Comparator<Map.Entry<K,V>>)   通过 Comparator<Map.Entry<K,V>> 对 Map 排序
     * static <K,V> LinkedHashMap<K,V>  sortByEntry(Map<K,V> map, Comparator<Map.Entry<K,V>> comparator)    通过 Comparator<Map.Entry<K,V>> 对 Map 排序
     * static <K,V> List<Map.Entry<K,V>>sortEntryToList(Collection<Map.Entry<K,V>> collection)              按照 Value 的值对 Entry<K, V> 做排序
     * </pre>
     */
    @Test
    public void testMap() {
        // sortEntryToList(Collection<Map.Entry<K,V>>)                  按照 Value 的值对 Entry<K, V> 做排序
        p(CollUtil.sortEntryToList(map.entrySet()));                    // [1=A, 2=B, 3=C]

        // values(Collection<Map<?,V>> mapCollection)                   获取 mapList 中 map 的所有的 value
        p(CollUtil.values(ListUtil.of(map, map, map)));                 // [A, B, C, A, B, C, A, B, C]

        // valuesOfKeys(Map<K,V>, Iterable<K>/Iterator<K>/K... keys)    根据 Keys 获取对应 key 的 value，如果为 null 则返回 null
        p(CollUtil.valuesOfKeys(map, list));                            // [A, B, C, null, null, null, null, null, null]

        // zip(Collection<K> keys, Collection<V> values)                给定两个集合，然后两个集合中的元素一一对应，成为一个 Map
        p(CollUtil.zip(list, list));                                    // {1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9}
    }

    /**
     * 修改原集合相关方法
     * <pre>
     * static T         removeAny(T collection, E... elesRemoved)       去除集合中的多个元素，修改原集合
     * static T         removeBlank(T collection)                       去除 null 或 "" 或 空白字符串 元素，修改原集合
     * static T         removeEmpty(T collection)                       去除 null 或 "" 元素，修改原集合
     * static T         removeNull(T collection)                        去除 null 元素，修改原集合
     * </pre>
     */
    @Test
    public void modifyOriginal() {
        List<Integer> list1 = ObjectUtil.clone(list);
        List<Integer> list2 = ObjectUtil.clone(list);

        // padLeft(List<T> list, int minLen, T padObj)                  左补齐
        CollUtil.padLeft(list1, 12, 0);
        p(list1); // [0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        // padRight(List<T> list, int minLen, T padObj)                 右补齐
        CollUtil.padRight(list2, 12, 0);
        p(list2); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0]
    }

    @Test
    public void testCollUtil2() {
        p(subList);                                                     // [2, 3, 4, 5, 6]
        p(subList2);                                                    // [4, 5, 6, 7, 8]
        p(descList);                                                    // [9, 8, 7, 6, 5, 4, 3, 2, 1]
        // union()
        p(CollUtil.union(subList, subList2, descList));                 // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        p(CollUtil.unionAll(subList, subList2, descList));              // [2, 3, 4, 5, 6, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1]
        p(CollUtil.unionDistinct(subList, subList2, descList));         // [2, 3, 4, 5, 6, 7, 8, 9, 1]
        // subtract()                                                   差集
        p(CollUtil.subtract(subList, subList2));                        // [2, 3]
        p(CollUtil.subtractToList(subList, subList2));                  // [2, 3]
        // intersection()                                               交集
        p(CollUtil.intersection(subList, subList2));                    // [4, 5, 6]
        p(CollUtil.intersectionDistinct(subList, subList2, descList));  // [4, 5, 6]
        // disjunction()                                                交集的补集
        p(CollUtil.disjunction(subList, subList2));                     // [2, 3, 7, 8]
    }

    /**
     * static <E> Collection<E>	    toCollection(Iterable<E> iterable)  Iterable → Collection
     */
    @Test
    public void convert() {
        // asEnumeration(Iterator<E> iter)          Iterator → Enumeration
        Enumeration<Integer> enumeration = CollUtil.asEnumeration(list.iterator());

        // toTreeSet(Collection, Comparator)        Collection → TreeSet
        TreeSet<Integer> treeSet = CollUtil.toTreeSet(list, Comparator.reverseOrder());

        // 	unmodifiable(Collection<? extends T> c) Collection → unmodifiable
        Collection<Integer> unmodifiable = CollUtil.unmodifiable(list);
    }

    /**
     * 将给定的多个集合放到一个列表中，根据给定的 Comparator 对象排序，然后分页取数据
     */
    @Test
    public void sortPageAll() {
        Comparator<Integer> comparator = Integer::compareTo;
        List<Integer> sortPageAll = CollUtil.sortPageAll(0, 9, comparator, list, list, list);
        p(sortPageAll); // [1, 1, 1, 2, 2, 2, 3, 3, 3]
    }

    /**
     * 传入一个栈对象，然后弹出指定数目的元素对象，弹出是指 pop() 方法，会从原栈中删掉
     */
    @Test
    public void popPart() {
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        p(CollUtil.popPart(queue, 2));  // [1, 2]
        p(queue);                       // [3]
    }
}
