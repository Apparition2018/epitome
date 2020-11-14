package jar.apache.commons.collections4;

import l.demo.Demo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * CollectionsUtils
 * <p>
 * static Object                get(Object object, int index)
 * static <K, V> Entry<K, V>    get(Map<K, V> map, int index)
 * static <T> Collection<T>	    emptyCollection()                                               返回空集合，不可修改 ?
 * static <T> Collection<T>	    emptyIfNull(Collection<T> collection)                           如果集合为 null，返回空集合
 * static <C> boolean	        addAll(Collection<C> collection, ...)                           合并
 * static <T> boolean	        addIgnoreNull(Collection<T> collection, T object)               添加元素，忽略 null
 * static boolean	            containsAll(Collection<?> coll1, Collection<?> coll2)           包含所有
 * static boolean	            containsAny(Collection<?> coll1, Collection<?> coll2)           包含任意
 * static boolean	            isEmpty(Collection<?> coll)                                     判断为空
 * static boolean	            isNotEmpty(Collection<?> coll)                                  判断非空
 * static boolean	            sizeIsEmpty(Object object)                                      判断为空
 * <p>
 * static <C> Collection<C>	predicatedCollection(Collection<C> collection, Predicate<? super C> predicate)
 * static <E> Collection<E>	transformingCollection(Collection<E> collection, Transformer<? super E,? extends E> transformer)o
 * <p>
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/CollectionUtils.html
 */
public class CollectionUtilsDemo extends Demo {

    @Test
    public void testCollectionUtils() {
        p(list);                                    // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // static void                      reverseArray(Object[] array)
        // 反转数组
        Object[] objects = list.toArray();
        CollectionUtils.reverseArray(objects);
        p(objects);                                 // [9, 8, 7, 6, 5, 4, 3, 2, 1]

        // static <O> Map<O,Integer>	    getCardinalityMap(Iterable<? extends O> coll)
        // 返回一个 Map，表示元素的出现次数
        p(CollectionUtils.getCardinalityMap(list)); // {1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 7=1, 8=1, 9=1}

        // static <E> Collection<List<E>>   permutations(Collection<E> collection)
        // 返回输入集合的所有排列的集合
        p(CollectionUtils.permutations(Arrays.asList(1, 2, 3)));
        // [[1, 2, 3], [1, 3, 2], [3, 1, 2], [3, 2, 1], [2, 3, 1], [2, 1, 3]]
    }

    /**
     * static <O> Collection<O>	union(Iterable<? extends O> a, Iterable<? extends O> b)         并集
     * static <O> Collection<O>	subtract(Iterable<? extends O> a, Iterable<? extends O> b)      差集
     * static <E> Collection<E>	removeAll(Collection<E>/Iterable<E> ...)                        差集
     * static <O> Collection<O>	intersection(Iterable<? extends O> a, Iterable<? extends O> b)  交集
     * static <E> Collection<E>	retainAll(Collection<E>/Iterable<E> ...)                        交集
     * static <O> Collection<O>	disjunction(Iterable<? extends O> a, Iterable<? extends O> b)   交集的补集
     */
    @Test
    public void testCollectionUtils2() {
        p(subList);                                             // [2, 3, 4, 5, 6]        
        p(subList2);                                            // [4, 5, 6, 7, 8]
        p(CollectionUtils.union(subList, subList2));            // [2, 3, 4, 5, 6, 7, 8]
        p(CollectionUtils.subtract(subList, subList2));         // [2, 3]
        p(CollectionUtils.removeAll(subList, subList2));        // [2, 3]
        p(CollectionUtils.intersection(subList, subList2));     // [4, 5, 6]
        p(CollectionUtils.retainAll(subList, subList2));        // [4, 5, 6]
        p(CollectionUtils.disjunction(subList, subList2));      // [2, 3, 7, 8]
    }

    @Test
    public void testCollectAndTransform() {
        p(list); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // collect()，返回一个新集合，根据 Transformer 转换
        p(CollectionUtils.collect(list, i -> i * 2));
        // [2, 4, 6, 8, 10, 12, 14, 16, 18]

        // transform()，根据 Transformer 转换
        CollectionUtils.transform(list, i -> i * 2);
        p(list); // [2, 4, 6, 8, 10, 12, 14, 16, 18]
    }

    @Test
    public void testSelectAndFilter() {
        p(list); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // select()         返回一个新集合，过滤掉不符合 Predicate 的元素
        p(CollectionUtils.select(list, e -> e % 2 == 0));
        // [2, 4, 6, 8]

        // filter()         过滤掉不符合 Predicate 的元素
        // filterInverse()  过滤掉符合 Predicate 的元素
        CollectionUtils.filter(list, e -> e % 2 == 0);
        p(list); // [2, 4, 6, 8]
    }

    /**
     * static boolean	isSubCollection(Collection<?> a, Collection<?> b)
     * 判断前一个集合是否为后一个集合的子集合，包括相等的情况
     * <p>
     * static boolean	isProperSubCollection(Collection<?> a, Collection<?> b)
     * 判断前一个集合是否为后一个集合的子集合，不包括相等的情况
     * <p>
     * static boolean   isEqualCollection(Collection<?> a, Collection<?> b)
     * 判断前一个集合与后一个集合是否相等
     */
    @Test
    public void isSubCollection() {
        p(list);                                                    // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        p(subList);                                                 // [2, 3, 4, 5, 6]
        p(descList);                                                // [9, 8, 7, 6, 5, 4, 3, 2, 1]
        p(CollectionUtils.isSubCollection(subList, list));          // true
        p(CollectionUtils.isSubCollection(descList, list));         // true
        p(CollectionUtils.isEqualCollection(descList, list));       // true
        p(CollectionUtils.isProperSubCollection(subList, list));    // true
        p(CollectionUtils.isProperSubCollection(descList, list));   // false
    }

    /**
     * 返回一个新集合，根据 Comparable 合并两个有序集合 ???
     */
    @Test
    public void collate() {
        p(subList);                                             // [2, 3, 4, 5, 6]        
        p(subList2);                                            // [4, 5, 6, 7, 8]

        p(CollectionUtils.collate(subList, subList2, false));   // [2, 3, 4, 5, 6, 7, 8]
        p(CollectionUtils.collate(subList, subList2, true));    // [2, 3, 4, 4, 5, 5, 6, 6, 7, 8]
        p(CollectionUtils.collate(subList, subList2,
                Comparator.reverseOrder(), false));             // [4, 5, 6, 7, 8, 2, 3, 4, 5, 6]
        p(CollectionUtils.collate(subList, subList2,
                Comparator.reverseOrder(), true));              // [4, 5, 6, 7, 8, 2, 3, 4, 5, 6]
    }

    /**
     * BoundedCollection 相关方法
     */
    @Test
    public void testBoundedCollection() {
        CircularFifoQueue<Object> circularFifoQueue = new CircularFifoQueue<>(list);
        FixedSizeList<Integer> fixedSizeList = FixedSizeList.fixedSizeList(list);

        // static boolean       isFull(Collection<? extends Object> coll)       判断能否添加元素
        p(CollectionUtils.isFull(circularFifoQueue));   // false
        p(CollectionUtils.isFull(fixedSizeList));       // true

        // static int           maxSize(Collection<? extends Object> coll)      返回不可变集合最大长度
        p(CollectionUtils.maxSize(circularFifoQueue));  // 9
        p(CollectionUtils.maxSize(fixedSizeList));      // 9
    }

}
