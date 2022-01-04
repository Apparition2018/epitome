package knowledge.design;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 六大原则：
 * 1.单一责任原则 (Single Responsibility Principle)
 * 2.开闭原则 (Open-Closed Principle)
 * 3.里氏替换原则 (Liskov Substitution Principle)
 * 4.依赖倒置原则 (Dependence Inversion Principle)
 * 5.接口隔离原则 (Interface Segregation Principle)
 * 6.迪米特法则 (Law of Demeter)
 *
 * @author ljh
 * created on 2020/9/24 16:13
 */
public class DesignPatternPrinciples {

    /**
     * 单一责任原则
     * 有且仅有一个原因引起类/方法的变更（一个类只负责一项职责）
     * https://www.cnblogs.com/liebrother/p/10182494.html
     */
    static class SRP {

        interface Shopping {
            void shopping();
        }

        interface Cooking {
            void cooking();
        }

        interface Washing {
            void washing();
        }

        static class Jack implements Shopping {

            @Override
            public void shopping() {
            }
        }

        static class Mary implements Cooking, Washing {

            @Override
            public void cooking() {
            }

            @Override
            public void washing() {
            }
        }
    }

    /**
     * 开闭原则
     * 对扩展开放，对修改关闭；尽量在不修改原有代码的情况下进行扩展
     */
    static class OCP {
        private abstract static class Chart {
            public void display() {

            }
        }

        static class PieChart extends Chart {
            public void display() {
            }
        }

        static class BarChart extends Chart {
            public void display() {
            }
        }

        static class ChartDisplay {
            private Chart chart;

            public void setChart(Chart chart) {
                this.chart = chart;
            }

            public void display() {
                chart.display();
            }
        }
    }

    /**
     * 里氏替换原则
     * 所有引用基类的地方必须能透明地使用其子类的对象
     */
    static class LSP {

        interface EmailSender {
            void send(Customer c);
        }

        @Getter
        @Setter
        private abstract static class Customer implements EmailSender {
            protected String name;
            protected String email;
        }

        static class VIPCustomer extends Customer {

            @Override
            public void send(Customer c) {
            }
        }

        static class CommonCustomer extends Customer {

            @Override
            public void send(Customer c) {
            }
        }
    }

    /**
     * 依赖倒置原则
     * 面向接口编程，不要面向实现编程
     * https://blog.csdn.net/iteye_8149/article/details/82295068
     */
    static class DIP {
        interface ICar {
            void run();

            void turn();

            void stop();
        }

        static class BMW implements ICar {
            public void run() {
            }

            public void turn() {
            }

            public void stop() {
            }
        }

        static class Ford implements ICar {
            public void run() {
            }

            public void turn() {
            }

            public void stop() {
            }
        }

        static class AutoSystem {
            private ICar icar;

            public AutoSystem(ICar icar) {
                this.icar = icar;
            }

            public void Run() {
                icar.run();
            }

            public void Turn() {
                icar.turn();
            }

            public void Stop() {
                icar.stop();
            }
        }
    }

    /**
     * 接口隔离原则
     * 使用多个专门的接口，而不使用单一的总接口
     * https://learnku.com/articles/39202
     */
    static class ISP {
        interface Work {
            void work();
        }

        interface Sleep {
            void sleep();
        }

        interface Managed {
            void beManaged();
        }

        static class Worker implements Work, Sleep, Managed {

            @Override
            public void work() {
            }

            @Override
            public void sleep() {
            }

            @Override
            public void beManaged() {
            }
        }

        static class Robot implements Work, Managed {

            @Override
            public void work() {
            }

            @Override
            public void beManaged() {
            }
        }

        static class Captain {
            public void manage(Managed managed) {
                managed.beManaged();
            }
        }
    }

    /**
     * 迪米特法则
     * 一个软件实体应当尽可能少地与其他实体发生相互作用
     * http://c.biancheng.net/view/1331.html
     */
    static class LoD {
        @Data
        static class Broker {
            private Star star;
            private Fan fan;
            private Company company;

            public void meeting() {
                System.out.println(star.getName() + "与粉丝" + fan.getName() + "见面");
            }

            public void business() {
                System.out.println(star.getName() + "与" + company.getName() + "洽谈业务");
            }
        }

        @Data
        static class Star {
            private String name;
        }

        @Data
        static class Fan {
            private String name;
        }

        @Data
        static class Company {
            private String name;
        }
    }

}
