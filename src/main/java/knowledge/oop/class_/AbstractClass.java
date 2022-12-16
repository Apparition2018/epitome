package knowledge.oop.class_;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象类
 * <pre>
 * 1 不能实例化
 * 2 不一定包含抽象方法，但是有抽象方法的类必定是抽象类N
 * 3 抽象类的子类必须重写父类的抽象方法，或者声明自身为抽象类
 * </pre>
 * 抽象类：代码的复用
 * <p>接口：实现多态
 * <p>参考：
 * <pre>
 * <a href="https://www.iteye.com/blog/yinny-1152430">接口 vs 抽象类</a>
 * <a href="https://mp.weixin.qq.com/s/HD8aXbdcWnTovqWO5ZP_Gw">Java8 接口 vs 抽象类</a>
 * <a href="https://www.zhihu.com/question/36909455/answer/303566988">抽象类实现接口有什么意义？</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
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
    static abstract class Employee {
        private String name;
        private String address;
        private int number;

        private Employee(String name, String address, int number) {
            System.out.println("Constructing an Employee");
            this.name = name;
            this.address = address;
            this.number = number;
        }

        /**
         * 抽象方法
         */
        public abstract double computePay();

        public void mailCheck() {
            System.out.println("Mailing a check to " + this.name + " " + this.address);
        }

        public String toString() {
            return name + " " + address + " " + number;
        }

    }

    static class Salary extends Employee {

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
