package knowledge.oop.interface_;

import org.junit.Test;

/**
 * 函数式接口 (Functional Interface)
 * <p>
 * JDK8 之前已有的函数式接口：
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
 * <p>
 * JDK8 新增的函数式接口：
 * java.util.function
 * <p>
 * https://blog.csdn.net/icarusliu/article/details/79495534
 */
public class FunctionalInterface {

    /**
     * 使用 Lambda 表达式来表示函数式接口的一个实现
     */
    @Test
    public void testFunctionalInterface() {
        GreetingService greetingService = message -> System.out.println("Hello " + message);
        greetingService.sayMessage("World!");
    }

    /**
     * 函数式接口就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
     */
    @java.lang.FunctionalInterface
    // 用于编译级错误检查，当你写的接口不符合函数式接口定义的时候，编译器会报错
    interface GreetingService {
        void sayMessage(String message);
    }

}
