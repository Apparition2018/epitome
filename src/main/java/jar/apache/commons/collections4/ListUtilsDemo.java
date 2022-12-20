package jar.apache.commons.collections4;

import l.demo.Demo;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * <a href="http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/ListUtils.html">ListUtils</a>
 * <pre>
 * static <T> List<T>   defaultIfNull(List<T> list, List<T> defaultList)        如果为 null，返回给定默认 List
 * static <T> List<T>   emptyIfNull(List<T> list)                               如果为 null，返回空 List
 * static int           hashCodeForList(Collection<?> list)                     生成 HashCode
 * static boolean       isEqualList(Collection<?> list1, Collection<?> list2)   判断两个集合是否相等
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ListUtilsDemo extends Demo {

    @Test
    public void testListUtils() {
        p(list);                                                    // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // static <E> int	        indexOf(List<E> list, Predicate<E> predicate)   返回符合 Predicate 的第一个元素的下标
        p(ListUtils.indexOf(list, i -> i > 4));                     // 4

        // static <T> List<List<T>>	partition(List<T> list, int size)               按指定长度分割 List
        p(ListUtils.partition(list, 2));                            // [[1, 2], [3, 4], [5, 6], [7, 8], [9]]
        p(ListUtils.partition(list, 3));                            // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

        // static <E> List<E>	    select(Collection<? extends E> inputCollection, Predicate<? super E> predicate)
        // static <E> List<E>       selectRejected(Collection<? extends E> inputCollection, Predicate<? super E> predicate)
        // 返回一个新 List，过滤掉 不符合/符合 Predicate 的元素
        p(ListUtils.select(list, i -> i % 2 == 0));                 // [2, 4, 6, 8]
        p(ListUtils.selectRejected(list, i -> i % 2 == 0));         // [1, 3, 5, 7, 9]


        p(subList);                                                 // [2, 3, 4, 5, 6]
        p(subList2);                                                // [4, 5, 6, 7, 8]

        // static String/List       longestCommonSubsequence(CharSequence a, CharSequence b)
        // static <E> List<E>	    longestCommonSubsequence(List<E> a, List<E> b[, Equator<? super E> equator])
        // 返回两个 List/String 的最长公共子序列
        p(ListUtils.longestCommonSubsequence(subList, subList2));   // [4, 5, 6]
        p(ListUtils.longestCommonSubsequence("abc", "bcd"));        // bc
    }

    /**
     * <pre>
     * static <E> List<E>	fixedSizeList(List<E> list)                             固定长度
     * static <E> List<E>   lazyList(List<E> list, Factory<? extends E> factory)    延迟创建
     * static <E> List<E>   predicatedList(List<E> list, Predicate<E> predicate)    追加限制
     * static <E> List<E>	synchronizedList(List<E> list)                          同步
     * static <E> List<E>	unmodifiableList(List<? extends E> list)                不可修改
     * static <E> List<E>	transformedList(List<E> list, Transformer<? super E,? extends E> transformer)   追加转换
     */
    @Test
    public void testListUtils2() {
        p(list);                // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // lazyList()           延迟创建
        List<Integer> lazyList = ListUtils.lazyList(ObjectUtils.clone(list), () -> 0);
        p(lazyList.get(9));     // 0

        // transformedList()    追加转换
        List<Integer> transformedList = ListUtils.transformedList(ObjectUtils.clone(list), i -> i * 2);
        transformedList.add(10);
        p(transformedList);     // [1, 2, 3, 4, 5, 6, 7, 8, 9, 20]

        // predicatedList()     追加限制
        List<Integer> predicatedList = ListUtils.predicatedList(ObjectUtils.clone(list), i -> i <= 9);
        predicatedList.add(10);// IllegalArgumentException: Cannot add Object '11' - Predicate '...ListUtilsDemo$$Lambda$3/33553623@1341870' rejected it
    }

    @Test
    public void testListUtils3() {
        p(subList);                                     // [2, 3, 4, 5, 6]
        p(subList2);                                    // [4, 5, 6, 7, 8]
        // static <E> List<E>	sum(List<? extends E> list1, List<? extends E> list2)           并集
        p(ListUtils.sum(subList, subList2));            // [2, 3, 4, 5, 6, 7, 8]，相当于 CollectionUtils.union(subList, subList2)
        // static <E> List<E>	union(List<? extends E> list1, List<? extends E> list2)         合并
        p(ListUtils.union(subList, subList2));          // [2, 3, 4, 5, 6, 4, 5, 6, 7, 8]
        // static <E> List<E>	subtract(List<E> list1, List<? extends E> list2)                差集
        p(ListUtils.subtract(subList, subList2));       // [2, 3]
        // static <E> List<E>	removeAll(Collection<E> collection, Collection<?> remove)       差集
        p(ListUtils.removeAll(subList, subList2));      // [2, 3]
        // static <E> List<E>	intersection(List<? extends E> list1, List<? extends E> list2)  交集
        p(ListUtils.intersection(subList, subList2));   // [4, 5, 6]
        // static <E> List<E>	retainAll(Collection<E> collection, Collection<?> retain)       交集
        p(ListUtils.retainAll(subList, subList2));      // [4, 5, 6]
    }
}
