package knowledge.design.pattern.gof.behavioral.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.EnumMap;
import java.util.function.Function;

/**
 * 策略模式：定义一系列算法，并将每一个算法封装到具有共同接口的类中，使得它们可以互相替换
 * 使用场景：多行为、多算法、if-else、switch-case
 * 使用实例：
 * 1.Functional Interface {@link knowledge.oop.interface_.FunctionalInterface}
 * 2.{@link javax.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain)}
 * 3.{@link javax.servlet.http.HttpServlet} 的 service(req, resp), doXXX(req, resp)
 * 4.{@link java.util.concurrent.ThreadPoolExecutor} 的构造器参数 RejectedExecutionHandler 的四个实现：
 * -    https://blog.csdn.net/yangsen159/article/details/103146038
 * 5.{@link ResourceLoader#getResource(String)} 和 {@link Resource}
 * <p>
 * 角色:
 * 上下文 Context：接收 Strategy 的引用
 * 抽象策略 Strategy：声明 Context 执行策略的方法
 * 具体策略 ConcreteStrategy
 * <p>
 * 优点：符合开闭原则
 * 缺点：使用者必须事先知道有哪些策略和策略之间的不同才能选择策略
 * <p>
 * Strategy：https://refactoringguru.cn/design-patterns/strategy
 * Java设计模式：http://c.biancheng.net/view/1378.html
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class StrategyDemo {

    /**
     * 电影票折扣
     * https://blog.csdn.net/LoveLion/article/details/7819216
     */
    @Test
    public void testStrategy() {
        MovieTicket ticket = new MovieTicket(60D, new NormalDiscount());
        System.out.println("原始价格：" + ticket.getPrice() + "\n");

        ticket.setDiscount(new ChildrenDiscount());
        System.out.println("折扣价格：" + ticket.getPrice());
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

    /**
     * 使用 java.function.* 和 lambda 优化策略模式
     * <p>
     * 策略模式优化过程：
     * 1.一个算法写一个 ConcreteStrategy
     * 2.使用工厂来管理 ConcreteStrategy
     * 3.用匿名内部类代替 ConcreteStrategy
     * 4.用函数式接口代替匿名内部类
     * 5.用 lambda 简化函数式接口
     * <p>
     * 优化策略模式：https://mp.weixin.qq.com/s/hkypvNBkRjPM6HM51_jW9g
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
            System.out.println("折扣价格：" + STRATEGY_MAP.get(DiscountEnum.STUDENT).apply(price) + "\n");
            System.out.println("折扣价格：" + STRATEGY_MAP.get(DiscountEnum.CHILDREN).apply(price));
        }

        enum DiscountEnum {
            NORMAL, STUDENT, CHILDREN
        }
    }
}
