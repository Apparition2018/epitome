package knowledge.design.behavioral.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 策略模式：定义一系列算法，并将每一个算法封装到具有共同接口的类中，使得它们可以互相替换
 * 使用场景：多行为、多算法、if-else、switch-case
 * 使用实例：
 * 1.{@link java.util.Comparator#compare(Object, Object)}
 * 2.{@link javax.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain)}
 * 3.{@link javax.servlet.http.HttpServlet#service(ServletRequest, ServletResponse)}
 * 4.{@link javax.servlet.http.HttpServlet} 的 doXXX
 * 5.{@link java.util.concurrent.ThreadPoolExecutor} 的构造器参数 RejectedExecutionHandler 的四个实现：
 * -    https://blog.csdn.net/yangsen159/article/details/103146038
 * 6.{@link ResourceLoader#getResource(String)} 和 {@link Resource}
 * <p>
 * 角色:
 * 上下文 Context：接收 Strategy 的引用
 * 抽象策略 Strategy：声明 Context 执行策略的方法
 * 具体策略 ConcreteStrategy
 * <p>
 * 优点：符合开闭原则
 * 缺点：使用者必须事先知道有哪些策略和策略之间的不同才能选择策略
 * 优化：{@link StrategyOpt}
 * <p>
 * Strategy：https://refactoringguru.cn/design-patterns/strategy
 * Java设计模式：http://c.biancheng.net/view/1378.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/strategy-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class StrategyDemo {

    /**
     * 电影票折扣
     * https://blog.csdn.net/LoveLion/article/details/7819216
     */
    @Test
    public void testStrategy() {
        MovieTicket ticker = new MovieTicket(60.0, new NormalDiscount());
        System.out.println("原始价格：" + ticker.getPrice() + "\n");

        ticker.setDiscount(new ChildrenDiscount());
        System.out.println("折扣价格：" + ticker.getPrice());
    }

    /**
     * Context
     * 电影票
     */
    @Data
    @AllArgsConstructor
    static class MovieTicket {
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
    static class NormalDiscount implements Discount {
        public double calculate(double price) {
            System.out.println("普通票：");
            return price;
        }
    }

    /**
     * ConcreteStrategy
     * 学生折扣策略
     */
    static class StudentDiscount implements Discount {
        public double calculate(double price) {
            System.out.println("学生票：");
            return price * 0.8;
        }
    }

    /**
     * ConcreteStrategy
     * 儿童折扣策略
     */
    static class ChildrenDiscount implements Discount {
        public double calculate(double price) {
            System.out.println("儿童票：");
            return price - 10 >= 0 ? price - 10 : 0;
        }
    }
}