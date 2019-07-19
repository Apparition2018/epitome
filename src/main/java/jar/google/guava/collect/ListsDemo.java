package jar.google.guava.collect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Lists
 * <p>
 * https://guava.dev/releases/snapshot-jre/api/docs/
 */
public class ListsDemo {

    @Test
    public void _new() {
        List<String> list = Lists.newArrayList("1", "2", "3");
        List<String> list2 = Lists.newArrayList(list);
        System.out.println(list2);
    }

    /**
     * static <E> List<E>	            asList(E first[, E second], E[] rest)
     * 返回一个不可变 List，其中包含指定的第一个和第二个元素，和指定的附加元素数组。
     */
    @Test
    public void asList() {
        String str1 = "0";
        String str2 = "1";
        String[] strs = {"2", " 3", "4"};
        List<String> list = Lists.asList(str1, str2, strs);
        System.out.println(list); // [0, 1, 2,  3, 4]
    }

    /**
     * static <B> List<List<B>>	        cartesianProduct(List<? extends B>... lists)
     * static <B> List<List<B>>	        cartesianProduct(List<? extends List<? extends B>> lists)
     * 返回按顺序从给定列表中选择一个元素可以形成的所有可能列表;列表的“n元笛卡尔积”。
     * <p>
     * Since: 19.0
     */
    @Test
    public void cartesianProduct() {
        Lists.newArrayList("1", "2", "3");
        Lists.newArrayList("one", "tree", "three");
        Lists.newArrayList("壹", "贰", "伞");
    }

    /**
     * static List<Character>	        charactersOf(CharSequence sequence)
     * static ImmutableList<Character>	charactersOf(String string)
     * 将 sequence 或 string 分割为单个字符
     */
    @Test
    public void charactersOf() {
        ImmutableList<Character> characters = Lists.charactersOf("I Love You");
        System.out.println(characters); // [I,  , L, o, v, e,  , Y, o, u]
    }

    /**
     * static <T> List<List<T>>         partition(List<T> list, int size)
     * 根据 size 对 list 进行切割
     */
    @Test
    public void partition() {
        List<String> list = Lists.newArrayList("1", "2", "3", "4", "5");
        List<List<String>> lists = Lists.partition(list, 2);
        System.out.println(lists); // [[1, 2], [3, 4], [5]]
    }

    /**
     * static <T> List<T>	            reverse(List<T> list)
     * 返回指定列表的反向视图
     */
    @Test
    public void reverse() {
        List<String> list = Lists.newArrayList("1", "2", "3");
        System.out.println(Lists.reverse(list)); // [3, 2, 1]
    }

    /**
     * static <F,T> List<T>	            transform(List<F> fromList, Function<? super F,? extends T> function)
     * 根据 function 对 fromList 进行相应处理，并返回处理后的 list
     */
    @Test
    public void transform() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        List<String> newList = Lists.transform(list, String::toUpperCase);
        System.out.println(newList); //         System.out.println(newList);

    }

}
