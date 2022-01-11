package knowledge.design;

import lombok.Data;

/**
 * 七大原则：
 * 1.开闭原则 (Open Closed Principle)
 * 2.里氏替换原则 (Liskov Substitution Principle)
 * 3.依赖倒置原则 (Dependence Inversion Principle)
 * 4.单一职责原则 (Single Responsibility Principle)
 * 5.接口隔离原则 (Interface Segregation Principle)
 * 6.迪米特法则 (Law of Demeter) / 最少知识原则 (Least Knowledge Principle)
 * 7.合成复用原则 (Composite Reuse Principle)
 *
 * @author ljh
 * created on 2020/9/24 16:13
 */
public class DesignPatternPrinciples {

    /**
     * 开闭原则
     * 对扩展开放，对修改关闭
     * 核心思想：抽象
     */
    static class OCP {
        static class CounterExample {
            static class PieChart {
                void display() {
                }
            }

            static class LineChart {
                void display() {
                }
            }

            static class ChartDisplay {
                void display(Object chart) {
                    if (chart instanceof PieChart) {
                        ((PieChart) chart).display();
                    } else if (chart instanceof LineChart) {
                        ((LineChart) chart).display();
                    }
                }
            }
        }

        static class PositiveExample {
            interface Chart {
                void display();
            }

            static class PieChart implements Chart {
                public void display() {
                }
            }

            static class BarChart implements Chart {
                public void display() {
                }
            }

            static class ChartDisplay {
                public void display(Chart chart) {
                    chart.display();
                }
            }
        }
    }

    /**
     * 里氏替换原则
     * 继承必须确保超类所拥有的性质在子类中仍然成立
     * 子类继承父类尽量不要重写父类方法
     */
    static class LSP {
        static class CounterExample {
            static class Person {
                public void move() {
                    System.out.println("移动");
                }
            }

            static class Man extends Person {
                @Override
                public void move() {
                    System.out.println("步行");
                }
            }

            static class Baby extends Person {
                @Override
                public void move() {
                    System.out.println("爬行");
                }
            }
        }

        static class PositiveExample {
            interface Person {
                void move();
            }

            static class Adult implements Person {
                @Override
                public void move() {
                    System.out.println("步行");
                }
            }

            static class Man extends Adult {
            }

            static class Baby implements Person {
                @Override
                public void move() {
                    System.out.println("爬行");
                }
            }
        }
    }

    /**
     * 依赖倒置原则
     * 高层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象
     * 要面向接口编程，不要面向实现编程
     */
    static class DIP {
        static class CounterExample {
            static class Math {
                public void knowledge() {
                }
            }

            static class Student {
                public void learn(Math course) {
                    course.knowledge();
                }
            }
        }

        static class PositiveExample {
            interface Course {
                void knowledge();
            }

            static class Math implements Course {
                @Override
                public void knowledge() {
                }
            }

            static class Chinese implements Course {
                @Override
                public void knowledge() {
                }
            }

            static class Student {
                public void learn(Course course) {
                    course.knowledge();
                }
            }
        }
    }

    /**
     * 单一职责原则
     * 一个类/方法应该有且仅有一个引起它变化的原因，否则类/方法应该被拆分
     */
    static class SRP {
    }

    /**
     * 接口隔离原则
     * 客户端不应该被迫依赖于它不使用的方法；一个类对另一个类的依赖应该建立在最小的接口上
     * 使用多个专门的接口比使用单一的总接口要好
     * SRP: 对类的约束
     * ISP: 对接口的约束
     */
    static class ISP {
        interface Work {
            void work();
        }

        interface Sleep {
            void sleep();
        }

        interface BeManaged {
            void beManaged();
        }

        static class Worker implements Work, Sleep, BeManaged {
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

        static class Robot implements Work, BeManaged {
            @Override
            public void work() {
            }

            @Override
            public void beManaged() {
            }
        }
    }

    /**
     * 迪米特法则（最少知识原则）
     * 只与你的直接朋友交谈，不跟陌生人说话
     * 一个软件实体应当尽可能少地与其他软件实体发生相互作用
     */
    static class LOD {
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

    /**
     * 合成复用原则（组合/聚合复用原则）
     * 在软件复用时，要尽量先使用组合或者聚合等关联关系来实现，其次才考虑使用继承关系来实现
     * 如果要使用继承关系，则必须严格遵循里氏替换原则
     */
    static class CRP {

    }

}
