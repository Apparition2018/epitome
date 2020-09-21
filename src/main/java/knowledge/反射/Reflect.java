package knowledge.反射;

import l.demo.Demo;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射
 * 在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；
 * 这种动态获取信息以及动态调用对象方法的功能成为java语言的反射机制
 * <p>
 * Object API:
 * Class<?>	        getClass()                          返回此 Object 的运行时类
 * <p>
 * Class API:
 * static Class<?>	forName(String name[, boolean initialize, ClassLoader loader])  使用给定的类加载器，返回与带有给定字符串名的类或接口相关联的 Class 对象
 * T	            newInstance()                       创建此 Class 对象所表示的类的一个新实例
 * int	            getModifiers()                      返回此类或接口以整数编码的 Java 语言修饰符
 * String	        getName()                           以 String 的形式返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称
 * String	        getSimpleName()                     返回源代码中给出的底层类的简称
 * Method	        getDeclaredMethod(String name, Class<?>... parameterTypes)      返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法
 * Method[]	        getDeclaredMethods()                返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法
 * Field	        getDeclaredField(String name)       返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段
 * Field[]	        getDeclaredFields()                 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
 * <p>
 * AccessibleObject API:
 * void	            setAccessible(boolean flag)         将此对象的 accessible 标志设置为指示的布尔值
 * <p>
 * Method API:
 * String	        getName()                           以 String 形式返回此 Method 对象表示的方法名称
 * Object	        invoke(Object obj, Object... args)  对带有指定参数的指定对象调用由此 Method 对象表示的底层方法
 * <p>
 * Field API:
 * String	        getName()                           返回此 Field 对象表示的字段的名称
 * int	            getModifiers()                      以整数形式返回由此 Field 对象表示的字段的 Java 语言修饰符
 * <p>
 * Modifier API:
 * static boolean	isXXX(int mod)                      如果整数参数包括 XXX 修饰符，则返回 true，否则返回 false
 * static String	toString(int mod)                   返回描述指定修饰符中的访问修饰符标志的字符串
 */
public class Reflect extends Demo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {

        //********** 1.获取类 **********//
        // 1.1 Class.forName()
        // "动态"（运行期间）加载类到内存中
        Class<?> clazz = Class.forName("knowledge.反射.Reflect$Letter");

        // 1.2 XXX.class
        Class<?> clazz2 = Letter.class;

        // 1.3 xxx.getClass()
        Letter letter = new Letter();
        Class<?> clazz3 = letter.getClass();
        
        //********** 2.动态创建对象 **********//
        letter = (Letter) clazz.newInstance();

        //********** 3.Method **********//
        // 动态获取类中声明的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 改变访问限制
            method.setAccessible(true);
            // 获取方法名称
            p("mName = " + method.getName());
        }

        // 动态查找一个方法
        Method method = clazz.getDeclaredMethod("setA", String.class);
        // 动态调用方法
        method.invoke(letter, "A");
        p("letter.getA() = " + letter.getA() + "\n");


        //********** 4.Field **********//
        // 动态获取类中声明的属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
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
            if (Modifier.isStatic(mod)) {
                p("fieldName = " + field.getName());
            }
        }

        // 动态查找一个属性
        Field field = clazz.getDeclaredField("a");
        field.setAccessible(true);
        field.set(letter, "a");
        p("letter.getA() = " + letter.getA());

    }

    @Getter
    @Setter
    static class Letter {

        private final static String LETTER = "LETTER";

        @Value
        private String a;

        private String b;

        private String c;

        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        @interface Value {
            String value() default "";
        }

    }

}
