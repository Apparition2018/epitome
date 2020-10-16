package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

/**
 * CharUtils
 * <p>
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
    public void is() {
        char c1 = '1';
        char c2 = 'a';
        char c3 = 'A';
        char c4 = '-';
        char c5 = '\n';
        char c6 = '©';

        p(CharUtils.isAscii(c1));               // true
        p(CharUtils.isAscii(c6));               // false

        p(CharUtils.isAsciiPrintable(c5));      // false
        p(CharUtils.isAsciiPrintable(c6));      // false

        p(CharUtils.isAsciiControl(c1));        // false

        p(CharUtils.isAsciiAlpha(c2));          // true

        p(CharUtils.isAsciiAlphaLower(c2));     // true
        p(CharUtils.isAsciiAlphaUpper(c3));     // true

        p(CharUtils.isAsciiNumeric(c1));        // true

        p(CharUtils.isAsciiAlphanumeric(c1));   // true
        p(CharUtils.isAsciiAlphanumeric(c2));   // true
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
