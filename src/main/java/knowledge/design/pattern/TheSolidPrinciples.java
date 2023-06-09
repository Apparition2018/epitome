package knowledge.design.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * The SOLID Principles:
 * <pre>
 * 1 单一职责原则 (Single Responsibility Principle)
 * 2 开闭原则 (Open-Closed Principle)
 * 3 里氏替换原则 (Liskov Substitution Principle)
 * 4 接口隔离原则 (Interface Segregation Principle)
 * 5 依赖倒置原则 (Dependence Inversion Principle)
 * 6 迪米特法则 (Law of Demeter) / 最少知识原则 (Least Knowledge Principle)
 * 7 合成复用原则 (Composite/Aggregate Reuse Principle)
 * </pre>
 * 参考：<a href="https://www.baeldung.com/solid-principles">A Solid Guide to SOLID Principles</a>
 *
 * @author ljh
 * @since 2020/9/24 16:13
 */
public class TheSolidPrinciples {

    /**
     * 单一职责原则
     * <p>一个类/方法应该有且仅有一个引起它变化的原因，否则类/方法应该被拆分
     *
     * @see <a href=" * https://www.baeldung.com/java-single-responsibility-princi">Single Responsibility Principle in Java</a>ple
     */
    private static class SRP {
        private static class CounterExample {
            /** 此类有两个职责：操作文本、打印文本 */
            @Getter
            @AllArgsConstructor
            private static class TextManipulator {
                private String text;

                public void appendText(String newText) {
                    text = text.concat(newText);
                }

                public String findWordAndReplace(String word, String replacementWord) {
                    if (text.contains(word)) text = text.replace(word, replacementWord);
                    return text;
                }

                public String findWordAndDelete(String word) {
                    if (text.contains(word)) text = text.replace(word, StringUtils.EMPTY);
                    return text;
                }

                public void printText() {
                    System.out.println(getText());
                }
            }
        }

        private static class PositiveExample {
            /** 此类只有一个职责：操作文本 */
            @Getter
            @AllArgsConstructor
            private static class TextManipulator {
                private String text;

                public void appendText(String newText) {
                    text = text.concat(newText);
                }

                public String findWordAndReplace(String word, String replacementWord) {
                    if (text.contains(word)) text = text.replace(word, replacementWord);
                    return text;
                }

                public String findWordAndDelete(String word) {
                    if (text.contains(word)) text = text.replace(word, StringUtils.EMPTY);
                    return text;
                }
            }

            /** 此类只有一个职责：打印文本 */
            private record TextPrinter(TextManipulator textManipulator) {
                public void printText() {
                    System.out.println(textManipulator.getText());
                }

                public void printOutEachWordOfText() {
                    System.out.println(Arrays.toString(textManipulator.getText().split(StringUtils.SPACE)));
                }

                public void printRangeOfCharacters(int startingIndex, int endIndex) {
                    System.out.println(textManipulator.getText().substring(startingIndex, endIndex));
                }
            }
        }
    }

    /**
     * 开闭原则
     * <pre>
     * 对扩展开放，对修改关闭
     * 核心思想：抽象
     * </pre>
     *
     * @see <a href="https://www.baeldung.com/java-open-closed-principle">Open/Closed Principle in Java</a>
     */
    private static class OCP {
        private static class CounterExample {
            interface CalculatorOperation {
            }

            @Data
            private static class Addition implements CalculatorOperation {
                private double left;
                private double right;
                private double result = 0D;

                public Addition(double left, double right) {
                    this.left = left;
                    this.right = right;
                }
            }

            @Data
            private static class Subtraction implements CalculatorOperation {
                private double left;
                private double right;
                private double result = 0D;

                public Subtraction(double left, double right) {
                    this.left = left;
                    this.right = right;
                }
            }

            /** 添加乘法或除法功能时，需要修改 Calculator */
            private static class Calculator {
                public void calculate(CalculatorOperation operation) {
                    if (operation instanceof Addition addition) {
                        addition.setResult(addition.getLeft() + addition.getRight());
                    } else if (operation instanceof Subtraction subtraction) {
                        subtraction.setResult(subtraction.getLeft() - subtraction.getRight());
                    }
                }
            }
        }

        private static class PositiveExample {
            interface CalculatorOperation {
                void perform();
            }

            @Data
            private static class Addition implements CalculatorOperation {
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
            private static class Division implements CalculatorOperation {
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

            private static class Calculator {
                public void calculate(CalculatorOperation operation) {
                    operation.perform();
                }
            }
        }
    }

    /**
     * 里氏替换原则
     * <pre>
     * 继承必须确保超类所拥有的性质在子类中仍然成立
     * 子类可以扩展父类的功能，但不能改变父类原有的功能
     * 子类添加新的方法完成新增功能，尽量不要重写父类的方法
     * </pre>
     * 1.签名规则：
     * <pre>
     * 1 重写的子类方法参数类型 >= 超类方法参数类型
     * 2 重写的子类方法返回类型 <= 超类方法的返回类型，如：子类 Integer < 超类 Number
     * 3 子类方法异常 < 超类方法异常
     * </pre>
     * 2.属性规则：
     * <pre>
     * 1 类不变量：子类方法必须维护或加强超类型的类不变量 ???
     * 2 历史约束：子类方法状态的改变要符合基类
     * </pre>
     * 3.方法规则：
     * <pre>
     * 1 先决条件：子类可以削弱重写方法的先决条件
     * 2 后置条件：子类可以增强重写方法的后置条件
     * </pre>
     *
     * @see <a href="https://www.baeldung.com/java-liskov-substitution-principle">Liskov Substitution Principle in Java</a>
     */
    private static class LSP {
        private static class CounterExample {
            private static class Person {
                public void move() {
                    System.out.println("移动");
                }
            }

            private static class Man extends Person {
                @Override
                public void move() {
                    System.out.println("步行");
                }
            }

            private static class Baby extends Person {
                @Override
                public void move() {
                    System.out.println("爬行");
                }
            }
        }

        private static class PositiveExample {
            interface Person {
                void move();
            }

            private static class Adult implements Person {
                @Override
                public void move() {
                    System.out.println("步行");
                }
            }

            private static class Man extends Adult {
            }

            private static class Baby implements Person {
                @Override
                public void move() {
                    System.out.println("爬行");
                }
            }
        }
    }

    /**
     * 接口隔离原则
     * <pre>
     * 客户端不应该被迫依赖于它不使用的方法；一个类对另一个类的依赖应该建立在最小的接口上
     * 使用多个专门的接口，而不使用单一的总接口
     * </pre>
     * <p>SRP: 对类的约束
     * <p>ISP: 对接口的约束
     *
     * @see <a href="https://www.baeldung.com/java-interface-segregation">Interface Segregation Principle in Java</a>
     */
    private static class ISP {
        private static class CounterExample {
            interface Payment {
                Object status();

                void initiatePayments();

                void initiateLoanSettlement();
            }

            private static class LoanPayment implements Payment {
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

            private static class BankPayment implements Payment {
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

        private static class PositiveExample {
            interface Payment {
                Object status();
            }

            interface Bank extends Payment {
                void initiatePayments();
            }

            interface Loan extends Payment {
                void initiateLoanSettlement();
            }

            private static class BankPayment implements Bank {
                @Override
                public Object status() {
                    return new Object();
                }

                @Override
                public void initiatePayments() {
                }
            }

            private static class LoanPayment implements Loan {
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
     * <pre>
     * 高层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象
     * 要面向接口编程，不要面向实现编程
     * </pre>
     *
     * @see <a href="https://www.baeldung.com/java-dependency-inversion-principle">The Dependency Inversion Principle in Java</a>
     */
    private static class DIP {
        private static class CounterExample {
            private static class Math {
                public void knowledge() {
                }
            }

            private static class Student {
                public void learn(Math course) {
                    course.knowledge();
                }
            }
        }

        private static class PositiveExample {
            interface Course {
                void knowledge();
            }

            private static class Math implements Course {
                @Override
                public void knowledge() {
                }
            }

            private static class Chinese implements Course {
                @Override
                public void knowledge() {
                }
            }

            private static class Student {
                public void learn(Course course) {
                    course.knowledge();
                }
            }
        }
    }

    /**
     * 迪米特法则（最少知识原则）
     * <pre>
     * 只与你的直接朋友交谈，不跟陌生人说话
     * 一个软件实体应当尽可能少地与其他软件实体发生相互作用
     * </pre>
     */
    private static class LOD {
        private static class CounterExample {
            private static class Phone {
                // 打开 Phone 可以看到 App
                App app = new App();
                // 打开 Phone 不该看到 Book
                Book book = new Book("Book");

                public void readBook() {
                    app.read(book);
                }
            }

            private static class App {
                public void read(Book book) {
                    System.out.println(book.getTitle());
                }
            }

            @Data
            private static class Book {
                String title;

                public Book(String title) {
                    this.title = title;
                }
            }
        }

        private static class PositiveExample {
            private static class Phone {
                App app = new App();

                public void readBook() {
                    app.read();
                }
            }

            private static class App {
                // 打开 App 可以看到 Book
                Book book = new Book("Book");

                public void read() {
                    System.out.println(book.getTitle());
                }
            }

            @Data
            private static class Book {
                private String title;

                public Book(String title) {
                    this.title = title;
                }
            }
        }
    }

    /**
     * 合成复用原则（组合/聚合复用原则）
     * <p>尽量使用组合(has-a)/聚合(contains-a)而不是继承关系达到软件复用的目的
     */
    private static class CARP {
        private static class CounterExample {
            private static class Car {
            }

            private static class GasolineCar extends Car {
            }

            private static class ElectricCar extends Car {
            }

            private static class WhiteGasolineCar extends GasolineCar {
            }

            private static class BlackGasolineCar extends GasolineCar {
            }

            private static class WhiteElectricCar extends ElectricCar {
            }

            private static class BlackElectricCar extends ElectricCar {
            }
        }

        private static class PositiveExample {
            private static class Color {
            }

            private static class White extends Color {
            }

            private static class Black extends Color {
            }

            private static class Car {
                Color color;
            }

            private static class GasolineCar extends Car {
            }

            private static class ElectricCar extends Car {
            }
        }
    }
}
