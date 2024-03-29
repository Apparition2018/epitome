package knowledge.data.structure.collections.framework.collection;

import l.demo.Demo;
import l.demo.Person;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Set.html">Set</a>
 * <p>Set 继承 Collection
 * <pre>
 * HashSet                  快速访问
 * LinkedHashSet            记录插入顺序
 * TreeSet                  自动排序
 * CopyOnWriteArraySet      线程安全，有序，基于 CopyOnWriteArrayList
 * ConcurrentSkipListSet    线程安全，无序，基于 ConcurrentSkipListMap
 * </pre>
 *
 * @author ljh
 * @since 2020/9/4 17:14
 */
public class SetDemo extends Demo {

    @Test
    public void testOf() {
        // Set<E>       of(E e1...)
        // 返回一个不可修改的 Set，JDK9 引入
        Set<Integer> set = Set.of(1, 2, 3);

        // Set<E>       copyOf(Collection<? extends E> coll)
        // 返回一个不可修改的 Set，JDK10 引入
        set = Set.copyOf(set);
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/TreeSet.html">TreeSet</a>
     * <pre>
     * TreeSet → NavigableSet → SortedSet → Set
     * 基于 TreeMap 的 NavigableSet 实现。使用元素的自然顺序对元素进行排序，或者根据创建 set 时提供的 Comparator 进行排序
     * SortedSet 只在元素加入集合时进行排序，修改元素不重新排序
     * </pre>
     */
    @Test
    public void testTreeSet() {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("3");
        treeSet.add("1");
        treeSet.add("4");
        treeSet.add("2");
        treeSet.add("5");

        // E	            first()                     返回此 set 中当前第一个（最低）元素
        p(treeSet.first());         // 1
        // E	            last()                      返回此 set 中当前最后一个（最高）元素
        p(treeSet.last());          // 5

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
        p(headSet);                 // [1, 2, 3]

        // SortedSet<E>	    tailSet(E fromElement)      返回此 set 的部分视图，其元素大于等于 fromElement
        // NavigableSet<E>	tailSet(E fromElement, boolean inclusive)   返回此 set 的部分视图，其元素大于（或等于，如果 inclusive 为 true）fromElement
        SortedSet<String> tailSet = treeSet.tailSet("3.8");
        p(tailSet);                 // [4, 5]

        // SortedSet<E>	    subSet(E fromElement, E toElement)          返回此 set 的部分视图，其元素从 fromElement（包括）到 toElement（不包括
        // NavigableSet<E>	subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive)      返回此 set 的部分视图，其元素范围从 fromElement 到 toElement
        SortedSet<String> subSet = treeSet.subSet("0.8", "4.6");
        p(subSet);                  // [1, 2, 3, 4]

        // Iterator<E>	    descendingIterator()        返回在此 set 元素上按降序进行迭代的迭代器
        // NavigableSet<E>	descendingSet()             返回此 set 中所包含元素的逆序视图
        NavigableSet<String> descSet = treeSet.descendingSet();
        p(descSet);                 // [5, 4, 3, 2, 1]

        // E	            pollFirst(E e)              获取并移除第一个（最低）元素；如果此 set 为空，则返回 null
        p(treeSet.pollFirst());     // 1
        p(treeSet);                 // [2, 3, 4, 5]

        // E	            pollLast()                  获取并移除最后一个（最高）元素；如果此 set 为空，则返回 null
        p(treeSet.pollLast());      // 5
        p(treeSet);                 // [2, 3, 4]

        // Comparator<? super E>	comparator()        返回对此 set 中的元素进行排序的比较器；如果此 set 使用其元素的自然顺序，则返回 null
        p(treeSet.comparator());    // null

        // TreeSet(Comparator<? super E> comparator)    构造一个新的空 TreeSet，它根据指定比较器进行排序
        TreeSet<Integer> descTreeSet = new TreeSet<>((o1, o2) -> o2 - o1);
        descTreeSet.add(1);
        descTreeSet.add(2);
        descTreeSet.add(3);
        descTreeSet.add(4);
        descTreeSet.add(5);
        p(descTreeSet);             // [5, 4, 3, 2, 1]
    }

    /**
     * TreeSet 通过 Comparator 实现 排序/去重
     *
     * @see <a href="http://www.cnblogs.com/bravolove/p/5810267.html">TreeSet 与 Comparator</a>
     */
    @Test
    public void testComparator() {
        Set<Person> treeSet = new TreeSet<>((p1, p2) -> new CompareToBuilder().append(p1.getName(), p2.getName()).toComparison());
        Collections.shuffle(personList);
        p(personList);
        treeSet.addAll(personList);
        treeSet.addAll(personList);
        p(treeSet);
    }
}
