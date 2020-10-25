package knowledge.设计模式.creational.singleton;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单例模式：保证整个应用程序中某个实例有且只有一个
 * <p>
 * 应用场合：有些对象只需要一个就足够了
 * <p>
 * 使用场景：
 * 1.配置文件
 * 2.工具类
 * 3.线程池
 * 4.缓存
 * 5.日志对象
 * 6.计数器
 * <p>
 * 关键代码：构造器私有，另外提供一个用于获取实例的方法
 * <p>
 * 例模式的七种写法：https://cantellow.iteye.com/blog/838473
 * 深入理解单例模式：https://blog.csdn.net/mnb65482/article/details/80458571
 * 单例模式 | 菜鸟教程：https://www.runoob.com/design-pattern/singleton-pattern.html
 * 单例模式：https://blog.csdn.net/wangwei129549/article/details/50623579
 * <p>
 * https://www.imooc.com/video/1778
 *
 * @author Arsenal
 * created on 2019/8/7 17:12
 */
public class SingletonDemo {

    /**
     * 测试饿汉模式
     */
    @Test
    public void testEager() {
        Assert.assertSame(EagerSingleton.getSInstance(), EagerSingleton.getSInstance());
    }

    /**
     * 测试懒汉模式
     */
    @Test
    public void testLazy() {
        Assert.assertSame(LazySingleton.getSInstance(), LazySingleton.getSInstance());
    }

    /**
     * 饿汉模式，线程安全
     */
    private static class EagerSingleton {
        // 1.创建类的唯一实例，使用 private static 修饰
        // 类加载就创建唯一实例，形象成为饿汉模式
        private static EagerSingleton instance = new EagerSingleton();

        // 2.将构造方法私有化，不允许外部直接创建对象
        private EagerSingleton() {

        }

        // 3.提供一个用于获取实例的方法，使用 public static 修饰
        public static EagerSingleton getSInstance() {
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
        // 调用方法才创建唯一实例，形象成为懒汉模式
        public static LazySingleton getSInstance() {
            if (null == instance) {
                instance = new LazySingleton();
            }
            return instance;
        }
    }

    /**
     * 静态内部类，线程安全
     */
    private static class StaticInnerClassSingleton {
        // 1.将构造方法私有化，不允许外部直接创建对象
        private StaticInnerClassSingleton() {

        }

        // 2.静态内部类
        private static class SingletonHolder {
            private static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
        }

        // 3.提供一个用于获取实例的方法，使用 public static 修饰
        public static StaticInnerClassSingleton getSInstance() {
            return SingletonHolder.INSTANCE;
        }
    }
}