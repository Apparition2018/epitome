package knowledge.operator;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Objects;

import static l.demo.Demo.p;

/**
 * 运算符
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class Operators {

    /**
     * 相等运算符
     *
     * @see <a href="http://xiaohuafyle.iteye.com/blog/1473335">String 缓冲池概念的举例说明</a>
     */
    @Test
    public void equality() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";

        String s4 = "ab";
        p("s3==s4? " + (s3 == s4)); // true

        String s5 = "a" + "b";
        p("s3==s5? " + (s3 == s5)); // true

        String s6 = s1 + s2;
        p("s3==s6? " + (s3 == s6)); // false

        String s7 = new String("ab");
        p("s3==s7? " + (s3 == s7)); // false

        String s8 = s3.intern();
        p("s3==s8? " + (s3 == s8)); // true

        final String s9 = "a";
        final String s10 = "b";
        String s11 = s9 + s10;
        p("s3==s11? " + (s3 == s11)); // true
    }

    /**
     * 三目运算符
     * <p>转换规则：
     * <pre>
     * 1 若两个操作数不可转换，则不作转换，返回值是Object类型
     * 2 若两个操作数是明确类型的表达式(比如变量)，则按照正常的二进制数字转换，int转为long，long转为float等
     * 3 若两个操作数中有一个是数字S，另外一个是表达式，且其类型标志位T，那么，若数字S在T的范围内，则转换为T类型；若S超出了T的范围，则T转换为S
     * 4 若两个操作数都是直接量数字，则返回值类型范围较大者
     * </pre>
     * 阿里编程规约：
     * <pre>
     * 三目运算符 condition ? 表达式 1 : 表达式 2 中，高度注意表达式 1 和 2 在类型对齐时，可能抛出因自动拆箱导致的 NPE 异常
     *  1 表达式 1 或 表达式 2 的值只要有一个是原始类型。
     *  2 表达式 1 或 表达式 2 的值的类型不一致，会强制拆箱升级成表示范围更大的那个类型。
     * </pre>
     */
    @Test
    public void ternary() {
        Serializable serializable = true ? "abc" : 1;
        p(serializable);            // abc

        String s1 = String.valueOf(true ? 90 : 100);
        String s2 = String.valueOf(true ? 90 : 100.0);
        p(Objects.equals(s1, s2));  // false

        Integer a = 1;
        Integer b = 2;
        Integer c = null;
        p(false ? a * b : c);       // NullPointerException
    }

    /**
     * 位运算符
     */
    @Test
    public void bitWise() {
        int a = 60; // 0011 1100
        int b = 13; // 0000 1101
        int c;

        c = a & b;  // 0000 1100
        p("a & b = " + c); // 12

        c = a | b;  // 0011 1101
        p("a | b = " + c); // 61

        c = a ^ b;  // 0011 0001
        p("a ^ b = " + c); // 49

        c = ~a;     // 1100 0011 前面24个1
        p("~a = " + c);    // -61

        c = a << 2; // 1111 0000
        p("a << 2 = " + c);// 240

        c = a >> 2; // 0000 1111
        p("a >> 2 = " + c);// 15

        c = a >>> 2;// 0000 1111
        p("a >> 2 = " + c);// 15
    }

    /**
     * instanceof
     * <pre>
     * 用于操作对象实例，如果被比较的对象兼容于右侧类型，该运算符仍然返回 true
     * (Object reference variable) instanceof (Class/Interface type)
     * 在无泛型限制定义的集合赋值给泛型限制的集合时，在使用集合元素时，需要进行instanceof 判断，避免抛出 ClassCastException 异常（阿里编程规约）
     * </pre>
     */
    @Test
    public void instanceof_() {
        ops("3.3");
        ops(3.3D);
    }

    /**
     * @see <a href="https://openjdk.org/jeps/394">JDK16 JEP 394: Pattern Matching for instanceof</a>
     */
    private void ops(Object obj) {
        if (obj instanceof String str) {
            p("String: " + str);
        } else if (obj instanceof Double d) {
            p("double: " + d);
        }
    }
}
