package spring.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * StringUtils
 *
 * @author ljh
 * created on 2020/12/9 14:26
 */
public class StringUtilsDemo extends Demo {

    @Test
    public void testStringUtils() {
        // static String        arrayToDelimitedString(Object[] arr, String delim)
        String[] strArr = Arrays.stream(arr).map(String::valueOf).toArray(String[]::new);
        p(StringUtils.arrayToDelimitedString(strArr, ", ")); // 1, 2, 3, 4, 5, 6, 7, 8, 9
    }
}
