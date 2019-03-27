package knowledge.包装类;

import org.junit.Test;

/**
 * Number
 * <p>
 * Number 定义了6种数值类型相互转换的方法 (byte, short, int, long, float, double)
 * <p>
 * Number 的子类：
 * AtomicInteger, AtomicLong, BigDecimal, BigInteger
 * Byte, Short, Integer, Long, Float, Double
 */
public class NumberDemo {

    /**
     * byte	            byteValue()
     * 以 byte 形式返回指定的数值
     * <p>
     * abstract  double	doubleValue()
     * 以 double 形式返回指定的数值
     * <p>
     * abstract  float	floatValue()
     * 以 float 形式返回指定的数值
     * <p>
     * abstract  int	intValue()
     * 以 int 形式返回指定的数值
     * <p>
     * abstract  long	longValue()
     * 以 long 形式返回指定的数值
     * <p>
     * short	        shortValue()
     * 以 short 形式返回指定的数值
     */
    @Test
    public void xxxValue() {
        Integer x = 5;

        System.out.println(x.byteValue());  // 5
        System.out.println(x.doubleValue());// 5.0
        System.out.println(x.longValue());  // 5
    }

    // 子类方法

    /**
     * int	compareTo(Short anotherShort)
     * 比较两个 (Byte, Short, Integer, Long, Float, Double) 对象所表示的数值
     */
    @Test
    public void compareTo() {
        Integer x = 5;

        System.out.println(x.compareTo(3)); // 1
        System.out.println(x.compareTo(5)); // 0
        System.out.println(x.compareTo(7)); // -1
    }

    /**
     * static int	    parseInt(String s[, int radix])
     * 使用第二个参数指定的基数，将字符串参数解析为有符号的整数
     * (parseByte, parseShort, parseLong) 同理
     * <p>
     * static double	parseDouble(String s)
     * 返回一个新的 double 值，该值被初始化为用指定 String 表示的值，这与 Double 类的 valueOf 方法一样
     * (parseFloat) 同理
     */
    @Test
    public void parseXXX() {
        int x = Integer.parseInt("b", 16);
        System.out.println(x);  // 11
    }

    /**
     * static Integer	valueOf(int i)
     * 返回一个表示指定的 int 值的 Integer 实例
     * (Byte, Short, Long) 同理
     * <p>
     * static Integer	valueOf(String s[, int radix])
     * 返回一个 Integer 对象，该对象中保存了用第二个参数提供的基数进行解析时从指定的 String 中提取的值
     * (Byte, Short, Long) 同理
     * <p>
     * static Double	valueOf(double d)
     * 返回表示指定的 double 值的 Double 实例
     * (Float) 同理
     * <p>
     * static Double	valueOf(String s)
     * 返回保存用参数字符串 s 表示的 double 值的 Double 对象
     * (Float) 同理
     */
    @Test
    public void valueOf() {
        Integer x = Integer.valueOf("16", 10);
        System.out.println(x);  // 16
    }

}
