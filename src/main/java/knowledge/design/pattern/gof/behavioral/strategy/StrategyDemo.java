package knowledge.design.pattern.gof.behavioral.strategy;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 策略模式：定义一系列算法，并将每一个算法封装到具有共同接口的类中，使得它们可以互相替换
 * <p>使用场景：多行为、多算法、if-else、switch-case
 * <p>使用实例：
 * <pre>
 * 1 JDK：
 *  ① {@link FunctionInterfaceStrategyDemo @FunctionalInterface}
 *  ② {@link Filter#doFilter(ServletRequest, ServletResponse, FilterChain)}
 *  ③ {@link HttpServlet} 的 service(req, resp), doXXX(req, resp)
 *  ④ {@link ThreadPoolExecutor} 的构造器参数 RejectedExecutionHandler 的四个实现：
 *      <a href="https://blog.csdn.net/yangsen159/article/details/103146038">策略模式在JDK中的应用</a>
 * 2 Spring：{@link ResourceLoader#getResource(String)} 和 {@link Resource}
 * 3 {@link StrategyFactoryDemo 策略模式 + 简单工厂}
 * </pre>
 * 角色:
 * <pre>
 * 上下文 Context：接收 Strategy 的引用
 * 抽象策略 Strategy：声明 Context 执行策略的方法
 * 具体策略 ConcreteStrategy
 * </pre>
 * 优点：符合开闭原则<br/>
 * 缺点：使用者必须事先知道有哪些策略和策略之间的不同才能选择策略
 * <p>
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/strategy">Strategy</a>
 * @see <a href="https://c.biancheng.net/view/nlsg9m5.html">Java设计模式</a>
 * @since 2020/9/26 2:51
 */
public class StrategyDemo {

    /** <a href="https://blog.csdn.net/LoveLion/article/details/7819216">电影票折扣</a> */
    @Test
    public void testStrategy() {
        MovieTicket ticket = new MovieTicket(60D, new NormalDiscount());
        System.out.println("原始价格：" + ticket.getPrice() + StringUtils.CR);

        ticket.setDiscount(new ChildrenDiscount());
        System.out.println("折扣价格：" + ticket.getPrice());
    }
}

/**
 * Context
 * 电影票
 */
@Data
@AllArgsConstructor
class MovieTicket {
    private double price;
    private Discount discount;

    public double getPrice() {
        return discount.calculate(this.price);
    }
}

/**
 * Strategy
 * 折扣策略
 */
interface Discount {
    double calculate(double price);
}

/**
 * ConcreteStrategy
 * 无折扣策略
 */
class NormalDiscount implements Discount {
    public double calculate(double price) {
        System.out.println("普通票：");
        return price;
    }
}

/**
 * ConcreteStrategy
 * 学生折扣策略
 */
class StudentDiscount implements Discount {
    public double calculate(double price) {
        System.out.println("学生票：");
        return price * 0.8;
    }
}

/**
 * ConcreteStrategy
 * 儿童折扣策略
 */
class ChildrenDiscount implements Discount {
    public double calculate(double price) {
        System.out.println("儿童票：");
        return price - 10 >= 0 ? price - 10 : 0;
    }
}
