package knowledge.design.creational.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

/**
 * 建造者模式：将一个复杂对象的构造与它的表示分离，使得同样的构建过程可以创建不同的表示
 * 使用场景：
 * 1.避免 telescopic constructor：构造器多，构造器参数多
 * 2.构造步骤基本相同 (顺序不一定相同)，创建不同的产品 (类型相同)
 * 使用实例：
 * 1. {@link StringBuilder#append(String)} 和 {@link StringBuilder#append(String)}
 * 2. {@link java.nio.ByteBuffer#put(byte[])}, XXXBuffer
 * 3. {@link java.lang.Appendable#append(CharSequence)} 的所有实现
 * 4. {@link org.springframework.beans.factory.support.BeanDefinitionBuilder}
 * 5. {@link org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder}
 * 6. {@link org.springframework.web.util.UriComponentsBuilder}
 * <p>
 * 角色：
 * 抽象建造者 Builder (可选)：定义通用产品构造步骤
 * 具体建造者 ConcreteBuilder：实现各具体构造步骤，还包含一个返回 Product (一一对应) 的方法 build()
 * 产品 Product
 * 导演 Director (可选)：调用 ConcreteBuilder 创建 Product
 * <p>
 * 优点：符合单一职责原则、迪米特法则
 * <p>
 * Builder：https://refactoringguru.cn/design-patterns/builder
 * Java设计模式：http://c.biancheng.net/view/1354.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/builder-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class BuilderDemo {

    /**
     * 分步骤生产汽车
     * https://refactoringguru.cn/design-patterns/builder/java/example
     */
    @Test
    public void testBuilder() {
        Director director = new Director();

        CarBuilder carBuilder = new CarBuilder();
        director.constructCityCar(carBuilder);
        Car car = carBuilder.getResult();
        System.out.println("Car built:\n" + car.getCarType());

        CarManualBuilder manualBuilder = new CarManualBuilder();
        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }

    /**
     * Builder
     */
    interface Builder {
        void setCarType(CarType type);

        void setSeats(int seats);

        void setEngine(Engine engine);

        void setTransmission(Transmission transmission);

        void setTripComputer(TripComputer tripComputer);

        void setGPSNavigator(GPSNavigator gpsNavigator);
    }

    /**
     * ConcreteBuilder
     */
    @Setter
    static class CarBuilder implements Builder {
        private CarType carType;
        private int seats;
        private Engine engine;
        private Transmission transmission;
        private TripComputer tripComputer;
        private GPSNavigator gpsNavigator;

        @Override
        public void setGPSNavigator(GPSNavigator gpsNavigator) {
            this.gpsNavigator = gpsNavigator;
        }

        public Car getResult() {
            return new Car(carType, seats, engine, transmission, tripComputer, gpsNavigator);
        }
    }

    /**
     * ConcreteBuilder
     */
    @Setter
    static class CarManualBuilder implements Builder {
        private CarType carType;
        private int seats;
        private Engine engine;
        private Transmission transmission;
        private TripComputer tripComputer;
        private GPSNavigator gpsNavigator;

        @Override
        public void setGPSNavigator(GPSNavigator gpsNavigator) {
            this.gpsNavigator = gpsNavigator;
        }

        public Manual getResult() {
            return new Manual(carType, seats, engine, transmission, tripComputer, gpsNavigator);
        }
    }

    /**
     * Product
     */
    @Getter
    static class Car {
        private final CarType carType;
        private final int seats;
        private final Engine engine;
        private final Transmission transmission;
        private final TripComputer tripComputer;
        private final GPSNavigator gpsNavigator;
        private double fuel = 0;

        public Car(CarType carType, int seats, Engine engine, Transmission transmission,
                   TripComputer tripComputer, GPSNavigator gpsNavigator) {
            this.carType = carType;
            this.seats = seats;
            this.engine = engine;
            this.transmission = transmission;
            this.tripComputer = tripComputer;
            if (this.tripComputer != null) {
                this.tripComputer.setCar(this);
            }
            this.gpsNavigator = gpsNavigator;
        }

        public void setFuel(double fuel) {
            this.fuel = fuel;
        }
    }

    /**
     * Product
     */
    @AllArgsConstructor
    static class Manual {
        private final CarType carType;
        private final int seats;
        private final Engine engine;
        private final Transmission transmission;
        private final TripComputer tripComputer;
        private final GPSNavigator gpsNavigator;

        public String print() {
            String info = "";
            info += "Type of car: " + carType + "\n";
            info += "Count of seats: " + seats + "\n";
            info += "Engine: volume - " + engine.getVolume() + "; mileage - " + engine.getMileage() + "\n";
            info += "Transmission: " + transmission + "\n";
            if (this.tripComputer != null) {
                info += "Trip Computer: Functional" + "\n";
            } else {
                info += "Trip Computer: N/A" + "\n";
            }
            if (this.gpsNavigator != null) {
                info += "GPS Navigator: Functional" + "\n";
            } else {
                info += "GPS Navigator: N/A" + "\n";
            }
            return info;
        }
    }

    /**
     * Director
     */
    static class Director {
        public void constructCityCar(Builder builder) {
            builder.setCarType(CarType.CITY_CAR);
            builder.setSeats(2);
            builder.setEngine(new Engine(1.2, 0));
            builder.setTransmission(Transmission.AUTOMATIC);
            builder.setTripComputer(new TripComputer());
            builder.setGPSNavigator(new GPSNavigator());
        }

        public void constructSportsCar(Builder builder) {
            builder.setCarType(CarType.SPORTS_CAR);
            builder.setSeats(2);
            builder.setEngine(new Engine(3.0, 0));
            builder.setTransmission(Transmission.SEMI_AUTOMATIC);
            builder.setTripComputer(new TripComputer());
            builder.setGPSNavigator(new GPSNavigator());
        }

        public void constructSUV(Builder builder) {
            builder.setCarType(CarType.SUV);
            builder.setSeats(4);
            builder.setEngine(new Engine(2.5, 0));
            builder.setTransmission(Transmission.MANUAL);
            builder.setGPSNavigator(new GPSNavigator());
        }
    }

    /**
     * 静态内部类实现分步骤生产汽车
     */
    static class InnerClassBuilderDemo {

        @Test
        public void testInnerClassBuilder() {
            Car car = new Car.Builder()
                    .setCarType(CarType.CITY_CAR)
                    .setSeats(2)
                    .setEngine(new Engine(1.2, 0))
                    .setTransmission(Transmission.AUTOMATIC)
                    .setTripComputer(new TripComputer())
                    .setGPSNavigator(new GPSNavigator())
                    .build();
            System.out.println("Car built:\n" + car.getCarType());
        }

        /**
         * Product
         */
        @Getter
        @Setter
        static class Car {
            private CarType carType;
            private int seats;
            private Engine engine;
            private Transmission transmission;
            private TripComputer tripComputer;
            private GPSNavigator gpsNavigator;
            private double fuel = 0;

            /**
             * Builder
             */
            static class Builder {
                private final Car car = new Car();

                public Builder setCarType(CarType carType) {
                    car.setCarType(carType);
                    return this;
                }

                public Builder setSeats(int seats) {
                    car.setSeats(seats);
                    return this;
                }

                public Builder setEngine(Engine engine) {
                    car.setEngine(engine);
                    return this;
                }

                public Builder setTransmission(Transmission transmission) {
                    car.setTransmission(transmission);
                    return this;
                }

                public Builder setTripComputer(TripComputer tripComputer) {
                    car.setTripComputer(tripComputer);
                    return this;
                }

                public Builder setGPSNavigator(GPSNavigator gpsNavigator) {
                    car.setGpsNavigator(gpsNavigator);
                    return this;
                }

                public Builder setFuel(double fuel) {
                    car.setFuel(fuel);
                    return this;
                }

                public Car build() {
                    return car;
                }
            }
        }
    }

    enum CarType {
        CITY_CAR, SPORTS_CAR, SUV
    }

    @Getter
    static class Engine {
        private final double volume;
        private double mileage;
        private boolean started;

        public Engine(double volume, double mileage) {
            this.volume = volume;
            this.mileage = mileage;
        }

        public void on() {
            started = true;
        }

        public void off() {
            started = false;
        }

        public boolean isStarted() {
            return started;
        }

        public void go(double mileage) {
            if (started) {
                this.mileage += mileage;
            } else {
                System.err.println("Cannot go(), you must start engine first!");
            }
        }
    }

    enum Transmission {
        SINGLE_SPEED, MANUAL, AUTOMATIC, SEMI_AUTOMATIC
    }

    @Setter
    static class TripComputer {
        private Car car;

        public void showFuelLevel() {
            System.out.println("Fuel level: " + car.getFuel());
        }

        public void showStatus() {
            if (this.car.getEngine().isStarted()) {
                System.out.println("Car is started");
            } else {
                System.out.println("Car isn't started");
            }
        }
    }

    @Getter
    static class GPSNavigator {
        private final String route;

        public GPSNavigator() {
            this.route = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London";
        }

        public GPSNavigator(String manualRoute) {
            this.route = manualRoute;
        }
    }
}