package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.ClassPathUtils;
import org.junit.Test;

/**
 * ClassPathUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ClassPathUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ClassPathUtilsDemo extends Demo {

    /**
     * static String	toFullyQualifiedName(Class<?>/Package context, String resourceName)
     * static String	toFullyQualifiedPath(Class<?>/Package context, String resourceName)
     */
    @Test
    public void classPath() {
        p(ClassPathUtils.toFullyQualifiedName(Integer.class, ""));              // java.lang.
        p(ClassPathUtils.toFullyQualifiedName(Integer.class.getPackage(), "")); // java.lang.
        p(ClassPathUtils.toFullyQualifiedPath(Integer.class, ""));              // java/lang/
        p(ClassPathUtils.toFullyQualifiedPath(Integer.class.getPackage(), "")); // java/lang/
    }

}
