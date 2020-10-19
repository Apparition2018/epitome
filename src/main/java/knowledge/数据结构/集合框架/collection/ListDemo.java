package knowledge.数据结构.集合框架.collection;

import l.demo.Demo;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 * List
 * List 继承 Collection
 * https://jdk6.net/util/List.html
 * <p>
 * List             Vector              ArrayList                       LinkedList
 * 线程同步             是                   否                               否
 * 查找               效率高 (实现 RandomAccess)                           效率低
 * 实现               数组              数组                                  链表          
 * 插入删除         效率低             效率低                                 效率高
 * 其它           自动扩容 100%       自动扩容 50%                实现 Deque，提供了更多的方法，包括 offer(), peek(), poll() 等，多与一些线程池一起使用
 * <p>
 * void	            add(int index, E element)   在列表的指定位置插入指定元素（可选操作）
 * E	            get(int index)              返回列表中指定位置的元素
 * E	            remove(int index)           移除列表中指定位置的元素（可选操作）
 * int	            indexOf(Object o)           返回此列表中首次出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1
 * int	            lastIndexOf(Object o)       返回此列表中最后出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1
 * ListIterator<E>	listIterator([int index])   返回列表中元素的列表迭代器（按适当顺序），从列表的指定位置开始
 * ************************************************************
 * AbstractList
 * 1.实现 List，专为继承而设计的类
 * 2.有抽象方法：get(), size()
 * 3.实现了 List 中除以上两个方法外的所有方法，但 set(int, E) / add(int, E) / remove(int) 会抛出 UnsupportedOperationException。
 * 如果想要通过 AbstractList 派生出来的 List 支持 set(int, E) / add(int, E) / remove(int)，需要重写
 * 4.有内部迭代器 Itr, ListItr
 * 5.有内部类 SubList, RandomAccessSublist
 * 6.get() 效率快于 iterator()
 * ************************************************************
 * AbstractSequentialList
 * 1.继承 AbstractList，LinkedList 的父类，专为继承而设计的类 (只能按次序访问)
 * 2.有抽象方法：listIterator(), size()
 * 3.get() 效率慢于 iterator()
 * 4....
 * ************************************************************
 */
public class ListDemo extends Demo {

    @Test
    public void testList() {
        p(list); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        // boolean      addAll([int index, ]Collection<? extends E> c)
        // 将指定 collection 中的所有元素都插入到列表中的指定位置（可选操作）
        list.addAll(0, Arrays.asList(-1, -1));
        p(list); // [-1, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        // E            set(int index, E element)       
        // 用指定元素替换列表中指定位置的元素（可选操作）
        list.set(1, 0);
        p(list); // [-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        // List<E>      subList(int fromIndex, int toIndex):
        // 返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图；子列表与主列表共享同一个存储空间
        List<Integer> subList = list.subList(2, 7);
        p(subList); // [2, 3, 4, 5, 6]
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
     * ArrayList
     * 作为一个动态数组，它有 capacity 和 size 两个概念。0 <= size <= capacity，可以访问的下标则是 0 <= index < size
     * 原理：引用着一个数组作为实际的存储容器。该数组的 length 就是 capacity
     * capacity 总共可以装多少个元素
     * size     已经装了多少个元素
     * <p>
     * 多线程环境下可以考虑用 Collections.synchronizedList(List l) 返回一个线程安全的 ArrayList 类，
     * 也可以使用 concurrent 并发包下的 CopyOnWriteArrayList。
     */
    @Test
    public void testArrayList() {
        final int N = 10000000;
        StopWatch watch = StopWatch.createStarted();

        //********** 没有调用 ensureCapacity() **********//
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Object());
        }
        p("没有调用 ensureCapacity() 所需时间：" + watch.getTime()); // 没有调用 ensureCapacity() 所需时间：2813

        //********** 调用了 ensureCapacity() **********//
        watch.reset();
        watch.start();
        list = new ArrayList<>();
        // void	    ensureCapacity(int minCapacity)     
        // 如有必要，增加此 ArrayList 实例的容量，以确保它至少能够容纳最小容量参数所指定的元素数。
        list.ensureCapacity(N);
        for (int i = 0; i <= N; i++) {
            list.add(new Object());
        }
        p("调用了 ensureCapacity() 所需时间：" + watch.getTime());  // 调用了 ensureCapacity() 所需时间：766
        watch.stop();


        p(getArrayListCapacity(list)); // 15000000
        // void	    trimToSize()                        
        // 将此 ArrayList 实例的容量调整为列表的当前大小，即把 capacity = size，减少内存消耗
        list.trimToSize();
        p(getArrayListCapacity(list)); // 10000001
    }

    /**
     * 通过反射获取 ArrayList 的 capacity
     */
    @SuppressWarnings("rawtypes")
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
