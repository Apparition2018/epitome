package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

/**
 * CharUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/CharUtils.html
 */
public class CharUtilsDemo extends Demo {

    /**
     * static int	compare(char x, char y)
     */
    @Test
    public void compare() {
        char c1 = 'a';
        char c2 = 'A';
        p(CharUtils.compare(c1, c2)); // 32
    }

    @Test
    public void isAscii() {
        p(CharUtils.isAscii('1'));              // true
        p(CharUtils.isAscii('©'));              // false

        // 可见
        p(CharUtils.isAsciiPrintable('1'));     // false
        p(CharUtils.isAsciiPrintable('\n'));    // false

        // 不可见
        p(CharUtils.isAsciiControl('\n'));      // true
        p(CharUtils.isAsciiControl('1'));       // false

        // 字母
        p(CharUtils.isAsciiAlpha('a'));         // true
        p(CharUtils.isAsciiAlpha('1'));         // false

        // 大写字母
        p(CharUtils.isAsciiAlphaLower('a'));    // true
        // 小写字母
        p(CharUtils.isAsciiAlphaUpper('A'));    // false

        // 数字
        p(CharUtils.isAsciiNumeric('1'));       // true
        p(CharUtils.isAsciiNumeric('a'));       // false

        // 字幕数字
        p(CharUtils.isAsciiAlphanumeric('1'));   // true
        p(CharUtils.isAsciiAlphanumeric('a'));   // true
        p(CharUtils.isAsciiAlphanumeric('©'));   // false
    }

    /**
     * static char	    toChar(Character/String ch[, char defaultValue])
     * static Character	toCharacterObject(String str)
     */
    @Test
    public void toChar() {
        String s = null;

        p(CharUtils.toChar(' '));               //
        p(CharUtils.toChar('A'));               // A
        p(CharUtils.toChar("A"));               // A
        // p(CharUtils.toChar(s));              // IllegalArgumentException: The String must not be empty
        p(CharUtils.toChar(s, '?'));            // ?

        p(CharUtils.toCharacterObject(null));   // null
        p(CharUtils.toCharacterObject(""));     // null
        p(CharUtils.toCharacterObject("A"));    // A
        p(CharUtils.toCharacterObject("AB"));   // A
    }

    /**
     * static int	toIntValue(char/Character ch[, int defaultValue])
     */
    @Test
    public void toIntValue() {
        p(CharUtils.toIntValue('3'));       // 3
        // p(CharUtils.toIntValue('A'));    // IllegalArgumentException: The character A is not in the range '0' - '9'
        p(CharUtils.toIntValue('A', -1));   // -1
        // p(CharUtils.toIntValue(null));   // IllegalArgumentException: The character must not be null
    }

    /**
     * static String	toString(char/Character ch)
     */
    @Test
    public void toString_() {
        p(CharUtils.toString('A')); // A
        p(CharUtils.toString(null));// null
    }

    /**
     * static String	unicodeEscaped(char/Character ch)
     */
    @Test
    public void unicodeEscaped() {
        p(CharUtils.unicodeEscaped(' '));   // \u0020
        p(CharUtils.unicodeEscaped('A'));   // \u0041
        p(CharUtils.unicodeEscaped(null));  // null
    }

}
