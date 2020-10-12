package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * ArrayUtils
 * static void	    shift(XXX[] array[, int startIndexInclusive, int endIndexExclusive], int offset)    移动 ???
 * static void	    swap(XXX[] array, int offset1, int offset2[, int len])  交换位置
 * static void	    reverse(XXX[] array)                                    反转
 * static void	    shuffle(XXX[] array[, Random random])                   洗牌
 * static XXX[]	    clone(XXX[] array)                                      复制
 * static <T> T	    get(T[] array, int index, T defaultValue)               获取指定
 * static String    toString(Object array[, String stringIfNull])           打印数组
 * static int	    getLength(Object array)                                 获取数组长度，对 null 安全
 * static boolean   isSameLength(XXX[] array1, XXX[] array2)                判断两数组长度是否相等，对 null 安全
 * static boolean   isSameType(Object array1, Object array2)                判断两数组类型是否相等
 * static boolean   contains(XXX[] array, byte valueToFind)                 判断是否包含
 * static boolean   isEmpty(XXX[] array)                                    判断是否为空
 * static boolean   isNotEmpty(XXX[] array)                                 判断是否不为空
 * static boolean   isSorted(XXX[] array)                                   判断是否已排序
 *
 * <p>
 * https://blog.csdn.net/u010046887/article/details/47398213
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ArrayUtils.html
 */
public class ArrayUtilsDemo extends Demo {

    /**
     * 常量
     */
    @Test
    public void constant() {
        // 各种类型的空数组
        p(ArrayUtils.EMPTY_OBJECT_ARRAY);
        p(ArrayUtils.EMPTY_INT_ARRAY);
        p(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY);
        // ...

        // INDEX_ONT_FOUND
        p(ArrayUtils.INDEX_NOT_FOUND);
    }

    @Test
    public void array() {
        // 创建数组
        String[] arr = ArrayUtils.toArray("1", "2", "3");
        p(arr);                                 // [1, 2, 3]

        // 添加元素
        p(ArrayUtils.addAll(arr, "4", "5"));    // [1, 2, 3, 4, 5]
        p(ArrayUtils.addFirst(arr, "0"));       // [0, 1, 2, 3]

        // 插入元素
        p(ArrayUtils.insert(0, arr, "0"));      // [0, 1, 2, 3]

        // 查找位置
        p(ArrayUtils.indexOf(arr, "0"));        // -1
        p(ArrayUtils.lastIndexOf(arr, "2"));    // 1

        // 移除元素
        p(ArrayUtils.remove(arr, 2));           // [1, 2]
        p(ArrayUtils.removeAll(arr, 1, 2));     // [1]
        p(ArrayUtils.removeElement(arr, "2"));  // [1, 3]
        p(ArrayUtils.removeElements(arr, "2", "3")); // [1]
        p(ArrayUtils.removeAllOccurrences(arr, "2"));// [1, 3]

        // 子数组
        p(ArrayUtils.subarray(arr, 0, 2));      // [1, 2]
        
        // 如果数组为 null，转换成空数组
        String[] arr2 = null;
        p(ArrayUtils.nullToEmpty(arr2));        // []

        // Array → Map
        String[][] arr3 = {{"A", "1"}, {"B", "2"}};
        p(ArrayUtils.toMap(arr3));              // {A=1, B=2}

        // 基本类型数组 → 包装类型数组
        p(ArrayUtils.toObject(new int[]{1, 2, 3, 4, 5}));       // [1, 2, 3, 4, 5]

        // 包装类型数组 → 基本类型数组
        p(ArrayUtils.toPrimitive(new Integer[]{1, 2, 3, 4, 5})); // [1, 2, 3, 4, 5]

        // 包装类型数组 → 字符串类型数组
        // p(ArrayUtils.toStringArray(new Integer[]{1, 2, 3, 4, 5}));
    }

}