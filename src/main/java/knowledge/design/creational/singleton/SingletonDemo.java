package knowledge.design.creational.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式：保证一个类有且只有一个实例，并提供一个访问该实例的全局访问点
 * 使用场景：
 * 使用实例：
 * 1.配置文件
 * 2.工具类
 * 3.线程池
 * 4.缓存
 * 5.日志对象
 * 6.计数器
 * <p>
 * 优点：
 * 1.减少内存开销
 * 2.避免对资源的多重占用：如写文件操作
 * 缺点：
 * 1.违反单一职责原则：单例业务逻辑通常写在一个类中
 * 2.违反开闭原则：没有接口，扩展困难，要扩展只能修改源码
 * 3.难以单元测试：许多测试框架以基于继承的方式创建模拟对象。单例构造器私有，大多数语言不能重写静态方法
 * 4.线程安全问题
 * 扩展：多例模式
 * <p>
 * Singleton：https://refactoringguru.cn/design-patterns/singleton
 * Java设计模式：http://c.biancheng.net/view/1338.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/singleton-pattern.html
 * 《设计模式解析与实战》读书笔记：https://blog.csdn.net/wangwei129549/article/details/50623579
 * 深入理解单例模式：https://blog.csdn.net/mnb65482/article/details/80458571
 *
 * @author Arsenal
 * created on 2019/8/7 17:12
 */
public class SingletonDemo {

    /**
     * 饿汉模式，线程安全
     */
    static class EagerSingleton {
        // 1.创建类的唯一实例，使用 private static 修饰
        // 类加载就创建唯一实例，且类只会被加载一次，故不存在线程安全问题，形象称为饿汉模式
        private static final EagerSingleton instance = new EagerSingleton();

        // 2.将构造方法私有化，不允许外部通过 new 直接创建对象
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
    static class LazySingleton {
        private static LazySingleton instance;

        private LazySingleton() {
        }

        // 调用方法才创建唯一实例，形象称为懒汉模式
        // 存在线程安全问题，添加 synchronized 修饰方法则线程安全，但降低了效率
        public static LazySingleton getInstance() {
            if (instance == null) instance = new LazySingleton();
            return instance;
        }
    }

    /**
     * 双重检查锁 (double-checked locking)，线程安全，懒汉模式的升级版
     * <p>
     * volatile 解决 DCL（在并发场景下）存在延迟初始化的优化问题隐患（阿里编程规约）
     * instance = new DoubleCheckLockSingleton(); 在 JVM 里面的执行分为三步：
     * 1.在堆内存开辟内存空间
     * 2.在堆内存中实例化 Singleton 里面的各个参数
     * 3.把对象指向堆内存空间
     * 由于 JVM 存在重排序（乱序执行），所以可能在 2 还没执行时就先执行了 3，
     * 如果此时再被切换到其它线程上，由于执行了 3，INSTANCE 已经非空了，会被直接拿出来用，
     * 这样的话，就会出现异常。这个就是著名的 DCL 失效问题。
     * JDK1.6 开始，volatile 确保 instance 每次均在主内存中读取，解决了 DCL 失效问题
     * <p>
     * https://www.cnblogs.com/xz816111/p/8470048.html
     */
    static class DoubleCheckLockSingleton {
        private volatile static DoubleCheckLockSingleton instance;

        private DoubleCheckLockSingleton() {
        }

        public static DoubleCheckLockSingleton getInstance() {
            if (instance != null) return instance;
            synchronized ((DoubleCheckLockSingleton.class)) {
                if (instance == null) instance = new DoubleCheckLockSingleton();
                return instance;
            }
        }
    }

    /**
     * 静态内部类，饿汉的升级版
     * 1.延迟加载：内部类属于被动引用，外部类加载时不会对其进行初始化，getInstance 第一次被调用的时候才加载内部类
     * 2.线程安全：同饿汉线程安全原因
     * 缺点：外部无法传递参数进去内部类里
     */
    static class StaticInnerClassSingleton {

        static class SingletonHolder {
            private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
        }

        private StaticInnerClassSingleton() {
        }

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
        Assertions.assertSame(singleton, singleton2);
    }

    /**
     * 枚举，线程安全，防止反射攻击，反序列化攻击
     * 枚举实现单例：https://blog.csdn.net/qq_38844728/article/details/88903939
     */
    private enum EnumSingleton {
        INSTANCE;

        public void doSomething() {
            // ...
        }
    }

    /**
     * 容器管理，线程不安全
     * 在程序初始化的时候，把多个单例放到 map 里边统一管理
     */
    static class SingletonManager {
        private static final Map<String, Object> singletonMap = new HashMap<>();

        private SingletonManager() {
        }

        public static void putInstance(String key, Object instance) {
            if (!singletonMap.containsKey(key) && instance != null) {
                singletonMap.put(key, instance);
            }
        }

        public static Object getInstance(String key) {
            return singletonMap.get(key);
        }
    }
}