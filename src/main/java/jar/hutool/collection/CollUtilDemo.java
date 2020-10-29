package jar.hutool.collection;

import cn.hutool.core.collection.CollUtil;
import l.demo.Demo;
import org.junit.Test;

import java.util.*;

/**
 * CollUtil
 * <p>
 * static <T> Set<T>            emptyIfNull(Set<T> set)                                             如果为空则返回空 Set
 * static <T> List<T>           emptyIfNull(List<T> set)                                            如果为空则返回空 List
 * static <T> Collection<T>     addAll(Collection<T> coll, Object value[, Type elementType])        将多个数据合并成一个 Coll
 * <p>
 * static <T> boolean           contains(Collection<T> coll, Predicate<? super T> containFunc)      包含
 * static boolean               contains(Collection<?> coll, Object value)                          包含
 * static boolean               containsAll(Collection<?> coll1, Collection<?> coll2)               包含所有
 * static boolean               containsAny(Collection<?> coll1, Collection<?> coll2)               包含任意
 * <p>
 * https://hutool.cn/docs/#/core/%E9%9B%86%E5%90%88%E7%B1%BB/%E9%9B%86%E5%90%88%E5%B7%A5%E5%85%B7-CollUtil?id=%e9%9b%86%e5%90%88%e5%b7%a5%e5%85%b7-collutil
 *
 * @author Arsenal
 * created on 2020/10/27 20:13
 */
public class CollUtilDemo extends Demo {
    
    @Test
    public void testCollUtil() {
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
        // subtract()
        p(CollUtil.subtract(subList, subList2));                        // [2, 3]
        p(CollUtil.subtractToList(subList, subList2));                  // [2, 3]
        // intersection()
        p(CollUtil.intersection(subList, subList2));                    // [4, 5, 6]
        p(CollUtil.intersectionDistinct(subList, subList2, descList));  // [4, 5, 6]
        // disjunction()
        p(CollUtil.disjunction(subList, subList2));                     // [2, 3, 7, 8]
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
     * 对 Entry<K, V> 按照 Value 的值做排序
     */
    @Test
    public void sortEntrySetToList() {
        Map<Integer, String> map = new HashMap<Integer, String>(3) {
            {
                put(3, "C");
                put(2, "B");
                put(1, "A");
            }
        };
        p(CollUtil.sortEntryToList(map.entrySet())); // [1=A, 2=B, 3=C]
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

    /**
     * 给定两个集合，然后两个集合中的元素一一对应，成为一个 Map
     */
    @Test
    public void zip() {
        Collection<Integer> keys = CollUtil.newArrayList(1, 2, 3, 4, 5);
        Collection<String> values = CollUtil.newArrayList("A", "B", "C", "D", "E");
        Map<Integer, String> map = CollUtil.zip(keys, values);
        p(map); // {1=A, 2=B, 3=C, 4=D, 5=E}
    }
}
