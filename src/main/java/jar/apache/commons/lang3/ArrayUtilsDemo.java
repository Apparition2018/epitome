package jar.apache.commons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ArrayUtils.html">ArrayUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ArrayUtilsDemo {

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
        p(ArrayUtils.indexOf(arr, '0'));        // -1
        p(ArrayUtils.lastIndexOf(arr, '2'));    // 1

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
