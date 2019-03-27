package knowledge.api.lang.math;

import org.junit.Test;

/**
 * Math
 * <p>
 * static double	E                       比任何其他值都更接近 e（即自然对数的底数）的 double 值
 * static double	PI                      比任何其他值都更接近 pi（即圆的周长与直径之比）的 double 值
 * <p>
 * static XXX	    abs(XXX a)              返回 XXX 值的绝对值
 * <p>
 * static double	acos(double a)          返回一个值的反余弦；返回的角度范围在 0.0 到 pi 之间
 * static double	asin(double a)          返回一个值的反正弦；返回的角度范围在 -pi/2 到 pi/2 之间
 * static double	atan(double a)          返回一个值的反正切；返回的角度范围在 -pi/2 到 pi/2 之间
 * static double	cos(double a)           返回角的三角余弦
 * static double	sin(double a)           返回角的三角正弦
 * static double	tan(double a)           返回角的三角正切
 * <p>
 * static double	cbrt(double a)          返回 double 值的立方根
 * static double	pow(double a, double b) 返回第一个参数的第二个参数次幂的值
 * static double	sqrt(double a)          返回正确舍入的 double 值的正平方根
 * <p>
 * static double	ceil(double a)          返回最小的（最接近负无穷大）double 值，该值大于等于参数，并等于某个整数
 * static double	floor(double a)         返回最大的（最接近正无穷大）double 值，该值小于等于参数，并等于某个整数
 * static double	rint(double a)          返回最接近参数并等于某一整数的 double 值
 * static long	    round(double a)         返回最接近参数的 long
 * <p>
 * static double	log(double a)           返回 double 值的自然对数（底数是 e）
 * <p>
 * static double	random()                返回带正号的 double 值，该值大于等于 0.0 且小于 1.0
 */
public class MathDemo {


    /**
     * static double	copySign(double/float magnitude, double/float sign)
     * 返回带有第二个浮点参数符号的第一个浮点参数
     */
    @Test
    public void copySign() {
        System.out.println(Math.copySign(1.5, -2)); // -1.5
    }


    /**
     * static double	IEEEremainder(double f1, double f2)
     * 按照 IEEE 754 标准的规定，对两个参数进行余数运算
     */
    @Test
    public void IEEEremainder() {
        System.out.println(Math.IEEEremainder(10, 3)); // -1
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
        System.out.println(Math.nextAfter(1.0, 2.5));   // 1.0000000000000002

        System.out.println(Math.nextUp(1.0));           // 1.0000000000000002
        System.out.println(Math.nextDown(1.0));         // 0.9999999999999999
    }
}
