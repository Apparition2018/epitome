package knowledge.design.pattern.other.Idiom;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 分派
 * Double Dispatch：https://java-design-patterns.com/patterns/double-dispatch/
 * 伪动态双分派：http://c.biancheng.net/view/8499.html
 *
 * @author ljh
 * created on 2022/2/2 2:12
 */
public class DispatchDemo {

    @Test
    public void testDispatch() {
        // 类型
        List<Integer> list = new ArrayList<>();
        System.out.println("list 的静态类型(Static Type)是 List");
        System.out.println("list 的实际类型(Actual Type)是 ArrayList");

        // 静态分派：编译期确定方法执行版本
        // 动态分派：运行时确定方法执行版本
        // 单分派：根据实际引用类型确定方法执行版本
        // 多分派：根据多个参数类型和个数确定方法执行版本
        // Java 是静态多分派、动态单分派语言
        // Visitor 进行两次动态单分派实现伪动态双分派
    }
}
