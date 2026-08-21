package spring.api.util;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.File;

import static com.ljh.commons.Demo.*;

/**
 * StringUtils
 *
 * @author ljh
 * @since 2020/12/9 14:26
 */
public class StringUtilsDemo {

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
