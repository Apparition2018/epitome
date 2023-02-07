package knowledge.oop.interface_;

import java.util.Comparator;
import java.util.List;

/**
 * 接口
 * <p>没有 this 指针，不能有构造方法和静态块，不能实例化，支持接口多继承
 * <p>可包含元素：
 * <pre>
 * JDK8-    常量、抽象方法
 * JDK8     默认方法、静态方法
 * JDK9     私有方法、私有静态方法
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://mp.weixin.qq.com/s/Jy0zu5LcNM-EvZFJIY9Eeg">Java8 默认方法和静态方法</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/8/8 19:39
 */
public class Interface {

    // 接口 (隐式 abstract)
    abstract interface A {
        // 变量 (隐式 public static final)
        public static final String VAR = "variable";

        // 抽象方法 (隐式 public abstract)
        public abstract void abstractMethod();

        /**
         * 默认方法 (隐式 public)
         * <pre>
         * 为什么需要默认方法？：如果没有默认方法，在接口添加一个抽象方法，那么所有实现类都必须实现这个方法，即使有些类不需要实现这个方法
         * JDK 使用默认方法的实例：{@link List#sort(Comparator)}
         * </pre>
         *
         * @see <a href="https://www.zhihu.com/question/54479642/answer/139588886">默认方法是一个好的设计吗？</a>
         */
        public default String defaultMethod() {
            return "defaultMethod";
        }

        /**
         * 静态方法 (隐式 public)
         * <pre>
         * 为什么需要静态方法？：与默认方法类似，不过静态方法不可以被实现类重写，适用于提供不允许被重写的公用方法
         * JDK 使用静态方法的实例：{@link Comparator#reverseOrder()}，{@link Comparator#naturalOrder()}
         * </pre>
         */
        public static String staticMethod() {
            return "staticMethod";
        }

        /**
         * 私有方法
         * <p>为什么需要私有方法？：使多个默认方法之间可以共享代码
         *
         * @see <a href="https://openjdk.org/jeps/213">JDK9 JEP 213: Milling Project Coin - Description 5</a>
         */
        private String privateMethod() {
            return "privateMethod";
        }

        /**
         * 私有静态方法
         * <p>为什么需要私有静态方法？：使多个静态方法之间可以共享代码
         */
        private static String privateStaticMethod() {
            return "privateStaticMethod";
        }
    }
}
