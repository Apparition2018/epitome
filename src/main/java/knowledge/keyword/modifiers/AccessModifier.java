package knowledge.keyword.modifiers;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * 访问修饰符
 * <pre>
 * 修饰词      当前类 同一包内    子孙类（同一包）    子孙类（不同包）    其它包
 * public       Y       Y           Y               Y               Y
 * protected    Y       Y           Y               Y/N             N
 * default      Y       Y           Y               N               N
 * private      Y       N           N               N               N
 * </pre>
 * 访问控制和继承：
 * <pre>
 * 1 父类中声明为 public 的方法在子类中也必须为 public
 * 2 父类中声明为 protected 的方法在子类中要么声明为 protected，要么声明为 public，不能声明为 private
 * 3 父类中声明为 private 的方法，不能够被继承
 * </pre>
 * 阿里编程规约：类成员与方法访问控制从严，利于模块解耦、重构
 * <pre>
 * 1 如果不允许外部直接通过 new 来创建对象，那么构造方法必须是 private。
 * 2 工具类不允许有 public 或 default 构造方法。
 * 3 类非 static 成员变量并且与子类共享，必须是 protected。
 * 4 类非 static 成员变量并且仅在本类使用，必须是 private。
 * 5 类 static 成员变量如果仅在本类使用，必须是 private。
 * 6 若是 static 成员变量，考虑是否为 final。
 * 7 类成员方法只供类内部调用，必须是 private。
 * 8 类成员方法只对继承类公开，那么限制为 protected。
 * </pre>
 *
 * @author ljh
 * @since 2020/11/18 19:37
 */
public class AccessModifier {

    /**
     * private
     * <pre>
     * 类和接口不能声明为 private
     * 被 private 修饰的方法、变量和构造方法只能被所属类访问
     * 被 private 修饰的变量只能通过 public 修饰的 getter 方法被外部类访问
     * </pre>
     */
    private int x = 0;

    /**
     * protected
     * <pre>
     * 类和接口不能声明为 protected
     * 子类与基类在同一包中：被声明为 protected 的变量、方法和构造器能被同一个包中的任何其他类访问
     * 子类与基类不在同一包中：那么在子类中，子类实例可以访问其从基类继承而来的 protected 方法，而不能访问基类实例的 protected 方法
     * </pre>
     *
     * @see <a href="http://www.runoob.com/w3cnote/java-protected-keyword-detailed-explanation.html">protected 详解</a>
     */
    protected int y = 1;


    /**
     * default
     * <p>使用默认访问修饰符声明的变量和方法，对同一个包内的类是可见的
     */
    int z = 2;

    public int sum() {
        return x + y + z;
    }

    /**
     * <a href="https://www.imooc.com/video/18992">基于嵌套的访问控制</a>：如果在一个类中嵌套了多个子类，那么子类中可以访问彼此的私有成员
     *
     * @see <a href="https://openjdk.org/jeps/181">JDK11 JEP 181: Nest-Based Access Control</a>
     */
    @Test
    public void testNestBasedAccessControl() throws NoSuchFieldException, IllegalAccessException {
        X x = new X();
        x.accessY();
    }

    private static class X {
        private void accessY() throws NoSuchFieldException, IllegalAccessException {
            Y y = new Y();
            y.y = 1;

            Field field = Y.class.getDeclaredField("y");
            // JDK11 之前需要 setAccessible(true)，否者报 IllegalAccessException: Class com.ljh.Test$X can not access a member of class com.ljh.Test$Y with modifiers "private"
            // field.setAccessible(true);
            field.setInt(y, 2);

            System.out.println(y.y);
        }
    }

    private static class Y {
        private int y;
    }
}
