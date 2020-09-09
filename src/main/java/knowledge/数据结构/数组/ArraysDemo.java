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
     * static <T>List<T> asList(T... a)
     * 返回一个受指定数组支持的固定大小的列表
     * <p>
     * 将数组作为线性表使用
     * 1.与数组共享存储空间
     * 2.不是完整功能的List，不能改变长度
     */
    @Test
    public void asList() {
        String[] arr = {"A", "B", "C", "D"};
        List<String> list = Arrays.asList(arr);
        p(list);    // [A, B, C, D]

        // 修改数组，影响list
        arr[0] = "Z";
        p(list);    // [Z, B, C, D]
        p(arr);     // [Z, B, C, D]

        // 修改list，影响数组
        list.set(1, "E");
        p(list);    // [Z, E, C, D]
        p(arr);     // [Z, E, C, D]

        // list长度不能变
        // list.add("F"); // UnsupportedOperationException

        // 不能把基本数据类型转化为列表
        int[] intArr = {1, 2, 3, 4, 5};
        // List<Integer> intList = Arrays.asList(intArr);   // 编译不通过，asList接受的参数是一个泛型的变长参数，而基本数据类型是无法泛型化的
        List<Integer> intList = Ints.asList(intArr);        // guava类库的工具方法
        p(intList); // [1, 2, 3, 4, 5]
    }

    /**
     * static int	    binarySearch(XXX[] xxx[, int fromIndex, int toIndex], XXX key)
     * 使用二分搜索法来搜索指定的 XXX 型数组，以获得指定的值
     * <p>
     * 必须在进行此调用之前对范围进行排序
     */
    @Test
    public void binarySearch() {
        int[] arr = {2, 5, -2, 6, -3, 8, 0, -7, -9, 4};

        Arrays.sort(arr);
        p(arr);

        int index = Arrays.binarySearch(arr, 0);
        p("元素 0 在第 " + index + " 个位置"); // 元素 0 在第 4 个位置
    }

    /**
     * static XXX[]	    copyOf(XXX[] original, int newLength)
     * 复制指定的数组，截取或用 0/false/null 填充（如有必要），以使副本具有指定的长度
     */
    @Test
    public void copyOf() {
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr2 = Arrays.copyOf(arr1, 5);
        p(arr2); // [1, 2, 3, 4, 5]

    }

    /**
     * static boolean	equals(XXX[] xxx, XXX[] xxx)
     * 如果两个指定的 XXX 型数组彼此相等，则返回 true
     */
    @Test
    public void equals() {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {1, 3, 5, 7, 9};
        int[] arr3 = {9, 7, 5, 3, 1};

        p(Arrays.equals(arr1, arr2)); // true
        p(Arrays.equals(arr1, arr3)); // false
    }

    /**
     * static boolean	deepEquals(Object[] a1, Object[] a2)
     * 如果两个指定数组彼此是深层相等 的，则返回 true
     * <p>
     * 多维数组比较要使用 deepEquals()
     */
    @Test
    public void deepEquals() {
        String[][] name1 = {{"L", "i", "a", "n", "g"}, {"J", "i", "e"}, {"H", "u", "i"}};
        String[][] name2 = {{"L", "i", "a", "n", "g"}, {"J", "i", "e"}, {"H", "u", "i"}};

        p(Arrays.equals(name1, name2));    // false
        p(Arrays.deepEquals(name1, name2));// true
    }

    /**
     * static void	    fill(XXX[] a[, int fromIndex, int toIndex], XXX val)
     * 将指定的 XXX 值分配给指定 XXX 节型数组的每个元素
     */
    @Test
    public void fill() {
        int[] arr = new int[5];
        Arrays.fill(arr, 3);

        p(arr); // [3, 3, 3, 3, 3]
    }

    /**
     * static void	    sort(XXX[] a[, int fromIndex, int toIndex])
     * 对指定的 XXX 型数组按数字升序进行排序
     */
    @Test
    public void sort() {
        binarySearch();
    }
}
