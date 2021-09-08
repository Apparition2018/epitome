package spring.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

/**
 * StringUtils.
 * <p>
 * static Locale        parseLocale(String localeValue)                                 解析为 Locale
 * static Locale        parseLocaleString(String localeString)                          解析为 Locale
 * <p>
 * static String[]      toStringArray(Enumeration/Collection<String> e)                 转换成数组
 * static String[]      addStringToArray(String[] array, String str)                    数据添加元素
 * static String[]      concatenateStringArrays(String[] array1, String[] array2)       数组合并
 * static String[]      sortStringArray(String[] array)                                 数组排序
 * static String[]      trimArrayElements(String[] array)                               数组 trim 元素
 * static String[]      removeDuplicateStrings(String[] array)                          数组去重
 *
 * @author ljh
 * created on 2020/12/9 14:26
 */
public class StringUtilsDemo extends Demo {

    @Test
    public void testStringUtils() {
        String[] arr = new String[]{"1:A", "2:B"};
        // static Properties    splitArrayElementsIntoProperties(String[] array, String delimiter[, String charsToDelete])
        p(StringUtils.splitArrayElementsIntoProperties(arr, ":")); // {2=B, 1=A}
    }

    @Test
    public void testPath() {
        p(StringUtils.cleanPath("d:/java/wolfcode/../other/Some.java"));
    }

    @Test
    public void testDelimited() {
        // static String        arrayToDelimitedString(Object[] arr[, String delim])
        String str = StringUtils.arrayToDelimitedString(arr, ",");
        p(str); // 1,2,3,4,5,6,7,8,9

        // static Set<String>   commaDelimitedListToSet(String str)
        p(StringUtils.commaDelimitedListToSet(str)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // static String[]      commaDelimitedListToStringArray(String str)
        p(StringUtils.commaDelimitedListToStringArray(str));            // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        // static String[]      delimitedListToStringArray(String str, String delimiter, String charsToDelete)
        p(StringUtils.delimitedListToStringArray(str, ",", "12345"));   // [, , , , , 6, 7, 8, 9]
        // static String[]      tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
        p(StringUtils.tokenizeToStringArray(str, ",", true, true));     // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // static String        collectionToDelimitedString(Collection<?> coll[, String delim, String prefix, String suffix])
        p(StringUtils.collectionToDelimitedString(list, ",", "[", "]"));// [1],[2],[3],[4],[5],[6],[7],[8],[9]
    }


}
