package jar.google.guava.collect;

import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sets
 * <p>
 * static Set<E>                    newConcurrentHashSet([Iterable<? extends E> elements])      创建 ConcurrentHashSet
 * static CopyOnWriteArraySet<E>    newCopyOnWriteArraySet(Iterable<? extends E> elements)      创建 CopyOnWriteArraySet
 * static TreeSet<E>                newTreeSet(Iterable<? extends E> elements)                  创建 TreeSet
 * static EnumSet<E>                newEnumSet(Iterable<E> iterable, Class<E> elementType)      创建 EnumSet
 * static Set<E>                    newIdentityHashSet()                                        创建 IdentityHashSet
 * <p>
 * static <E> NavigableSet<E>       unmodifiableNavigableSet(NavigableSet<E> set)
 * static <E> NavigableSet<E>       synchronizedNavigableSet(NavigableSet<E> navigableSet)
 * <p>
 * http://www.ibloger.net/article/3313.html
 * https://github.com/google/guava/wiki/CollectionUtilitiesExplained#sets
 * https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/collect/Sets.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class SetsDemo extends Demo {

    @Test
    public void testSets() {
        // static <E> HashSet<E>        newHashSetWithExpectedSize(int expectedSize)
        // static <E> LinkedHashSet<E>  newLinkedHashSetWithExpectedSize
        // 创建 set，有足够的初始容量来容纳元素，而不需要扩容
        Set<Integer> guavaSet = Sets.newHashSetWithExpectedSize(list.size());

        // static <E> Set<Set<E>>	    powerSet(Set<E> set)
        // 返回集合的所有指定 size 的子集的集合
        p(Sets.powerSet(Sets.newHashSet(1, 2, 3)).toArray());
        // [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]

        // static <E> Set<Set<E>>	    combinations(Set<E> set, int size)
        // 返回集合的所有指定 size 的子集的集合
        p(Sets.combinations(Sets.newHashSet(1, 2, 3), 2).toArray());
        // [[1, 2], [1, 3], [2, 3]]

        // static <B> Set<List<B>>	    cartesianProduct(List<? extends Set<? extends B>> sets)
        // static <B> Set<List<B>>	    cartesianProduct(Set<? extends B>... sets)
        // 笛卡尔积
        p(Sets.cartesianProduct(Sets.newHashSet("i love u", "i hate u"), Sets.newHashSet("tom", "jerry")));
        // [[i hate u, tom], [i hate u, jerry], [i love u, tom], [i love u, jerry]]

        // static <E> Set<E>            filter(Set</SortedSet/NavigableSet<E>, Predicate<? super E>)
        // 经过 Predicate 判断返回
        p(Sets.filter(Sets.newHashSet(arr), i -> Objects.requireNonNull(i) > 2)); // [3, 4, 5, 6, 7, 8, 9]

        TreeSet<Integer> treeSet = Sets.newTreeSet();
        treeSet.addAll(list);
        // static <K extends Comparable<? super K>> NavigableSet<K>     subSet(NavigableSet<K> set, Range<K> range)
        // 返回集合中指定范围内的视图
        p(Sets.subSet(treeSet, Range.open(1, treeSet.size()))); // [2, 3, 4, 5, 6, 7, 8]
    }

    /**
     * static <E> Sets.SetView<E>	union(Set<? extends E> set1, Set<? extends E> set2)                 并集
     * static <E> Sets.SetView<E>	intersection(Set<E> set1, Set<?> set2)                              交集
     * static <E> Sets.SetView<E>	difference(Set<E> set1, Set<?> set2)                                差集，属于 set1 且不属于 set2
     * static <E> Sets.SetView<E>	symmetricDifference(Set<? extends E> set1, Set<? extends E> set2)   对称差，只属于其中一个集合，而不属于另一个集合的元素组成的集合
     */
    @Test
    public void testSets2() {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3);
        Set<Integer> set2 = Sets.newHashSet(5, 4, 3);

        p(Sets.union(set1, set2));                 // [1, 2, 3, 4, 5]
        p(Sets.intersection(set1, set2));          // [3]
        p(Sets.difference(set1, set2));            // [1, 2]
        p(Sets.symmetricDifference(set1, set2));   // [1, 2, 4, 5]
    }

}
