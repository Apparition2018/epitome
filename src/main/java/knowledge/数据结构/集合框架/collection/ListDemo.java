package knowledge.数据结构.集合框架.collection;

import l.demo.Demo;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * List
 * List 继承 Collection
 * https://jdk6.net/util/List.html
 * <p>
 * List             Vector              ArrayList                       LinkedList
 * 线程同步             是                   否                               否
 * 查找               效率高 (实现 RandomAccess)                           效率低
 * 插入删除         效率低             效率低                                 效率高
 * 其它           自动扩容 100%       自动扩容 50%                实现 Deque，提供了更多的方法，包括 offer(), peek(), poll() 等，多与一些线程池一起使用
 * <p>
 * E	            get(int index)              返回列表中指定位置的元素
 * E	            remove(int index)           移除列表中指定位置的元素（可选操作）
 * ListIterator<E>	listIterator([int index])   返回列表中元素的列表迭代器（按适当顺序），从列表的指定位置开始
 */
public class ListDemo extends Demo {

    @Before
    public void before() {
        p(list); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * 遍历
     */
    @Test
    public void traversal() {
        // 方法一
        for (Integer str : list) {
            p(str);
        }

        // 方法二
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            p(it.next());
        }

        // 方法三
        // java8 新增遍历方法 forEach
        // Iterable 接口下方法
        list.forEach(System.out::println);
    }

    /**
     * void	    add(int index, E element)       在列表的指定位置插入指定元素（可选操作）
     * boolean  addAll([int index, ]Collection<? extends E> c)
     * 将指定 collection 中的所有元素都插入到列表中的指定位置（可选操作）
     */
    @Test
    public void add() {
        sList.addAll(1, Arrays.asList("x", "y", "z"));
        p(sList); // [0, x, y, z, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * E        set(int index, E element)       用指定元素替换列表中指定位置的元素（可选操作）
     */
    @Test
    public void set() {
        list.set(3, list.set(6, list.get(3)));
        p(list); // [0, 1, 2, 6, 4, 5, 3, 7, 8, 9]
    }

    /**
     * List<E>  subList(int fromIndex, int toIndex):
     * 返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图
     * <p>
     * PS：子列表与主列表共享同一个存储空间
     */
    @Test
    public void subList() {
        List<Integer> subList = list.subList(2, 7);
        p(subList); // [2, 3, 4, 5, 6]
    }

    /**
     * int	    indexOf(Object o)           返回此列表中首次出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1
     * int	    lastIndexOf(Object o)       返回此列表中最后出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1
     */
    @Test
    public void indexOf() {
        p(list.indexOf("6"));       // 6
        p(list.lastIndexOf("6"));   // 6
    }

    /**
     * ArrayList 作为一个动态数组，它有 capacity 和 size 两个概念。0 <= size <= capacity，可以访问的下标则是 0 <= index < size
     * 原理：引用着一个数组作为实际的存储容器。该数组的 length 就是 capacity
     * capacity 总共可以装多少个元素
     * size     已经装了多少个元素
     * <p>
     * 多线程环境下可以考虑用 Collections.synchronizedList(List l) 返回一个线程安全的 ArrayList 类，
     * 也可以使用 concurrent 并发包下的 CopyOnWriteArrayList。
     */
    @Test
    public void testArrayList() {
        /* ensureCapacity() */
        final int N = 10000000;

        ArrayList<Object> list = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.add(new Object());
        }
        long endTime = System.currentTimeMillis();
        p("没有调用 ensureCapacity() 所需时间：" + (endTime - startTime)); // 没有调用 ensureCapacity() 所需时间：2813

        list = new ArrayList<>();
        startTime = System.currentTimeMillis();
        // void	    ensureCapacity(int minCapacity)     如有必要，增加此 ArrayList 实例的容量，以确保它至少能够容纳最小容量参数所指定的元素数。
        list.ensureCapacity(N);
        for (int i = 0; i <= N; i++) {
            list.add(new Object());
        }
        endTime = System.currentTimeMillis();
        p("调用了 ensureCapacity() 所需时间：" + (endTime - startTime)); // 没有调用 ensureCapacity() 所需时间：766


        p(getArrayListCapacity(list)); // 15000000
        // void	    trimToSize()                        将此 ArrayList 实例的容量调整为列表的当前大小，即把 capacity = size，减少内存消耗
        list.trimToSize();
        p(getArrayListCapacity(list)); // 10000001
    }

    /**
     * 通过反射获取 ArrayList capacity
     */
    public static int getArrayListCapacity(List<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] objects = (Object[]) field.get(arrayList);
            return objects.length;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

