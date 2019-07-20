package jar.google.guava.collect;

import com.google.common.collect.Sets;
import org.junit.Test;
import utils.Tools;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * SetsDemo
 * <p>
 * http://www.ibloger.net/article/3313.html
 * https://guava.dev/releases/snapshot-jre/api/docs/index.html?com/google/common/collect/Sets.html
 */
public class SetsDemo {


    /**
     * static <E> Sets.SetView<E>	union(Set<? extends E> set1, Set<? extends E> set2)
     * 交集
     * static <E> Sets.SetView<E>	intersection(Set<E> set1, Set<?> set2)
     * 并集
     * static <E> Sets.SetView<E>	difference(Set<E> set1, Set<?> set2)
     * 差集，属于 set1 且不属于 set2
     * static <E> Sets.SetView<E>	symmetricDifference(Set<? extends E> set1, Set<? extends E> set2)
     * 对称差，只属于其中一个集合，而不属于另一个集合的元素组成的集合
     */
    @Test
    public void set() {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3);
        Set<Integer> set2 = Sets.newHashSet(5, 4, 3);

        Tools.p(Sets.union(set1, set2));                 // [1, 2, 3, 4, 5]
        Tools.p(Sets.intersection(set1, set2));          // [3]
        Tools.p(Sets.difference(set1, set2));            // [1, 2]
        Tools.p(Sets.symmetricDifference(set1, set2));   // [1, 2, 4, 5]
    }

    /**
     * static <B> Set<List<B>>	    cartesianProduct(List<? extends Set<? extends B>> sets)
     * static <B> Set<List<B>>	    cartesianProduct(Set<? extends B>... sets)
     * 生成笛卡尔积
     */
    @Test
    public void cartesianProduct() {
        Set<String> set1 = Sets.newHashSet("i love u", "i hate u");
        Set<String> set2 = Sets.newHashSet("tom", "jerry");
        Set<List<String>> set = Sets.cartesianProduct(set1, set2);
        Tools.p(set); // [[i hate u, tom], [i hate u, jerry], [i love u, tom], [i love u, jerry]]
    }

    /**
     * static <E> Set<Set<E>>	powerSet(Set<E> set)
     * 返回集合的所有可能子集的集合
     */
    @Test
    public void powerSet() {
        Set<String> set = Sets.newHashSet("A", "B", "C");
        Set<Set<String>> sets = Sets.powerSet(set);
        Tools.p(sets);              // powerSet({A=0, B=1, C=2})
        Tools.p(sets.toArray());    // [[], [A], [B], [A, B], [C], [A, C], [B, C], [A, B, C]]
    }

    /**
     * static <E> Set<Set<E>>	combinations(Set<E> set, int size)
     * 返回集合的所有指定 size 的子集的集合
     */
    @Test
    public void combinations() {
        Set<String> set = Sets.newHashSet("A", "B", "C");
        Set<Set<String>> sets = Sets.combinations(set, 2);
        Tools.p(sets);              // Sets.combinations([A, B, C], 2)
        Tools.p(sets.toArray());    // [[A, B], [A, C], [B, C]]
    }

    /**
     * static <E> NavigableSet<E>	filter(NavigableSet<E> unfiltered, Predicate<? super E> predicate)
     * static <E> Set<E>	        filter(Set<E> unfiltered, Predicate<? super E> predicate)
     * static <E> SortedSet<E>	    filter(SortedSet<E> unfiltered, Predicate<? super E> predicate)
     * 过滤
     */
    @Test
    public void filter() {
        Set<String> set = Sets.newHashSet("i like u", "i miss u", "i love u");
        Set<String> filterSet = Sets.filter(set, e -> Objects.requireNonNull(e).contains("love"));
        Tools.p(filterSet); // [i love u]
    }


}
