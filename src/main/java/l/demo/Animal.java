package l.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Animal
 *
 * @author ljh
 * created on 2020/9/18 9:40
 */
@Getter
@Setter
public abstract class Animal {

    public String name;

    static {
        System.out.println("The is a Animal.");
    }

    public abstract void eat();

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Cat extends Animal {
        public int age;
        public int foot;

        static {
            System.out.println("The is a Cat.");
        }

        public void eat() {
            System.out.println("吃鱼");
        }

        public void work() {
            System.out.println("抓老鼠");
        }

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Chicken extends Animal {

        public int age;
        private int wing;

        static {
            System.out.println("The is a Chicken.");
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
}
