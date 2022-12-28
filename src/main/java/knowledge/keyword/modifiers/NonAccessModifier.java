package knowledge.keyword.modifiers;

/**
 * 非访问修饰符：static, final, abstract, synchronized, transient, volatile
 *
 * @author ljh
 * @since 2020/11/18 19:37
 */
public class NonAccessModifier {

    /**
     * static
     * <pre>
     * 1 静态变量：
     *   1.1 无论一个类实例化多少对象，它的静态变量只有一份拷贝
     *   1.2 局部变量不能声明为 static 变量
     *   1.3 通过 ClassName.VariableName 访问
     * 2 静态方法：
     *   2.1 静态方法不能非静态方法和变量
     *   2.2 静态方法从参数列表得到数据，然后计算这些数据 ?
     *   2.3 通过 ClassName.MethodName 访问
     * </pre>
     *
     * @see <a href="https://zhuanlan.zhihu.com/p/112001357">为什么静态方法不能调用非静态方法和变量？</a>
     */
    private static int numInstances = 0;

    private static void addInstance() {
        numInstances++;
    }

    /**
     * final
     * <pre>
     * 1 final 常量：
     *   1.1 一旦赋值后，不能被重新赋值
     *   1.2 被 final 修饰的实例变量必须显式指定初始值
     *   1.3 通常和 static 一起使用来创建类常量
     *       不允许任何魔法值（即未经预先定义的常量）直接出现在代码中（阿里编程规约）
     * 2 final 方法：可以被子类继承，不能被子类重写
     * 3 final 类：不能被继承
     * </pre>
     * 阿里编程规约：下列情况使用 final 关键字
     * <pre>
     * 1）不允许被继承的类，如：String 类。
     * 2）不允许修改引用的域对象，如：POJO 类的域变量。
     * 3）不允许被覆写的方法，如：POJO 类的 setter 方法。
     * 4）不允许运行过程中重新赋值的局部变量。
     * 5）避免上下文重复使用一个变量，使用 final 关键字可以强制重新定义一个变量，方便更好地进行重构。
     * </pre>
     *
     * @see <a href="https://www.cnblogs.com/wangymd/p/13213265.html">JDK1.8 常量池整理</a>
     * @see <a href="https://blog.csdn.net/qq_26947195/article/details/79505553">Java 常量池理解和经典总结</a>
     */
    final int value = 10;

    /**
     * transient
     * <p>序列化的对象包含被 transient 修饰的实例变量时，java 虚拟机 (JVM) 跳过该变量
     */
    public transient int limit = 55;

    /**
     * synchronized
     * <p>synchronized 声明的方法同一时间只能被一个线程访问
     */
    public synchronized void synchronizedMethod() {
        System.out.println("synchronized");
    }


    /**
     * abstract
     * <pre>
     * 1 抽象类：
     *   1.1 一个类不能同时被 abstract 和 final 修饰
     *   1.2 如果一个类包含抽象方法，那么该类一定要声明为抽象类
     *   1.3 抽象类中不一定包含抽象方法，但是有抽象方法的类必定是抽象类
     * 2 抽象方法：
     *   2.1 抽象方法具体实现由子类提供
     *   2.2 抽象方法不能被声明成 final 和 static
     *   2.3 任何继承抽象类的子类必须实现父类的所有抽象方法，除非该子类也是抽象类
     *   2.4 如果一个类包含若干个抽象方法，那么该类必须声明为抽象类
     * </pre>
     */
    static abstract class Caravan {
        private double price;
        private String model;
        private String year;

        protected abstract void goFast();

        protected abstract void changeColor();
    }
}
