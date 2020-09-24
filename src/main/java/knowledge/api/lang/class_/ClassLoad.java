package knowledge.api.lang.class_;

import l.demo.Demo;
import org.junit.Test;

/**
 * 类加载
 * https://blog.csdn.net/u012426327/article/details/77160634
 * <p>
 * JVM 加载 Class 文件到内存的方式：
 * 1.隐式加载：继承或引用某个类
 * 2.显式加载：
 * - 1) new()
 * - 2) Class.forName()
 * - 3) ClassLoader’s loadClass()
 */
public class ClassLoad extends Demo {

    /**
     * 字段
     */
    @Test
    public void filed() {
        p(Son.value);
        // Grand init
        // Father init
        // 123
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
    public void classForName() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 内部调用 Class.forName(className, true, classloader);
        // boolean 值表示是否初始化，执行 static 静态块
        Class<?> clazz = Class.forName("knowledge.api.lang.class_.ClassLoad$SonClass");
        // Grand init
        // Father init
        // Son init
        clazz.newInstance();
        // init Father
        // init Son
    }

    /**
     * ClassLoader loadClass()  动态加载
     */
    @Test
    public void loadClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 内部调用 ClassLoader 的 loadClass(className, false)
        // boolean 值表示目标对象是否进行链接
        // 进行链接意味着进行包括初始化等一系列步骤，比如静态块和静态对象
        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("knowledge.api.lang.class_.ClassLoad$SonClass");
        clazz.newInstance();
        // Grand init
        // Father init
        // Son init
        // init Father
        // init Son
    }

    private static class Grand {
        static {
            p("Grand init");
        }
    }

    private static class Father extends Grand {
        static {
            p("Father init");
        }

        public static int value = 123;

        Father() {
            p("init Father");
        }
    }

    private static class Son extends Father {
        static {
            p("Son init");
        }

        static int a;

        Son() {
            p("init Son");
        }
    }
}