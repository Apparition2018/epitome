package knowledge.api.lang.classloader;

import org.junit.Test;

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

    public SonClass() {
        System.out.println("init SonClass");
    }
}

public class ClassInitialization {

    @Test
    public void test1() {
        System.out.println(SonClass.value);
        // GrandClass init
        // FatherClass init
        // 123
    }

    @Test
    public void classForName() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // Class.forName(className, true, classloader);
        // boolean 值表示是否初始化，执行 static 静态块
        Class<?> clazz = Class.forName("knowledge.api.lang.classloader.SonClass");
        // GrandClass init
        // FatherClass init
        // SonClass init
        clazz.newInstance();
        // init FatherClass
        // init SonClass
    }

    @Test
    public void loadClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // loadClass(className, false)
        // boolean 值表示目标对象是否进行链接
        // 进行链接意味着进行包括初始化等一系列步骤，比如静态块和静态对象
        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("knowledge.api.lang.classloader.SonClass");
        System.out.println();
        clazz.newInstance();
        // GrandClass init
        // FatherClass init
        // SonClass init
        // init FatherClass
        // init SonClass
    }
}
