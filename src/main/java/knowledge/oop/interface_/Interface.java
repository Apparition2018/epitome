package knowledge.oop.interface_;

/**
 * 接口
 * 1.没有 this 指针，没有构造方法，静态快，不能实例化，不能保存状态
 * 2.变量只能是 public static final，而且隐式
 * 3.方法只能是 abstract, default, static
 * 4.支持接口多继承
 * <p>
 * 1.接口隐式 abstract
 * 2.变量隐式 public static final
 * 3.方法隐式 public，抽象方法隐式 abstract
 * <p>
 * JDK1.8 接口改变：静态方法与默认方法：https://mp.weixin.qq.com/s/LEbhb5H2AMQOqvK_wTIVnA
 * JDK1.8 接口中方法可以不是抽象的：https://zhuanlan.zhihu.com/p/108274393
 *
 * @author ljh
 * created on 2020/8/8 19:39
 */
public class Interface {

    // 接口隐式 abstract
    interface A {

        // 变量隐式 public static final
        String VAR = "variable";

        /**
         * 默认方法隐式 public
         * <p>
         * 1.方法前面加 default 关键字
         * 2.必须是实现方法（有方法体）
         * 3.隐式 public
         * <p>
         * 要点：
         * 1.扩展接口，而不必担心破坏实现类，因为默认方法无须子类去实现
         * 2.弥补了接口和抽象类之间的差异
         * 3.避免使用工具类，例如所有 Collections 类方法都可以在接口本身中提供
         * 4.不能重写 java.lang.Object 中的方法
         * 5.又称为 Defender Method 和 Virtual Extension Method
         * <p>
         * JDK 使用默认方法的实例：Collections.sort(list, comparator) 里调用的 list.sort(comparator) 就是 List 接口的默认方法
         * <p>
         * 默认方法是一个好的设计吗？-知乎：https://www.zhihu.com/question/54479642/answer/139588886
         * Java 8 有多牛逼？打破一切你对接口的认知！：https://mp.weixin.qq.com/s/Jy0zu5LcNM-EvZFJIY9Eeg
         */
        default String defaultMethod() {
            return "defaultMethod";
        }

        /**
         * 静态方法隐式 public
         * 可以把常用的工具方法直接写在接口上，可以更好地组织代码，更易阅读和使用
         * <p>
         * JDK 使用静态方法的实例：Comparator.reverseOrder()，通过接口直接获取比较器对象
         */
        static String staticMethod() {
            return "staticMethod";
        }

        // 抽象方法隐式 public abstract
        void abstractMethod(String name);
    }
}
