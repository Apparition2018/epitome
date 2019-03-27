package knowledge.日期和时间;

import org.junit.Test;

import java.time.Instant;
import java.util.Date;

/**
 * Date
 * <p>
 * boolean	    before(Date when)               测试此日期是否在指定日期之前
 * boolean	    after(Date when)                测试此日期是否在指定日期之后
 * int	        compareTo(Date anotherDate)     比较两个日期的顺序
 * boolean	    equals(Object obj)              比较两个日期的相等性
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
 */
public class DateDemo {

    private Date date;

    public DateDemo() {
        date = new Date(1000000000000L);
    }


    /**
     * Object	    clone()                         返回此对象的副本
     */
    @Test
    public void clone_() {
        Object date2 = date.clone();
        p(date2);               // Wed May 18 11:33:20 CST 2033
    }


    /**
     * int	        hashCode()
     * 返回此对象的哈希码值
     * 结果是 getTime() 方法返回的基本 long 值的两部分的异或
     * 也就是说，哈希码就是以下表达式的值：
     * (int)(this.getTime()^(this.getTime() >>> 32))
     */
    @Test
    public void hashCode_() {
        p(date.hashCode());     // -727379736
    }

    /**
     * long	        getTime()
     * 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数
     * <p>
     * void	        setTime(long time)
     * 设置此 Date 对象，以表示 1970 年 1 月 1 日 00:00:00 GMT 以后 time 毫秒的时间点
     */
    @Test
    public void time() {
        p(date.getTime());      // 1000000000000

        date.setTime(2000000000000L);
        p(date);                // Wed May 18 11:33:20 CST 2033

    }

    /**
     * Instant	    toInstant()                 Date → Instant
     * static Date	from(Instant instant)       Instant → Date
     */
    @Test
    public void instant() {
        Instant instant = date.toInstant();
        p(instant);             // 2001-09-09T01:46:40Z

        date = Date.from(instant);
        p(date);                // Sun Sep 09 09:46:40 CST 2001
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }


}
