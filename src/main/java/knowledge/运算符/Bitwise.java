package knowledge.运算符;

import l.demo.Demo;
import org.junit.Test;

/**
 * Bitwise  位运算
 * CPU 直接支持，效率最高
 * 使用场景：
 * 1.加密算法
 * 2.图形算法
 * <p>
 * Java 语言位运算符详解：https://zhuanlan.zhihu.com/p/106893096
 * Java 位运算经典应用(一)：https://zhuanlan.zhihu.com/p/107246755
 * Java 位运算经典应用(二)：https://zhuanlan.zhihu.com/p/107426506
 * Java 位运算经典应用(三)：https://zhuanlan.zhihu.com/p/109272709
 * Java中对于位运算的优化以及运用与思考：https://zhuanlan.zhihu.com/p/101723848
 *
 * @author ljh
 * created on 2020/11/23 17:57
 */
public class Bitwise extends Demo {

    /**
     * 变量占据的内存迅速清零
     */
    @Test
    public void testRapidZeroClearing() {
        int a = 99;
        a = a ^ a;
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
        int i = 6;
        int j = 9;

        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
        p("i = " + i + ", j = " + j);
    }

    /**
     * 用位运算 & 取代 % 取模
     * X % 2^n = X & (2^n – 1)
     */
    @Test
    public void testReplaceMod() {
        p(103 % 4);
        p(103 & 3);

        p(103 % 64);
        p(103 & 63);
    }
}
