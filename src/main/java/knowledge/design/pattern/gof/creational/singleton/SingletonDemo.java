package knowledge.design.pattern.gof.creational.singleton;

import knowledge.design.pattern.other.creational.MultitonDemo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 单例模式：确保一个类只有一个实例，并提供一个全局访问点
 * <p>使用场景：
 * <pre>
 * 1 同定义
 * 2 减少内存开销
 * 3 避免对资源的多重占用：如写文件操作
 * </pre>
 * 使用实例：
 * <pre>
 * 1 JDK：{@link Runtime#getRuntime()}、{@link Desktop#getDesktop()}
 * 2 Spring：@Bean、@Component …
 *  ① 配置：{@link ConfigurationProperties}
 *  ② 数据源/连接池：{@link AbstractDataSource}
 *  ③ 缓存：{@link CacheManager}
 *  ④ 线程池：{@link ThreadPoolTaskExecutor}
 * 3 手动实现单例
 *  ① JNI 底层库调用、独占型硬件资源
 *  （这些底层资源在整个 JVM 进程中一旦被初始化两次，就会直接导致 JVM 崩溃或端口被占用的系统级异常）
 *  ② SPI 实现类内部使用的单例
 *  （JVM 通过反射直接 newInstance() 创建的对象，不受 Spring IOC 控制，@Autowired、@Component 无法生效）
 *  ③ {@link RichDomainModelDemo “充血模型”实体} 或 new 出来的线程任务，内部调用 Spring 容器内的服务
 * </pre>
 * 缺点：
 * <pre>
 * 1 违反单一职责原则：除了充当工厂角色，一般还包含一些业务方法
 * 2 违反开闭原则：没有抽象层，扩展困难，要扩展只能修改源码
 * 3 难以单元测试：许多测试框架以基于继承的方式创建模拟对象。单例构造器私有，大多数语言不能重写静态方法
 * 4 实例化的共享对象长时间不利用会被 GC 回收，导致单例对象状态的丢失，再次利用时又重新实例化
 * </pre>
 * 扩展：
 * <pre>
 * 1 {@link MultitonDemo 多例模式}
 * 2 TODO(LJH) 分布式单例
 * </pre>
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/singleton">Singleton</a>
 * @see <a href="http://c.biancheng.net/view/1338.html">Java设计模式</a>
 * @see <a href="">设计模式之美：单例模式（上）：为什么说支持懒加载的双重检测不比饿汉式更优？</a>
 * @see <a href="">设计模式之美：单例模式（中）：我为什么不推荐使用单例模式？又有何替代方案？</a>
 * @see <a href="">设计模式之美：单例模式（下）：如何设计实现一个集群环境下的分布式单例模式？</a>
 * @see <a href="https://gupaoedu-tom.blog.csdn.net/article/details/120972136">Tom|多种单例写法和两种攻击</a>
 * @see <a href="https://www.baeldung.com/java-static-class-vs-singleton">单例 vs 静态类</a>
 * @since 2019/8/7 17:12
 */
public class SingletonDemo {

    /** 测试反射攻击 */
    @Test
    public void testReflectAttack() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        InitOnDemandSingleton singleton = InitOnDemandSingleton.getInstance();
        Constructor<InitOnDemandSingleton> constructor = InitOnDemandSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InitOnDemandSingleton singleton2 = constructor.newInstance();
        // 断言是否相等
        Assertions.assertSame(singleton, singleton2);
    }

    /** <a href="https://www.zhihu.com/zvideo/1437472799569379328">测试序列化攻击</a> */
    @Test
    public void serializeAttack() {
        EarlySingleton singleton1;
        EarlySingleton singleton2 = EarlySingleton.getInstance();
        String pathStr = "Singleton.obj";
        Path path = Paths.get(pathStr);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
             ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            oos.writeObject(singleton2);
            oos.flush();
            singleton1 = (EarlySingleton) ois.readObject();
            // 断言是否相等
            Assertions.assertSame(singleton1, singleton2);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        new File(pathStr).deleteOnExit();
    }
}

/** 饿汉模式，线程安全 */
class EarlySingleton implements Serializable {
    @Serial
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
    @Serial
    private Object readResolve() {
        return INSTANCE;
    }
}

/** 懒汉模式，线程不安全 */
class LazySingleton {
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
 * <a href="https://www.cnblogs.com/xz816111/p/8470048.html">双重检查锁</a> (double-checked locking)：线程安全，懒汉模式的升级版
 * <p>instance = new DoubleCheckLockSingleton(); 在 JVM 里面的执行分为三步：
 * <pre>
 * 1 在堆内存开辟内存空间
 * 2 在堆内存中实例化 Singleton 里面的各个参数
 * 3 把对象指向堆内存空间
 *
 * 由于 JVM 存在重排序（乱序执行），所以可能在 2 还没执行时就先执行了 3，
 * 如果此时再被切换到其它线程上，由于执行了 3，INSTANCE 已经非空了，会被直接拿出来用，
 * 这样的话，就会出现异常。这个就是著名的 DCL 失效问题。
 * </pre>
 * 通过双重检查锁（double-checked locking），实现延迟初始化需要将目标属性声明为 volatile 型（阿里编程规约）
 */
class DoubleCheckLockSingleton {
    private volatile static DoubleCheckLockSingleton instance;

    private DoubleCheckLockSingleton() {
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static DoubleCheckLockSingleton getInstance() {
        if (instance != null) return instance;
        synchronized (DoubleCheckLockSingleton.class) {
            if (instance == null) instance = new DoubleCheckLockSingleton();
            return instance;
        }
    }
}

/**
 * 按需初始化 (Initialization on Demand Holder)：静态内部类，饿汉的优化版
 * <pre>
 * 1 延迟加载：内部类属于被动引用，外部类加载时不会对其进行初始化，getInstance 第一次被调用的时候才加载内部类
 * 2 线程安全：同饿汉线程安全原因
 * </pre>
 * 缺点：外部无法传递参数进去内部类里
 */
class InitOnDemandSingleton {

    @SuppressWarnings("InstantiationOfUtilityClass")
    private static class InstanceHolder {
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
