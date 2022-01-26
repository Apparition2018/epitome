package knowledge.design.behavioral.strategy;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
 *
 * @author ljh
 * created on 2022/1/7 10:14
 */
public class StrategyOpt {

    private final static Map<DiscountEnum, Function<Double, Double>> STRATEGY_MAP = new HashMap<>();

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
        double price = 60.0;
        System.out.println("折扣价格：" + STRATEGY_MAP.get(DiscountEnum.STUDENT).apply(price) + "\n");
        System.out.println("折扣价格：" + STRATEGY_MAP.get(DiscountEnum.CHILDREN).apply(price));
    }

    enum DiscountEnum {
        NORMAL, STUDENT, CHILDREN
    }
}
