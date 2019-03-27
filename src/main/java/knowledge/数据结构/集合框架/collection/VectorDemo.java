package knowledge.数据结构.集合框架.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Vector 向量
 * <p>
 * Vector 是同步的
 * Vector 主要用在事先不知道数组的大小，或者只是需要一个可以改变大小的数组的情况
 */
public class VectorDemo {

    /**
     * Vector()
     * 构造一个空向量，使其内部数据数组的大小为 10，其标准容量增量为零
     * <p>
     * Vector(Collection<? extends E> c)
     * 构造一个包含指定 collection 中的元素的向量，这些元素按其 collection 的迭代器返回元素的顺序排列
     * <p>
     * Vector(int initialCapacity[, int capacityIncrement])
     * 使用指定的初始容量和容量增量构造一个空的向量
     */
    @Test
    public void vector() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Vector<String> v = new Vector<>(list);
        System.out.println(v); // [A, B, C]
    }

    /**
     * void	addElement(E obj)
     * 将指定的组件添加到此向量的末尾，将其大小增加 1
     */
    @Test
    public void addElement() {
        capacity();
    }

    /**
     * int	capacity()
     * 返回此向量的当前容量
     */
    @Test
    public void capacity() {
        Vector<Integer> v = new Vector<>(3, 2);
        System.out.println(v.size());       // 0
        System.out.println(v.capacity());   // 3

        v.addElement(1);
        System.out.println(v.size());       // 1
        System.out.println(v.capacity());   // 3

        v.addElement(2);
        System.out.println(v.size());       // 2
        System.out.println(v.capacity());   // 3

        v.addElement(3);
        System.out.println(v.size());       // 3
        System.out.println(v.capacity());   // 3

        v.addElement(4);
        System.out.println(v.size());       // 4
        System.out.println(v.capacity());   // 5

        System.out.println(v.firstElement());// 1
        System.out.println(v.lastElement());// 4

        Enumeration vEnum = v.elements();
        while (vEnum.hasMoreElements()) {
            System.out.println(vEnum.nextElement()); // 1 2 3 4
        }
    }

    /**
     * Enumeration<E>	elements()
     * 返回此向量的组件的枚举
     */
    @Test
    public void elements() {
        capacity();
    }

    /**
     * E	firstElement()
     * 返回此向量的第一个组件（位于索引 0) 处的项）
     */
    @Test
    public void firstElement() {
        capacity();
    }

    /**
     * E	lastElement()
     * 返回此向量的最后一个组件
     */
    @Test
    public void lastElement() {
        capacity();
    }

    /**
     * int	size()
     * 返回此向量中的组件数
     */
    @Test
    public void size() {
        capacity();
    }


}
