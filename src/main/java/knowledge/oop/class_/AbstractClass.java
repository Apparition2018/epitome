package knowledge.oop.class_;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象类
 * 1.不能实例化
 * 2.不一定包含抽象方法，但是有抽象方法的类必定是抽象类
 * 3.抽象类的子类必须重写父类的抽象方法，或者声明自身为抽象类
 * <p>
 * 抽象类命名使用 Abstract 或 Base 开头（阿里编程规约）
 * <p>
 * 抽象类：代码的复用
 * 接口：实现多态
 * <p>
 * 抽象类和接口的区别，使用场景：https://www.iteye.com/blog/yinny-1152430
 * Java 8 中的接口和抽象类到底有啥区别？:https://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247510272&idx=1&sn=ece5d0df4895d267be05b7e79494b3b7&scene=21
 * 抽象类实现接口有什么意义？：https://www.zhihu.com/question/36909455/answer/303566988
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class AbstractClass {

    public static void main(String[] args) {
        Employee e = new Salary("Mary", "London", 1);
        System.out.println(e.computePay());
    }

    /**
     * 抽象类
     */
    @Getter
    @Setter
    private static abstract class Employee {
        private String name;
        private String address;
        private int number;

        public Employee(String name, String address, int number) {
            System.out.println("Constructing an Employee");
            this.name = name;
            this.address = address;
            this.number = number;
        }

        /**
         * 抽象方法
         * 所有的抽象方法（包括接口中的方法）必须要用 Javadoc 注释、除了返回值、参数、异常说明外，还必须指出该方法做什么事情，实现什么功能（阿里编程规约）
         */
        public abstract double computePay();

        public void mailCheck() {
            System.out.println("Mailing a check to " + this.name + " " + this.address);
        }

        public String toString() {
            return name + " " + address + " " + number;
        }

    }

    private static class Salary extends Employee {

        private double salary;

        public Salary(String name, String address, int number) {
            super(name, address, number);
        }

        // 重写父类抽象方法
        public double computePay() {
            System.out.println("Computing salary pay for " + getName());
            return salary / 52;
        }
    }

}
