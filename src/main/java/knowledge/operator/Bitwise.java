package knowledge.operator;

import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * Bitwise  位运算
 * CPU 直接支持，效率最高
 * 使用场景：
 * 1.加密算法
 * 2.图形算法
 * 3.状态位
 * <p>
 * Java 语言位运算符详解：https://zhuanlan.zhihu.com/p/106893096
 * Java 位运算经典应用(一)：https://zhuanlan.zhihu.com/p/107246755
 * Java 位运算经典应用(二)：https://zhuanlan.zhihu.com/p/107426506
 * Java 位运算经典应用(三)：https://zhuanlan.zhihu.com/p/109272709
 * Java 位运算经典应用(四)：https://zhuanlan.zhihu.com/p/110263046
 * Java中对于位运算的优化以及运用与思考：https://zhuanlan.zhihu.com/p/101723848
 *
 * @author ljh
 * created on 2020/11/23 17:57
 */
public class Bitwise {

    /**
     * 变量占据的内存迅速清零
     */
    @Test
    public void testRapidZeroClearing() {
        int x = 99;
        x = x ^ x;
    }

    /**
     * 最有效率的方法算出 2 乘以 8 等于几？
     */
    @Test
    public void testRapidMultiply() {
        p(2 << 3);
    }

    /**
     * 交换两个变量值
     */
    @Test
    public void testSwapValue() {
        int x = 6, y = 9;
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        p("x = " + x + ", y = " + y); // x = 9, y = 6
    }

    /**
     * 用位运算 & 取代 % 取模，也可用来判断奇偶
     * X % 2^n = X & (2^n – 1)
     */
    @Test
    public void testReplaceMod() {
        p(103 % 4);     // 3
        p(103 & 3);     // 3

        p(103 % 64);    // 39
        p(103 & 63);    // 39
    }

    /**
     * 求 x 的绝对值
     */
    @Test
    public void testAbsolute() {
        int x = -12;
        int sign = x >> 31;     // int 为32位，右移31位得到符号位，赋值给 sign，正数 sign == 0，负数 sign == -1
        p((x ^ sign) - sign);   // x ^ 0 = x，x ^ -1 = |x| - 1
    }

    /**
     * 求与 x 向上最接近的2的 n 次方
     */
    @Test
    public void testClosestTwoNPower() {
        p(testTableSizeFor(17));    // 32
        p(testTableSizeFor(31));    // 32
        p(testTableSizeFor(33));    // 64
    }

    /**
     * HashMap 实现方案
     */
    private int testTableSizeFor(int x) {
        int y = x - 1;
        y |= y >>> 1;
        y |= y >>> 2;
        y |= y >>> 4;
        y |= y >>> 8;
        y |= y >>> 16;
        return y < 0 ? 1 : (y >= 1073741824 ? 1073741824 : y + 1);
    }

    /**
     * 计算状态位 1 的个数
     */
    @Test
    public void testCountOne() {
        int x = 99; // 1100011
        int count = 0;
        while (x != 0) {
            count++;
            x = x & (x - 1);
        }
        p(count);   // 4
    }
}
