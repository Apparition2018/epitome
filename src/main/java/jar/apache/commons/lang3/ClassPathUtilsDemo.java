package jar.apache.commons.lang3;

import org.apache.commons.lang3.ClassPathUtils;
import org.junit.Test;

/**
 * ClassPathUtils
 * <p>
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ClassPathUtils.html
 */
public class ClassPathUtilsDemo {

    /**
     * static String	toFullyQualifiedName(Class<?>/Package context, String resourceName)
     * static String	toFullyQualifiedPath(Class<?>/Package context, String resourceName)
     */
    @Test
    public void classPath() {
        System.out.println(ClassPathUtils.toFullyQualifiedName(Integer.class, ""));                 // java.lang.
        System.out.println(ClassPathUtils.toFullyQualifiedName(Integer.class.getPackage(), ""));    // java.lang.
        System.out.println(ClassPathUtils.toFullyQualifiedPath(Integer.class, ""));                 // java/lang/
        System.out.println(ClassPathUtils.toFullyQualifiedPath(Integer.class.getPackage(), ""));    // java/lang/
    }



}
