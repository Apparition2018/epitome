package knowledge.api.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/math/RoundingMode.html">RoundingMode</a>
 * <p>RoundingMode 是一个枚举类，为可能丢弃精度的数值操作指定一种舍入行为。
 * <p>不同舍入模式下的舍入操作汇总：
 * <pre>
 * 输入数字   UP    DOWN    CEILING     FLOOR      HALF_UP    HALF_DOWN   HALF_EVEN         UNNECESSARY
 * 舍入模式  远离零   向零   向正无穷大   向负无穷大   距离相等向上  距离相等向下  距离相等向相邻偶数   精确结果不需要舍入
 *                                                四舍五入                银行家舍入法
 * 5.5      6       5       6           5           6           5           6       抛出 ArithmeticException
 * 2.5      3       2       3           2           3           2           2       抛出 ArithmeticException
 * 1.6      2       1       2           1           2           2           2       抛出 ArithmeticException
 * 1.1      2       1       2           1           1           1           1       抛出 ArithmeticException
 * 1.0      1       1       1           1           1           1           1       1
 * -1.0     -1      -1      -1          -1          -1          -1          -1      -1
 * -1.1     -2      -1      -1          -2          -1          -1          -1      抛出 ArithmeticException
 * -1.6     -2      -1      -1          -2          -2          -2          -2      抛出 ArithmeticException
 * -2.5     -3      -2      -2          -3          -3          -2          -2      抛出 ArithmeticException
 * -5.5     -6      -5      -5          -6          -6          -5          -6      抛出 ArithmeticException
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class RoundingModeDemo {

    public static void main(String[] args) {
        // multiply
        System.out.println(new BigDecimal("99.99").multiply(new BigDecimal("0.3")).setScale(2, RoundingMode.HALF_UP)); // 30.00

        // divide
        System.out.println(new BigDecimal(1).divide(new BigDecimal(3), 2, RoundingMode.HALF_UP));   // 0.33
        System.out.println(new BigDecimal(2).divide(new BigDecimal(3), 2, RoundingMode.HALF_UP));   // 0.67
    }
}
