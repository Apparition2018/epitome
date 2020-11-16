package knowledge.modifires.nonaccess;

/**
 * 非访问修饰符：static, final, abstract, synchronized, transient, volatile
 */
public class NonAccessModifier {

    /**
     * static
     * <p>
     * 静态变量：
     * 1.无论一个类实例化多少对象，它的静态变量只有一份拷贝
     * 2.局部变量不能声明为 static 变量
     * 3.通过 ClassName.VariableName 访问
     * <p>
     * 静态方法：
     * 1.静态方法不能使用类的非静态变量
     * 2.静态方法从参数列表得到数据，然后计算这些数据 ?
     * 3.通过 ClassName.MethodName 访问
     */
    private static int numInstances = 0;

    private static void addInstance() {
        numInstances++;
    }

    /**
     * final
     * <p>
     * final 常量：
     * 1.一旦赋值后，不能被重新赋值
     * 2.被 final 修饰的实例变量必须显式指定初始值
     * 3.通常和 static 一起使用来创建类常量
     * <p>
     * final 方法：
     * 可以被子类继承，不能被子类重写
     * <p>
     * final 类：
     * 不能被继承
     */
    final int value = 10;

    /**
     * transient
     * <p>
     * 序列化的对象包含被 transient 修饰的实例变量时，java 虚拟机 (JVM) 跳过该变量
     */
    public transient int limit = 55; // 不会持久化

    /**
     * synchronized
     * <p>
     * synchronized 声明的方法同一时间只能被一个线程访问。
     */
    public synchronized void synchronizedMethod() {
        System.out.println("synchronized");
    }


    /**
     * abstract
     * <p>
     * 抽象类：
     * 1.一个类不能同时被 abstract 和 final 修饰
     * 2.如果一个类包含抽象方法，那么该类一定要声明为抽象类
     * 3.抽象类中不一定包含抽象方法，但是有抽象方法的类必定是抽象类
     * <p>
     * 抽象方法：
     * 1.抽象方法具体实现由子类提供
     * 2.抽象方法不能被声明成 final 和 static
     * 3.任何继承抽象类的子类必须实现父类的所有抽象方法，除非该子类也是抽象类
     * 4.如果一个类包含若干个抽象方法，那么该类必须声明为抽象类
     */
    abstract class Caravan {
        private double price;
        private String model;
        private String year;

        public abstract void goFast();

        public abstract void changeColor();
    }

}