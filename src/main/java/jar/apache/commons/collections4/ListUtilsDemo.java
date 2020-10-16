package jar.apache.commons.collections4;

import l.demo.Demo;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * ListUtils
 * <p>
 * static <E> List<E>	union(List<? extends E> list1, List<? extends E> list2)         合并
 * static <E> List<E>	sum(List<? extends E> list1, List<? extends E> list2)           并集
 * static <E> List<E>	intersection(List<? extends E> list1, List<? extends E> list2)  交集
 * static <E> List<E>	retainAll(Collection<E> collection, Collection<?> retain)       交集
 * static <E> List<E>	subtract(List<E> list1, List<? extends E> list2)                差集
 * static <E> List<E>	removeAll(Collection<E> collection, Collection<?> remove)       差集
 * <p>
 * static <E> List<E>	fixedSizeList(List<E> list)                                     固定长度
 * static <E> List<E>	lazyList(List<E> list, Factory<? extends E> factory)            Lazy
 * static <E> List<E>	synchronizedList(List<E> list)                                  同步
 * static <E> List<E>	unmodifiableList(List<? extends E> list)                        不可修改
 * <p>
 * static <T> List<T>	defaultIfNull(List<T> list, List<T> defaultList)                如果为 null，返回给定默认 List
 * static <T> List<T>	emptyIfNull(List<T> list)                                       如果为 null，返回空 List
 * static int	        hashCodeForList(Collection<?> list)                             生成 HashCode
 * static boolean	    isEqualList(Collection<?> list1, Collection<?> list2)           判断两个集合是否相等
 * <p>
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/ListUtils.html
 */
public class ListUtilsDemo extends Demo {

    /**
     * static <E> int	indexOf(List<E> list, Predicate<E> predicate)
     * 返回符合 Predicate 的第一个元素的下标
     */
    @Test
    public void indexOf() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        int index = ListUtils.indexOf(list, e -> e > 3);
        p(index); // 3
    }

    /**
     * static <E> List<E>	lazyList(List<E> list, Factory<? extends E> factory)
     * LazyList
     */
    @Test
    public void lazyList() {
        List<Date> lazyList = ListUtils.lazyList(new ArrayList<>(), Date::new);
        Date date = lazyList.get(3);
        p(date);       // Mon Nov 26 15:57:23 CST 2018
        p(lazyList);   // [null, null, null, Mon Nov 26 15:57:23 CST 2018]
    }

    /**
     * static String	    longestCommonSubsequence(CharSequence a, CharSequence b)
     * static <E> List<E>	longestCommonSubsequence(List<E> a, List<E> b[, Equator<? super E> equator])
     * 返回两 List/String 的 longest common subsequence (LCS)
     */
    @Test
    public void longestCommonSubsequence() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> list3 = Arrays.asList(3, 4, 5, 6, 7);
        p(ListUtils.longestCommonSubsequence(list1, list2));    // [1]
        p(ListUtils.longestCommonSubsequence(list1, list3));    // [3, 4, 5]
        p(ListUtils.longestCommonSubsequence(list2, list3));    // [5]

        String s1 = "abc";
        String s2 = "bce";
        p(ListUtils.longestCommonSubsequence(s1, s2)); // bc
    }

    /**
     * static <T> List<List<T>>	partition(List<T> list, int size)
     * 按指定长度分割 List
     */
    @Test
    public void partition() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        p(ListUtils.partition(list, 2));   // [[1, 2], [3, 4], [5, 6]]
        p(ListUtils.partition(list, 3));   // [[1, 2, 3], [4, 5, 6]]
        p(ListUtils.partition(list, 4));   // [[1, 2, 3, 4], [5, 6]]
        p(ListUtils.partition(list, 5));   // [[1, 2, 3, 4, 5], [6]]
    }

    /**
     * static <E> List<E>	predicatedList(List<E> list, Predicate<E> predicate)
     * 追加限制条件 （满足 Predicate 才能添加进 List）
     */
    @Test
    public void predicatedList() {
        List<Integer> list = ListUtils.predicatedList(new ArrayList<>(), n -> n % 2 == 0);

        list.add(2);
        list.add(3); // IllegalArgumentException: Cannot add Object '3' ...

    }

    /**
     * static <E> List<E>	transformedList(List<E> list, Transformer<? super E,? extends E> transformer)
     * 追加转换 （新添加进 List 的元素，将要经过 Transformer 转换）
     */
    @Test
    public void transformedList() {
        List<Integer> list = ListUtils.transformedList(new ArrayList<>(), n -> n * 2);

        list.add(1);
        list.add(2);
        list.add(3);
        p(list); // [2, 4, 6]
    }

    /**
     * static <E> List<E>	select(Collection<? extends E> inputCollection, Predicate<? super E> predicate)
     * 返回一个新 List，过滤掉不符合 Predicate 的元素
     */
    @Test
    public void select() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        p(ListUtils.select(list, e -> e % 2 == 0)); // [2, 4, 6]
    }
}
