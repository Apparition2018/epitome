package knowledge.types.basic;

import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * Java 基本数据类型：
 * 1.原始数据类型 (Primitive Data Types)
 * 2.引用类型 (Reference Data Types)
 * <p>
 * 8 种原始数据类型：
 * 4 种整数型：byte, short, int, long
 * 2 种浮点型：float, double
 * 1 种字符型：char
 * 1 种布尔型：boolean
 * <p>
 * 引用类型：
 * 对象、数组都是引用类型
 * 默认值 null
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class BasicDataTypes {

    /**
     * byte 是8位、有符号的，以二进制补码表示的整数
     * 默认值 0
     * <p>
     * 在大型数组中代替 int，节约空间。因为 byte 占用空间是 int 的四分之一
     */
    @Test
    public void byte_() {
        p("二进制位数：Byte.SIZE = " + Byte.SIZE);        // 8
        p("最小值：Byte.MIN_VALUE = " + Byte.MIN_VALUE); // -128 (-2^7)
        p("最大值：Byte.MAX_VALUE = " + Byte.MAX_VALUE); // 127 (2^7-1)
    }

    /**
     * short 是16位、有符号的，以二进制补码表示的整数
     * 默认值 0
     */
    @Test
    public void short_() {
        p("二进制位数：Short.SIZE = " + Short.SIZE);        // 16
        p("最小值：Short.MIN_VALUE = " + Short.MIN_VALUE); // -32768 (-2^15)
        p("最大值：Short.MAX_VALUE = " + Short.MAX_VALUE); // 32767 (2^15-1)
    }

    /**
     * int 是32位、有符号的，以二进制补码表示的整数
     * 默认值 0
     * 整数的默认类型是 int
     */
    @Test
    public void int_() {
        p("二进制位数：Integer.SIZE = " + Integer.SIZE);       // 32
        p("最小值：Integer.MIN_VALUE = " + Integer.MIN_VALUE); // -2147483648 (-2^31)
        p("最大值：Integer.MAX_VALUE = " + Integer.MAX_VALUE); // 2147483647 (2^31-1)
    }

    /**
     * long 是32位、有符号的，以二进制补码表示的整数
     * 默认值 0L
     * <p>
     * 主要使用在需要比较大整数的系统上
     */
    @Test
    public void long_() {
        p("二进制位数：Long.SIZE = " + Long.SIZE);       // 64
        p("最小值：Long.MIN_VALUE = " + Long.MIN_VALUE); // -9223372036854775808 (-2^63)
        p("最大值：Long.MAX_VALUE = " + Long.MAX_VALUE); // 9223372036854775807 (2^63-1)
    }

    /**
     * float 是单精度、32位、符合 IEEE 754 标准的浮点数
     * 默认值 0.0f
     * 不能用来表示精确的值 (https://www.cnblogs.com/ziyu-trip/p/8168881.html)
     * <p>
     * 在大型浮点数组中代替 double，节约空间
     */
    @Test
    public void float_() {
        p("二进制位数：Float.SIZE = " + Float.SIZE);       // 32
        p("最小值：Float.MIN_VALUE = " + Float.MIN_VALUE); // 1.4E-45
        p("最大值：Float.MAX_VALUE = " + Float.MAX_VALUE); // 3.4028235E38
    }

    /**
     * double 是双精度、64位、符合 IEEE 754 标准的浮点数
     * 默认值 0.0
     * 不能用来表示精确的值
     * 浮点数默认类型时 double
     */
    @Test
    public void double_() {
        p("二进制位数：Double.SIZE = " + Double.SIZE);       // 64
        p("最小值：Double.MIN_VALUE = " + Double.MIN_VALUE); // 4.9E-324
        p("最大值：Double.MAX_VALUE = " + Double.MAX_VALUE); // 1.7976931348623157E308
    }

    /**
     * char 是一个单一的16位 unicode 字符
     * 默认值 \u0000 (空格)
     */
    @Test
    public void char_() {
        p("二进制位数：Character.SIZE = " + Character.SIZE);             // 16
        p("最小值：Character.MIN_VALUE = " + (int) Character.MIN_VALUE); // 0 (\u0000)
        p("最大值：Character.MAX_VALUE = " + (int) Character.MAX_VALUE); // 65535 (\uffff)
    }

    /*
     * boolean 只有两个值 true 和false
     * 默认值 false
     */

}
