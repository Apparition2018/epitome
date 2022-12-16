package knowledge.oop.interface_;

/**
 * 接口
 * <pre>
 * 没有 this 指针，没有构造方法，静态快，不能实例化，不能保存状态
 * 变量只能是 public static final，而且隐式
 * 方法只能是 abstract, default, static
 * 支持接口多继承
 * </pre>
 * <pre>
 * 1 接口隐式 abstract
 * 2 变量隐式 public static final
 * 3 方法隐式 public，抽象方法隐式 abstract
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://mp.weixin.qq.com/s/LEbhb5H2AMQOqvK_wTIVnA">Java8 接口：静态方法和默认方法</a>
 * <a href="https://zhuanlan.zhihu.com/p/108274393">Java8 接口中方法可以不是抽象的</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/8/8 19:39
 */
public class Interface {

    // 接口隐式 abstract
    interface A {

        // 变量隐式 public static final
        String VAR = "variable";

        /**
         * 默认方法隐式 public
         * <pre>
         * 1 方法前面加 default 关键字
         * 2 必须是实现方法（有方法体）
         * 3 隐式 public
         * </pre>
         * 要点：
         * <pre>
         * 1 扩展接口，而不必担心破坏实现类，因为默认方法无须子类去实现
         * 2 弥补了接口和抽象类之间的差异
         * 3 避免使用工具类，例如所有 Collections 类方法都可以在接口本身中提供
         * 4 不能重写 java.lang.Object 中的方法
         * 5 又称为 Defender Method 和 Virtual Extension Method
         * </pre>
         * JDK 使用默认方法的实例：Collections.sort(list, comparator) 里调用的 list.sort(comparator) 就是 List 接口的默认方法
         *
         * @see <a href="https://www.zhihu.com/question/54479642/answer/139588886">默认方法是一个好的设计吗？</a>
         * @see <a href="https://mp.weixin.qq.com/s/Jy0zu5LcNM-EvZFJIY9Eeg">Java8 打破一切你对接口的认知</a>
         */
        default String defaultMethod() {
            return "defaultMethod";
        }

        /**
         * 静态方法隐式 public
         * <p>可以把常用的工具方法直接写在接口上，可以更好地组织代码，更易阅读和使用
         * <p>JDK 使用静态方法的实例：Comparator.reverseOrder()，通过接口直接获取比较器对象
         */
        static String staticMethod() {
            return "staticMethod";
        }

        // 抽象方法隐式 public abstract
        void abstractMethod(String name);
    }
}
