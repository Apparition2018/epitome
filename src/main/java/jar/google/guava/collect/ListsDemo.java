package jar.google.guava.collect;

import com.google.common.collect.Lists;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Lists
 * <p>
 * static <E> LinkedList<E>             newLinkedList([Iterable<? extends E> elements])             创建 LinkedList
 * static <E> CopyOnWriteArrayList<E>   newCopyOnWriteArrayList([Iterable<? extends E> elements])   创建 CopyOnWriteArrayList
 * static <T> List<T>	                reverse(List<T> list)                                       返回指定列表的反向视图
 * <p>
 * http://www.ibloger.net/article/3312.html
 * https://github.com/google/guava/wiki/CollectionUtilitiesExplained#lists
 * https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/collect/Lists.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ListsDemo extends Demo {

    @Test
    public void testLists() {
        List<Integer> guavaList;

        // static <E> ArrayList<E>	        newArrayListWithExpectedSize(int estimatedSize)
        // 创建 list，有足够的初始容量来容纳元素，而不需要扩容
        guavaList = Lists.newArrayListWithExpectedSize(list.size());

        // static <E> List<E>	            asList(E first[, E second], E[] rest)
        // 返回一个不可变 List，其中包含指定的第一个和第二个元素，和指定的附加元素数组。
        guavaList = Lists.asList(1, 2, new Integer[]{3, 4, 5, 6, 7, 8, 9});

        // static <T> List<List<T>>         partition(List<T> list, int size)
        // 根据 size 对 list 进行切割，可用于分页
        p(Lists.partition(list, 2)); // [[1, 2], [3, 4], [5, 6], [7, 8], [9]]

        // static <B> List<List<B>>         cartesianProduct(List<? extends B>... lists)
        // 笛卡尔积
        p(Lists.cartesianProduct(subList, subList2));
        // [[2, 4], [2, 5], [2, 6], [2, 7], [2, 8], [3, 4], [3, 5], [3, 6], [3, 7], [3, 8], [4, 4], [4, 5], [4, 6], [4, 7], [4, 8], [5, 4], [5, 5], [5, 6], [5, 7], [5, 8], [6, 4], [6, 5], [6, 6], [6, 7], [6, 8]]

        // static List<Character>	        charactersOf(CharSequence sequence)
        // static ImmutableList<Character>	charactersOf(String string)
        // 将 CharSequence 分割为单个字符
        p(Lists.charactersOf(HELLO_WORLD)); // [H, e, l, l, o,  , W, o, r, l, d, !]
    }

}
