package knowledge.api.lang.class_;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Objects;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/ClassLoader.html">ClassLoader</a>
 * <p>JVM 中类的装载是由 ClassLoader 和它的子类来实现的，Java ClassLoader 是一个重要的 Java 运行时系统组件。它负责在运行时查找和装入类文件的类。
 * <p>类加载到卸载的生命周期：
 * <pre>
 * 1 加载 Loading
 * 2 验证 Verification
 * 3 准备 Preparation
 * 4 解析 Resolution
 * 5 初始化 Initialization
 * 6 使用 Using
 * 7 卸载 Unloading
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://blog.csdn.net/briblue/article/details/54973413">ClassLoader 详解</a>
 * <a href="https://blog.csdn.net/xiongyouqiang/article/details/79151903">可以自定义一个 Java.lang.String 吗？</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ClassLoaderDemo extends Demo {

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

    /**
     * <pre>
     * InputStream          getResourceAsStream(String name)        返回读取指定资源的输入流
     * static InputStream   getSystemResourceAsStream(String name)  从用来加载类的搜索路径打开具有指定名称的资源，以读取该资源
     * </pre>
     */
    @Test
    public void getSystemResourceAsStream() {
        InputStream is1 = ClassLoaderDemo.class.getResourceAsStream(DEMO_FILE_PATH);
        InputStream is2 = ClassLoader.getSystemResourceAsStream(DEMO_FILE_PATH);
        p(Objects.equals(is1, is2));
    }
}
