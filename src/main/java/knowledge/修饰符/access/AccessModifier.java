package knowledge.修饰符.access;

/**
 * 访问修饰符: private, default, protected, public
 * <p>
 * 修饰词      当前类 同一包内    子孙类（同一包）    子孙类（不同包）    其它包
 * public       Y       Y           Y               Y               Y
 * protected    Y       Y           Y               Y/N             N
 * default      Y       Y           Y               N               N
 * private      Y       N           N               N               N
 * <p>
 * 访问控制和继承：
 * 1.父类中声明为 public 的方法在子类中也必须为 public
 * 2.父类中声明为 protected 的方法在子类中要么声明为 protected，要么声明为 public，不能声明为 private
 * 3.父类中声明为 private 的方法，不能够被继承
 */
public class AccessModifier {

    /**
     * private
     * <p>
     * 类和接口不能声明为 private
     * 被 private 修饰的方法、变量和构造方法只能被所属类访问
     * 被 private 修饰的变量只能通过 public 修饰的 getter 方法被外部类访问
     */
    private int x = 0;

    /**
     * protected
     * http://www.runoob.com/w3cnote/java-protected-keyword-detailed-explanation.html
     * <p>
     * 类和接口不能声明为 protected
     * 子类与基类在同一包中：被声明为 protected 的变量、方法和构造器能被同一个包中的任何其他类访问
     * 子类与基类不在同一包中：那么在子类中，子类实例可以访问其从基类继承而来的 protected 方法，而不能访问基类实例的 protected 方法
     */
    protected int y = 1;



    /**
     * default
     * <p>
     * 使用默认访问修饰符声明的变量和方法，对同一个包内的类是可见的
     */
    int z = 2;

    public int sum() {
        return x + y + z;
    }

}