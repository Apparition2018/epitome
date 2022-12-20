package jar.apache.commons.lang3.math;

import org.apache.commons.lang3.math.Fraction;

/**
 * <a href="https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/math/Fraction.html">Fraction</a> 分数
 *
 * @author ljh
 * @since 2022/11/9 10:19
 */
public class FractionDemo {

    public static void main(String[] args) {
        Fraction fraction1 = Fraction.getFraction(3, 4);
        Fraction fraction2 = Fraction.getFraction(1, 4);

        System.out.println("3/4 + 1/4 = " + fraction1.add(fraction2).intValue());           // 3/4 + 1/4 = 1
        System.out.println("3/4 - 1/4 = " + fraction1.subtract(fraction2).floatValue());    // 3/4 - 1/4 = 0.5
        System.out.println("3/4 * 1/4 = " + fraction1.multiplyBy(fraction2).floatValue());  // 3/4 * 1/4 = 0.1875
        System.out.println("3/4 / 1/4 = " + fraction1.divideBy(fraction2).floatValue());    // 3/4 / 1/4 = 3.0
    }
}
