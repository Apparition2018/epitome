package knowledge.api.lang.class_;

import org.junit.Test;

/**
 * JVM：加载类的三种途径：
 * 1) new()
 * 2) Class.forName()
 * 3) ClassLoader.loadClass()
 */
public class LoadClass {

    @Test
    public void test() {
        System.out.println(SonClass.value);
        // GrandClass init
        // FatherClass init
        // 123
    }

    /**
     * new()
     * 静态加载
     */
    @Test
    public void _new() {
        new SonClass();
        // GrandClass init
        // FatherClass init
        // SonClass init
        // init FatherClass
        // init SonClass
    }

    /**
     * Class.forName()
     * 动态加载
     */
    @Test
    public void classForName() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 内部调用 Class.forName(className, true, classloader);
        // boolean 值表示是否初始化，执行 static 静态块
        Class<?> clazz = Class.forName("knowledge.api.lang.class_.SonClass");
        // GrandClass init
        // FatherClass init
        // SonClass init
        clazz.newInstance();
        // init FatherClass
        // init SonClass
    }

    /**
     * ClassLoader loadClass()
     * 动态加载
     */
    @Test
    public void LoadClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 内部调用 ClassLoader 的 loadClass(className, false)
        // boolean 值表示目标对象是否进行链接
        // 进行链接意味着进行包括初始化等一系列步骤，比如静态块和静态对象
        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("knowledge.api.lang.class_.SonClass");
        clazz.newInstance();
        // GrandClass init
        // FatherClass init
        // SonClass init
        // init FatherClass
        // init SonClass
    }
}

class GrandClass {
    static {
        System.out.println("GrandClass init");
    }
}

class FatherClass extends GrandClass {
    static {
        System.out.println("FatherClass init");
    }

    public static int value = 123;

    FatherClass() {
        System.out.println("init FatherClass");
    }
}

class SonClass extends FatherClass {
    static {
        System.out.println("SonClass init");
    }

    static int a;

    SonClass() {
        System.out.println("init SonClass");
    }
}