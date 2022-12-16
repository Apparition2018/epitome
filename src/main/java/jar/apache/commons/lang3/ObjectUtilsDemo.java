package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * ObjectUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ObjectUtils.html
 * <p>
 * static boolean   notEqual(Object object1, Object object2)            是否不相等
 * static boolean   isEmpty(Object object)                              是否为空
 * static boolean   isNotEmpty(Object object)                           是否不为空
 * static boolean	allNotNull(Object... values)                        检查数组中的所有值是否不为空
 * static boolean	anyNotNull(Object... values)                        检查给定数组中的任何值是否不为空
 * static <T> T     defaultIfNull(T object, T defaultValue)             如果为空返回默认值
 * static <T> T     getIfNull(T object, Supplier<T> defaultSupplier)    如果为空返回 Supplier
 * <p>
 * static int	    compare(T c1, T c2[, boolean nullGreater])          比较大小，null-safe
 * static T         max(T... values)                                    最大值，null-safe
 * static T         min(T... values)                                    最小值，null-safe
 * <p>
 * static <T> T	    getFirstNonNull(Supplier<T>... suppliers)           ???
 * static String	identityToString(Object object)                     ???
 * static void	    identityToString(Appendable/StringBuffer/StringBuilder,
 * -                    Object object)                                  ???
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ObjectUtilsDemo extends Demo {

    /**
     * getFirstNonNull
     * identityToString
     */
    @Test
    public void testObjectUtils() {
        // static <T> T     firstNonNull(T... values)           返回给定数组中的第一个不为空的值
        p(ObjectUtils.firstNonNull(arr));   // 1

        // static <T> T	    median([Comparator], T... items)    返回中间值
        p(ObjectUtils.median(arr));         // 5

        // static <T> T	    mode(T... items                     返回出现最多的值
        p(ObjectUtils.mode(1, 2, 2, 3));    // 2
    }

    /**
     * static <T> T	    clone(T obj)                    克隆一个对象，如果对象不可克隆，则返回 null
     * static <T> T	    cloneIfPossible(T obj)          克隆一个对象，如果对象不可克隆，则返回该对象
     */
    @Test
    public void clone_() {
        List<Integer> cloneList = ObjectUtils.clone(list);
        cloneList.add(10);
        p(cloneList);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        p(list);        // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        p(ObjectUtils.clone(1));            //
        p(ObjectUtils.cloneIfPossible(1));  // 1
    }

    /**
     * static byte	    CONST_BYTE(int v)           Supplied value must be a valid byte literal between -128 and 127
     * static short	    CONST_SHORT(int v)          Supplied value must be a valid byte literal between -32768 and 32767
     * static XXX	    CONST(XXX v)
     */
    @Test
    public void CONST() {
        p(ObjectUtils.CONST_BYTE(127));         // v >= -128 && v <= 127
        p(ObjectUtils.CONST_SHORT(32767));      // v >= -32768 && v <= 32767
        p(ObjectUtils.CONST(0));
    }
}
