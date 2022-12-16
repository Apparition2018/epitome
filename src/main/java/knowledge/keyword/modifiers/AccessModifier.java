package knowledge.keyword.modifiers;

/**
 * 访问修饰符
 * <pre>
 * 修饰词      当前类 同一包内    子孙类（同一包）    子孙类（不同包）    其它包
 * public       Y       Y           Y               Y               Y
 * protected    Y       Y           Y               Y/N             N
 * default      Y       Y           Y               N               N
 * private      Y       N           N               N               N
 * </pre>
 * <p>访问控制和继承：
 * <ol>
 *  <li>父类中声明为 public 的方法在子类中也必须为 public</li>
 *  <li>父类中声明为 protected 的方法在子类中要么声明为 protected，要么声明为 public，不能声明为 private</li>
 *  <li>父类中声明为 private 的方法，不能够被继承</li>
 * </ol>
 * <p>阿里编程规约：类成员与方法访问控制从严，利于模块解耦、重构
 * <ol>
 *  <li>如果不允许外部直接通过 new 来创建对象，那么构造方法必须是 private。</li>
 *  <li>工具类不允许有 public 或 default 构造方法。</li>
 *  <li>类非 static 成员变量并且与子类共享，必须是 protected。</li>
 *  <li>类非 static 成员变量并且仅在本类使用，必须是 private。</li>
 *  <li>类 static 成员变量如果仅在本类使用，必须是 private。</li>
 *  <li>若是 static 成员变量，考虑是否为 final。</li>
 *  <li>类成员方法只供类内部调用，必须是 private。</li>
 *  <li>类成员方法只对继承类公开，那么限制为 protected。</li>
 * </ol>
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
}
