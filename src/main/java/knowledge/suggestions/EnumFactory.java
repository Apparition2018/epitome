package knowledge.suggestions;

import java.lang.reflect.InvocationTargetException;

/**
 * 建议88：枚举实现工厂模式
 *
 * @author ljh
 * @since 2020/10/10 19:23
 */

// 一般工厂模式
class CarFactory {
    public static Car createCar(Class<? extends Car> c) {
        try {
            return c.getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}

enum CarEnumFactory1 {
    FORD_CAR, BUICK_CAR;

    public Car create() {
        return switch (this) {
            case FORD_CAR -> new FordCar();
            case BUICK_CAR -> new BuickCar();
        };
    }

}

enum CarEnumFactory2 {

    FORD_CAR {
        public Car create() {
            return new FordCar();
        }
    },
    BUICK_CAR {
        public Car create() {
            return new BuickCar();
        }
    };

    public abstract Car create();
}

interface Car {
}

class FordCar implements Car {
    public String toString() {
        return "福特";
    }
}

class BuickCar implements Car {
    public String toString() {
        return "别克";
    }
}
