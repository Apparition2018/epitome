package knowledge.design.behavioral.visitor;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 分派
 * http://c.biancheng.net/view/8499.html
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

        // 静态多分派：按照多个变量的静态类型进行分派，从而确定方法的执行版本
        // 动态单分派：按照单个变量的实际类型进行分派，从而确定方法的执行版本
    }
}
