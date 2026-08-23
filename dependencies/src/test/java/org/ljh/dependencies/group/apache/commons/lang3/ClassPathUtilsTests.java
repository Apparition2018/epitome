package org.ljh.dependencies.group.apache.commons.lang3;

import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ClassPathUtils.html">ClassPathUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ClassPathUtilsTests {

    @Test
    public void test() {
        // static String	toFullyQualifiedName(Class<?>/Package context, String resourceName)
        System.out.println(ClassPathUtils.toFullyQualifiedName(Integer.class, StringUtils.EMPTY));             // java.lang.
        System.out.println(ClassPathUtils.toFullyQualifiedName(Integer.class.getPackage(), StringUtils.EMPTY));// java.lang.
        // static String	toFullyQualifiedPath(Class<?>/Package context, String resourceName)
        System.out.println(ClassPathUtils.toFullyQualifiedPath(Integer.class, StringUtils.EMPTY));             // java/lang/
        System.out.println(ClassPathUtils.toFullyQualifiedPath(Integer.class.getPackage(), StringUtils.EMPTY));// java/lang/
    }
}
