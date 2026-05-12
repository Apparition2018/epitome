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
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Collections.html">Collections</a>
 * <p>Collections 类返回的对象，如：emptyList() / singletonList() 等都是 immutable list，不可对其进行添加或者删除元素的操作（阿里编程规约）
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class CollectionsDemo extends Demo {

    /**
     * static int	        indexOfSubList(List<?> source, List<?> target)
     * <p>返回指定源列表中第一次出现指定目标列表的起始位置；如果没有出现这样的列表，则返回 -1
     * <p>
     * static int	        lastIndexOfSubList(List<?> source, List<?> target)
     * <p>返回指定源列表中最后一次出现指定目标列表的起始位置；如果没有出现这样的列表，则返回 -1
     */
    @Test
    public void indexOfSubList() {
        p(list);    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        p(subList); // [1, 2, 3, 5]
        p(Collections.indexOfSubList(list, subList)); // 1
    }

    /**
     * static <T> int       binarySearch(List<? extends Comparable<? super T>> list, T key)
     * <p>
     * static <T> int       binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
     * <p>使用二分搜索法搜索指定列表，以获得指定对象
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
     * <p>根据指定的距离轮换指定列表中的元素
     */
    @Test
    public void rotate() {
        p(list); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        Collections.rotate(list, 3);
        p(list); // [7, 8, 9, 0, 1, 2, 3, 4, 5, 6]
    }

    /**
     * static <T> Comparator<T>	reverseOrder(Comparator<T> cmp)
     * <p>返回一个比较器，它强行逆转指定比较器的顺序
     */
    @Test
    public void reverseOrder() {
        list.sort(Collections.reverseOrder());
        p(list); // [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
    }

    /**
     * default Stream<E>	stream()            返回以此集合作为源的顺序 Stream
     * <p>
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
     * <p>返回指定列表的一个动态类型安全视图
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
