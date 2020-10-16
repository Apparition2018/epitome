package knowledge.基本数据类型;

import l.demo.Demo;
import org.junit.Test;

/**
 * 转换
 */
public class Conversion extends Demo {

    /**
     * 自动类型转换:
     * <p>
     * 低  ------------------------------------------->  高
     * byte, short, char --> int --> long --> float --> double
     */
    @Test
    public void auto() {
        char a = 'a';
        p(a + 1); // 98
    }

    /**
     * 数据类型转换必须满足一下规则：
     * 1.不能对 boolean 类型进行类型转换
     * 2.不能把对象类型转换成不相关类的对象
     * 3.在把容量大的类型转换为容量小的类型时必须使用强制类型转换
     * 4.转换过程中可能导致溢出或精度损失
     * 5.浮点数到整数的转换是通过舍弃小数得到的，而不是四舍五入
     */
    @Test
    public void rule() {
        // 强制转换，转换的数据类型必须是兼容的
        // 精度损失
        int i = 128;
        byte b = (byte) i;
        p("b = " + b);     // -128

        // 浮点数 ==> 整数，舍弃小数
        p((int) 23.7);     // 23
        p((int) -45.89f);  // -45
    }

    // int <=> String
    @Test
    public void cnvIntAndString() {
        int i;
        String s;

        // String → int
        i = Integer.parseInt("2");
        i = Integer.valueOf("2");

        // int → String
        s = String.valueOf(2);
        s = Integer.toString(2);
        s = "" + 2;

    }

}
