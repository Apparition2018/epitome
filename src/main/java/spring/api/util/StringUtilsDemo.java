package spring.api.util;

import cn.hutool.core.util.StrUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * StringUtils
 * <pre>
 * static boolean   hasLength(CharSequence/String str)                          判断是否长度大于1
 * static boolean   hasText(CharSequence/String str)                            判断是否长度大于1且不为空格
 * static boolean   containsWhitespace(CharSequence/String str)                 判断是否包含空格
 * static boolean   matchesCharacter(String str, char singleCharacter)          判断字符串和字符是否匹配
 * static boolean   startsWithIgnoreCase(String str, String prefix)             判断开头是否匹配，大小写不敏感
 * static boolean   endsWithIgnoreCase(String str, String suffix)               判断结尾是否匹配，大小写不敏感
 * static boolean   substringMatch(CharSequence str, int index, CharSequence substring) 判断从指定字符开始是否匹配
 *
 * static int       countOccurrencesOf(String str, String sub)                  计算出现次数
 *
 * static String    trimWhitespace(String str)                                  trim 空格
 * static String    trimAllWhitespace(String str)                               trim 所有空格
 * static String    trimLeadingWhitespace(String str)                           trim 开头空格
 * static String    trimTrailingWhitespace(String str)                          trim 结尾空格
 * static String    trimLeadingCharacter(String str, char leadingCharacter)     trim 开头指定字符
 * static String    trimTrailingCharacter(String str, char trailingCharacter)   trim 结尾指定字符
 * static String    replace(String inString, String oldPattern, String newPattern)  替换
 * static String    quote(String str)                                           给字符串前后添加''
 * static Object    quoteIfString(Object obj)                                   如果对象是字符串在前后添加''
 * static String    capitalize(String str)                                      首字母大写
 * static String    uncapitalize(String str)                                    首字母小写
 *
 * static Locale    parseLocale(String localeValue)                             解析为 Locale
 * static Locale    parseLocaleString(String localeString)                      解析为 Locale
 *
 * static String[]  toStringArray(Enumeration/Collection<String> e)             转换成数组
 * static String[]  addStringToArray(String[] array, String str)                数据添加元素
 * static String[]  concatenateStringArrays(String[] array1, String[] array2)   数组合并
 * static String[]  sortStringArray(String[] array)                             数组排序
 * static String[]  trimArrayElements(String[] array)                           数组 trim 元素
 * static String[]  removeDuplicateStrings(String[] array)                      数组去重
 * </pre>
 *
 * @author ljh
 * @since 2020/12/9 14:26
 */
public class StringUtilsDemo extends Demo {

    @Test
    public void testStringUtils() {
        // static String        delete(String inString, String pattern)
        // 删除匹配
        p(StringUtils.delete("Xyz_Yzx_Zxy", "Xyz"));            // _Yzx_Zxy
        // static String        deleteAny(String inString, String charsToDelete)
        // 删除任意字符
        p(StringUtils.deleteAny("Xyz_Yzx_Zxy", "Xyz"));         // _Yx_Zx

        // static String        unqualify(String qualifiedName[, char separator])
        // 返回最后指定分隔符后面的字符串
        p(StringUtils.unqualify("a\\b\\c", File.separatorChar));// c
    }

    @Test
    public void testFileName() {
        p(StringUtils.getFilename("dir/xyz.txt"));              // xyz.txt
        p(StringUtils.getFilenameExtension("dir/xyz.txt"));     // txt
        p(StringUtils.stripFilenameExtension("dir/xyz.txt"));   // dir/xyz
    }

    /** 路径 */
    @Test
    public void testPath() {
        // static String        cleanPath(String path)
        // normalize 路径
        p(StringUtils.cleanPath("C:/a/../b/index.html")); // C:/b/index.html
        // static String        applyRelativePath(String path, String relativePath)
        // 合并路径和相对路径
        p(StringUtils.applyRelativePath("C:/a/../b/index.html", "../../c/index2.html")); // C:/a/../b/../../c/index2.html
        p(StringUtils.cleanPath(StringUtils.applyRelativePath("C:/a/../b/index.html", "../../c/index2.html"))); // C:/../c/index2.html
        // static boolean       pathEquals(String path1, String path2)
        // 判断路径 normalize 后是否相等，会先调用 cleanPath()
        p(StringUtils.pathEquals("C:/a/../b/index.html", "C:/b/index.html")); // true
    }

    /** 分隔符 */
    @Test
    public void testDelimited() {
        /* Object[]         →   delimited String */
        // static String        arrayToDelimitedString(Object[] arr[, String delim])
        String str = StringUtils.arrayToDelimitedString(arr, StrUtil.COMMA);
        p(str); // 1,2,3,4,5,6,7,8,9

        /* Collection<?>    →   delimited String */
        // static String        collectionToDelimitedString(Collection<?> coll[, String delim, String prefix, String suffix])
        p(StringUtils.collectionToDelimitedString(list, StrUtil.COMMA, "[", "]"));  // [1],[2],[3],[4],[5],[6],[7],[8],[9]

        /* delimited String →   Set<String> */
        // static Set<String>   commaDelimitedListToSet(String str)
        p(StringUtils.commaDelimitedListToSet(str));                                // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        /* delimited String →   String[] */
        // static String[]      commaDelimitedListToStringArray(String str)
        p(StringUtils.commaDelimitedListToStringArray(str));                        // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        // static String[]      delimitedListToStringArray(String str, String delimiter, String charsToDelete)
        p(StringUtils.delimitedListToStringArray(str, StrUtil.COMMA, "12345"));     // [, , , , , 6, 7, 8, 9]
        // static String[]      tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
        p(StringUtils.tokenizeToStringArray(str, StrUtil.COMMA, true, true));       // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        String[] arr = new String[]{"1:A", "2:B"};
        /* delimited String →   Properties */
        // static Properties    splitArrayElementsIntoProperties(String[] array, String delimiter[, String charsToDelete])
        p(StringUtils.splitArrayElementsIntoProperties(arr, ":"));                  // {2=B, 1=A}
    }
}
