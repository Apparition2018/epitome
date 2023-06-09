package jar.apache.commons.collections4;

import com.google.common.collect.Lists;
import l.demo.Demo;
import org.apache.commons.collections4.list.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/list/package-summary.html">List</a>
 * <pre>
 * UnmodifiableList         修饰另一个 List，使 List 不允许被修改
 * TreeList                 一个 List 实现，优化任意索引的快速插入和删除
 * NodeCachingLinkedList    一个 List 实现，存储内部节点对象的缓存，以减少浪费的对象创建
 *                          适用于 add 和 remove 操作的 long-lived List，不适用于 short-lived List 和只增长的 List
 *                          线程不同步
 *
 * @author ljh
 * @since 2020/11/14 11:55
 */
public class ListDemo extends Demo {

    /**
     * CursorableLinkedList
     * <p>一个 List 实现，能够在同一时间修改 List 和迭代 List；线程不同步
     */
    @Test
    public void testCursorableLinkedList() {
        CursorableLinkedList<Integer> cursorableLinkedList = new CursorableLinkedList<>(list);

        CursorableLinkedList.Cursor<Integer> cursor = cursorableLinkedList.cursor();
        while (cursor.hasNext()) {
            p(cursor.next() + StringUtils.SPACE);
            cursor.remove();
        }
    }

    /**
     * FixedSizeList
     * <pre>
     * 修饰另一个 List，使其大小固定
     * 不支持 add(), remove(), clear(), retain() 操作，支持 set() 操作
     */
    @Test
    public void testFixedSizeList() {
        FixedSizeList<Integer> fixedSizeList = FixedSizeList.fixedSizeList(list);
        fixedSizeList.set(0, 0);
        p(fixedSizeList);
        fixedSizeList.remove(0); // UnsupportedOperationException: List is fixed size
    }

    /**
     * GrowthList
     * <p>修饰另一个 List，以使它在 add() 和 set() 时使用的索引大于 List 大小时无缝增长，避免大多数 IndexOutOfBoundsException
     */
    @Test
    public void testGrowthList() {
        GrowthList<Integer> growthList = GrowthList.growthList(list);
        growthList.set(9, 10);
        p(growthList); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    /**
     * LazyList
     * <pre>
     * 修饰另一个 List，使其根据需要在 List 中创建对象
     * LazyList 和 GrowthList 修饰的 List 都可以自动增长，LazyList 发生在 get()，GrowthList 发生在 set() 和 add()
     */
    @Test
    public void testLazyList() {
        LazyList<Integer> lazyList = LazyList.lazyList(list, () -> 0);
        p(lazyList.get(9)); // 0
    }

    /**
     * PredicatedList
     * <p>修饰另一个 List，验证所有添加项是否与指定谓词匹配
     */
    @Test
    public void testPredicatedList() {
        // org.apache.commons.collections4.functors 提供了一些 xxxPredicate 实现
        PredicatedList<Integer> predicatedList = PredicatedList.predicatedList(list, i -> i < 10);
        predicatedList.add(10); // IllegalArgumentException: Cannot add Object '10' - Predicate 'jar.apache.commons.collections4.ListDemo$$Lambda$1/2470654@bbfa04' rejected it
    }

    /**
     * SetUniqueList
     * <p>修饰另一个 List，使其不存在重复项
     */
    @Test
    public void testSetUniqueList() {
        SetUniqueList<Integer> setUniqueList = SetUniqueList.setUniqueList(Lists.newArrayList(1, 2, 2, 3, 3, 3));
        p(setUniqueList.add(4));// true
        p(setUniqueList.add(4));// false
        p(setUniqueList);       // [1, 2, 3, 4]
    }

    /**
     * TransformedList
     * <p>修饰另一个 List，在 add() 和 set() 的时候对元素进行转换
     */
    @Test
    public void testTransformedList() {
        TransformedList<Integer> transformedList = TransformedList.transformedList(list, i -> i * 2);
        transformedList.add(10);
        p(transformedList); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 20]
    }
}
