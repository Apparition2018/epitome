package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * ArrayUtils
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
        String[] arr = {"1", "2", "3"};
        char[] arr2 = {'1', '2', '3', '4'};

        // 添加元素
        p(ArrayUtils.addAll(arr, "4", "5")); // [1, 2, 3, 4, 5]

        // 复制
        p(ArrayUtils.clone(arr)); // [1, 2, 3]

        // 判断包含
        p(ArrayUtils.contains(arr, "1")); // true

        // 长度，对 null 安全
        p(ArrayUtils.getLength(arr)); // 3

        // 查找位置
        p(ArrayUtils.indexOf(arr, "0")); // -1
        p(ArrayUtils.lastIndexOf(arr, "2")); // 1

        // 判断是否为空
        p(ArrayUtils.isEmpty(arr)); // false
        p(ArrayUtils.isNotEmpty(arr)); // true

        // 判断两数组长度，对 null 安全
        p(ArrayUtils.isSameLength(arr, arr)); // true

        // 判断两数组类型
        p(ArrayUtils.isSameType(arr, arr2)); // false

        // 移除元素
        p(ArrayUtils.remove(arr, 2)); // [1, 2]
        p(ArrayUtils.removeAll(arr, 1, 2)); // [1]
        p(ArrayUtils.removeElement(arr, "2")); // [1, 3]
        p(ArrayUtils.removeElements(arr, "2", "3")); // [1]

        // 反转数组
        ArrayUtils.reverse(arr);
        p(arr); // [3, 2, 1]
        ArrayUtils.reverse(arr);

        // 子数组
        p(ArrayUtils.subarray(arr, 0, 2)); // [1, 2]

        // 如果数组为 null，转换成空数组
        p(ArrayUtils.nullToEmpty(arr)); // [1, 2, 3]

        // 创建数组
        p(ArrayUtils.toArray("1", "2", "3")); // [1, 2, 3]

        // Array → Map
        String[][] arr3 = {{"A", "1"}, {"B", "2"}};
        p(ArrayUtils.toMap(arr3)); // {A=1, B=2}

        // 基本类型数组 → 包装类型数组
        p(ArrayUtils.toObject(new int[]{1, 2, 3, 4, 5})); // [1, 2, 3, 4, 5]

        // 包装类型数组 → 基本类型数组
        p(ArrayUtils.toPrimitive(new Integer[]{1, 2, 3, 4, 5})); // [1, 2, 3, 4, 5]

        // 包装类型数组 → 字符串类型数组
        // p(ArrayUtils.toStringArray(new Integer[]{1, 2, 3, 4, 5}));
    }

}