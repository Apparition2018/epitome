package jar.hutool.util;

import cn.hutool.core.util.NumberUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.math.RoundingMode;

/**
 * NumberUtil
 * 针对数学运算做工具性封装
 * <p>
 * NumberUtil.factorial()       阶乘
 * NumberUtil.sqrt()            平方根
 * NumberUtil.divisor()         最大公约数
 * NumberUtil.multiple()        最小公倍数
 * NumberUtil.getBinaryStr()    获得数字对应的二进制字符串
 * NumberUtil.binaryToInt()     二进制转 int
 * NumberUtil.binaryToLong()    二进制转 long
 * NumberUtil.compare()         比较两个值的大小
 * NumberUtil.toStr()           数字转字符串，自动并去除尾小数点儿后多余的 0
 * <p>
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E6%95%B0%E5%AD%97%E5%B7%A5%E5%85%B7-NumberUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/NumberUtil.html
 *
 * @author ljh
 * @since 2020/10/27 14:50
 */
public class NumberUtilDemo extends Demo {

    @Test
    public void testNumberUtil() {
        // 加减乘除
        // 都会将 double 转为 BigDecimal 后计算，解决 float 和 double 类型无法进行精确计算的问题，常用于商业计算。
        p(NumberUtil.div(10, 6, 2, RoundingMode.HALF_EVEN));    // 3.33

        // 舍入
        p(NumberUtil.round(2.25, 1, RoundingMode.HALF_EVEN));   // 2.2  
        p(NumberUtil.roundStr(5.55, 1, RoundingMode.HALF_EVEN));// 5.6

        // 随机数 (不重复的)
        p(NumberUtil.generateRandomNumber(0, 999, 10));         // [296, 100, 985, 868, 87, 952, 412, 850, 40, 254]
        p(NumberUtil.generateRandomNumber(0, 999, 5,
                new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9}));         // [9, 1, 4, 0, 7]
        p(NumberUtil.generateBySet(0, 999, 10));                // [736, 352, 434, 450, 258, 851, 547, 185, 155, 413]

        // 有序整数列表
        p(NumberUtil.range(12));                                // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
        p(NumberUtil.range(2, 12));                             // [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
        p(NumberUtil.range(2, 12, 2));                          // [2, 4, 6, 8, 10, 12]
        p(NumberUtil.appendRange(2, 12, 2, list));              // [1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 4, 6, 8, 10, 12]
    }

    /**
     * 0                    取一位整数
     * 0.00                 取一位整数和两位小数
     * 00.000               取两位整数和三位小数
     * #                    取所有整数部分
     * #.##%                以百分比方式计数，并取两位小数
     * #.#####E0            显示为科学计数法，并取五位小数
     * ,###                 每三位以逗号进行分隔，例如：299,792,458
     * 光速大小为每秒,###米   将格式嵌入文本
     */
    @Test
    public void testDecimalFormat() {
        p(NumberUtil.decimalFormat(",###", 299792458)); // 299,792,458
    }

    @Test
    public void testIs() {
        // 数字
        p(NumberUtil.isNumber("1"));
        // 有效数字
        p(NumberUtil.isValidNumber(Double.POSITIVE_INFINITY));
        // Integer
        p(NumberUtil.isInteger("2147483648"));  // false
        // Long
        p(NumberUtil.isLong("2147483648"));     // true
        // 浮点数
        p(NumberUtil.isDouble("1"));
        // 质数
        p(NumberUtil.isPrimes(3));
        // 相邻
        p(NumberUtil.isBeside(3, 4));           // true
        p(NumberUtil.isBeside(3, 5));           // false
        // 2 的 n 次幂
        p(NumberUtil.isPowerOfTwo(8));          // true
    }

}
