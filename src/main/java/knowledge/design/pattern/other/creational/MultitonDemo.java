package knowledge.design.pattern.other.creational;

import lombok.Getter;

import java.util.EnumMap;

/**
 * <a href="https://java-design-patterns.com/patterns/multiton/">多例模式</a>：确保一个类只有有限数量的实例，并提供对它们的全局访问点
 *
 * @author ljh
 * @since 2022/2/8 1:13
 */
public class MultitonDemo {

    public static void main(String[] args) {
        System.out.println(Fruit.getFruit(FruitEnum.APPLE));
    }

    enum FruitEnum {
        APPLE, BANANA
    }

    private record Fruit(@Getter String name) {
        private static final EnumMap<FruitEnum, Fruit> FRUIT_MAP = new EnumMap<>(FruitEnum.class);

        static {
            FRUIT_MAP.put(FruitEnum.APPLE, new Fruit("Apple"));
            FRUIT_MAP.put(FruitEnum.BANANA, new Fruit("Banana"));
        }

        public static Fruit getFruit(FruitEnum fruitEnum) {
            return FRUIT_MAP.get(fruitEnum);
        }
    }
}
