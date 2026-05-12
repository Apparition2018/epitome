package jar.hutool.util;

import cn.hutool.core.util.ArrayUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <a href="https://doc.hutool.cn/pages/ArrayUtil/">ArrayUtil</a>
 * <p>主要针对 原始类型数组 和 泛型数组 相关方案进行封装
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/ArrayUtil.html">ArrayUtil api</a>
 *
 * @author ljh
 * @since 2020/11/2 23:44
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
        // static <T> T[]       edit(T[] array, Editor<T> editor)       经过 Editor 处理返回
        p(ArrayUtil.edit(arr, i -> {
            if (i % 2 == 1) return i;
            return null;
        })); // [1, 3, 5, 7, 9]

        // static <T> T[]       filter(T[] array, Filter<T> filter)     经过 filter 判断返回
        p(ArrayUtil.filter(arr, i -> i % 2 == 1)); // [1, 3, 5, 7, 9]

        // static <T> void	    edit(T[] array, Editor<T> editor)       编辑数组
        Integer[] cloneArr = arr.clone();
        ArrayUtil.edit(cloneArr, i -> {
            if (i % 2 == 1) return i;
            return null;
        });
        p(cloneArr); // [1, null, 3, null, 5, null, 7, null, 9]
    }

    /**
     * <pre>
     * static XXX[]     wrap(XXX... values)                     将原始类型数组包装为包装类型
     * static XXX[]     unWrap(XXX... values)                   将包装类数组转为原始类型数组
     * Object[]         cast(Class<?> type, Object arrayObj)    强转数组类型，前提是数组元素类型可被强制转换
     * </pre>
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
