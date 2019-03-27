package knowledge.反射;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射
 * <p>
 * 在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；
 * 这种动态获取信息以及动态调用对象方法的功能成为java语言的反射机制
 */
public class Reflect {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {

        //*** 获取类 ***//
        Class<?> clazz = Class.forName("knowledge.反射.Letter"); // "动态"（运行期间）加载类到内存中

        Class<?> clazz2 = Letter.class;

        Letter letter = new Letter();
        Class<?> clazz3 = letter.getClass();


        //*** 动态创建对象 ***//
        letter = (Letter) clazz.newInstance();


        //*** Method ***//
        // 动态获取类中声明的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 改变访问限制
            method.setAccessible(true);
            // 获取方法名称
            String mName = method.getName();
            System.out.println("mName = " + mName);
        }

        // 动态查找一个方法
        Method method = clazz.getDeclaredMethod("setA", String.class);
        // 动态调用方法
        method.invoke(letter, "A");
        System.out.println("letter.getA() = " + letter.getA());


        //*** Field ***//
        // 动态获取类中声明的属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers(); // 返回int类型值表示该字段的修饰符
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
            if (Modifier.isStatic(mod)) { // 判定给定的 mod 参数是否是 static 修饰符
                System.out.println("fieldName = " + field.getName());
            } else {
                field.setAccessible(true);
            }
        }

        // 动态查找一个属性
        Field field = clazz.getDeclaredField("a");
        field.set(letter, "a");
        System.out.println("letter.getA() = " + letter.getA());

    }

}
