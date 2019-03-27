package knowledge.api.lang.classloader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.Test;

/**
 * ClassLoader
 * <p>
 * JVM 中类的装载是由 ClassLoader 和它的子类来实现的，Java ClassLoader 是一个重要的 Java 运行时系统组件。它负责在运行时查找和装入类文件的类。
 * <p>
 * 类加载到卸载的生命周期：
 * 1)加载（Loading） 2)验证（Verification） 3)准备(Preparation) 4)解析(Resolution) 5)初始化(Initialization) 6)使用(Using) 7)卸载(Unloading)
 * <p>
 * https://blog.csdn.net/briblue/article/details/54973413
 * http://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/ClassLoader.html
 */
public class ClassLoaderDemo {

    /**
     * 获得 ClassLoader 的三种途径
     */
    @Test
    public void getClassLoader() {
        // 使用当前类
        p(this.getClass().getClassLoader());

        // 使用当前线程
        p(Thread.currentThread().getContextClassLoader());

        // 使用系统 ClassLoader
        p(ClassLoader.getSystemClassLoader());
    }

    /**
     * JVM 加载类的三种途径
     */
    @Test
    public void loadClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 1
        // new
        Dog dog1 = new Dog("WangCai", 3); // 会执行静态块
        p(dog1);

        // 2
        // static Class<?>	    forName(String name, boolean initialize, ClassLoader loader)
        Class clazz = Class.forName("knowledge.api.lang.classloader.Dog");
        Dog dog2 = (Dog) clazz.newInstance(); // 会执行静态块
        dog2.setName("WangCai").setAge(3);
        p(dog2);

        // 3
        // Class<?>	            loadClass(String name)
        Class clazz2 = this.getClass().getClassLoader().loadClass("knowledge.api.lang.classloader.Dog"); // 不会执行静态块
        Dog dog3 = (Dog) clazz2.newInstance();
        dog3.setName("WangCai").setAge(3);
        p(dog3);
    }

    public static <T> void p(T obj) {
        if (obj == null) {
            return;
        }
        System.out.println(obj);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
class Dog {

    private String name;
    private int age;

    static {
        System.out.println("The is a Dog.");
    }

}