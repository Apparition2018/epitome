package knowledge.类和接口;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 函数式接口 (Functional Interface)
 * <p>
 * Java8 之前已有的函数式接口：
 * java.lang.Runnable
 * java.util.concurrent.Callable
 * java.security.PrivilegedAction
 * java.util.Comparator
 * java.io.FileFilter
 * java.nio.file.PathMatcher
 * java.lang.reflect.InvocationHandler
 * java.beans.PropertyChangeListener
 * java.awt.event.ActionListener
 * javax.swing.event.ChangeListener
 * <p>
 * Java8 新增的函数式接口：
 * java.util.function
 * 它包含了很多类，用来支持 Java 的函数式编程
 * <p>
 * https://blog.csdn.net/icarusliu/article/details/79495534
 */
public class FunctionalInterfaceDemo {

    // 使用 Lambda 表达式来表示函数式接口的一个实现
    @Test
    public void test() {
        GreetingService greetingService = message -> System.out.println("Hello " + message);
        greetingService.sayMessage("World!");
    }

    /**
     * Predicate <T>    接受一个输入参数 T，返回一个布尔值结果。
     * 该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑（比如：与，或，非）。
     * 该接口用于测试对象是 true 或 false。
     */
    @Test
    public void predicate() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Lambda 允许把函数作为一个方法的参数
        // 函数式接口可以被隐式转换为 lambda 表达式
        System.out.println("输出所有数据:");
        eval(list, n -> true);

        System.out.println("输出所有偶数:");
        eval(list, n -> n % 2 == 0);

        System.out.println("输出大于 3 的所有数字:");
        eval(list, n -> n > 3);
    }

    private void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

}

/**
 * 函数式接口就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
 */
@FunctionalInterface
// 用于编译级错误检查，当你写的接口不符合函数式接口定义的时候，编译器会报错
interface GreetingService {
    void sayMessage(String message);
}