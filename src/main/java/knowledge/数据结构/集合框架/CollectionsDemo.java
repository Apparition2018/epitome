package knowledge.数据结构.集合框架;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collections
 * <p>
 * static <T> List<T>	emptyList()
 * 返回空的列表（不可变的）
 * static <K,V> Map<K,V>	emptyMap()
 * 返回空的映射（不可变的）
 * static <T> Set<T>	emptySet()
 * 返回空的 set（不可变的）
 * <p>
 * static <T extends Object & Comparable<? super T>> T	max(Collection<? extends T> coll)
 * 根据元素的自然顺序，返回给定 collection 的最大元素
 * static <T extends Object & Comparable<? super T>> T	min(Collection<? extends T> coll)
 * 根据元素的自然顺序 返回给定 collection 的最小元素
 * static void	reverse(List<?> list)                               反转指定列表中元素的顺序
 */
public class CollectionsDemo {

    /**
     * static <T> int	        binarySearch(List<? extends Comparable<? super T>> list, T key)
     * 使用二分搜索法搜索指定列表，以获得指定对象
     * <p>
     * static <T> int	        binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
     * 使用二分搜索法搜索指定列表，以获得指定对象
     * <p>
     * 试用期前必须必须先进行排序
     */
    @Test
    public void binarySearch() {
        List<String> list = Arrays.asList("X", "M", "D", "A", "O");
        Collections.sort(list);
        p(list);   // [A, D, M, O, X]

        int index = Collections.binarySearch(list, "M");
        p(index);  // 2
    }

    /**
     * static int	        indexOfSubList(List<?> source, List<?> target)
     * 返回指定源列表中第一次出现指定目标列表的起始位置；如果没有出现这样的列表，则返回 -1
     * <p>
     * static int	        lastIndexOfSubList(List<?> source, List<?> target)
     * 返回指定源列表中最后一次出现指定目标列表的起始位置；如果没有出现这样的列表，则返回 -1
     */
    @Test
    public void indexOfSubList() {
        List<String> list1 = Arrays.asList("0 1 2 3 4 5 6 7 8 9".split(" "));
        List<String> list2 = Arrays.asList("1 2 3".split(" "));

        p(Collections.indexOfSubList(list1, list2)); // 1
    }

    /**
     * static <T> List<T>	nCopies(int n, T o)
     * 返回由指定对象的 n 个副本组成的不可变列表
     */
    @Test
    public void nCopies() {
        List<Integer> initData = Collections.nCopies(100, 0);
        p(initData.size()); // 100
    }

    /**
     * static <T> boolean	replaceAll(List<T> list, T oldVal, T newVal)
     * 使用另一个值替换列表中出现的所有某一指定值
     */
    @Test
    public void replaceAll() {
        List<String> list = Arrays.asList("0 1 2 3 4 5 6 7 8 9".split(" "));
        p("List :" + list);

        Collections.replaceAll(list, "0", "-1");
        p("replaceAll: " + list);
    }

    /**
     * static void	        reverse(List<?> list)
     * 反转指定列表中元素的顺序
     */
    @Test
    public void reverse() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        p(list); // [1, 2, 3]

        Collections.reverse(list);
        p(list); // [3, 2, 1]
    }

    /**
     * static void	        rotate(List<?> list, int distance)
     * 根据指定的距离轮换指定列表中的元素
     */
    @Test
    public void rotate() {
        List list = Arrays.asList("one Two three Four five six".split(" "));
        p(list); // [one, Two, three, Four, five, six]

        Collections.rotate(list, 3);
        p(list); // [Four, five, six, one, Two, three]
    }

    /**
     * static void	        shuffle(List<?> list[, Random rnd])
     * 使用指定的随机源对指定列表进行置换
     */
    @Test
    public void shuffle() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        Collections.shuffle(list);
        p(list); // [7, 4, 8, 5, 0, 9, 2, 6, 1, 3]
    }

    /**
     * static <T> List<T>	singletonList(T o)
     * 返回一个只包含指定对象的不可变列表
     */
    @Test
    public void singletonList() {
        p(Collections.singletonList(1));
    }

    /**
     * static <T extends Comparable<? super T>> void	sort(List<T> list)
     * 根据元素的自然顺序 对指定列表按升序进行排序
     * <p>
     * static <T> void	    sort(List<T> list, Comparator<? super T> c)
     * 根据指定比较器产生的顺序对指定列表进行排序
     */
    @Test
    public void sort() {
        List<String> list = Arrays.asList("X", "M", "D", "A", "O");
        Collections.sort(list);
        p(list);
    }

    /**
     * default Stream<E>	stream()
     * Returns a sequential Stream with this collection as its source
     * <p>
     * default Stream<E>	parallelStream()
     * Returns a possibly parallel Stream with this collection as its source
     */
    @Test
    public void stream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        p(filtered); // [abc, bc, efg, abcd, jkl]
    }

    /**
     * static void	        swap(List<?> list, int i, int j)
     * 在指定列表的指定位置处交换元素
     */
    @Test
    public void swap() {
        List<String> list = Arrays.asList("A", "B", "C", "D");

        Collections.swap(list, 0, list.size() - 1);
        p(list); // [D, B, C, A]
    }

    /**
     * static <T> XXX<T>	synchronizedXXX(XXX<T> c)
     * 返回指定 XXX 支持的同步（线程安全的）XXX
     */
    @Test
    public void synchronizedXXX() {
        List<String> list = Arrays.asList("A", "B", "C");
        list = Collections.synchronizedList(list);
        p(list);
    }

    /**
     * static <T> XXX<T>	unmodifiableXXX(XXX<? extends T> xxx)
     * 返回指定列表的不可修改视图
     */
    @Test
    public void unmodifiableXXX() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        list = new ArrayList<>(list);
        list = Collections.unmodifiableList(list);
        list.set(0, -1); // UnsupportedOperationException
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}