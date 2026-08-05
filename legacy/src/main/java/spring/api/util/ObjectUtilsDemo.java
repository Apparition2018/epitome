package spring.api.util;

import org.springframework.util.ObjectUtils;

import static l.demo.Demo.p;

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
        p(ObjectUtils.nullSafeEquals("a", "a"));    // true
        p(ObjectUtils.nullSafeEquals("a", null));   // false
        p(ObjectUtils.nullSafeEquals(null, "a"));   // false
    }
}
