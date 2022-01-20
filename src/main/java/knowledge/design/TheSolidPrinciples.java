package knowledge.design;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;

/**
 * The SOLID Principles:
 * 1.单一职责原则 (Single Responsibility Principle)
 * 2.开闭原则 (Open-Closed Principle)
 * 3.里氏替换原则 (Liskov Substitution Principle)
 * 4.接口隔离原则 (Interface Segregation Principle)
 * 5.依赖倒置原则 (Dependence Inversion Principle)
 * 6.迪米特法则 (Law of Demeter) / 最少知识原则 (Least Knowledge Principle)
 * 7.合成复用原则 (Composite/Aggregate Reuse Principle)
 * <p>
 * A Solid Guide to SOLID Principles：https://www.baeldung.com/solid-principles
 *
 * @author ljh
 * created on 2020/9/24 16:13
 */
public class TheSolidPrinciples {

    /**
     * 单一职责原则
     * 一个类/方法应该有且仅有一个引起它变化的原因，否则类/方法应该被拆分
     * https://www.baeldung.com/java-single-responsibility-principle
     */
    static class SRP {
        static class CounterExample {
            /**
             * 此类有两个职责：操作文本、打印文本
             */
            @Getter
            @AllArgsConstructor
            static class TextManipulator {
                private String text;

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
            /**
             * 此类只有一个职责：操作文本
             */
            @Getter
            @AllArgsConstructor
            static class TextManipulator {
                private String text;

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

            /**
             * 此类只有一个职责：打印文本
             */
            @AllArgsConstructor
            static class TextPrinter {
                private final TextManipulator textManipulator;

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
            interface CalculatorOperation {
            }

            @Data
            static class Addition implements CalculatorOperation {
                private double left;
                private double right;
                private double result = 0.0;

                public Addition(double left, double right) {
                    this.left = left;
                    this.right = right;
                }
            }

            @Data
            static class Subtraction implements CalculatorOperation {
                private double left;
                private double right;
                private double result = 0.0;

                public Subtraction(double left, double right) {
                    this.left = left;
                    this.right = right;
                }
            }

            /**
             * 添加乘法或除法功能时，需要修改 Calculator
             */
            static class Calculator {
                public void calculate(CalculatorOperation operation) {
                    if (operation instanceof Addition) {
                        Addition addition = (Addition) operation;
                        addition.setResult(addition.getLeft() + addition.getRight());
                    } else if (operation instanceof Subtraction) {
                        Subtraction subtraction = (Subtraction) operation;
                        subtraction.setResult(subtraction.getLeft() - subtraction.getRight());
                    }
                }
            }
        }

        static class PositiveExample {
            interface CalculatorOperation {
                void perform();
            }

            @Data
            static class Addition implements CalculatorOperation {
                private double left;
                private double right;
                private double result;

                public Addition(double left, double right) {
                    this.left = left;
                    this.right = right;
                }

                @Override
                public void perform() {
                    result = left + right;
                }
            }

            @Data
            static class Division implements CalculatorOperation {
                private double left;
                private double right;
                private double result;

                public Division(double left, double right) {
                    this.left = left;
                    this.right = right;
                }

                @Override
                public void perform() {
                    if (right != 0) {
                        result = left / right;
                    }
                }
            }

            static class Calculator {
                public void calculate(CalculatorOperation operation) {
                    operation.perform();
                }
            }
        }
    }

    /**
     * 里氏替换原则
     * 继承必须确保超类所拥有的性质在子类中仍然成立
     * 子类可以扩展父类的功能，但不能改变父类原有的功能
     * 子类添加新的方法完成新增功能，尽量不要重写父类的方法
     * 1.签名规则：
     * ①重写的子类方法参数类型 >= 超类方法参数类型
     * ②重写的子类方法返回类型 <= 超类方法的返回类型，如：子类 Integer < 超类 Number
     * ③子类方法异常 < 超类方法异常
     * 2.属性规则：
     * ①类不变量：子类方法必须维护或加强超类型的类不变量 ???
     * ②历史约束：子类方法状态的改变要符合基类
     * 3.方法规则：
     * ①先决条件：子类可以削弱重写方法的先决条件
     * ②后置条件：子类可以增强重写方法的后置条件
     * https://www.baeldung.com/java-liskov-substitution-principle
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
     * 接口隔离原则
     * 客户端不应该被迫依赖于它不使用的方法；一个类对另一个类的依赖应该建立在最小的接口上
     * 使用多个专门的接口，而不使用单一的总接口
     * SRP: 对类的约束
     * ISP: 对接口的约束
     * https://www.baeldung.com/java-liskov-substitution-principle
     */
    static class ISP {
        static class CounterExample {
            interface Payment {
                Object status();

                void initiatePayments();

                void initiateLoanSettlement();
            }

            static class LoanPayment implements Payment {
                @Override
                public Object status() {
                    return new Object();
                }

                @Override
                public void initiatePayments() {
                    throw new UnsupportedOperationException("This is not a bank payment");
                }

                @Override
                public void initiateLoanSettlement() {
                }
            }

            static class BankPayment implements Payment {
                @Override
                public Object status() {
                    return null;
                }

                @Override
                public void initiatePayments() {
                }

                @Override
                public void initiateLoanSettlement() {
                    throw new UnsupportedOperationException("This is not a loan payment");

                }
            }
        }

        static class PositiveExample {
            interface Payment {
                Object status();
            }

            interface Bank extends Payment {
                void initiatePayments();
            }

            interface Loan extends Payment {
                void initiateLoanSettlement();
            }

            static class BankPayment implements Bank {
                @Override
                public Object status() {
                    return new Object();
                }

                @Override
                public void initiatePayments() {
                }
            }

            static class LoanPayment implements Loan {
                @Override
                public Object status() {
                    return new Object();
                }

                @Override
                public void initiateLoanSettlement() {
                }
            }
        }
    }

    /**
     * 依赖倒置原则
     * 高层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象
     * 要面向接口编程，不要面向实现编程
     * https://www.baeldung.com/java-dependency-inversion-principle
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
     * 尽量使用组合(has-a)/聚合(contains-a)而不是继承关系达到软件复用的目的
     */
    static class CARP {
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
