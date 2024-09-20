package knowledge.oop.class_;

/**
 * 封闭类
 * <p>Java 限制可扩展性的方法：
 * <pre>
 * 1 private 类      不公开，只能内部使用
 * 2 final 类        彻底放弃了可扩展性
 * 3 sealed 类       把可扩展控制在可预测和可控制的范围内
 * </pre>
 *
 * @author ljh
 * @see <a href="https://blog.csdn.net/qq_43284469/article/details/126415220">封闭类</a>
 * @see <a href="https://achang.blog.csdn.net/article/details/126573767">空指针烦恼</a>
 * @see <a href="https://openjdk.org/jeps/409">JDK17 JEP 409: Sealed Classes</a>
 * @since 2023/2/16 10:10
 */
public class SealedClass {

    /**
     * 封闭类
     * <pre>
     * 1 使用 sealed 类修饰符
     * 2 在 extends 和 implements 语句之后，使用 permits 指定允许扩展该封闭类的子类
     * 3 如果子类和封闭类在同一源代码文件，可以不使用 permits 语句，java 编译器将在编译期检索文件为封闭类添加上许可的子类
     * </pre>
     */
    private abstract sealed static class Shape permits Circle, Square {
    }

    /**
     * 许可类/封闭类
     * <pre>
     * 1 许可类必须和封闭类处于同一模块(module)或包空间(package)，也就是说，在编译时，封闭类必须可以访问它的许可类
     * 2 许可类必须是封闭类的直接扩展类
     * 3 许可类必须声明是否继续保持封闭
     *   3.1 许可类声明为终极类(final)，关闭扩展性
     *   3.2 许可类声明为封闭类(sealed)，延续受限制的扩展性
     *   3.3 许可类声明为解封类(non-sealed)，不受限制的扩展性
     * </pre>
     */
    private static sealed class Square extends Shape permits ColoredSquare {
    }

    /**
     * 许可类/终极类
     */
    private static final class ColoredSquare extends Square {
    }

    /**
     * 许可类/解封类
     */
    private static non-sealed class Circle extends Shape {
    }


    private static class ColoredCircle extends Circle {
    }
}
