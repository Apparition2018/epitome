package knowledge.api.lang;

import l.demo.Demo;
import org.junit.Test;

/**
 * Math
 * https://www.runoob.com/manual/jdk1.6/java/lang/Math.html
 * <p>
 * static XXX	    abs(XXX a)              返回 XXX 值的绝对值
 * <p>
 * static double	random()                返回带正号的 double 值，该值 ≥ 0.0 且 ＜ 1.0
 * <p>
 * static XXX	    max(XXX a, XXX b)	    返回两个 XXX 值中较大的一个
 * static XXX	    min(XXX a, XXX b)       返回两个 XXX 值中较小的一个
 * <p>
 * static double	acos(double a)          返回一个值的反余弦；返回的角度范围在 0.0 到 pi 之间
 * static double	asin(double a)          返回一个值的反正弦；返回的角度范围在 -pi/2 到 pi/2 之间
 * static double	atan(double a)          返回一个值的反正切；返回的角度范围在 -pi/2 到 pi/2 之间
 * static double	cos(double a)           返回角的三角余弦
 * static double	sin(double a)           返回角的三角正弦
 * static double	tan(double a)           返回角的三角正切
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class MathDemo extends Demo {

    @Test
    public void constant() {
        // static           double	                E比任何其他值都更接近 e（即自然对数的底数）的 double 值
        p(Math.E);  // 2.718281828459045
        // static           double	PI              比任何其他值都更接近 pi（即圆的周长与直径之比）的 double 值
        p(Math.PI); // 3.141592653589793
    }

    @Test
    public void testMath() {
        // static double	sqrt(double a)          返回正确舍入的 double 值的正平方根
        p(Math.sqrt(4));    // 2.0
        // static double	cbrt(double a)          返回 double 值的立方根
        p(Math.cbrt(8));    // 2.0
        // static double	pow(double a, double b) 返回第一个参数的第二个参数次幂的值
        p(Math.pow(2, 2));  // 4.0

        // static double	log(double a)           返回 double 值的自然对数（底数是 e）
        p(Math.log(2));     // 0.6931471805599453
        // static double	log10(double a)	        返回 double 值的底数为 10 的对数
        p(Math.log10(2));   // 0.3010299956639812

        // static double	ceil(double a)          返回最小的（最接近负无穷大）double 值，该值大于等于参数，并等于某个整数
        p(Math.ceil(4.5));  // 5.0
        // static double	floor(double a)         返回最大的（最接近正无穷大）double 值，该值小于等于参数，并等于某个整数
        p(Math.floor(4.5)); // 4.0
        // static double	rint(double a)          返回最接近参数并等于某一整数的 double 值
        p(Math.rint(4.5));  // 4.0
        // static long	    round(double a)         返回最接近参数的 long
        p(Math.round(4.5)); // 5
    }

    /**
     * 符号
     */
    @Test
    public void sign() {
        // static double    copySign(double/float magnitude, double/float sign)
        // 返回带有第二个浮点参数符号的第一个浮点参数
        p(Math.copySign(1.5, -2)); // -1.5
        
        // static double	signum(double d)
        // 返回参数的符号函数；如果参数为 0，则返回 0；如果参数大于 0，则返回 1.0；如果参数小于 0，则返回 -1.0
        p(Math.signum(99)); // 1.0
        p(Math.signum(0));  // 0.0
        p(Math.signum(-99));// -1.0
    }

    /**
     * static double/float	nextAfter(double/float start, double direction)
     * 返回第一个参数和第二个参数之间与第一个参数相邻的浮点数
     * <p>
     * static double/float	nextUp(double/float d)
     * 返回参数和正无穷大之间与 d 相邻的浮点值
     * <p>
     * static double/float	nextDown(double/float d)
     * 返回参数和负无穷大之间与 d 相邻的浮点值
     */
    @Test
    public void nextXXX() {
        p(Math.nextAfter(1.0, 2.5));// 1.0000000000000002
        p(Math.nextUp(1.0));        // 1.0000000000000002
        p(Math.nextDown(1.0));      // 0.9999999999999999
    }
}
