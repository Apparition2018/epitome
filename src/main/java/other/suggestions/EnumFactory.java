package other.suggestions;

import com.mchange.util.AssertException;

/**
 * 建议88：枚举实现工厂模式
 */

// 一般工厂模式
class CarFactory {
    public static Car createCar(Class<? extends Car> c) {
        try {
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

enum CarEnumFactory1 {
    FORD_CAR, BUICK_CAR;

    public Car create() {
        switch (this) {
            case FORD_CAR:
                return new FordCar();
            case BUICK_CAR:
                return new BuickCar();
            default:
                throw new AssertException("无效参数");
        }
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