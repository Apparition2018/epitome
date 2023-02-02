package knowledge.api.lang.class_;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static l.demo.Demo.p;

/**
 * <a href="https://blog.csdn.net/u012426327/article/details/77160634">类加载</a>
 * <p>JVM 加载 Class 文件到内存的方式：
 * <pre>
 * 1 隐式加载：继承或引用某个类
 * 2 显式加载：
 *   2.1 new()
 *   2.2 Class.forName()
 *   2.3 classLoader.loadClass()
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class ClassLoad {

    /**
     * 字段
     */
    @Test
    public void filed() {
        p(Son.X);
        p(Son.Y);
        p(Son.Z);
        // 0                static final String 不会触发类初始化
        // 1                static final 基本类型 不会触发类初始化
        // Grand init
        // Father init
        // 2
    }

    /**
     * new()                    静态加载
     */
    @Test
    public void new_() {
        new Son();
        // Grand init
        // Father init
        // Son init
        // init Father
        // init Son
    }

    /**
     * Class.forName()          动态加载
     */
    @Test
    public void classForName() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 内部调用 Class.forName(className, true, classloader);
        // boolean 值表示是否初始化，执行 static 静态块
        Class<?> clazz = Class.forName("knowledge.api.lang.class_.ClassLoad$SonClass");
        // Grand init
        // Father init
        // Son init
        clazz.getConstructor().newInstance();
        // init Father
        // init Son
    }

    /**
     * ClassLoader loadClass()  动态加载
     */
    @Test
    public void loadClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 内部调用 ClassLoader 的 loadClass(className, false)
        // boolean 值表示目标对象是否进行链接
        // 进行链接意味着进行包括初始化等一系列步骤，比如静态块和静态对象
        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("knowledge.api.lang.class_.ClassLoad$SonClass");
        clazz.getConstructor().newInstance();
        // Grand init
        // Father init
        // Son init
        // init Father
        // init Son
    }

    static class Grand {
        static {
            p("Grand init");
        }
    }

    static class Father extends Grand {
        public static final String X = "0";
        public static final int Y = 1;
        public static final Integer Z = 2;

        static {
            p("Father init");
        }

        Father() {
            p("init Father");
        }
    }

    static class Son extends Father {
        static {
            p("Son init");
        }

        Son() {
            p("init Son");
        }
    }
}
