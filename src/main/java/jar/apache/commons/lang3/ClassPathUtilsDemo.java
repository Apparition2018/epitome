package jar.apache.commons.lang3;

import org.apache.commons.lang3.ClassPathUtils;
import org.junit.jupiter.api.Test;

/**
 * ClassPathUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ClassPathUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ClassPathUtilsDemo {

    /**
     * static String	toFullyQualifiedName(Class<?>/Package context, String resourceName)
     * static String	toFullyQualifiedPath(Class<?>/Package context, String resourceName)
     */
    @Test
    public void classPath() {
        System.out.println(ClassPathUtils.toFullyQualifiedName(Integer.class, ""));             // java.lang.
        System.out.println(ClassPathUtils.toFullyQualifiedName(Integer.class.getPackage(), ""));// java.lang.
        System.out.println(ClassPathUtils.toFullyQualifiedPath(Integer.class, ""));             // java/lang/
        System.out.println(ClassPathUtils.toFullyQualifiedPath(Integer.class.getPackage(), ""));// java/lang/
    }

}
