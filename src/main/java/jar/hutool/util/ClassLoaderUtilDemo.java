package jar.hutool.util;

import cn.hutool.core.util.ClassLoaderUtil;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * ClassLoaderUtil  类加载工具
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%B7%A5%E5%85%B7-ClassLoaderUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/ClassLoaderUtil.html
 *
 * @author ljh
 * created on 2020/11/2 14:49
 */
public class ClassLoaderUtilDemo {

    @Test
    public void testClassLoaderUtil() {
        // static ClassLoader	getClassLoader()
        // 获取 ClassLoader
        p(ClassLoaderUtil.getClassLoader());                // sun.misc.Launcher$AppClassLoader@18b4aac2

        // static ClassLoader	getContextClassLoader()
        // 获取当前线程的 ClassLoader
        p(ClassLoaderUtil.getContextClassLoader());         // sun.misc.Launcher$AppClassLoader@18b4aac2

        // static Class<?>      loadClass(String name[[, ClassLoader classLoader], boolean isInitialized])
        // 加载类
        p(ClassLoaderUtil.loadClass("java.lang.String"));   // class java.lang.String

        // static Class<?>	    loadPrimitiveClass(String name)
        // 加载原始类型的类。包括 原始类型、原始类型数组、void
        p(ClassLoaderUtil.loadPrimitiveClass("int"));       // int

        // static boolean	    isPresent(String className[, ClassLoader classLoader])
        p(ClassLoaderUtil.isPresent("java.lang.String"));   // true
    }
}
