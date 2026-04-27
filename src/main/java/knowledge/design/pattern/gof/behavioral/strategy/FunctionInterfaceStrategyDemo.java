package knowledge.design.pattern.gof.behavioral.strategy;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.function.Function;

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
 * @author ljh
 * @see <a href="https://mp.weixin.qq.com/s/hkypvNBkRjPM6HM51_jW9g">优化策略模式</a>
 * @since 2026/4/27 12:43
 */
public class FunctionInterfaceStrategyDemo {

    private final static EnumMap<DiscountEnum, Function<Double, Double>> STRATEGY_MAP =
        new EnumMap<>(DiscountEnum.class);

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
