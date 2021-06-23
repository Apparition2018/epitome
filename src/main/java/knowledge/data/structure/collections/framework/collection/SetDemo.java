package knowledge.data.structure.collections.framework.collection;

import l.demo.CompanyEnum;
import org.junit.jupiter.api.Test;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

import static l.demo.Demo.p;

/**
 * Set
 * Set 继承 Collection
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Set.html
 * <p>
 * Set          HashSet                 LinkedHashSet           TreeSet
 * 适用场景     快速访问                记录插入顺序              自动排序
 * ************************************************************
 * ConcurrentSkipListSet
 * ConcurrentSkipListSet 是线程安全的有序的集合，适用于高并发的场景
 *
 * @author ljh
 * created on 2020/9/4 17:14
 */
public class SetDemo {

    /**
     * TreeSet
     * TreeSet → NavigableSet → SortedSet → Set
     * 基于 TreeMap 的 NavigableSet 实现。使用元素的自然顺序对元素进行排序，或者根据创建 set 时提供的 Comparator 进行排序。
     * SortedSet 只在元素加入集合时进行排序，修改元素不重新排序
     * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/TreeSet.html
     * TreeSet 与 Comparator 接口：http://www.cnblogs.com/bravolove/p/5810267.html
     * <p>
     * Comparator<? super E>	comparator()        返回对此 set 中的元素进行排序的比较器；如果此 set 使用其元素的自然顺序，则返回 null
     */
    @Test
    public void testTreeSet() {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("1");
        treeSet.add("2");
        treeSet.add("3");
        treeSet.add("4");
        treeSet.add("5");

        // E	            first()                     返回此 set 中当前第一个（最低）元素
        p(treeSet.first()); // 1
        // E	            last()                      返回此 set 中当前最后一个（最高）元素
        p(treeSet.last());  // 5

        // E	            ceiling(E e)                返回此 set 中大于等于给定元素的最小元素；如果不存在这样的元素，则返回 null
        p(treeSet.ceiling("1.5"));  // 2
        // E	            higher(E e)                 返回此 set 中严格大于给定元素的最小元素；如果不存在这样的元素，则返回 null
        p(treeSet.higher("1.5"));   // 2
        // E	            floor(E e)                  返回此 set 中小于等于给定元素的最大元素；如果不存在这样的元素，则返回 null
        p(treeSet.floor("1.5"));    // 1
        // E	            lower(E e)                  返回此 set 中严格小于给定元素的最大元素；如果不存在这样的元素，则返回 null
        p(treeSet.lower("1.5"));    // 1

        // SortedSet<E>	    headSet(E toElement)        返回此 set 的部分视图，其元素严格小于 toElement
        // NavigableSet<E>	headSet(E toElement, boolean inclusive)     返回此 set 的部分视图，其元素小于（或等于，如果 inclusive 为 true）toElement
        SortedSet<String> headSet = treeSet.headSet("3.8");
        p(headSet); // [1, 2, 3]

        // SortedSet<E>	    tailSet(E fromElement)      返回此 set 的部分视图，其元素大于等于 fromElement
        // NavigableSet<E>	tailSet(E fromElement, boolean inclusive)   返回此 set 的部分视图，其元素大于（或等于，如果 inclusive 为 true）fromElement
        SortedSet<String> tailSet = treeSet.tailSet("3.8");
        p(tailSet); // [4, 5]

        // SortedSet<E>	    subSet(E fromElement, E toElement)          返回此 set 的部分视图，其元素从 fromElement（包括）到 toElement（不包括
        // NavigableSet<E>	subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive)      返回此 set 的部分视图，其元素范围从 fromElement 到 toElement
        SortedSet<String> subSet = treeSet.subSet("0.8", "4.6");
        p(subSet);  // [1, 2, 3, 4]

        // Iterator<E>	    descendingIterator()        返回在此 set 元素上按降序进行迭代的迭代器
        // NavigableSet<E>	descendingSet()             返回此 set 中所包含元素的逆序视图
        NavigableSet<String> descSet = treeSet.descendingSet();
        p(descSet); // [5, 4, 3, 2, 1]

        // E	            pollFirst(E e)              获取并移除第一个（最低）元素；如果此 set 为空，则返回 null
        p(treeSet.pollFirst()); // 1
        p(treeSet);             // [2, 3, 4, 5]

        // E	            pollLast()                  获取并移除最后一个（最高）元素；如果此 set 为空，则返回 null
        p(treeSet.pollLast());  // 5
        p(treeSet);             // [2, 3, 4]

        // TreeSet(Comparator<? super E> comparator)    构造一个新的空 TreeSet，它根据指定比较器进行排序
        TreeSet<Integer> descTreeSet = new TreeSet<>((o1, o2) -> o2 - o1);
        descTreeSet.add(1);
        descTreeSet.add(2);
        descTreeSet.add(3);
        descTreeSet.add(4);
        descTreeSet.add(5);
        p(descTreeSet); // [5, 4, 3, 2, 1]
    }
}
