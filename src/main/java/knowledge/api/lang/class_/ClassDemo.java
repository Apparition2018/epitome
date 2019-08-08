package knowledge.api.lang.class_;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.junit.Test;
import utils.Tools;

import java.lang.reflect.Field;
import java.util.function.Predicate;

/**
 * Class<T>
 * <p>
 * Class 类的实例表示正在运行的 Java 应用程序中的类和接口。
 * 枚举是一种类，注释是一种接口。
 * 每个数组属于被映射为 Class 对象的一个类，所有具有相同元素类型和维数的数组都共享该 Class 对象。
 * 基本的 Java 类型（boolean、byte、char、short、int、long、float 和 double）和关键字 void 也表示为 Class 对象。
 * <p>
 * Class 没有公共构造方法。Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的 defineClass 方法自动构造的。
 * <p>
 * ClassLoader	    getClassLoader()            返回该类的类加载器
 * Package	        getPackage()                返回该类的包
 * Class<? super T>	getSuperclass()             返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class
 * T	            newInstance()               创建此 Class 对象所表示的类的一个新实例
 * <p>
 * boolean	        isAnnotation()              注释类型
 * boolean	        isAnnotationPresent(Class<? extends Annotation> annotationClass)
 * boolean	        isAnonymousClass()          匿名类
 * boolean	        isArray()                   数组类型
 * boolean	        isEnum()                    枚举
 * boolean	        isInterface()               接口
 * boolean	        isLocalClass()              本地类
 * boolean	        isMemberClass()             成员类
 * boolean	        isPrimitive()               基本类型
 * boolean	        isSynthetic()               符合类
 * <p>
 * http://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Class.html
 */
public class ClassDemo {

    private static class ClassInnerDemo {
    }

    /**
     * static Class<?>	forName(String name[, boolean initialize, ClassLoader loader])
     * 使用给定的类加载器，返回与带有给定字符串名的类或接口相关联的 Class 对象
     */
    @Test
    public void forName() throws ClassNotFoundException {
        // Class.forName("Foo"）
        // 等同于
        // Class.forName("Foo", true, this.getClass().getClassLoader())
        // true 表示初始化，执行 static 静态块

        Class.forName("knowledge.api.lang.class_.Animal", false, this.getClass().getClassLoader());
        // The is a Dog.
    }

    /**
     * Field[]      getDeclaredFields()     返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
     * Field[]	    getFields()             返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口的所有可访问公共字段
     * <p>
     * 类似的还有 getConstructors() 和 getDeclaredConstructors(), getMethods() 和 getDeclaredMethods()
     */
    @Test
    public void getFields() {
        // getDeclaredFields() 获取某个类的所有字段，不包括父类
        Field[] fields1 = Dog.class.getDeclaredFields();
        // getFields() 获取某个类的 public 字段，包括父类
        Field[] fields2 = Dog.class.getFields();
        Tools.p(fields1);
        // [public java.lang.String knowledge.api.lang.class_.Dog.dog1, private java.lang.String knowledge.api.lang.class_.Dog.dog2]
        Tools.p(fields2);
        // [public java.lang.String knowledge.api.lang.class_.Dog.dog1, public java.lang.String knowledge.api.lang.class_.Animal.name]
    }

    /**
     * String	getName()           返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称
     * String	getCanonicalName()  返回 Java Language Specification 中所定义的底层类的规范化名称
     * String	getSimpleName()     返回源代码中给出的底层类的简称
     * <p>
     * https://blog.csdn.net/moakun/article/details/80402562
     */
    @Test
    public void getName() {
        // 普通类
        p(ClassDemo.class.getName());               // knowledge.api.lang.class_.ClassDemo
        p(ClassDemo.class.getCanonicalName());      // knowledge.api.lang.class_.ClassDemo
        p(ClassDemo.class.getSimpleName());         // ClassDemo

        // 内部类
        p(ClassInnerDemo.class.getName());          // knowledge.api.lang.class_.ClassDemo$ClassInnerDemo
        p(ClassInnerDemo.class.getCanonicalName()); // knowledge.api.lang.class_.ClassDemo.ClassInnerDemo
        p(ClassInnerDemo.class.getSimpleName());    // ClassInnerDemo

        // 数组类
        ClassDemo[] classDemos = new ClassDemo[]{};
        p(classDemos.getClass().getName());         // [Lknowledge.api.lang.class_.ClassDemo;
        p(classDemos.getClass().getCanonicalName());// knowledge.api.lang.class_.ClassDemo[]
        p(classDemos.getClass().getSimpleName());   // ClassDemo[]
    }

    /**
     * URL	        getResource(String name)            查找具有给定名称的资源
     * InputStream	getResourceAsStream(String name)    查找具有给定名称的资源
     * <p>
     * Class.getResource 和ClassLoader.getResource 的区别：
     * https://blog.csdn.net/zhangshk_/article/details/82704010
     * https://www.cnblogs.com/doit8791/p/5851026.html
     */
    @Test
    public void getResource() {
        // Class.getResource()
        p(ClassDemo.class.getResource(""));                     // file:/C:/Users/234607/git/mavenTest/target/classes/knowledge/api/lang/class_/
        p(ClassDemo.class.getResource("/"));                    // file:/C:/Users/234607/git/mavenTest/target/test-classes/

        // ClassLoader.getResource()
        p(ClassDemo.class.getClassLoader().getResource(""));    // file:/C:/Users/234607/git/mavenTest/target/test-classes/
        p(ClassDemo.class.getClassLoader().getResource("/"));   //

        // PS：在获取资源路径时推荐使用 Class.getResource("/")
    }


    /**
     * boolean	isAssignableFrom(Class<?> cls)  判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
     * boolean	isInstance(Object obj)          判定指定的 Object 是否与此 Class 所表示的对象赋值兼容
     * <p>
     * instanceof, isInstance, isAssignableFrom 的区别：
     * https://www.cnblogs.com/exmyth/p/3164492.html
     */
    @Test
    public void is() {
        Dog dog = new Dog();

        p(dog instanceof Animal);                       // true
        p(Animal.class.isInstance(dog));                // true
        p(Animal.class.isAssignableFrom(Dog.class));    // true
        p(Dog.class.isAssignableFrom(Animal.class));    // false
    }

    /**
     * boolean	isAnnotationPresent(Class<? extends Annotation> annotationClass)
     * 如果指定类型的注释存在于此元素上，则返回 true，否则返回 false
     */
    @Test
    public void isAnnotationPresent() {
        p(Predicate.class.isAnnotationPresent(FunctionalInterface.class)); // true
    }


    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }
}

@Data
@Accessors(chain = true)
class Animal {

    public String name;
    private int age;

    static {
        System.out.println("The is a Animal.");
    }

}

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
class Dog extends Animal {

    public String dog1;
    private String dog2;

    static {
        System.out.println("The is a Dog.");
    }

}

