package knowledge.syntactic.sugar.lambda;

import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * Lambda
 * Lambda 允许把函数作为一个方法的参数
 * 1.可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 * 2.可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 * 3.可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 * 4.可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 * http://www.runoob.com/java/java8-lambda-expressions.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class Lambda {

    /**
     * Lambda 表达式主要用来定义行内执行的方法类型接口。
     * Lambda 表达式免去了使用匿名方法的麻烦，并且给予 Java 简单但是强大的函数化的编程能力。
     */
    @Test
    public void testLambda() {
        // 声明类型
        MathOperation addition = (int a, int b) -> a + b;

        // 不声明类型
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (a, b) -> {
            return a * b;
        };

        // 没有大括号和返回语句
        MathOperation division = (a, b) -> a / b;

        Lambda lambda = new Lambda();
        p("10 + 5 = " + lambda.operate(10, 5, addition));
        p("10 - 5 = " + lambda.operate(10, 5, subtraction));
        p("10 x 5 = " + lambda.operate(10, 5, multiplication));
        p("10 / 5 = " + lambda.operate(10, 5, division));

        // 不用括号
        GreetingService greetingService = message -> p("Hello " + message);
        greetingService.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    /**
     * 不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误
     * lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
     */
    @Test
    public void testChangeVariable() {
        String salutation = "Hello! ";
        GreetingService greetingService = message -> {
            // salutation += ""; // Variable used in lambda expression should be final or effectively final
            p(salutation + message);
        };
        greetingService.sayMessage("Google");
    }

}
