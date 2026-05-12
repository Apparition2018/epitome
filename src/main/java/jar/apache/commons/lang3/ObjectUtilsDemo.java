package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ObjectUtils.html">ObjectUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ObjectUtilsDemo extends Demo {

    /**
     * getFirstNonNull
     * <p>
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
     * <p>
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
     * <p>static byte   CONST_BYTE(int v)       Supplied value must be a valid byte literal between -128 and 127
     * <p>static short  CONST_SHORT(int v)      Supplied value must be a valid byte literal between -32768 and 32767
     * <p>static XXX    CONST(XXX v)
     */
    @Test
    public void CONST() {
        p(ObjectUtils.CONST_BYTE(127));         // v >= -128 && v <= 127
        p(ObjectUtils.CONST_SHORT(32767));      // v >= -32768 && v <= 32767
        p(ObjectUtils.CONST(0));
    }
}
