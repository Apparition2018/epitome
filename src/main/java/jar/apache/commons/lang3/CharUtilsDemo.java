package jar.apache.commons.lang3;

import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

/**
 * CharUtils
 * <p>
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/CharUtils.html
 */
public class CharUtilsDemo {

    /**
     * static int	compare(char x, char y)
     */
    @Test
    public void compare() {
        char c1 = 'a';
        char c2 = 'A';
        System.out.println(CharUtils.compare(c1, c2)); // 32
    }

    @Test
    public void is() {
        char c1 = '1';
        char c2 = 'a';
        char c3 = 'A';
        char c4 = '-';
        char c5 = '\n';
        char c6 = '©';

        System.out.println(CharUtils.isAscii(c1));          // true
        System.out.println(CharUtils.isAscii(c6));          // false

        System.out.println(CharUtils.isAsciiPrintable(c5)); // false
        System.out.println(CharUtils.isAsciiPrintable(c6)); // false

        System.out.println(CharUtils.isAsciiControl(c1));   // false

        System.out.println(CharUtils.isAsciiAlpha(c2));     // true
        
        System.out.println(CharUtils.isAsciiAlphaLower(c2));// true
        System.out.println(CharUtils.isAsciiAlphaUpper(c3));// true

        System.out.println(CharUtils.isAsciiNumeric(c1));   // true

        System.out.println(CharUtils.isAsciiAlphanumeric(c1));// true
        System.out.println(CharUtils.isAsciiAlphanumeric(c2));// true
    }

    /**
     * static char	    toChar(Character/String ch[, char defaultValue])
     * static Character	toCharacterObject(String str)
     */
    @Test
    public void toChar() {
        String s = null;

        System.out.println(CharUtils.toChar(' '));                  //
        System.out.println(CharUtils.toChar('A'));                  // A
        System.out.println(CharUtils.toChar("A"));              // A
//        System.out.println(CharUtils.toChar(s)); // IllegalArgumentException: The String must not be empty
        System.out.println(CharUtils.toChar(s, '?'));   // ?

        System.out.println(CharUtils.toCharacterObject(null));  // null
        System.out.println(CharUtils.toCharacterObject(""));    // null
        System.out.println(CharUtils.toCharacterObject("A"));   // A
        System.out.println(CharUtils.toCharacterObject("AB"));  // A
    }

    /**
     * static int	toIntValue(char/Character ch[, int defaultValue])
     */
    @Test
    public void toIntValue() {
        System.out.println(CharUtils.toIntValue('3'));                  // 3
//        System.out.println(CharUtils.toIntValue('A'));                    // IllegalArgumentException: The character A is not in the range '0' - '9'
        System.out.println(CharUtils.toIntValue('A', -1));  // -1
//        System.out.println(CharUtils.toIntValue(null));                   // IllegalArgumentException: The character must not be null
    }

    /**
     * static String	toString(char/Character ch)
     */
    @Test
    public void toString_() {
        System.out.println(CharUtils.toString('A'));    // A
        System.out.println(CharUtils.toString(null));   // null
    }

    /**
     * static String	unicodeEscaped(char/Character ch)
     */
    @Test
    public void unicodeEscaped() {
        System.out.println(CharUtils.unicodeEscaped(' '));  // \u0020
        System.out.println(CharUtils.unicodeEscaped('A'));  // \u0041
        System.out.println(CharUtils.unicodeEscaped(null)); // null
    }

}
