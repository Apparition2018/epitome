package knowledge.api.math;

/**
 * RoundingMode
 * RoundingMode 是一个枚举类，为可能丢弃精度的数值操作指定一种舍入行为。
 * https://www.runoob.com/manual/jdk1.6/java/math/RoundingMode.html
 * <p>
 * 不同舍入模式下的舍入操作汇总：
 * 输入数字	UP	    DOWN    CEILING	    FLOOR	    HALF_UP	    HALF_DOWN	HALF_EVEN	        UNNECESSARY
 * -        远离零   向零    向正无限大   向负无限大   距离相等向上  距离相等向下  距离相等向下向相邻偶数 精确结果不需要舍入
 * -                                                四舍五入                银行家舍入法
 * 5.5	    6	    5	    6	        5	        6	        5	        6	                抛出 ArithmeticException
 * 2.5	    3	    2	    3	        2	        3	        2	        2	                抛出 ArithmeticException
 * 1.6	    2	    1	    2	        1	        2	        2	        2	                抛出 ArithmeticException
 * 1.1	    2	    1	    2	        1	        1	        1	        1	                抛出 ArithmeticException
 * 1.0	    1	    1	    1	        1	        1	        1	        1	                1
 * -1.0	    -1	    -1	    -1	        -1	        -1	        -1	        -1	                -1
 * -1.1	    -2	    -1	    -1	        -2	        -1	        -1	        -1	                抛出 ArithmeticException
 * -1.6	    -2	    -1	    -1	        -2	        -2	        -2	        -2	                抛出 ArithmeticException
 * -2.5	    -3	    -2	    -2	        -3	        -3	        -2	        -2	                抛出 ArithmeticException
 * -5.5	    -6	    -5	    -5	        -6	        -6	        -5	        -6	                抛出 ArithmeticException
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class RoundingModeDemo {
}
