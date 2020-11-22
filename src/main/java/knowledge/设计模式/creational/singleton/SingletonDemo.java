package knowledge.设计模式.creational.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式：保证整个应用程序中某个实例有且只有一个
 * 应用场合：有些对象只需要一个就足够了
 * 应用场景：
 * 1.配置文件
 * 2.工具类
 * 3.线程池
 * 4.缓存
 * 5.日志对象
 * 6.计数器
 * 关键代码：构造器私有，另外提供一个用于获取实例的方法
 * <p>
 * 《设计模式解析与实战》单例模式：https://blog.csdn.net/wangwei129549/article/details/50623579
 * 深入理解单例模式：https://blog.csdn.net/mnb65482/article/details/80458571
 * 单例模式 | 菜鸟教程：https://www.runoob.com/design-pattern/singleton-pattern.html
 *
 * @author Arsenal
 * created on 2019/8/7 17:12
 */
public class SingletonDemo {

    /**
     * 饿汉模式，线程安全
     */
    private static class EagerSingleton {
        // 1.创建类的唯一实例，使用 private static 修饰
        // 类加载就创建唯一实例，以空间换时间，故不存在线程安全问题，形象称为饿汉模式
        private static EagerSingleton instance = new EagerSingleton();

        // 2.将构造方法私有化，不允许外部直接创建对象
        private EagerSingleton() {

        }

        // 3.提供一个用于获取实例的方法，使用 public static 修饰
        public static EagerSingleton getInstance() {
            return instance;
        }
    }

    /**
     * 懒汉模式，线程不安全
     */
    private static class LazySingleton {
        // 1.创建类的唯一实例，使用 private static 修饰
        private static LazySingleton instance;

        // 2.将构造方法私有化，不允许外部直接创建对象
        private LazySingleton() {

        }

        // 3.提供一个用于获取实例的方法，使用 public static 修饰
        // 调用方法才创建唯一实例，以时间换空间，存在线程安全问题，形象称为懒汉模式
        // 添加 synchronized 修饰方法则线程安全，但降低了效率
        public static LazySingleton getInstance() {
            if (null == instance) {
                instance = new LazySingleton();
            }
            return instance;
        }
    }

    /**
     * 双重检查锁 (DCL)，线程安全，懒汉模式的升级版
     * <p>
     * volatile 解决 DCL 失效问题：
     * instance = new DoubleCheckLockSingleton(); 在 JVM 里面的执行分为三步：
     * 1.在堆内存开辟内存空间
     * 2.在堆内存中实例化 SingleTon 里面的各个参数
     * 3.把对象指向堆内存空间
     * 由于 JVM 存在重排序（乱序执行），所以可能在 2 还没执行时就先执行了 3，
     * 如果此时再被切换到其它线程上，由于执行了 3，INSTANCE 已经非空了，会被直接拿出来用，
     * 这样的话，就会出现异常。这个就是著名的 DCL 失效问题。
     * JDK1.6 开始，volatile 确保 instance 每次均在主内存中读取，解决了 DCL 失效问题
     */
    private static class DoubleCheckLockSingleton {
        private volatile static DoubleCheckLockSingleton instance;

        private DoubleCheckLockSingleton() {
        }

        public static DoubleCheckLockSingleton getInstance() {
            // 避免不必要加锁
            if (null == instance) {
                synchronized ((DoubleCheckLockSingleton.class)) {
                    if (null == instance) {
                        instance = new DoubleCheckLockSingleton();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 静态内部类，线程安全
     * 内部类属于被动引用，类加载时不会对其进行初始化，所以实现了延迟加载
     * 缺点：外部无法传递参数进去内部类里
     */
    private static class StaticInnerClassSingleton {

        // 1.静态内部类创建类的唯一实例
        private static class SingletonHolder {
            private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
        }

        // 2.将构造方法私有化，不允许外部直接创建对象
        private StaticInnerClassSingleton() {

        }

        // 3.提供一个用于获取实例的方法，使用 public static 修饰
        public static StaticInnerClassSingleton getInstance() {
            return SingletonHolder.instance;
        }
    }

    /**
     * 测试反射攻击
     */
    @Test
    public void testReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        StaticInnerClassSingleton singleton = StaticInnerClassSingleton.getInstance();
        Constructor<StaticInnerClassSingleton> constructor = StaticInnerClassSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        StaticInnerClassSingleton singleton2 = constructor.newInstance();
        Assert.assertSame(singleton, singleton2);
    }

    /**
     * 枚举，线程安全，防止反射攻击，反序列化攻击
     * 枚举实现单例：https://blog.csdn.net/qq_38844728/article/details/88903939
     */
    private enum EnumSingleton {
        INSTANCE;

        public void doSomething() {
            // TODO something
        }
    }

    /**
     * 容器管理，线程不安全
     * 在程序初始化的时候，把多个单例放到 map 里边统一管理
     */
    private static class SingletonManager {
        private static Map<String, Object> singletonMap = new HashMap<>();

        private SingletonManager() {
        }

        public static void putInstance(String key, Object instance) {
            if (!singletonMap.containsKey(key) && null != instance) {
                singletonMap.put(key, instance);
            }
        }

        public static Object getInstance(String key) {
            return singletonMap.get(key);
        }
    }
}