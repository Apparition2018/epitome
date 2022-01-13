package knowledge.design;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;

/**
 * The SOLID Principles:
 * 1.单一职责原则 (Single Responsibility Principle)
 * 2.开闭原则 (Open Closed Principle)
 * 3.里氏替换原则 (Liskov Substitution Principle)
 * 4.接口隔离原则 (Interface Segregation Principle)
 * 5.依赖倒置原则 (Dependence Inversion Principle)
 * 6.迪米特法则 (Law of Demeter) / 最少知识原则 (Least Knowledge Principle)
 * 7.合成复用原则 (Composite Reuse Principle)
 * <p>
 * A Solid Guide to SOLID Principles：https://www.baeldung.com/solid-principles
 *
 * @author ljh
 * created on 2020/9/24 16:13
 */
public class SolidPrinciples {

    /**
     * 单一职责原则
     * 一个类/方法应该有且仅有一个引起它变化的原因，否则类/方法应该被拆分
     * https://www.baeldung.com/java-single-responsibility-principle
     */
    static class SRP {
        static class CounterExample {
            @Getter
            @AllArgsConstructor
            static class TextManipulator {
                String text;

                public void appendText(String newText) {
                    text = text.concat(newText);
                }

                public String findWordAndReplace(String word, String replacementWord) {
                    if (text.contains(word)) text = text.replace(word, replacementWord);
                    return text;
                }

                public String findWordAndDelete(String word) {
                    if (text.contains(word)) text = text.replace(word, "");
                    return text;
                }

                public void printText() {
                    System.out.println(getText());
                }
            }
        }

        static class PositiveExample {
            @Getter
            @AllArgsConstructor
            static class TextManipulator {
                String text;

                public void appendText(String newText) {
                    text = text.concat(newText);
                }

                public String findWordAndReplace(String word, String replacementWord) {
                    if (text.contains(word)) text = text.replace(word, replacementWord);
                    return text;
                }

                public String findWordAndDelete(String word) {
                    if (text.contains(word)) text = text.replace(word, "");
                    return text;
                }
            }

            @AllArgsConstructor
            static class TextPrinter {
                TextManipulator textManipulator;

                public void printText() {
                    System.out.println(textManipulator.getText());
                }

                public void printOutEachWordOfText() {
                    System.out.println(Arrays.toString(textManipulator.getText().split(" ")));
                }

                public void printRangeOfCharacters(int startingIndex, int endIndex) {
                    System.out.println(textManipulator.getText().substring(startingIndex, endIndex));
                }
            }
        }
    }

    /**
     * 开闭原则
     * 对扩展开放，对修改关闭
     * 核心思想：抽象
     * https://www.baeldung.com/java-open-closed-principle
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
     * 接口隔离原则
     * 客户端不应该被迫依赖于它不使用的方法；一个类对另一个类的依赖应该建立在最小的接口上
     * 使用多个专门的接口比使用单一的总接口要好
     * SRP: 对类的约束
     * ISP: 对接口的约束
     * https://www.baeldung.com/java-liskov-substitution-principle
     */
    static class ISP {
        static class CounterExample {
            interface IEmployee {
                void work();

                void sleep();
            }

            static class Employee implements IEmployee {
                @Override
                public void work() {
                }

                @Override
                public void sleep() {
                }
            }

            static class Robot {
                public void work() {
                }
            }

            static class Manager {
                public void manage(Object worker) {
                    if (worker instanceof IEmployee) {
                        ((IEmployee) worker).work();
                    } else if (worker instanceof Robot) {
                        ((Robot) worker).work();
                    }
                }
            }
        }

        static class PositiveExample {
            interface Workable {
                void work();
            }

            interface NeedFood {
                void eat();
            }

            static class Employee implements Workable, NeedFood {
                @Override
                public void work() {
                }

                @Override
                public void eat() {
                }
            }

            static class Robot implements Workable {
                @Override
                public void work() {
                }
            }

            static class Manager {
                public void manage(Workable worker) {
                    worker.work();
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
     * 迪米特法则（最少知识原则）
     * 只与你的直接朋友交谈，不跟陌生人说话
     * 一个软件实体应当尽可能少地与其他软件实体发生相互作用
     */
    static class LOD {
        static class CounterExample {
            static class Phone {
                // 打开 Phone 可以看到 App
                App app = new App();
                // 打开 Phone 不该看到 Book
                Book book = new Book("Book");

                public void readBook() {
                    app.read(book);
                }
            }

            static class App {
                public void read(Book book) {
                    System.out.println(book.getTitle());
                }
            }

            @Data
            static class Book {
                String title;

                public Book(String title) {
                    this.title = title;
                }
            }
        }

        static class PositiveExample {
            static class Phone {
                App app = new App();

                public void readBook() {
                    app.read();
                }
            }

            static class App {
                // 打开 App 可以看到 Book
                Book book = new Book("Book");

                public void read() {
                    System.out.println(book.getTitle());
                }
            }

            @Data
            static class Book {
                private String title;

                public Book(String title) {
                    this.title = title;
                }
            }
        }
    }

    /**
     * 合成复用原则（组合/聚合复用原则）
     * 在软件复用时，要尽量先使用组合或者聚合等关联关系来实现，其次才考虑使用继承关系来实现
     * 如果要使用继承关系，则必须严格遵循里氏替换原则
     */
    static class CRP {
        static class CounterExample {
            static class Car {
            }

            static class GasolineCar extends Car {
            }

            static class ElectricCar extends Car {
            }

            static class WhiteGasolineCar extends GasolineCar {
            }

            static class BlackGasolineCar extends GasolineCar {
            }

            static class WhiteElectricCar extends ElectricCar {
            }

            static class BlackElectricCar extends ElectricCar {
            }
        }

        static class PositiveExample {
            static class Color {
            }

            static class White extends Color {
            }

            static class Black extends Color {
            }

            static class Car {
                Color color;
            }

            static class GasolineCar extends Car {
            }

            static class ElectricCar extends Car {
            }
        }
    }
}
