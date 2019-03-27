package jar.apache.commons.lang3;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

/**
 * BooleanUtils
 * <p>
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/BooleanUtils.html
 */
public class BooleanUtilsDemo {

    private boolean[] b1 = new boolean[]{true, true};
    private boolean[] b2 = new boolean[]{true, false};
    private boolean[] b3 = new boolean[]{false, true};
    private boolean[] b4 = new boolean[]{false, false};

    @Test
    public void boolean_() {

        // and
        System.out.println(BooleanUtils.and(b1));   // true
        System.out.println(BooleanUtils.and(b2));   // false
        System.out.println(BooleanUtils.and(b3));   // false
        System.out.println(BooleanUtils.and(b4));   // false
        // or
        System.out.println(BooleanUtils.or(b1));    // true
        System.out.println(BooleanUtils.or(b2));    // true
        System.out.println(BooleanUtils.or(b3));    // true
        System.out.println(BooleanUtils.or(b4));    // false
        // xor
        System.out.println(BooleanUtils.xor(b1));   // false
        System.out.println(BooleanUtils.xor(b2));   // true
        System.out.println(BooleanUtils.xor(b3));   // true
        System.out.println(BooleanUtils.xor(b4));   // false
        // negate
        System.out.println(BooleanUtils.negate(true));  // false
        System.out.println(BooleanUtils.negate(false)); // true
        System.out.println(BooleanUtils.negate(null));  // null

    }

    @Test
    public void compare() {
        System.out.println(BooleanUtils.compare(true, true));   // 0
        System.out.println(BooleanUtils.compare(true, false));  // 1
        System.out.println(BooleanUtils.compare(false, true));  // -1
        System.out.println(BooleanUtils.compare(false, false)); // 0
    }

    @Test
    public void is() {

        System.out.println(BooleanUtils.isTrue(true));     // true
        System.out.println(BooleanUtils.isNotTrue(true));  // false
        System.out.println(BooleanUtils.isFalse(true));    // false
        System.out.println(BooleanUtils.isNotFalse(true)); // true
    }

    @Test
    public void toBoolean() {
        // toBoolean(Boolean bool)
        // null will return false
        Boolean b = null;
        System.out.println(BooleanUtils.toBoolean(b)); // false

        // toBoolean(int value)
        // toBooleanObject(int value)
        // toBooleanObject(Integer value)
        // 0 will return false
        System.out.println(BooleanUtils.toBoolean(0));      // false
        System.out.println(BooleanUtils.toBooleanObject(0));// false

        // toBoolean(String str)
        // 'true', 'on', 'y', 't' or 'yes' (case insensitive) will return true
        System.out.println(BooleanUtils.toBoolean("true")); // true
        System.out.println(BooleanUtils.toBoolean("on"));   // true
        System.out.println(BooleanUtils.toBoolean("y"));    // true
        System.out.println(BooleanUtils.toBoolean("t"));    // true
        System.out.println(BooleanUtils.toBoolean("yes"));  // true
        System.out.println(BooleanUtils.toBoolean("1"));    // false
        System.out.println(BooleanUtils.toBoolean("null")); // false

        // toBooleanObject(String str)
        // 'true', 'on', 'y', 't' or 'yes' (case insensitive) will return true
        // 'false', 'off', 'n', 'f' or 'no' (case insensitive) will return false
        // Otherwise, null is returned
        System.out.println(BooleanUtils.toBooleanObject("y"));  // true
        System.out.println(BooleanUtils.toBooleanObject("t"));  // false
        System.out.println(BooleanUtils.toBooleanObject("1"));  // null

        // toBoolean(int value, int trueValue, int falseValue)
        // toBoolean(Integer value, Integer trueValue, Integer falseValue)
        // toBoolean(String str, String trueString, String falseString)
        // toBooleanObject(int value, int trueValue, int falseValue, int nullValue)
        // toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue)
        // toBooleanObject(String str, String trueString, String falseString, String nullString)
        System.out.println(BooleanUtils.toBoolean(1, 1, 2));                      // true; 指定 1 为 true, 2 为 false
        System.out.println(BooleanUtils.toBoolean("2", "1", "2"));                 // false; 指定 "1" 为 true, "2" 为 false
        System.out.println(BooleanUtils.toBooleanObject(3, 1, 2, 3));   // null; 指定 1 为 true, 2 为 false, 3 为 null

        // toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull)
        System.out.println(BooleanUtils.toBooleanDefaultIfNull(null, true)); // true
    }

    @Test
    public void toInteger() {
        System.out.println(BooleanUtils.toInteger(true));       // 1
        System.out.println(BooleanUtils.toIntegerObject(null)); // null

        System.out.println(BooleanUtils.toInteger(true, 1, 0)); // 1
        System.out.println(BooleanUtils.toInteger(null, 1, 0, 2)); // 2
    }

    @Test
    public void toString_() {

        System.out.println(BooleanUtils.toString(true, "1", "2")); // 1
        System.out.println(BooleanUtils.toString(null, "1", "2", "3")); // 3

        System.out.println(BooleanUtils.toStringYesNo(true));       // yes
        System.out.println(BooleanUtils.toStringOnOff(true));       // on
        System.out.println(BooleanUtils.toStringTrueFalse(true));   // true
    }


}
