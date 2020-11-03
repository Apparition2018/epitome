package jar.hutool;

import cn.hutool.core.util.ArrayUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * ArrayUtil
 * 主要针对 原始类型数组 和 泛型数组 相关方案进行封装
 * <p>
 * static <T> T[]	    newArray(Class<?> componentType, int newSize)   新建一个空数组
 * static Object[]	    newArray(int newSize)                           新建一个空数组
 * <p>
 * static XXX[]	        addAll(XXX[]... arrays)                         将多个数组合并在一起，忽略 null 的数组
 * <p>
 * static Class<?>	    getComponentType(Class<?>/Object)               获取数组对象的元素类型
 * static Class<?>	    getArrayType(Class<?> componentType)            根据数组元素类型，获取数组的类型
 * <p>
 * Object[]	            cast(Class<?> type, Object arrayObj)            强转数组类型，前提是数组元素类型可被强制转换
 * static <T> T[]	    clone(T[] array)                                克隆数组
 * <p>
 * static boolean       isEmpty(XXX array)                              是否为空
 * static boolean       isNotEmpty(XXX array)                           是否不为空
 * static <T> T[]	    defaultIfEmpty(T[] array, T[] defaultArray)     如果给定数组为空，返回默认数组
 * static <T> boolean   hasNull(T... array)                             是否包含 null
 * static <T> boolean	isAllNull(T... array)                           是否全为 null
 * static <T> T	        firstNonNull(T... array)                        返回数组中第一个非空元素
 * static <T> T	        firstMatch(Matcher<T> matcher, T... array)      返回数组中第一个匹配规则的值
 * <p>
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E6%95%B0%E7%BB%84%E5%B7%A5%E5%85%B7-ArrayUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/ArrayUtil.html
 *
 * @author Arsenal
 * created on 2020/11/2 23:44
 */
public class ArrayUtilDemo extends Demo {

    @Test
    public void testArrayUti() {
        // static <T> T[]	    append(T[] buffer, T... newElements)
        // 将新元素添加到已有数组中，添加新元素会生成一个新的数组，不影响原数组
        p(ArrayUtil.append(arr, 10));                   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        // static <T> T[]	    setOrAppend(T[] buffer, int index, T value)
        //将元素值设置为数组的某个位置，当给定的index大于数组长度，则追加
        p(ArrayUtil.setOrAppend(arr.clone(), 8, 0));    // [1, 2, 3, 4, 5, 6, 7, 8, 0]
        p(ArrayUtil.setOrAppend(arr.clone(), 9, 0));    // [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]

        // static <T> T[]	    insert(T[] buffer, int index, T... newElements)
        // 将新元素插入到到已有数组中的某个位置
        p(ArrayUtil.insert(arr, 0, -2, -1, 0));         // [-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        // static T[]	        resize(T[] buffer, int newSize)
        // 生成一个新的重新设置大小的数组
        p(ArrayUtil.resize(arr, 5));                    // [1, 2, 3, 4, 5]
        p(ArrayUtil.resize(arr, 15));                   // [1, 2, 3, 4, 5, 6, 7, 8, 9, null, null, null, null, null, null]

        // static Object	    copy(Object src[, int srcPos], Object dest[, int destPos], int length)
        // 包装 System.arraycopy(Object, int, Object, int, int) 数组复制
        p(ArrayUtil.copy(arr, 0, arr.clone(), 3, 3));   // [1, 2, 3, 1, 2, 3, 7, 8, 9]
    }
}
