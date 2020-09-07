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
 * boolean	        addAll(Collection<? extends E> c)   将指定 collection 中的所有元素都添加到此 collection 中（可选操作）。
 * boolean	        remove(Object o)                    从此 collection 中移除指定元素的单个实例，如果存在的话（可选操作）
 * Iterator<E>	    iterator()                          返回按适当顺序在列表的元素上进行迭代的迭代器
 * boolean	        isEmpty()                           如果此 collection 不包含元素，则返回 true
 */
public class CollectionDemo extends Demo {

    /**
     * void	        clear()                         移除此 collection 中的所有元素（可选操作）
     */
    @Test
    public void clear() {
        p(list); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        list.subList(1, 3).clear();
        p(list); // [0, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * boolean	    contains(Object o)              如果此 collection 包含指定的元素，则返回 true
     * boolean	    containsAll(Collection<?> c)    如果此 collection 包含指定 collection 中的所有元素，则返回 true
     */
    @Test
    public void contains() {
        p(list);                        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        p(list.contains("6"));          // true

        p(subList);                     // [1, 2, 3, 4, 5]
        p(list.containsAll(subList));   // true
    }

    /**
     * boolean	    removeAll(Collection<?> c)      移除此 collection 中那些也包含在指定 collection 中的所有元素（可选操作）
     */
    @Test
    public void removeAll() {
        p(list);    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        p(subList); // [1, 2, 3, 4, 5]
        
        list.removeAll(subList);
        p(list);    // [0, 6, 7, 8, 9]
    }

    /**
     * boolean	    retainAll(Collection<?> c)      仅保留此 collection 中那些也包含在指定 collection 的元素（可选操作）
     */
    @Test
    public void retainAll() {
        p(subList); // [1, 2, 3, 4, 5]
        p(subList2);// [3, 4, 5, 6, 7]
        subList.retainAll(subList2);
        p(subList); // [3, 4, 5]
    }

    /**
     * Object[]	    toArray()                       返回按适当顺序包含列表中的所有元素的数组（从第一个元素到最后一个元素）
     * <T> T[]	    toArray(T[] a)                  返回按适当顺序（从第一个元素到最后一个元素）包含列表中所有元素的数组；返回数组的运行时类型是指定数组的运行时类型
     */
    @Test
    public void toArray() {
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        p(arr); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

}
