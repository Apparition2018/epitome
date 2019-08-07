package other.设计模式.creational.singleton;

import org.junit.Test;

/**
 * 单例模式：保证整个应用程序中某个实例有且只有一个
 * <p>
 * 应用场合：有些对象只需要一个就足够了
 * 1.配置文件
 * 2.工具类
 * 3.线程池
 * 4.缓存
 * 5.日志对象
 * <p>
 * 例模式的七种写法：https://cantellow.iteye.com/blog/838473
 * 深入理解单例模式：https://blog.csdn.net/mnb65482/article/details/80458571
 * <p>
 * https://www.imooc.com/video/1778
 *
 * @author Arsenal
 * created on 2019/8/7 17:12
 */
public class SingletonDemo {

    @Test
    public void singleton() {
        // 饿汉模式
        Singleton1 s1 = Singleton1.getSInstance();
        Singleton1 s2 = Singleton1.getSInstance();
        // 懒汉模式
        // Singleton2 s1 = Singleton2.getSInstance();
        // Singleton2 s2 = Singleton2.getSInstance();
        if (s1 == s2) {
            System.out.println("s1 和 s2 是同一个实例");
        } else {
            System.out.println("s1 和 s2 不是同一个实例");
        }
    }
}

/**
 * 饿汉模式，线程安全
 */
class Singleton1 {
    // 1.创建类的唯一实例，使用 private static 修饰
    // 类加载就创建唯一实例，形象成为饿汉模式
    private static Singleton1 instance = new Singleton1();

    // 2.将构造方法私有化，不允许外部直接创建对象
    private Singleton1() {

    }

    // 3.提供一个用于获取实例的方法，使用 public static 修饰
    public static Singleton1 getSInstance() {
        return instance;
    }
}

/**
 * 懒汉模式，线程不安全
 */
class Singleton2 {
    // 1.创建类的唯一实例，使用 private static 修饰
    private static Singleton2 instance;

    // 2.将构造方法私有化，不允许外部直接创建对象
    private Singleton2() {

    }

    // 3.提供一个用于获取实例的方法，使用 public static 修饰
    // 调用方法才创建唯一实例，形象成为懒汉模式
    public static Singleton2 getSInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

/**
 * 静态内部类，线程安全
 */
class Singleton3 {

    // 1.将构造方法私有化，不允许外部直接创建对象
    private Singleton3() {

    }

    // 2.静态内部类
    private static class SingletonHolder {
        private static Singleton3 INSTANCE = new Singleton3();
    }

    // 3.提供一个用于获取实例的方法，使用 public static 修饰
    public static Singleton3 getSInstance() {
        return SingletonHolder.INSTANCE;
    }
}