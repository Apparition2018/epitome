package knowledge.数据结构.集合框架.collection;

import l.demo.Demo;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * List
 * <p>
 * List         Vector      ArrayList       LinkedList
 * 线程同步        是           否               否
 * 查找          效率高        效率高           效率低
 * 插入删除      效率低        效率低           效率高
 * 其它       自动扩容100%   自动扩容50%    实现了Deque 接口，提供了更多的方法，包括 offer(),peek(),poll() 等，多与一些线程池一起使用
 * <p>
 * List API:
 * int	            indexOf(Object o)           返回此列表中首次出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1
 * int	            lastIndexOf(Object o)       返回此列表中最后出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1
 * ListIterator<E>	listIterator(int index)     返回列表中元素的列表迭代器（按适当顺序），从列表的指定位置开始
 * <p>
 * LinkedList API:
 * void	            addFirst(E e)               将指定元素插入此列表的开头
 * void	            addLast(E e)                将指定元素添加到此列表的结尾
 * E	            getFirst()                  返回此列表的第一个元素
 * E	            getLast()                   返回此列表的最后一个元素
 * E	            removeFirst()               移除并返回此列表的第一个元素
 * E	            removeLast()                移除并返回此列表的最后一个元素
 */
public class ListDemo extends Demo {

    /**
     * 遍历
     */
    @Test
    public void traversal() {
        // 方法一
        for (String str : list) {
            p(str);
        }

        // 方法二
        Iterator<String> it = list.iterator();
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
        p(list); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        list.addAll(1, Arrays.asList("x", "y", "z"));
        p(list); // [0, x, y, z, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * E        set(int index, E element)       用指定元素替换列表中指定位置的元素（可选操作）
     */
    @Test
    public void set() {
        p(list); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        list.set(3, list.set(6, list.get(3)));
        p(list); // [0, 1, 2, 6, 4, 5, 3, 7, 8, 9]
    }

    /**
     * List<E>  subList(int fromIndex, int toIndex):
     * 返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图
     * <p>
     * PS:子列表与主列表共享同一个存储空间
     */
    @Test
    public void subList() {
        p(list);    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        List<String> subList = list.subList(2, 7);
        p(subList); // [2, 3, 4, 5, 6]
    }

}

