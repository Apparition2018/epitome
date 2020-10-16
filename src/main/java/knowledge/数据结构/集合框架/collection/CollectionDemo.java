package knowledge.数据结构.集合框架.collection;

import l.demo.Demo;
import org.junit.Test;

/**
 * Collection
 * Collection 继承 Iterable
 * https://jdk6.net/util/Collection.html
 * <p>
 * 接口        Set        List
 * 有序         否          是
 * 可重复       否          是
 * 查找       效率低       效率高
 * 插入删除    效率高       效率低（会引起其他元素位置改变）
 * contains   效率高       效率低
 * <p>
 * boolean	            addAll(Collection<? extends E> c)   将指定 collection 中的所有元素都添加到此 collection 中（可选操作）。
 * boolean	            remove(Object o)                    从此 collection 中移除指定元素的单个实例，如果存在的话（可选操作）
 * boolean	            contains(Object o)                  如果此 collection 包含指定的元素，则返回 true
 * boolean	            isEmpty()                           如果此 collection 不包含元素，则返回 true
 * int	                size()                              返回此 collection 中的元素数
 * Iterator<E>	        iterator()                          返回按适当顺序在列表的元素上进行迭代的迭代器
 * <p>
 * default Stream<E>	stream()                            返回连续 Stream                                          
 * default Stream<E>	parallelStream()                    返回并行 Stream
 */
public class CollectionDemo extends Demo {

    @Test
    public void testCollection() {
        p(list);    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        p(subList); // [2, 3, 4, 5, 6]

        // containsAll(Collection<?> c)     是否包含所有元素
        p(list.containsAll(subList)); // true

        // clear()                          移除所有元素
        list.subList(5, list.size()).clear();
        p(list); // [1, 2, 3, 4, 5]

        // toArray(T[] a)                   Collection → Array
        Integer[] arr = new Integer[list.size()];
        arr = list.toArray(arr);
        p(arr); // [1, 2, 3, 4, 5]
    }

    /**
     * 交集 差集
     */
    @Test
    public void testOperation() {
        p(subList); // [2, 3, 4, 5, 6]
        p(subList2);// [4, 5, 6, 7, 8]

        // 交集
        subList.retainAll(subList2);
        p(subList); // [4, 5, 6] 

        // 差集
        subList2.removeAll(subList);
        p(subList2);// [7, 8]
    }

}
