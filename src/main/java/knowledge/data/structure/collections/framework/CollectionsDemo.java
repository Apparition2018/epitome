package knowledge.data.structure.collections.framework;

import l.demo.Animal.Cat;
import l.demo.Animal.Chicken;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collections
 * https://www.runoob.com/manual/jdk1.6/java.base/java/util/Collections.html
 * <p>
 * static void	            copy(List<? super T> dest, List<? extends T> src) 将所有元素从一个列表复制到另一个列表
 * static void	            fill(List<? super T> list, T obj)               使用指定元素替换指定列表中的所有元素
 * static void	            swap(List<?> list, int i, int j)                在指定列表的指定位置处交换元素
 * static void	            shuffle(List<?> list[, Random rnd])             使用指定的随机源对指定列表进行置换
 * static void	            reverse(List<?> list)                           反转指定列表中元素的顺序
 * static <T> void	        sort(List<T> list, Comparator<? super T> c)     根据指定比较器产生的顺序对指定列表进行排序
 * static <T> void	        sort(List<T> list)                              根据元素的自然顺序 对指定列表按升序进行排序
 * <p>
 * static <T> List<T>	    emptyList()                                     返回空的列表（不可变的）
 * static <T> Set<T>	    emptySet()                                      返回空的 set（不可变的）
 * static <K,V> Map<K,V>    emptyMap()                                      返回空的映射（不可变的）
 * static <T> Iterator<T>   emptyIterator()                                 返回空的 Iterator
 * <p>
 * static <T> XXX<T>	    singletonXXX(T o)                               返回一个只包含指定对象的不可变列表（不可变的）
 * static <T> List<T>	    nCopies(int n, T o)                             返回由指定对象的 n 个副本组成的不可变列表
 * <p>
 * static <T> Queue<T>	    asLifoQueue(Deque<T> deque)                     以后进先出 (Lifo) Queue 的形式返回某个 Deque 的视图
 * static Set<E>	        newSetFromMap(Map<E,Boolean> map)               返回指定映射支持的 set
 * static Enumeration<T>	enumeration(Collection<T> c)                    返回一个指定 collection 上的枚举
 * static <T> ArrayList<T>	list(Enumeration<T> e)                          返回一个数组列表，它按返回顺序包含指定枚举返回的元素
 * static <T> XXX<T>	    synchronizedXXX(XXX<T> c)                       返回指定 XXX 支持的同步（线程安全的）XXX
 * static <T> XXX<T>	    unmodifiableXXX(XXX<? extends T> xxx)           返回指定列表的不可修改视图
 * <p>
 * static <T> boolean	    addAll(Collection<? super T> c, T... elements)  将所有指定元素添加到指定 collection 中
 * static <T> boolean	    replaceAll(List<T> list, T oldVal, T newVal)    使用另一个值替换列表中出现的所有某一指定值
 * static boolean	        disjoint(Collection<?> c1, Collection<?> c2)    如果两个指定 collection 中没有相同的元素，则返回 true
 * <p>
 * static int	            frequency(Collection<?> c, Object o)            返回指定 collection 中等于指定对象的元素个数
 * <p>
 * static <T extends Object & Comparable<? super T>> T	max(Collection<? extends T> coll[, Comparator<? super T> comp])   根据元素的自然顺序，返回给定 collection 的最大元素
 * static <T extends Object & Comparable<? super T>> T	min(Collection<? extends T> coll[, Comparator<? super T> comp])   根据元素的自然顺序 返回给定 collection 的最小元素
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class CollectionsDemo extends Demo {

    /**
     * static int	        indexOfSubList(List<?> source, List<?> target)
     * 返回指定源列表中第一次出现指定目标列表的起始位置；如果没有出现这样的列表，则返回 -1
     * static int	        lastIndexOfSubList(List<?> source, List<?> target)
     * 返回指定源列表中最后一次出现指定目标列表的起始位置；如果没有出现这样的列表，则返回 -1
     */
    @Test
    public void indexOfSubList() {
        p(list);    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        p(subList); // [1, 2, 3, 5]
        p(Collections.indexOfSubList(list, subList)); // 1
    }

    /**
     * static <T> int	    binarySearch(List<? extends Comparable<? super T>> list, T key)
     * static <T> int	    binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
     * 使用二分搜索法搜索指定列表，以获得指定对象
     */
    @Test
    public void binarySearch() {
        // 使用前必须必须先进行排序
        Collections.sort(descList);
        p(descList);// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        int index = Collections.binarySearch(descList, 6);
        p(index);   // 6
    }

    /**
     * static void	        rotate(List<?> list, int distance)
     * 根据指定的距离轮换指定列表中的元素
     */
    @Test
    public void rotate() {
        p(list); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        Collections.rotate(list, 3);
        p(list); // [7, 8, 9, 0, 1, 2, 3, 4, 5, 6]
    }

    /**
     * static <T> Comparator<T>	reverseOrder(Comparator<T> cmp)
     * 返回一个比较器，它强行逆转指定比较器的顺序
     */
    @Test
    public void reverseOrder() {
        list.sort(Collections.reverseOrder());
        p(list); // [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
    }

    /**
     * default Stream<E>	stream()            返回以此集合作为源的顺序 Stream
     * default Stream<E>	parallelStream()    返回可能并行的 Stream 与此集合作为其来源
     */
    @Test
    public void stream() {
        p(list);    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, ]
        List<Integer> filtered = list.stream().filter(i -> i > 5).collect(Collectors.toList());
        p(filtered);// [6, 7, 8, 9]
    }

    /**
     * static <E> XXX<E>	checkedXXX(XXX<E> xxx, Class<E> type)
     * 返回指定列表的一个动态类型安全视图
     */
    @Test
    public void checkedXXX() {
        List<Chicken> dogList = new ArrayList<>();
        addCat(dogList); // 这里插入了错误类型，但不会报错

        dogList = Collections.checkedList(dogList, Chicken.class);
        addCat(dogList); // ClassCastException
    }

    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    private void addCat(List list) {
        list.add(new Cat());
    }

}