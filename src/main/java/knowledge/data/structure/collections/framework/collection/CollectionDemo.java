package knowledge.data.structure.collections.framework.collection;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * Collection
 * Collection 继承 Iterable
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Collection.html
 * <p>
 * 接口        Set        List
 * 有序         否          是
 * 可重复       否          是
 * 查找       效率低       效率高
 * 插入删除    效率高       效率低（会引起其他元素位置改变）
 * contains   效率高       效率低
 * <p>
 * boolean	            add(E e)                                确保此 collection 包含指定的元素（可选操作）
 * boolean	            addAll(Collection<? extends E> c)       将指定 collection 中的所有元素都添加到此 collection 中（可选操作）
 * boolean	            remove(Object o)                        从此 collection 中移除指定元素的单个实例，如果存在的话（可选操作）
 * boolean              removeIf(Predicate<? super E> filter)   从此 collection 中移除满足条件的所有元素
 * boolean	            contains(Object o)                      如果此 collection 包含指定的元素，则返回 true
 * boolean	            containsAll(Collection<?> c)            如果此 collection 包含指定 collection 中的所有元素，则返回 true
 * boolean	            isEmpty()                               如果此 collection 不包含元素，则返回 true
 * int	                size()                                  返回此 collection 中的元素数
 * Iterator<E>	        iterator()                              返回在此 collection 的元素上进行迭代的迭代器
 * Object[]	            toArray()                               返回包含此 collection 中所有元素的数组 {@link ListDemo#testList()}
 * <p>
 * default Stream<E>	stream()                                返回连续 Stream
 * default Stream<E>	parallelStream()                        返回并行 Stream
 * <p>
 * 阿里编程规约：
 * 1.判断所有集合内部的元素是否为空，使用 isEmpty()方法，而不是 size()==0 的方式
 * 在某些集合中，前者的时间复杂度为 O(1)，而且可读性更好
 * 2.使用 Collection 接口任何实现类的 addAll() 方法时，要对输入的集合参数进行 NPE 判断
 * 3.集合初始化时，指定集合初始值大小
 * initialCapacity = (需要存储的元素个数 / 负载因子) + 1。注意负载因子（即 loaderfactor）默认为 0.75，如果暂时无法确定初始值大小，请设置为 16（即默认值）
 * 4.HashMap 在容量不够进行 resize 时由于高并发可能出现死链，导致 CPU 飙升
 * <p>
 * ************************************************************
 * AbstractCollection
 * <p>
 * 1.实现 Collection，专为继承而设计的类。
 * 2.有抽象方法 iterator(), size()
 * 3.如果想要通过 AbstractCollection 派生出 Collection。需要实现 iterator(), size() 和重写 add(E)，
 * 因为 add(E) 会抛出 UnsupportedOperationException。
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class CollectionDemo extends Demo {

    @Test
    public void testCollection() {
        p(list);    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        p(subList); // [2, 3, 4, 5, 6]

        // clear()                          移除所有元素
        list.subList(5, list.size()).clear();
        p(list);    // [1, 2, 3, 4, 5]

        // toArray(T[] a)                   Collection → Array
        Integer[] arr = new Integer[list.size()];
        arr = list.toArray(arr);
        p(arr);     // [1, 2, 3, 4, 5]
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
