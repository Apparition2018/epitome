package knowledge.数据结构.数组;

import com.google.common.primitives.Ints;
import l.demo.Demo;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays
 * <p>
 * 它提供的所有方法都是静态的
 */
public class ArraysDemo extends Demo {

    /**
     * static <T>List<T> asList(T... a)                 返回一个受指定数组支持的固定大小的列表
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

        // list 不能改变长度
        // list.add(10); // UnsupportedOperationException

        // 不能把基本数据类型转化为列表
        int[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // List<Integer> intList = Arrays.asList(intArr);   // 编译不通过，asList 接受的参数是一个泛型的变长参数，而基本数据类型是无法泛型化的
        List<Integer> intList = Ints.asList(intArr);        // guava 类库的工具方法
        p(intList); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    @Test
    public void testArrays() {
        // static void	    sort(XXX[] a[, int fromIndex, int toIndex])
        // 对指定 XXX 型数组的指定范围按数字升序进行排序
        Arrays.sort(arr);
        p(arr); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // static int	    binarySearch(XXX[] xxx[, int fromIndex, int toIndex], XXX key)
        // 使用二分搜索法来搜索指定的 XXX 型数组的范围，以获得指定的值；使用前必须先进行排序
        p(Arrays.binarySearch(arr, 5));     // 4

        // static XXX[]	    copyOf(XXX[] original, int newLength[, Class<? extends T[]> newType])
        // 复制指定的数组，截取或用 0/false/null 填充（如有必要），以使副本具有指定的长度
        p(Arrays.copyOf(arr, 5));           // [1, 2, 3, 4, 5]

        // static XXX[]	    copyOfRange(XXX[] original, int from, int to[, Class<? extends T[]> newType])
        // 将指定数组的指定范围复制到一个新数组
        p(Arrays.copyOfRange(arr, 0, 5));   // [1, 2, 3, 4, 5]

        // static boolean	equals(XXX[] xxx, XXX[] xxx)
        // 如果两个指定的 XXX 型数组彼此相等，则返回 true
        p(Arrays.equals(Arrays.copyOf(arr, 5), Arrays.copyOfRange(arr, 0, 5))); // true

        // static void	    fill(XXX[] a[, int fromIndex, int toIndex], XXX val)
        // 将指定的 XXX 值分配给指定 XXX 节型数组的每个元素
        Arrays.fill(arr, 0);                // [0, 0, 0, 0, 0, 0, 0, 0, 0]
        p(arr);

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

}
