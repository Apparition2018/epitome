package jar.hutool.util;

import cn.hutool.core.util.ClassLoaderUtil;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/ClassLoaderUtil/">ClassLoaderUtil</a>   类加载工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/ClassLoaderUtil.html">ClassLoaderUtil api</a>
 *
 * @author ljh
 * @since 2020/11/2 14:49
 */
public class ClassLoaderUtilDemo {

    public static void main(String[] args) {
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
