package knowledge.types.basic;

import org.junit.jupiter.api.Test;

/**
 * Java 基本数据类型：
 * 1.原始数据类型 (Primitive Data Types)
 * 2.引用类型 (Reference Data Types)
 * <p>
 * 8 种原始数据类型：
 * 4 种整数型：byte, short, int, long    二进制补码表示的证书
 * 2 种浮点型：float, double             IEEE 754 标准的浮点数
 * 1 种字符型：char                      单一16位 unicode 字符
 * 1 种布尔型：boolean
 * 类型       字节数     位数      默认值         最小值                 最大值
 * byte         1       8       0           -2^7(-128)          2^7-1(127)
 * short        2       16      0           -2^15(-32768)       2^15-1(32767)
 * char         2       16      \u0000      0(\u0000)           65535(\uffff)
 * int          4       32      0           -2^31(-2147483648)  2^31-1(2147483647)
 * float        4       32      0.0
 * long         8       64      0           -2^63               2^63-1
 * double       8       64      0.0
 * boolean      1/4     8/32    false       false               true
 * byte类型取值范围为什么是127到-128？：https://blog.csdn.net/heixuanfenghei/article/details/80403644
 * float和double的取值范围？：https://www.zhihu.com/question/46432979/answer/221485161
 * 解决float型数据精度损失问题：https://www.cnblogs.com/ziyu-trip/p/8168881.html
 * boolean类型占几个字节？：https://zhuanlan.zhihu.com/p/138648453
 * <p>
 * 引用类型：
 * 对象、数组都是引用类型
 * 默认值 null
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class BasicDataTypes {

    @Test
    public void testPrimitive() {
        // float 最大值比 long 最大值大
        System.out.println(Float.MAX_VALUE > Long.MAX_VALUE); // true
    }
}
