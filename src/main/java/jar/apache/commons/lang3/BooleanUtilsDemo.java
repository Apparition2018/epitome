package jar.apache.commons.lang3;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/BooleanUtils.html">BooleanUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class BooleanUtilsDemo {

    private final boolean[] b1 = new boolean[]{true, true};
    private final boolean[] b2 = new boolean[]{true, false};
    private final boolean[] b3 = new boolean[]{false, true};
    private final boolean[] b4 = new boolean[]{false, false};

    @Test
    public void boolean_() {

        // and
        p(BooleanUtils.and(b1));        // true
        p(BooleanUtils.and(b2));        // false
        p(BooleanUtils.and(b3));        // false
        p(BooleanUtils.and(b4));        // false
        // or
        p(BooleanUtils.or(b1));         // true
        p(BooleanUtils.or(b2));         // true
        p(BooleanUtils.or(b3));         // true
        p(BooleanUtils.or(b4));         // false
        // xor
        p(BooleanUtils.xor(b1));        // false
        p(BooleanUtils.xor(b2));        // true
        p(BooleanUtils.xor(b3));        // true
        p(BooleanUtils.xor(b4));        // false
        // negate
        p(BooleanUtils.negate(true));   // false
        p(BooleanUtils.negate(false));  // true
        p(BooleanUtils.negate(null));   // null

    }

    @Test
    public void compare() {
        p(BooleanUtils.compare(true, true));    // 0
        p(BooleanUtils.compare(true, false));   // 1
        p(BooleanUtils.compare(false, true));   // -1
        p(BooleanUtils.compare(false, false));  // 0
    }

    @Test
    public void is() {

        p(BooleanUtils.isTrue(true));       // true
        p(BooleanUtils.isNotTrue(true));    // false
        p(BooleanUtils.isFalse(true));      // false
        p(BooleanUtils.isNotFalse(true));   // true
    }

    @Test
    public void toBoolean() {
        // toBoolean(Boolean bool)
        // null will return false
        Boolean b = null;
        p(BooleanUtils.toBoolean(b)); // false

        // toBoolean(int value)
        // toBooleanObject(int value)
        // toBooleanObject(Integer value)
        // 0 will return false
        p(BooleanUtils.toBoolean(0));       // false
        p(BooleanUtils.toBooleanObject(0)); // false

        // toBoolean(String str)
        // 'true', 'on', 'y', 't' or 'yes' (case insensitive) will return true
        p(BooleanUtils.toBoolean("true"));  // true
        p(BooleanUtils.toBoolean("on"));    // true
        p(BooleanUtils.toBoolean("y"));     // true
        p(BooleanUtils.toBoolean("t"));     // true
        p(BooleanUtils.toBoolean("yes"));   // true
        p(BooleanUtils.toBoolean("1"));     // false
        p(BooleanUtils.toBoolean("null"));  // false

        // toBooleanObject(String str)
        // 'true', 'on', 'y', 't' or 'yes' (case insensitive) will return true
        // 'false', 'off', 'n', 'f' or 'no' (case insensitive) will return false
        // Otherwise, null is returned
        p(BooleanUtils.toBooleanObject("y")); // true
        p(BooleanUtils.toBooleanObject("t")); // false
        p(BooleanUtils.toBooleanObject("1")); // null

        // toBoolean(int value, int trueValue, int falseValue)
        // toBoolean(Integer value, Integer trueValue, Integer falseValue)
        // toBoolean(String str, String trueString, String falseString)
        // toBooleanObject(int value, int trueValue, int falseValue, int nullValue)
        // toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue)
        // toBooleanObject(String str, String trueString, String falseString, String nullString)
        p(BooleanUtils.toBoolean(1, 1, 2));         // true; 指定 1 为 true, 2 为 false
        p(BooleanUtils.toBoolean("2", "1", "2"));   // false; 指定 "1" 为 true, "2" 为 false
        p(BooleanUtils.toBooleanObject(3, 1, 2, 3));// null; 指定 1 为 true, 2 为 false, 3 为 null

        // toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull)
        p(BooleanUtils.toBooleanDefaultIfNull(null, true)); // true
    }

    @Test
    public void toInteger() {
        p(BooleanUtils.toInteger(true));            // 1
        p(BooleanUtils.toIntegerObject(null));      // null

        p(BooleanUtils.toInteger(true, 1, 0));      // 1
        p(BooleanUtils.toInteger(null, 1, 0, 2));   // 2
    }

    @Test
    public void toString_() {

        p(BooleanUtils.toString(true, "1", "2"));       // 1
        p(BooleanUtils.toString(null, "1", "2", "3"));  // 3

        p(BooleanUtils.toStringYesNo(true));            // yes
        p(BooleanUtils.toStringOnOff(true));            // on
        p(BooleanUtils.toStringTrueFalse(true));        // true
    }
}
