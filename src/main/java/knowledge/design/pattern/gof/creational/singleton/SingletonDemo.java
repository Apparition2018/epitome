package knowledge.design.pattern.gof.creational.singleton;

import knowledge.design.pattern.other.creational.MultitonDemo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式：确保一个类只有一个实例，并提供一个全局访问点
 * 使用场景：
 * 1.同定义
 * 2.减少内存开销
 * 3.避免对资源的多重占用：如写文件操作
 * 使用实例：
 * 1.配置信息类、ID 生成器、连接池、线程池、缓冲池、工具类、日志
 * 2.{@link Runtime#getRuntime()}
 * 3.{@link java.awt.Desktop#getDesktop()}
 * 4.{@link System#getSecurityManager()}
 * 5.{@link org.springframework.beans.factory.support.AbstractBeanFactory#getBean(String)} 单例注册表
 * <p>
 * 缺点：
 * 1.违反单一职责原则：除了充当工厂角色，一般还包含一些业务方法
 * 2.违反开闭原则：没有抽象层，扩展困难，要扩展只能修改源码
 * 3.难以单元测试：许多测试框架以基于继承的方式创建模拟对象。单例构造器私有，大多数语言不能重写静态方法
 * 4.实例化的共享对象长时间不利用会被 GC 回收，导致单例对象状态的丢失，再次利用时又重新实例化
 * 扩展：
 * 1.多例模式：{@link MultitonDemo}
 * 2.TODO-LJH 分布式单例
 * <p>
 * Singleton：https://refactoringguru.cn/design-patterns/singleton
 * Java设计模式：http://c.biancheng.net/view/1338.html
 * 设计模式之美：单例模式（上）：为什么说支持懒加载的双重检测不比饿汉式更优？
 * 设计模式之美：单例模式（中）：我为什么不推荐使用单例模式？又有何替代方案？
 * 设计模式之美：单例模式（下）：如何设计实现一个集群环境下的分布式单例模式？
 * Tom|多种单例写法和两种攻击：https://gupaoedu-tom.blog.csdn.net/article/details/120972136
 * 单例 vs 静态类：https://www.baeldung.com/java-static-class-vs-singleton
 *
 * @author Arsenal
 * created on 2019/8/7 17:12
 */
public class SingletonDemo {

    /**
     * 饿汉模式，线程安全
     */
    static class EarlySingleton implements Serializable {
        private static final long serialVersionUID = -7618188072415286252L;
        // 1.创建类的唯一实例，使用 private static 修饰
        // 类加载就创建唯一实例，且类只会被加载一次，故不存在线程安全问题，形象称为饿汉模式
        private static final EarlySingleton INSTANCE = new EarlySingleton();

        // 2.将构造方法私有化，不允许外部通过 new 直接创建对象
        private EarlySingleton() {
        }

        // 3.提供一个用于获取实例的方法，使用 public static 修饰
        public static EarlySingleton getInstance() {
            return INSTANCE;
        }

        // 防止序列化攻击
        // ObjectInputStream.java:1665      checkResolve(readOrdinaryObject(unshared))
        // ObjectInputStream.java:2194      desc.invokeReadResolve(obj)
        private Object readResolve() {
            return INSTANCE;
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
     * 双重检查锁 (double-checked locking)：线程安全，懒汉模式的升级版
     * <p>
     * instance = new DoubleCheckLockSingleton(); 在 JVM 里面的执行分为三步：
     * 1.在堆内存开辟内存空间
     * 2.在堆内存中实例化 Singleton 里面的各个参数
     * 3.把对象指向堆内存空间
     * 由于 JVM 存在重排序（乱序执行），所以可能在 2 还没执行时就先执行了 3，
     * 如果此时再被切换到其它线程上，由于执行了 3，INSTANCE 已经非空了，会被直接拿出来用，
     * 这样的话，就会出现异常。这个就是著名的 DCL 失效问题。
     * <p>
     * 通过双重检查锁（double-checked locking），实现延迟初始化需要将目标属性声明为 volatile 型（阿里编程规约）
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
     * 按需初始化 (Initialization on Demand Holder)：静态内部类，饿汉的优化版
     * 1.延迟加载：内部类属于被动引用，外部类加载时不会对其进行初始化，getInstance 第一次被调用的时候才加载内部类
     * 2.线程安全：同饿汉线程安全原因
     * 缺点：外部无法传递参数进去内部类里
     */
    static class InitOnDemandSingleton {

        static class InstanceHolder {
            private static final InitOnDemandSingleton INSTANCE = new InitOnDemandSingleton();
        }

        private static boolean beReflect = false;

        private InitOnDemandSingleton() {
            // 防止反射攻击
            synchronized (EarlySingleton.class) {
                if (!beReflect) {
                    beReflect = true;
                } else {
                    throw new RuntimeException("禁止通过反射创建单例！");
                }
            }
        }

        public static InitOnDemandSingleton getInstance() {
            return InstanceHolder.INSTANCE;
        }
    }

    /**
     * 测试反射攻击
     */
    @Test
    public void testReflectAttack() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        InitOnDemandSingleton singleton = InitOnDemandSingleton.getInstance();
        Constructor<InitOnDemandSingleton> constructor = InitOnDemandSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InitOnDemandSingleton singleton2 = constructor.newInstance();
        // 断言是否相等
        Assertions.assertSame(singleton, singleton2);
    }

    /**
     * 测试序列化攻击
     * https://www.zhihu.com/zvideo/1437472799569379328
     */
    @Test
    public void serializeAttack() {
        EarlySingleton singleton1;
        EarlySingleton singleton2 = EarlySingleton.getInstance();
        String filePath = "Singleton.obj";
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)));
             ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)))) {
            oos.writeObject(singleton2);
            oos.flush();
            singleton1 = (EarlySingleton) ois.readObject();
            // 断言是否相等
            Assertions.assertSame(singleton1, singleton2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        new File(filePath).deleteOnExit();
    }

    /**
     * 枚举
     * 1.线程安全：编译后，枚举项由 static 修饰
     * 2.防止反射攻击：Constructor.java:417 Cannot reflectively create enum objects
     * 3.防止序列化攻击：序列化时仅序列化枚举对象的 name，反序列化时通过 valueOf(name) 反序列化枚举对象
     * 枚举实现单例：https://blog.csdn.net/qq_38844728/article/details/88903939
     */
    private enum EnumSingleton {
        INSTANCE;

        public void doSomething() {
            // ...
        }
    }

    /**
     * 容器式单例，线程不安全
     */
    static class ContainerSingleton {
        private static final Map<String, Object> SINGLETON_MAP = new ConcurrentHashMap<>();

        private ContainerSingleton() {
        }

        public static Object getInstance(String key) {
            synchronized (SINGLETON_MAP) {
                if (!SINGLETON_MAP.containsKey(key)) {
                    Object obj = null;
                    try {
                        obj = Class.forName(key).newInstance();
                        SINGLETON_MAP.put(key, obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return obj;
                } else {
                    return SINGLETON_MAP.get(key);
                }
            }
        }
    }
}
