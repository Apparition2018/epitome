package jar.hutool.util;

import cn.hutool.core.lang.Editor;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * ArrayUtil
 * 主要针对 原始类型数组 和 泛型数组 相关方案进行封装
 * <p>
 * static <T> T[]	    newArray(Class<?> componentType, int newSize)   新建一个空数组
 * static Object[]	    newArray(int newSize)                           新建一个空数组
 * <p>
 * static XXX[]	        addAll(XXX[]... arrays)                         将多个数组合并在一起，忽略 null 的数组
 * static <T> T[]	    insert(T[] buffer, int index, T... newElements) 将新元素插入到到已有数组中的某个位置
 * <p>
 * static int	        indexOf(XXX[] array, Object value)              返回数组中指定元素所在位置，未找到返回 INDEX_NOT_FOUND
 * static int	        indexOfIgnoreCase(CharSequence[], Object)       返回数组中指定元素所在位置，忽略大小写，未找到返回 INDEX_NOT_FOUND
 * static int	        lastIndexOf(XXX[] array, Object value)          返回数组中指定元素所在最后的位置，未找到返回 INDEX_NOT_FOUND
 * static <T> T	        get(Object array, int index)                    获取数组对象中指定 index 的值，支持负数
 * static <T> T[]	    getAny(Object array, int... indexes)            获取数组中指定多个下标元素值，组成新数组
 * static <T> T[]	    remove(T[] array, int index)                    移除数组中对应位置的元素
 * static <T> T[]	    removeEle(T[] array, T element)                 移除数组中指定的元素，只会移除匹配到的第一个元素
 * <p>
 * static T	            max(T[] numberArray, Comparator<T> comparator)  最大值
 * static T	            min(T[] numberArray, Comparator<T> comparator)  最小值
 * <p>
 * static Class<?>	    getComponentType(Class<?>/Object)               获取数组对象的元素类型
 * static Class<?>	    getArrayType(Class<?> componentType)            根据数组元素类型，获取数组的类型
 * <p>
 * static <T> T[]	    swap(T[] array, int index1, int index2)         交换数组中两个位置的值
 * static XXX[]	        reverse(XXX[] array,
 * -                    int startIndexInclusive, int endIndexExclusive) 反转数组，会变更原数组
 * static <T> T[]	    distinct(T[] array)                             去重数组中的元素，去重后生成新的数组，原数组不变
 * static byte[][]	    split(byte[] array, int len)                    拆分 byte 数组为几个等份
 * static <T> T[]	    clone(T[] array)                                克隆数组
 * <p>
 * static boolean	    isArray(Object obj)                             对象是否为数组对象
 * static boolean	    equals(Object array1, Object array2)            判断两个数组是否相等，判断依据包括数组长度和每个元素都相等
 * static boolean	    contains(XXX[] array, T value)                  数组中是否包含元素
 * static boolean	    containsIgnoreCase(CharSequence[], CharSeq)     数组中是否包含元素，忽略大小写
 * static <T> boolean	containsAny(T[] array, T... values)             数组中是否包含指定元素中的任意一个
 * <p>
 * static boolean       isEmpty(XXX array)                              是否为空
 * static boolean       isNotEmpty(XXX array)                           是否不为空
 * static boolean	    hasEmpty(Object... args)                        是否存在 null 或空对象
 * static boolean	    isAllEmpty(Object... args)                      是否存都为 null 或空对象
 * static boolean	    isAllNotEmpty(Object... args)                   是否存都不为 null 或空对象
 * static int	        emptyCount(Object... args)                      计算 null 或空元素对象的个数
 * static <T> T[]	    defaultIfEmpty(T[] array, T[] defaultArray)     如果给定数组为空，返回默认数组
 * static T[]	        removeEmpty(T[] array)                          去除 null 或者 ""
 * static <T> boolean   hasNull(T... array)                             是否包含 null
 * static <T> boolean	isAllNull(T... array)                           是否全为 null
 * static <T> boolean	isAllNotNull(T... array)                        多个字段是否全部不为 null
 * static String[]	    nullToEmpty(String[] array)                     数组元素中的 null 转换为 ""
 * static <T> T[]	    removeNull(T[] array)                           去除 null
 * static <T> T	        firstNonNull(T... array)                        返回数组中第一个非空元素
 * static <T> T	        firstMatch(Matcher<T> matcher, T... array)      返回数组中第一个匹配规则的值
 * static T[]	        removeBlank(T[] array)                          去除 null 或者 "" 或 者空白字符串
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
        // static int[]	        range([int includedStart, ]int excludedEnd[, int step])
        // 生成一个数字列表，自动判定正序反序
        p(ArrayUtil.range(10, 1, 2));                   // [1, 3, 5, 7, 9]

        // static <T> T[]	    append(T[] buffer, T... newElements)
        // 将新元素添加到已有数组中，添加新元素会生成一个新的数组，不影响原数组
        p(ArrayUtil.append(arr, 10));                   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        // static <T> T[]	    setOrAppend(T[] buffer, int index, T value)
        //将元素值设置为数组的某个位置，当给定的index大于数组长度，则追加
        p(ArrayUtil.setOrAppend(arr.clone(), 8, 0));    // [1, 2, 3, 4, 5, 6, 7, 8, 0]
        p(ArrayUtil.setOrAppend(arr.clone(), 9, 0));    // [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]

        // static T[]	        resize(T[] buffer, int newSize)
        // 生成一个新的重新设置大小的数组
        p(ArrayUtil.resize(arr, 5));                    // [1, 2, 3, 4, 5]
        p(ArrayUtil.resize(arr, 15));                   // [1, 2, 3, 4, 5, 6, 7, 8, 9, null, null, null, null, null, null]

        // static Object	    copy(Object src[, int srcPos], Object dest[, int destPos], int length)
        // 包装 System.arraycopy(Object, int, Object, int, int) 数组复制
        p(ArrayUtil.copy(arr, 0, arr.clone(), 3, 3));   // [1, 2, 3, 1, 2, 3, 7, 8, 9]

        // static <T> String	join(T[] array, CharSequence conjunction[, String prefix, String suffix])
        // 以 conjunction 为分隔符将数组转换为字符串
        p(ArrayUtil.join(arr, "-", "[", "]"));          // [1]-[2]-[3]-[4]-[5]-[6]-[7]-[8]-[9]

        // static Object[]	    sub(Object array, int start, int end, int step)
        // 获取子数组
        p(ArrayUtil.sub(arr, 0, 7, 2));                 // [1, 3, 5, 7]

        // static Map<K,V>      zip(K[] keys, V[] values[, boolean isOrder])
        // 给定两个数组，然后两个数组中的元素一一对应，成为一个 Map
        p(ArrayUtil.zip(arr, arr, true));               // {1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9}
    }

    /**
     * 函数式接口相关
     */
    @Test
    public void testFunctionInterface() {
        // static <T> T[]       filter(T[] array, Editor<T> editor)     经过 Editor 处理返回
        p(ArrayUtil.filter(arr, new Editor<Integer>() {
            @Override
            public Integer edit(Integer i) {
                if (i % 2 == 1) return i;
                return null;
            }
        })); // [1, 3, 5, 7, 9]

        // static <T> T[]       filter(T[] array, Filter<T> filter)     经过 filter 判断返回
        p(ArrayUtil.filter(arr, new Filter<Integer>() {
            @Override
            public boolean accept(Integer i) {
                return i % 2 == 1;
            }
        })); // [1, 3, 5, 7, 9]

        // static <T> void	    edit(T[] array, Editor<T> editor)       编辑数组
        Integer[] cloneArr = arr.clone();
        ArrayUtil.edit(cloneArr, i -> {
            if (i % 2 == 1) return i;
            return null;
        });
        p(cloneArr); // [1, null, 3, null, 5, null, 7, null, 9]
    }

    /**
     * static XXX[]	        wrap(XXX... values)                             将原始类型数组包装为包装类型
     * static XXX[]	        unWrap(XXX... values)                           将包装类数组转为原始类型数组
     * Object[]	            cast(Class<?> type, Object arrayObj)            强转数组类型，前提是数组元素类型可被强制转换
     */
    @Test
    public void convert() {
        // static byte[]        toArray(ByteBuffer bytebuffer)          ByteBuffer → byte[]
        ArrayUtil.toArray(ByteBuffer.wrap("abc".getBytes(StandardCharsets.UTF_8)));

        // static <T,R> R[]	    map(T[], targetComponentType, Function) XXX[] → XXX[]
        p(ArrayUtil.map(arr, Long.class, Long::valueOf));

        // static String	    toString(Object obj)                    Array → String
        p(ArrayUtil.toString(arr));
    }

}
