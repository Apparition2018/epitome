package spring.api.util;

import org.springframework.util.ObjectUtils;

/**
 * ObjectUtils
 *
 * @author ljh
 * created on 2021/6/18 17:07
 */
public class ObjectUtilsDemo {

    public static void main(String[] args) {
        
        String x = "a";
        String y = "a";
        String z = null;

        System.out.println(ObjectUtils.nullSafeEquals(x, y)); // true
        System.out.println(ObjectUtils.nullSafeEquals(x, z)); // false
        System.out.println(ObjectUtils.nullSafeEquals(z, x)); // false
    }
}
