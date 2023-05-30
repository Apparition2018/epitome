package knowledge.design.pattern.gof.behavioral.strategy;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import knowledge.oop.interface_.FunctionalInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.EnumMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

/**
 * 策略模式：定义一系列算法，并将每一个算法封装到具有共同接口的类中，使得它们可以互相替换
 * <p>使用场景：多行为、多算法、if-else、switch-case
 * <p>使用实例：
 * <pre>
 * 1 Functional Interface {@link FunctionalInterface}
 * 2 {@link Filter#doFilter(ServletRequest, ServletResponse, FilterChain)}
 * 3 {@link HttpServlet} 的 service(req, resp), doXXX(req, resp)
 * 4 {@link ThreadPoolExecutor} 的构造器参数 RejectedExecutionHandler 的四个实现：
 *      <a href="https://blog.csdn.net/yangsen159/article/details/103146038">策略模式在JDK中的应用</a>
 * 5 {@link ResourceLoader#getResource(String)} 和 {@link Resource}
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
 * 参考：
 * <pre>
 * <a href="https://refactoringguru.cn/design-patterns/strategy">Strategy</a>
 * <a href="http://c.biancheng.net/view/1378.html">Java设计模式</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class StrategyDemo {

    /**
     * <a href="https://blog.csdn.net/LoveLion/article/details/7819216">电影票折扣</a>
     */
    @Test
    public void testStrategy() {
        MovieTicket ticket = new MovieTicket(60D, new NormalDiscount());
        System.out.println("原始价格：" + ticket.getPrice() + StringUtils.CR);

        ticket.setDiscount(new ChildrenDiscount());
        System.out.println("折扣价格：" + ticket.getPrice());
    }

    /**
     * Context
     * 电影票
     */
    @Data
    @AllArgsConstructor
    private static class MovieTicket {
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
    private static class NormalDiscount implements Discount {
        public double calculate(double price) {
            System.out.println("普通票：");
            return price;
        }
    }

    /**
     * ConcreteStrategy
     * 学生折扣策略
     */
    private static class StudentDiscount implements Discount {
        public double calculate(double price) {
            System.out.println("学生票：");
            return price * 0.8;
        }
    }

    /**
     * ConcreteStrategy
     * 儿童折扣策略
     */
    private static class ChildrenDiscount implements Discount {
        public double calculate(double price) {
            System.out.println("儿童票：");
            return price - 10 >= 0 ? price - 10 : 0;
        }
    }

    /**
     * 使用 java.function.* 和 lambda 优化策略模式
     * <p>策略模式优化过程：
     * <pre>
     * 1 一个算法写一个 ConcreteStrategy
     * 2 使用工厂来管理 ConcreteStrategy
     * 3 用匿名内部类代替 ConcreteStrategy
     * 4 用函数式接口代替匿名内部类
     * 5 用 lambda 简化函数式接口
     * </pre>
     *
     * @see <a href="https://mp.weixin.qq.com/s/hkypvNBkRjPM6HM51_jW9g">优化策略模式</a>
     */
    static class FunctionInterfaceStrategyDemo {
        private final static EnumMap<DiscountEnum, Function<Double, Double>> STRATEGY_MAP = new EnumMap<>(DiscountEnum.class);

        static {
            STRATEGY_MAP.put(DiscountEnum.NORMAL, price -> {
                System.out.println("普通票：");
                return price;
            });
            STRATEGY_MAP.put(DiscountEnum.STUDENT, price -> {
                System.out.println("学生票：");
                return price * 0.8;
            });
            STRATEGY_MAP.put(DiscountEnum.CHILDREN, price -> {
                System.out.println("儿童票：");
                return price - 10 >= 0 ? price - 10 : 0;
            });
        }

        @Test
        public void testStrategyLambda() {
            double price = 60D;
            System.out.println("折扣价格：" + STRATEGY_MAP.get(DiscountEnum.STUDENT).apply(price) + StringUtils.CR);
            System.out.println("折扣价格：" + STRATEGY_MAP.get(DiscountEnum.CHILDREN).apply(price));
        }

        enum DiscountEnum {
            NORMAL, STUDENT, CHILDREN
        }
    }
}
