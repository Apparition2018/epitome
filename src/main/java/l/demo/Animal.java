package l.demo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Animal
 *
 * @author ljh
 * created on 2020/9/18 9:40
 */
@Getter
@Setter
public abstract class Animal {

    public static final Boolean HAS_CELL_WALL = true;

    public String name;
    @Value
    public int age;

    static {
        System.out.println("The is a Animal.");
    }

    public abstract void eat();

    @Data
    public static class Cat extends Animal {

        private int foot;

        static {
            System.out.println("The is a Cat." + "\n");
        }

        public void eat() {
            System.out.println("吃鱼");
        }

        public void work() {
            System.out.println("抓老鼠");
        }

    }

    @Data
    public static class Chicken extends Animal {

        private int wing;

        static {
            System.out.println("The is a Chicken." + "\n");
        }

        public void eat() {
            System.out.println("吃米");
        }

        public void work() {
            System.out.println("生鸡蛋");
        }

        @Deprecated
        public void fly() {

        }

    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Value {
        int value() default 0;
    }
}
