package knowledge.数据结构.集合框架.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection
 * <p>
 * 接口        Set        List
 * 有序         否          是
 * 可重复       否          是
 * 查找       效率低       效率高
 * 插入删除    效率高       效率低（会引起其他元素位置改变）
 * <p>
 * boolean      add(E e)                            向列表的尾部添加指定的元素（可选操作）
 * boolean	    addAll(Collection<? extends E> c)   将指定 collection 中的所有元素都添加到此 collection 中（可选操作）。
 * Iterator<E>	iterator()                          返回按适当顺序在列表的元素上进行迭代的迭代器
 * boolean	    remove(Object o)                    从此 collection 中移除指定元素的单个实例，如果存在的话（可选操作）
 * <p>
 * boolean	    isEmpty()                           如果此 collection 不包含元素，则返回 true
 */
public class CollectionDemo {

    /**
     * void	clear()
     * 移除此 collection 中的所有元素（可选操作）
     */
    @Test
    public void clear() {
        List<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));
        list.subList(1, 3).clear();
        System.out.println(list); // [A, D]
    }

    /**
     * boolean	contains(Object o)
     * 如果此 collection 包含指定的元素，则返回 true
     * <p>
     * boolean	containsAll(Collection<?> c)
     * 如果此 collection 包含指定 collection 中的所有元素，则返回 true
     */
    @Test
    public void contains() {
        Collection<String> list = Arrays.asList("A", "B", "C", "D");
        System.out.println(list.contains("A"));     // true

        Collection<String> list2 = Arrays.asList("A", "B");
        System.out.println(list.containsAll(list2));// true
    }

    /**
     * boolean	removeAll(Collection<?> c)
     * 移除此 collection 中那些也包含在指定 collection 中的所有元素（可选操作）
     */
    @Test
    public void removeAll() {
        Collection<String> list = Arrays.asList("A", "B", "C", "D");
        Collection<String> list2 = Arrays.asList("C", "D", "E", "F");

        list2.removeAll(list);
        System.out.println(list2); // [E, F]
    }

    /**
     * boolean	retainAll(Collection<?> c)
     * 仅保留此 collection 中那些也包含在指定 collection 的元素（可选操作）
     */
    @Test
    public void retainAll() {
        Collection<String> list = Arrays.asList("A", "B", "C", "D");
        Collection<String> list2 = Arrays.asList("C", "D", "E", "F");

        list2.retainAll(list);
        System.out.println(list2); // [C, D]
    }

    /**
     * Object[]	toArray()
     * 返回按适当顺序包含列表中的所有元素的数组（从第一个元素到最后一个元素）
     * <p>
     * <T> T[]	toArray(T[] a)
     * 返回按适当顺序（从第一个元素到最后一个元素）包含列表中所有元素的数组；
     * 返回数组的运行时类型是指定数组的运行时类型
     */
    @Test
    public void toArray() {
        Collection<String> list = Arrays.asList("A", "B", "C", "D");
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        System.out.println(Arrays.toString(arr)); // [A, B, C, D]
    }

}
