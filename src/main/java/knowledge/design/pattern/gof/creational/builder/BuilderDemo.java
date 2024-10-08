package knowledge.design.pattern.gof.creational.builder;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.ByteBuffer;

/**
 * 建造者模式：将一个复杂对象的构造与它的表示分离，使得同样的构建过程可以创建不同的表示
 * <p>使用场景：
 * <pre>
 * 1 构造步骤基本相同 (顺序不一定相同)，创建不同的产品 (类型相同)
 * 2 避免 telescopic constructor (构造器多，构造器参数多)
 * </pre>
 * 使用实例：
 * <pre>
 * 1 {@link StringBuilder#append(String)} 和 {@link StringBuilder#append(String)}
 * 2 {@link ByteBuffer#put(byte[])}, XXXBuffer
 * 3 {@link Appendable#append(CharSequence)} 的所有实现
 * 4 {@link BeanDefinitionBuilder}
 * 5 {@link MockMvcWebClientBuilder}
 * 6 {@link UriComponentsBuilder}
 * </pre>
 * 角色：
 * <pre>
 * 抽象建造者 Builder (可选)：定义通用产品构造步骤
 * 具体建造者 ConcreteBuilder：实现各具体构造步骤，还包含一个返回 Product (一一对应) 的方法 build()
 * 产品 Product
 * 导演 Director (可选)：定义构造步骤的调用顺序，创建特定 Product
 * </pre>
 * 优点：符合单一职责原则、迪米特法则
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/builder">Builder</a>
 * @see <a href="http://c.biancheng.net/view/1354.html">Java设计模式</a>
 * @see <a href="https://gupaoedu-tom.blog.csdn.net/article/details/121016859">Tom|动态构建SQL语句</a>
 * @since 2020/9/26 2:51
 */
public class BuilderDemo {

    /** <a href="https://refactoringguru.cn/design-patterns/builder/java/example">分步骤生产汽车</a> */
    public static void main(String[] args) {
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

    /** Builder */
    interface Builder {
        void setCarType(CarType type);

        void setSeats(int seats);

        void setEngine(Engine engine);

        void setTransmission(Transmission transmission);

        void setTripComputer(TripComputer tripComputer);

        void setGPSNavigator(GPSNavigator gpsNavigator);
    }

    /** ConcreteBuilder */
    @Setter
    private static class CarBuilder implements Builder {
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

    /** ConcreteBuilder */
    @Setter
    private static class CarManualBuilder implements Builder {
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

    /** Product */
    @Getter
    private static class Car {
        private final CarType carType;
        private final int seats;
        private final Engine engine;
        private final Transmission transmission;
        private final TripComputer tripComputer;
        private final GPSNavigator gpsNavigator;
        private double fuel = 0D;

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

    /** Product */
    private record Manual(CarType carType, int seats, Engine engine, Transmission transmission,
                          TripComputer tripComputer, GPSNavigator gpsNavigator) {
        public String print() {
            String info = StringUtils.EMPTY;
            info += "Type of car: " + carType + StringUtils.CR;
            info += "Count of seats: " + seats + StringUtils.CR;
            info += "Engine: volume - " + engine.getVolume() + "; mileage - " + engine.getMileage() + StringUtils.CR;
            info += "Transmission: " + transmission + StringUtils.CR;
            if (this.tripComputer != null) {
                info += "Trip Computer: Functional" + StringUtils.CR;
            } else {
                info += "Trip Computer: N/A" + StringUtils.CR;
            }
            if (this.gpsNavigator != null) {
                info += "GPS Navigator: Functional" + StringUtils.CR;
            } else {
                info += "GPS Navigator: N/A" + StringUtils.CR;
            }
            return info;
        }
    }

    /** Director */
    private static class Director {
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

    /** 流式建造者模式 */
    private static class FluentBuilderDemo {

        public static void main(String[] args) {
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

        /** Product */
        @Getter
        @Setter
        static class Car {
            private CarType carType;
            private int seats;
            private Engine engine;
            private Transmission transmission;
            private TripComputer tripComputer;
            private GPSNavigator gpsNavigator;
            private double fuel = 0D;

            /** Builder */
            private static class Builder {
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
    private static class Engine {
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
    private static class TripComputer {
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
    private static class GPSNavigator {
        private final String route;

        public GPSNavigator() {
            this.route = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London";
        }

        public GPSNavigator(String manualRoute) {
            this.route = manualRoute;
        }
    }
}
