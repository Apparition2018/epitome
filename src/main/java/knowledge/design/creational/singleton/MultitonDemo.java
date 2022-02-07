package knowledge.design.creational.singleton;

import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 多例模式：确保一个类只有有限数量的实例，并提供对它们的全局访问点
 * https://java-design-patterns.com/patterns/multiton/
 *
 * @author ljh
 * created on 2022/2/8 1:13
 */
public class MultitonDemo {

    @Test
    public void testMultiton() {
        System.out.println(Fruit.getFruit(FruitEnum.APPLE));
    }

    enum FruitEnum {
        APPLE, BANANA
    }

    @ToString
    static class Fruit {
        @Getter
        private final String name;
        private static final Map<FruitEnum, Fruit> FRUIT_MAP = new HashMap<>();

        private Fruit(String name) {
            this.name = name;
        }

        static {
            FRUIT_MAP.put(FruitEnum.APPLE, new Fruit("Apple"));
            FRUIT_MAP.put(FruitEnum.BANANA, new Fruit("Banana"));
        }

        public static Fruit getFruit(FruitEnum fruitEnum) {
            return FRUIT_MAP.get(fruitEnum);
        }
    }
}
