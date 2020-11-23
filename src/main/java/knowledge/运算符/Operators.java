package knowledge.运算符;

import l.demo.Demo;
import org.junit.Test;

/**
 * 运算符
 */
public class Operators extends Demo {

    /**
     * 相等运算符
     * String 缓冲池概念的举例说明：http://xiaohuafyle.iteye.com/blog/1473335
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
     * 用于操作对象实例，如果被比较的对象兼容于右侧类型，该运算符仍然返回 true
     * (Object reference variable) instanceof (Class/Interface type)
     */
    @Test
    public void instanceof_() {
        Double d = 3.3;
        p(d instanceof Number); // true
    }

}