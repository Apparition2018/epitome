package knowledge.data.structure.array;

import com.google.common.primitives.Ints;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays
 * Arrays 提供的所有方法都是静态的
 * https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
 * <p>
 * static void	        sort(XXX[] a[, int fromIndex, int toIndex])                 对指定 XXX 型数组的指定范围按数字升序进行排序
 * static boolean	    equals(XXX[] xxx, XXX[] xxx)                                如果两个指定的 XXX 型数组彼此相等，则返回 true
 * static void	        fill(XXX[] a[, int fromIndex, int toIndex], XXX val)        将指定的 XXX 值分配给指定 XXX 节型数组的每个元素
 * static XXX	        deepHashCode(Object[] a)                                    基于指定数组的“深层内容”返回哈希码
 * <p>
 * static Stream	    stream(XXX[] arr[, int startInclusive, int endExclusive])   返回 Stream
 * static Spliterator[.OfXXX]	spliterator(XXX[] array)                            返回 Spliterator
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ArraysDemo extends Demo {

    @Test
    public void testArrays() {
        // static int	    binarySearch(XXX[] xxx[, int fromIndex, int toIndex], XXX key)
        // 使用二分搜索法来搜索指定的 XXX 型数组的范围，以获得指定的值；使用前必须先进行排序
        p(Arrays.binarySearch(arr, 5));     // 4

        // static XXX[]	    copyOf(XXX[] original, int newLength[, Class<? extends T[]> newType])
        // 复制指定的数组，截取或用 0/false/null 填充（如有必要），以使副本具有指定的长度
        // 底层调用了 System.arraycopy()
        p(Arrays.copyOf(arr, 5));           // [1, 2, 3, 4, 5]

        // static XXX[]	    copyOfRange(XXX[] original, int from, int to[, Class<? extends T[]> newType])
        // 将指定数组的指定范围复制到一个新数组
        p(Arrays.copyOfRange(arr, 0, 5));   // [1, 2, 3, 4, 5]

        // static boolean	deepEquals(Object[] a1, Object[] a2)
        // 如果两个指定数组彼此是深层相等 的，则返回 true；多维数组比较要使用 deepEquals()
        Integer[][] mArr = {{1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}};
        Integer[][] mArr2 = {{1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}};
        p(Arrays.equals(mArr, mArr2));      // false
        p(Arrays.deepEquals(mArr, mArr2));  // true
        // static String	deepToString(Object[] a)
        // 返回指定数组“深层内容”的字符串表示形式
        p(Arrays.deepToString(mArr2));      // [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]]
    }

    @Test
    public void testParallel() {
        // static void	    parallelSort(XXX[] a[, int fromIndex, int toIndex])
        // 并发将数组的指定范围按升序排序，效率比 sort() 高
        // static <T> void	parallelSort(T[] a[, int fromIndex, int toIndex], Comparator<? super T> cmp)
        // 根据指定比较器并发对指定对象数组的指定范围进行排序
        Arrays.parallelSort(arr);
        p(arr); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // static <T> void	parallelPrefix(XXX[] array[, int fromIndex, int toIndex], XxxOperator<T> op)
        // 根据 XxxOperator 函数(参数：前一个索引的元素和当前元素)并发地 Cumulates 每个元素
        Arrays.parallelPrefix(arr, (left, cur) -> cur - left);
        p(arr); // [1, 1, 2, 2, 3, 3, 4, 4, 5]

        // static void	    setAll(int[] array, XxxOperator generator)
        // 根据 XxxOperator 函数(参数：当前索引) 计算每个元素
        Arrays.setAll(arr, index -> ++index);
        p(arr); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        // static void	    parallelSetAll(int[] array, XxxOperator generator)
        // 根据 XxxOperator 函数(参数：当前索引) 并发计算每个元素
        Arrays.parallelSetAll(arr, index -> ++index);
        p(arr); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * static <T>List<T> asList(T... a)                 返回一个受指定数组支持的固定大小的列表
     * 阿里编程规约：
     * 使用工具类 Arrays.asList() 把数组转换成集合时，不能使用其修改集合相关的方法，它的 add / remove / clear 方法会抛出 UnsupportedOperationException 异常
     * asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。Arrays.asList 体现的是适配器模式，只是转换接口，后台的数据仍是数组
     */
    @Test
    public void asList() {
        p(arr);     // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        List<Integer> list = Arrays.asList(arr);
        p(list);    // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // arr 与 list 共享存储空间；修改 arr 影响 list，修改 list 影响 arr
        arr[0] = 0;
        p(arr);     // [0, 2, 3, 4, 5, 6, 7, 8, 9]
        p(list);    // [0, 2, 3, 4, 5, 6, 7, 8, 9]

        // 阿里编程规约：
        // asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法，它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常
        // Arrays.asList 体现的是适配器模式，只是转换接口，后台的数据仍是数组
        // list.add(10); // UnsupportedOperationException

        // 不能把基本数据类型转化为列表
        int[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // List<Integer> intList = Arrays.asList(intArr);   // 编译不通过，asList 接受的参数是一个泛型的变长参数，而基本数据类型是无法泛型化的
        List<Integer> intList = Ints.asList(intArr);        // guava 类库的工具方法
        p(intList); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

}
