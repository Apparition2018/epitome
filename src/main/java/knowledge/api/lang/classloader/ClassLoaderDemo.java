package knowledge.api.lang.classloader;


import org.junit.Test;

/**
 * ClassLoader
 * <p>
 * JVM 中类的装载是由 ClassLoader 和它的子类来实现的，Java ClassLoader 是一个重要的 Java 运行时系统组件。它负责在运行时查找和装入类文件的类。
 * <p>
 * 类加载到卸载的生命周期：
 * 1)加载（Loading） 2)验证（Verification） 3)准备(Preparation) 4)解析(Resolution) 5)初始化(Initialization) 6)使用(Using) 7)卸载(Unloading)
 * <p>
 * https://blog.csdn.net/briblue/article/details/54973413
 * http://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/ClassLoader.html
 */
public class ClassLoaderDemo {

    /**
     * 获得 ClassLoader 的三种途径
     */
    @Test
    public void getClassLoader() {
        // 使用当前类
        ClassLoader classLoader1 = this.getClass().getClassLoader();

        // 使用当前线程
        ClassLoader classLoader2 = Thread.currentThread().getContextClassLoader();

        // 使用系统 ClassLoader
        ClassLoader classLoader3 = ClassLoader.getSystemClassLoader();

        p(classLoader1);
        p(classLoader2);
        p(classLoader3);
    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }
}