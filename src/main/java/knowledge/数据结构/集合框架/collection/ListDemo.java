package knowledge.数据结构.集合框架.collection;

import org.junit.Test;

import java.util.*;

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
 * void	clear()                     从列表中移除所有元素（可选操作）
 * int	indexOf(Object o)           返回此列表中首次出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1
 * int	lastIndexOf(Object o)       返回此列表中最后出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1
 * E	remove(int index)           移除列表中指定位置的元素（可选操作）
 * <p>
 * LinkedList API:
 * void	addFirst(E e)               将指定元素插入此列表的开头
 * void	addLast(E e)                将指定元素添加到此列表的结尾
 * E	getFirst()                  返回此列表的第一个元素
 * E	getLast()                   返回此列表的最后一个元素
 * E	removeFirst()               移除并返回此列表的第一个元素
 * E	removeLast()                移除并返回此列表的最后一个元素
 */
public class ListDemo {

    /**
     * 遍历
     */
    @Test
    public void traversal() {
        List<String> list = Arrays.asList("A", "B", "C", "D");

        // 方法一
        for (String str : list) {
            System.out.println(str);
        }

        // 方法二
        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        System.out.println(Arrays.toString(strArray));

        // 方法三
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 方法四
        // java8 新增遍历方法 forEach
        // Iterable 接口下方法
        list.forEach(System.out::println);
        list.forEach(item -> System.out.println("item = " + item));
    }

    /**
     * void	    add(int index, E element)
     * 在列表的指定位置插入指定元素（可选操作）
     */
    @Test
    public void add() {
        // https://blog.csdn.net/Tracycater/article/details/77592472?locationNum=2&fps=1
        List<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));
        list.add(1, "Z");
        System.out.println(list);       // [A, Z, B, C, D]
    }

    /**
     * boolean  addAll([int index, ]Collection<? extends E> c)
     * 将指定 collection 中的所有元素都插入到列表中的指定位置（可选操作）
     */
    @Test
    public void addAll() {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        List<Integer> list2 = Arrays.asList(1, 2, 3);

        List<Object> list3 = new LinkedList<>();
        list3.addAll(list);
        list3.addAll(list2);
        System.out.println(list3); // [A, B, C, D, 1, 2, 3]
    }

    /**
     * E        set(int index, E element):
     * 用指定元素替换列表中指定位置的元素（可选操作）
     */
    @Test
    public void set() {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        System.out.println(list);                   // [A, B, C, D]

        String letter = list.set(2, "E");
        System.out.println("letter = " + letter);   // letter = C

        System.out.println(list);                   // [A, B, E, D]

        list.set(0, list.set(2, list.get(0)));
        System.out.println(list);                   // [E, B, A, D]
    }

    /**
     * List<E>  subList(int fromIndex, int toIndex):
     * 返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图
     * <p>
     * PS:子列表与主列表共享同一个存储空间
     */
    @Test
    public void subList() {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        List<String> sub = list.subList(1, 1 + 2);
        System.out.println(sub); // [B, C]
    }

}

