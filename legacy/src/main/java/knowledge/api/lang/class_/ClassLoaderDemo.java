package knowledge.api.lang.class_;

import cn.hutool.core.util.StrUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
 *
 * @author ljh
 * @see <a href="https://blog.csdn.net/briblue/article/details/54973413">ClassLoader 详解</a>
 * @see <a href="https://blog.csdn.net/xiongyouqiang/article/details/79151903">可以自定义一个 Java.lang.String 吗？</a>
 * @since 2019/8/8 19:39
 */
public class ClassLoaderDemo extends Demo {

    /** 获得 ClassLoader 的三种途径 */
    @Test
    public void getClassLoader() {
        ClassLoader classLoader1 = this.getClass().getClassLoader();
        ClassLoader classLoader2 = ClassLoader.class.getClassLoader();
        // 返回当前线程上下文类加载器
        ClassLoader classLoader3 = Thread.currentThread().getContextClassLoader();
        // 返回系统类加载器
        ClassLoader classLoader4 = ClassLoader.getSystemClassLoader();

        ae(classLoader1.getClass().getName(), "jdk.internal.loader.ClassLoaders$AppClassLoader");
        ae(classLoader2.getClass().getName(), "jdk.internal.loader.ClassLoaders$AppClassLoader");
        ae(classLoader3.getClass().getName(), "jdk.internal.loader.ClassLoaders$AppClassLoader");
        ae(classLoader4.getClass().getName(), "jdk.internal.loader.ClassLoaders$AppClassLoader");
    }

    /**
     * <pre>
     * InputStream          {@link Class#getResourceAsStream(String)}               查找具有给定名称的资源
     * URL                  {@link Class#getResource(String)}                       查找具有给定名称的资源
     * static InputStream   {@link ClassLoader#getSystemResourceAsStream(String)}   从用来加载类的搜索路径打开具有指定名称的资源，以读取该资源
     * </pre>
     */
    @Test
    public void getSystemResourceAsStream() throws IOException {
        Class<ClassLoaderDemo> clazz = ClassLoaderDemo.class;
        final String clazzExt = ".class";

        String relativePath = this.getClass().getSimpleName() + clazzExt;
        ae("ClassLoaderDemo.class", relativePath);
        /* 相对路径（相对 clazz 所在包的路径） */
        try (InputStream is = clazz.getResourceAsStream(relativePath)) {
            p(Objects.requireNonNull(is).available());
            URL url = clazz.getResource(relativePath);
            ann(url);
            p(url.getPath());
            ae(url.getPath(), "/D:/Liang/git/epitome/target/classes/knowledge/api/lang/class_/ClassLoaderDemo.class");
        }

        String absolutePath = StrUtil.SLASH + ClassUtils.convertClassNameToResourcePath(this.getClass().getName()) + clazzExt;
        ae("/knowledge/api/lang/class_/ClassLoaderDemo.class", absolutePath);
        /* 绝对路径 */
        try (InputStream is = clazz.getResourceAsStream(absolutePath)) {
            p(Objects.requireNonNull(is).available());
            URL url = clazz.getResource(absolutePath);
            ann(url);
            ae(url.getPath(), "/D:/Liang/git/epitome/target/classes/knowledge/api/lang/class_/ClassLoaderDemo.class");
        }

        String rootRelativePath = absolutePath.substring(1);
        ae("knowledge/api/lang/class_/ClassLoaderDemo.class", rootRelativePath);
        /* 根相对路径 */
        try (InputStream is = ClassLoader.getSystemResourceAsStream(rootRelativePath)) {
            p(Objects.requireNonNull(is).available());
            URL url = clazz.getResource(rootRelativePath);
            an(url);
        }
    }
}
