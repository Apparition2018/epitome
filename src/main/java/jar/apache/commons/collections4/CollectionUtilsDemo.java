package jar.apache.commons.collections4;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;

/**
 * CollectionsUtils
 * <p>
 * static <C> boolean	    addAll(Collection<C> collection, ...)                           合并
 * static <O> Collection<O>	union(Iterable<? extends O> a, Iterable<? extends O> b)         并集
 * static <O> Collection<O>	intersection(Iterable<? extends O> a, Iterable<? extends O> b)  交集
 * static <E> Collection<E>	retainAll(Collection<E>/Iterable<E> ...)                        交集
 * static <O> Collection<O>	disjunction(Iterable<? extends O> a, Iterable<? extends O> b)   交集的补集
 * static <E> Collection<E>	removeAll(Collection<E>/Iterable<E> ...)                        差集
 * static <O> Collection<O>	subtract(Iterable<? extends O> a, Iterable<? extends O> b)      差集
 * <p>
 * static <T> boolean	    addIgnoreNull(Collection<T> collection, T object)       添加元素，忽略 null
 * static boolean	        containsAll(Collection<?> coll1, Collection<?> coll2)   包含所有
 * static boolean	        containsAny(Collection<?> coll1, Collection<?> coll2)   包含任意
 * static <T> Collection<T>	emptyCollection()                                       返回空集合，不可修改 ?
 * static <T> Collection<T>	emptyIfNull(Collection<T> collection)                   如果集合为 null，返回空集合
 * static boolean	        isEmpty(Collection<?> coll)                             判断为空
 * static boolean	        isNotEmpty(Collection<?> coll)                          判断非空
 * static boolean	        isEqualCollection(Collection<?> a, Collection<?> b)     判断相等
 * static boolean	        sizeIsEmpty(Object object)                              判断为空
 * <p>
 * static <C> Collection<C>	predicatedCollection(Collection<C> collection, Predicate<? super C> predicate)
 * static <E> Collection<E>	transformingCollection(Collection<E> collection, Transformer<? super E,? extends E> transformer)o
 * <p>
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/CollectionUtils.html
 * https://blog.csdn.net/sinat_34093604/article/details/79551924
 */
public class CollectionUtilsDemo {

    /**
     * 返回一个新集合，根据 Comparable 合并两个有序集合
     */
    @Test
    public void collate() {
        List<String> list1 = Arrays.asList("A", "2", "C");
        List<String> list2 = Arrays.asList("1", "b", "3");

        p(CollectionUtils.collate(list1, list2)); // [1, A, 2, C, b, 3]
    }

    /**
     * 返回一个新集合，根据 Transformer 转换
     */
    @Test
    public void collect() {
        List<String> list = Arrays.asList("1", "2", "3");

        // collect()，返回新集合
        p(CollectionUtils.collect(list, s -> Integer.parseInt(s) * 2)); // [2, 4, 6]
        p(list); // [1, 2, 3]

        // transform()，修改原集合
        CollectionUtils.transform(list, s -> String.valueOf(Integer.parseInt(s) * 2));
        p(list); // [2, 4, 6]
    }

    /**
     * 返回一个新集合，过滤掉不符合 Predicate 的元素
     */
    @Test
    public void select() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        // select()，返回新集合
        p(CollectionUtils.select(list, e -> e % 2 == 0)); // [2, 4, 6]
        p(list); // [1, 2, 3, 4, 5, 6]

        // filter()，修改原集合
        CollectionUtils.filter(list, e -> e % 2 == 0);
        p(list); // [2, 4, 6, 8]
    }

    /**
     * 转换，根据 Transformer 转换
     */
    @Test
    public void transform() {
        collect();
    }

    /**
     * static <T> boolean	filter(Iterable<T> collection, Predicate<? super T> predicate)
     * 过滤，过滤掉不符合 Predicate 的元素
     */
    @Test
    public void filter() {
        select();
    }

    /**
     * static <T> boolean	filterInverse(Iterable<T> collection, Predicate<? super T> predicate)
     * 过滤，过滤掉符合 Predicate 的元素
     */
    @Test
    public void filterInverse() {
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        CollectionUtils.filterInverse(intList, input -> input % 2 == 0);

        p(intList); // [1, 3, 5, 7, 9]
    }

    /**
     * static <K,V> Map.Entry<K,V>	get(Map<K,V> map, int index)
     * 返回 Map 指定索引的 Map.Entry
     * <p>
     * static Object	get(Object object, int index)
     * 返回 Object 指定索引的 值
     */
    @Test
    public void get() {
        Map<Object, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        System.out.println(CollectionUtils.get(map, 2)); // c=3
    }

    /**
     * static <O> Map<O,Integer>	getCardinalityMap(Iterable<? extends O> coll)
     * 返回一个 Map，表示元素的出现次数
     */
    @Test
    public void getCardinalityMap() {
        List<Integer> list = Arrays.asList(1, 6, 9, 1, 8, 4, 8, 1, 8, 1, 9, 8, 4);

        System.out.println(CollectionUtils.getCardinalityMap(list)); // {1=4, 4=2, 6=1, 8=4, 9=2}
    }

    /**
     * static boolean	isSubCollection(Collection<?> a, Collection<?> b)
     * 判断前一个集合是否为后一个集合的子集合，包括相等的情况
     * <p>
     * static boolean	isProperSubCollection(Collection<?> a, Collection<?> b)
     * 判断前一个集合是否为后一个集合的子集合，不包括相等的情况
     */
    @Test
    public void isSubCollection() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list3 = Arrays.asList(5, 4, 3, 2, 1);

        p(CollectionUtils.isSubCollection(list1, list2));       // true
        p(CollectionUtils.isSubCollection(list1, list3));       // true
        p(CollectionUtils.isEqualCollection(list1, list3));     // true

        p(CollectionUtils.isProperSubCollection(list1, list2)); // true
        p(CollectionUtils.isProperSubCollection(list1, list3)); // false

    }

    /**
     * static <E> Collection<List<E>>	permutations(Collection<E> collection)
     * 返回输入集合的所有排列的集合
     */
    @Test
    public void permutations() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        p(CollectionUtils.permutations(list)); // [[1, 2, 3], [1, 3, 2], [3, 1, 2], [3, 2, 1], [2, 3, 1], [2, 1, 3]]
    }

    /**
     * static <O> Collection<O>	disjunction(Iterable<? extends O> a, Iterable<? extends O> b)
     * 获取两个集合的交集的补集
     */
    @Test
    public void disjunction() {
        List<String> list1 = Arrays.asList("A", "A", "B", "B", "C");
        List<String> list2 = Arrays.asList("A", "B", "C");

        p(CollectionUtils.disjunction(list1, list2)); // [A, B]
    }

    public static <T> void p(T obj) {
        if (obj == null) {
            return;
        }
        System.out.println(obj);
    }


}
