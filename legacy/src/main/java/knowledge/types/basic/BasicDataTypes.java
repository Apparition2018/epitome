package knowledge.types.basic;

/**
 * Java 基本数据类型：
 * <pre>
 * 1 Primitive  原始数据类型
 * 2 Reference  引用类型
 * </pre>
 * 8 种原始数据类型：
 * <pre>
 * 4 种整数型：byte, short, int, long    二进制补码表示的证书
 * 2 种浮点型：float, double             IEEE 754 标准的浮点数
 * 1 种字符型：char                      单一16位 unicode 字符
 * 1 种布尔型：boolean
 *
 * 类型       字节数     位数      默认值         最小值                 最大值
 * byte         1       8       0           -2^7(-128)          2^7-1(127)
 * short        2       16      0           -2^15(-32768)       2^15-1(32767)
 * char         2       16      \u0000      0(\u0000)           65535(\uffff)
 * int          4       32      0           -2^31(-2147483648)  2^31-1(2147483647)
 * float        4       32      0.0
 * long         8       64      0           -2^63               2^63-1
 * double       8       64      0.0
 * boolean      1/4     8/32    false       false               true
 * </pre>
 * <a href="https://zhuanlan.zhihu.com/p/138648453">boolean类型占几个字节？</a>
 * <pre>
 * 1 4字节：单独使用时，使用 int 相关指令代替
 * 2 1字节：以数组形式使用时，公用 byte 数组的 baload 和 bastore 指令
 * </pre>
 *
 * @author ljh
 * @see <a href="https://blog.csdn.net/heixuanfenghei/article/details/80403644">byte类型取值范围为什么是127到-128？</a>
 * @see <a href="https://www.zhihu.com/question/46432979/answer/221485161">float vs double</a>
 * @since 2019/8/8 19:39
 */
public class BasicDataTypes {

    public static void main(String[] args) {
        // float 最大值比 long 最大值大
        System.out.println(Float.MAX_VALUE > Long.MAX_VALUE); // true
    }
}
