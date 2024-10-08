package knowledge.oop.interface_;

/**
 * 函数式接口 (Functional Interface)
 * <p>函数式接口就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
 * <p>JDK8 之前已有的函数式接口：
 * <pre>
 * java.lang.Runnable
 * java.lang.reflect.InvocationHandler
 * java.util.concurrent.Callable
 * java.util.Comparator
 * java.io.FileFilter
 * java.nio.file.PathMatcher
 * java.beans.PropertyChangeListener
 * java.awt.event.ActionListener
 * java.security.PrivilegedAction
 * javax.swing.event.ChangeListener
 * </pre>
 * JDK8 新增的函数式接口：java.util.function.*
 *
 * @author ljh
 * @see <a href="https://blog.csdn.net/icarusliu/article/details/79495534">函数式编程</a>
 * @since 2020/11/10 19:19
 */
public class FunctionalInterface {

    /**
     * 使用 Lambda 表达式来表示函数式接口的一个实现
     */
    public static void main(String[] args) {
        GreetingService greetingService = message -> System.out.println("Hello " + message);
        greetingService.sayMessage("World!");
    }

    // 用于编译级错误检查，当你写的接口不符合函数式接口定义的时候，编译器会报错
    @java.lang.FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }
}
