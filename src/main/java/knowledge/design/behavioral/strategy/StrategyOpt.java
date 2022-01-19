package knowledge.design.behavioral.strategy;

import l.demo.CompanyEnum;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 使用 java.function.* 和 lambda 优化策略模式
 * <p>
 * 策略模式优化过程：
 * 1.一个算法写一个 ConcreteStrategy
 * 2.使用'工厂'来管理 ConcreteStrategy 
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

    private final static Map<CompanyEnum, Function<Integer, Double>> CAL_MAP = new HashMap<>();

    static {
        CAL_MAP.put(CompanyEnum.SF, weight -> 10 + weight * 1.2);
        CAL_MAP.put(CompanyEnum.YTO, weight -> 8 + weight * 1.5);
        CAL_MAP.put(CompanyEnum.STO, weight -> 12 + weight * 0.8);
    }

    @Test
    public void testStrategyLambda() {
        System.out.println(CAL_MAP.get(CompanyEnum.SF).apply(10));
    }
}
