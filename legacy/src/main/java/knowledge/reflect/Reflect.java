package knowledge.reflect;

import l.demo.Animal.Cat;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;

import static l.demo.Demo.p;

/**
 * 反射
 * <pre>
 * 在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；
 * 这种动态获取信息以及动态调用对象方法的功能成为java语言的反射机制
 * </pre>
 * <p>Class API：
 * <pre>
 * {@code
 * static Class<?>	forName(String name[, boolean initialize, ClassLoader loader])  使用给定的类加载器，返回与带有给定字符串名的类或接口相关联的 Class 对象
 * T                newInstance()                                                   创建此 Class 对象所表示的类的一个新实例
 * int              getModifiers()                                                  返回此类或接口以整数编码的 Java 语言修饰符
 * String           getName()                                                       以 String 的形式返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称
 * String           getSimpleName()                                                 返回源代码中给出的底层类的简称
 * Constructor<T>   getDeclaredConstructor(Class<?>... parameterTypes)              返回一个 Constructor 对象，该对象反映此 Class 对象所表示的类或接口的指定构造方法
 * Constructor<?>[] getDeclaredConstructors()                                       返回 Constructor 对象的一个数组，这些对象反映此 Class 对象表示的类声明的所有构造方法
 * Method           getDeclaredMethod(String name, Class<?>... parameterTypes)      返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法
 * Method[]         getDeclaredMethods()                                            返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法
 * Field            getDeclaredField(String name)                                   返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段
 * Field[]          getDeclaredFields()                                             返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段}
 * </pre>
 * Object API：
 * <pre>
 * Class<?>         getClass()                          返回此 Object 的运行时类
 * </pre>
 * AccessibleObject API：
 * <pre>
 * void             setAccessible(boolean flag)         将此对象的 accessible 标志设置为指示的布尔值
 * </pre>
 * Method API：
 * <pre>
 * String           getName()                           以 String 形式返回此 Method 对象表示的方法名称
 * Object           invoke(Object obj, Object... args)  对带有指定参数的指定对象调用由此 Method 对象表示的底层方法
 * </pre>
 * Field API：
 * <pre>
 * String           getName()                           返回此 Field 对象表示的字段的名称
 * int              getModifiers()                      以整数形式返回由此 Field 对象表示的字段的 Java 语言修饰符
 * </pre>
 * Modifier API：
 * <pre>
 * static boolean   isXXX(int mod)                      如果整数参数包括 XXX 修饰符，则返回 true，否则返回 false
 * static String    toString(int mod)                   返回描述指定修饰符中的访问修饰符标志的字符串
 * </pre>
 *
 * @author ljh
 * @since 2020/11/11 19:18
 */
public class Reflect {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        //********** 1.获取类 **********//
        // 1.1 Class.forName()
        // "动态"（运行期间）加载类到内存中
        Class<?> clazz = Class.forName("l.demo.Animal$Cat");

        // 1.2 XXX.class
        Class<?> clazz2 = Cat.class;

        // 1.3 xxx.getClass()
        Cat cat = new Cat();
        Class<?> clazz3 = cat.getClass();

        //********** 2.动态创建对象 **********//
        // newInstance() 会调用无参构造器，没有无参构造器会抛出异常
        cat = (Cat) clazz.getConstructor().newInstance();

        //********** 3.Field **********//
        Field[] fields;
        // 动态获取类中声明的属性
        fields = clazz.getDeclaredFields();
        // 动态获取类中声明的属性，包括父类
        fields = clazz.getFields();
        for (Field field : fields) {
            /*
             *  PUBLIC: 1
             *  PRIVATE: 2
             *  PROTECTED: 4
             *  STATIC: 8
             *  FINAL: 16
             *  SYNCHRONIZED: 32
             *  VOLATILE: 64
             *  TRANSIENT: 128
             *  NATIVE: 256
             *  INTERFACE: 512
             *  ABSTRACT: 1024
             *  STRICT: 2048
             */
            if (Modifier.isPublic(field.getModifiers()))
                p("fieldName = " + field.getName());
        }

        // 动态查找一个属性
        Field field = clazz.getDeclaredField("foot");
        // 改变访问限制
        field.setAccessible(true);
        field.set(cat, 3);
        p("cat.getFoot() = " + cat.getAge() + StringUtils.CR);

        //********** 4.Method **********//
        Method[] methods;
        // 动态获取类中声明的方法
        methods = clazz.getDeclaredMethods();
        // 动态获取类中声明的方法，包括父类
        methods = clazz.getMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            p("methodName = " + method.getName());
        }

        // 动态查找一个方法
        Method method = clazz.getDeclaredMethod("setFoot", int.class);
        // 动态调用方法
        method.invoke(cat, 3);
        p("cat.getFoot() = " + cat.getAge());

        // 遍历方法的参数
        for (Parameter parameter : method.getParameters()) {
            p(parameter.getName());
        }
    }
}
