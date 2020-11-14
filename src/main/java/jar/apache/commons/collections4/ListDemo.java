package jar.apache.commons.collections4;

import com.google.common.collect.Lists;
import l.demo.Demo;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.NotNullPredicate;
import org.apache.commons.collections.list.*;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.list.LazyList;
import org.junit.Test;


/**
 * List
 * SynchronizedList     修饰另一个列表，使其可以在多线程环境下同步
 * TypedList            修饰另一个列表，使其只能添加指定类型的实例，修饰后的列表本质上就是使用 InstanceofPredicate 断言的 PredicatedList
 * UnmodifiableList     修饰另一个列表，使列表不允许被修改
 *
 * @author Arsenal
 * created on 2020/11/14 11:55
 */
public class ListDemo extends Demo {

    /**
     * CursorableLinkedList
     * 能够在同一时间修改列表和迭代列表，不是线程同步的
     */
    @Test
    public void testCursorableLinkedList() {
        CursorableLinkedList cursorableLinkedList = new CursorableLinkedList(list);

        CursorableLinkedList.Cursor cursor = cursorableLinkedList.cursor();
        while (cursor.hasNext()) {
            System.out.print(cursor.next() + " ");
            cursor.remove();
        }
    }

    /**
     * FixedSizeList
     * 修饰另一个列表，使其大小固定
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
     * 修饰另一个列表，以使它在 add() 和 set() 时使用的索引大于列表大小时无缝增长，避免大多数 IndexOutOfBoundsException
     */
    @Test
    public void testGrowthList() {
        GrowthList growthList = (GrowthList) GrowthList.decorate(list);
        growthList.set(9, 10);
        p(growthList); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    /**
     * LazyList
     * 修饰另一个列表，使其根据需要在列表中创建对象
     * LazyList 和 GrowthList 修饰的列表都可以自动增长，LazyList 发生在 get()，GrowthList 发生在 set() 和 add()
     */
    @Test
    public void testLazyList() {
        LazyList<Integer> lazyList = LazyList.lazyList(list, () -> 0);
        p(lazyList.get(9)); // 0
    }

    /**
     * PredicatedList
     * 修饰另一个列表，验证所有添加项是否与指定谓词匹配
     */
    @Test
    public void testPredicatedList() {
        // org.apache.commons.collections.functors 提供了一些 xxxPredicate 实现
        PredicatedList predicatedList = (PredicatedList) PredicatedList.decorate(list, NotNullPredicate.INSTANCE);
        predicatedList.add(null); // IllegalArgumentException: Cannot add Object 'null' - Predicate rejected it
    }

    /**
     * SetUniqueList
     * 修饰另一个列表，使其不存在重复项
     */
    @Test
    public void testSetUniqueList() {
        SetUniqueList setUniqueList = SetUniqueList.decorate(Lists.newArrayList(1, 2, 2, 3, 3, 3));
        p(setUniqueList.add(4));// true
        p(setUniqueList.add(4));// false
        p(setUniqueList);       // [1, 2, 3, 4]
    }

    /**
     * TransformedList
     * 修饰另一个列表，在 add() 和 set() 的时候对元素进行转换后在执行相关操作
     */
    @Test
    public void testTransformedList() {
        TransformedList transformedList = (TransformedList) TransformedList.decorate(list, new Transformer() {
            @Override
            public Object transform(Object i) {
                return (Integer) i * 2;
            }
        });
        transformedList.add(10);
        p(transformedList); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 20]
    }

    /**
     * TreeList
     * 一个列表实现，优化的任意索引的快速插入和删除
     * <p>
     * -            get  add  insert  iterate  remove
     * TreeList       3    5       1       2       1
     * ArrayList      1    1      40       1      40
     * LinkedList  5800    1     350       2     325
     */
    @Test
    public void testTreeList() {

    }
}
