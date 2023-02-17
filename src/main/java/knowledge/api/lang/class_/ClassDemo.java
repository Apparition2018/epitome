package knowledge.api.lang.class_;

import l.demo.Animal;
import l.demo.Animal.Chicken;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static l.demo.Demo.p;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Class.html">Class</a>
 * <pre>
 * Class 类的实例表示正在运行的 Java 应用程序中的类和接口。
 * 枚举是一种类，注释是一种接口。
 * 每个数组属于被映射为 Class 对象的一个类，所有具有相同元素类型和维数的数组都共享该 Class 对象。
 * 基本的 Java 类型（boolean、byte、char、short、int、long、float 和 double）和关键字 void 也表示为 Class 对象。
 * </pre>
 * <pre>
 * Class<?>         getEnclosingClass()         返回底层类的立即封闭类
 * Class<?>         getComponentType()          返回表示数组组件类型的 Class
 * ClassLoader      getClassLoader()            返回该类的类加载器
 * Class<?>[]       getInterfaces()             确定此对象所表示的类或接口实现的接口
 * Package          getPackage()                返回该类的包
 * Class<? super T> getSuperclass()             返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class
 * T                newInstance()               创建此 Class 对象所表示的类的一个新实例
 *
 * boolean          isAnnotation()              注释类型
 * boolean          isAnnotationPresent(Class<? extends Annotation> annotationClass)
 * boolean          isAnonymousClass()          匿名类
 * boolean          isArray()                   数组类型
 * boolean          isEnum()                    枚举
 * boolean          isInterface()               接口
 * boolean          isLocalClass()              本地类
 * boolean          isMemberClass()             成员类
 * boolean          isPrimitive()               基本类型
 * boolean          isSynthetic()               符合类
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ClassDemo {

    private static class ClassInnerDemo {
    }

    /**
     * static Class<?>	forName(String name[, boolean initialize, ClassLoader loader])
     * <p>使用给定的类加载器，返回与带有给定字符串名的类或接口相关联的 Class 对象
     * <p>forName() 只是把一个类加载到内存中，之所以会初始化 static 代码，那是由类加载机制所决定的
     */
    @Test
    public void forName() throws ClassNotFoundException {
        // Class.forName("Foo"）
        // 等同于
        // Class.forName("Foo", true, this.getClass().getClassLoader())
        // true 表示初始化，执行 static 静态块

        Class.forName("l.demo.Animal", true, this.getClass().getClassLoader());
        // The is a Animal.
    }

    /**
     * <pre>
     * Field[]      getDeclaredFields()     返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
     * Field[]      getFields()             返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口的所有可访问公共字段
     * </pre>
     * 类似的还有 getConstructors() 和 getDeclaredConstructors(), getMethods() 和 getDeclaredMethods()
     */
    @Test
    public void getFields() {
        // getDeclaredFields() 获取某个类的所有字段，不包括父类
        Field[] fields1 = Chicken.class.getDeclaredFields();
        p(fields1);
        // [public int l.demo.Animal$Chicken.age, private int l.demo.Animal$Chicken.wing]

        // getFields() 获取某个类的 public 字段，包括父类
        Field[] fields2 = Chicken.class.getFields();
        p(fields2);
        // [public int l.demo.Animal$Chicken.age, public java.lang.String l.demo.Animal.name]
    }

    /**
     * <pre>
     * String       getName()               返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称
     * String       getCanonicalName()      返回 JLS (Java Language Specification) 中所定义的底层类的规范化名称
     * String       getSimpleName()         返回源代码中给出的底层类的简称
     * </pre>
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
     * <pre>
     * URL          getResource(String name)            查找具有给定名称的资源
     * InputStream  getResourceAsStream(String name)    查找具有给定名称的资源
     * </pre>
     *
     * @see <a href="https://blog.csdn.net/zhangshk_/article/details/82704010">Class.getResource vs ClassLoader.getResource</a>
     * @see <a href="https://www.cnblogs.com/doit8791/p/5851026.html">Class.getResource vs ClassLoader.getResource</a>
     */
    @Test
    public void getResource() {
        // Class.getResource()
        p(ClassDemo.class.getResource(""));                     // file:/C:/Users/234607/git/epitome/target/classes/knowledge/api/lang/class_/
        p(ClassDemo.class.getResource("/"));                    // file:/C:/Users/234607/git/epitome/target/test-classes/

        // ClassLoader.getResource()
        p(ClassDemo.class.getClassLoader().getResource(""));    // file:/C:/Users/234607/git/epitome/target/test-classes/
        p(ClassDemo.class.getClassLoader().getResource("/"));   //

        // PS：在获取资源路径时推荐使用 Class.getResource("/")
    }


    /**
     * <pre>
     * boolean      isAssignableFrom(Class<?> cls)      判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是其超类或超接口
     * boolean      isInstance(Object obj)              判定指定的 Object 是否与此 Class 所表示的对象赋值兼容
     * </pre>
     *
     * @see <a href="https://www.cnblogs.com/exmyth/p/3164492.html">instanceof, isInstance, isAssignableFrom</a>
     */
    @Test
    public void is() {
        Chicken chicken = new Chicken();

        p(chicken instanceof Animal);                       // true
        p(Animal.class.isInstance(chicken));                // true
        p(Animal.class.isAssignableFrom(Chicken.class));    // true
        p(Chicken.class.isAssignableFrom(Animal.class));    // false
    }

    /**
     * boolean	isAnnotationPresent(Class<? extends Annotation> annotationClass)
     * <p>如果指定类型的注释存在于此元素上，则返回 true，否则返回 false
     */
    @Test
    public void isAnnotationPresent() {
        p(Predicate.class.isAnnotationPresent(FunctionalInterface.class)); // true
    }
}
