package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

/**
 * ObjectUtils
 * <p>
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ObjectUtils.html
 */
public class ObjectUtilsDemo extends Demo {

    /**
     * static boolean	allNotNull(Object... values)
     * 检查数组中的所有值是否不为空
     * <p>
     * static boolean	anyNotNull(Object... values)
     * 检查给定数组中的任何值是否不为空
     * <p>
     * static <T> T	    firstNonNull(T... values)
     * 返回给定数组中的第一个不为空的值
     */
    @Test
    public void notNull() {
        // allNotNull
        p(ObjectUtils.allNotNull(1, 2));        // true
        p(ObjectUtils.allNotNull(1, null));     // false

        // anyNotNull
        p(ObjectUtils.anyNotNull(1, 2));        // true
        p(ObjectUtils.anyNotNull(1, null));     // true
        p(ObjectUtils.anyNotNull(null, null));  // false
    }

    /**
     * static <T> T	    clone(T obj)
     * 克隆一个对象，如果对象不可克隆，则返回 null
     * <p>
     * static <T> T	    cloneIfPossible(T obj)
     * 克隆一个对象，如果对象不可克隆，则返回该对象
     */
    @Test
    public void clone_() {
        Integer i = 2;
        Integer i2 = ObjectUtils.clone(i);
        p(i2); // null
        i2 = ObjectUtils.cloneIfPossible(i);
        p(i2); // 2
    }

    /**
     * static <T extends Comparable<? super T>> int	compare(T c1, T c2[, boolean nullGreater])
     * 比较，null-safe；nullGreater 默认为 false，表示 null 是最小的
     */
    @Test
    public void compare() {
        p(ObjectUtils.compare(1, null));                            // 1
        p(ObjectUtils.compare(-1, null));                           // 1
        p(ObjectUtils.compare(1, null, false));  // 1
        p(ObjectUtils.compare(-1, null, false)); // 1
        p(ObjectUtils.compare(1, null, true));   // -1
        p(ObjectUtils.compare(-1, null, true));  // -1
    }

    /**
     * static byte	    CONST_BYTE(int v)
     * static short	    CONST_SHORT(int v)
     * static XXX	    CONST(XXX v)    XXX: boolean, byte, char, double, float, int, long, short, T
     * <p>
     * ???
     */
    @Test
    public void CONST() {
        p(ObjectUtils.CONST_BYTE(127));     // v >= -128 && v <= 127
        p(ObjectUtils.CONST_SHORT(127));    // v >= -32768 && v <= 32767
        p(ObjectUtils.CONST(127));
    }

    /**
     * static <T> T	defaultIfNull(T object, T defaultValue)
     * 如果 object 为 null，则返回 defaultValue
     */
    @Test
    public void defaultIfNull() {
        p(ObjectUtils.defaultIfNull(2, 2)); // 2

        Integer i = null;
        p(ObjectUtils.defaultIfNull(i, 2));        // 2
    }

    /**
     * static <T> T	    firstNonNull(T... values)
     * 返回给定数组中的第一个不为空的值
     */
    @Test
    public void firstNonNull() {
        p(ObjectUtils.firstNonNull(1, null));   // 1
        p(ObjectUtils.firstNonNull(null, 1));   // 1
    }

    /**
     * static <T extends Comparable<? super T>> T	max(T... values)
     * static <T extends Comparable<? super T>> T	min(T... values)
     * null-safe 比较
     */
    @Test
    public void maxmin() {
        p(ObjectUtils.max(1, 5, 9, null, 7, 3));            // 9
        p(ObjectUtils.max("1", "5", "9", null, "7", "3"));  // 9
    }

    /**
     * static <T> T	                                median(Comparator<T> comparator, T... items)
     * static <T extends Comparable<? super T>> T	median(T... items)
     * 返回中间值，如果中间值有两个，则返回较低值
     */
    @Test
    public void median() {
        p(ObjectUtils.median(1, 2, 3, 4)); // 2
    }

    /**
     * static <T> T	    mode(T... items)
     * 返回最常出现的项
     */
    @Test
    public void mode() {
        p(ObjectUtils.mode(1, 2, 2, 3, 3, 3, 4, 4, 5)); // 3
    }

}
