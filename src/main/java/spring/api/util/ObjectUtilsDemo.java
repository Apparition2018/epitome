package spring.api.util;

import org.springframework.util.ObjectUtils;

/**
 * ObjectUtils
 * <p>
 * static boolean   isArray(Object obj)       判断是否为数组
 *
 * @author ljh
 * @since 2021/6/18 17:07
 */
public class ObjectUtilsDemo {

    public static void main(String[] args) {

        // 判断相等，null 安全
        System.out.println(ObjectUtils.nullSafeEquals("a", "a"));   // true
        System.out.println(ObjectUtils.nullSafeEquals("a", null));  // false
        System.out.println(ObjectUtils.nullSafeEquals(null, "a"));  // false
    }
}
