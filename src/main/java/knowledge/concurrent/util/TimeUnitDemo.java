package knowledge.concurrent.util;

import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * TimeUnit
 * TimeUnit 表示给定单元粒度的时间段，它提供在这些单元中进行跨单元转换和执行计时及延迟操作的实用工具方法。
 * TimeUnit 是一个枚举类，NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS
 * TimeUnit 主要用于通知基于时间的方法如何解释给定的计时参数。
 * https://www.runoob.com/manual/jdk1.6/java/util/concurrent/TimeUnit.html
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class TimeUnitDemo extends Demo {

    /**
     * long	    convert(long sourceDuration, TimeUnit sourceUnit)
     * 将给定单元的时间段转换到此单元
     */
    @Test
    public void convert() {
        p(TimeUnit.MICROSECONDS.convert(1, TimeUnit.SECONDS)); // 1e6
        p(TimeUnit.NANOSECONDS.convert(1, TimeUnit.SECONDS));  // 1e9
    }

    /**
     * void	    sleep(long timeout)
     * 使用此单元执行 Thread.sleep.这是将时间参数转换为 Thread.sleep 方法所需格式的便捷方法
     */
    @Test
    public void sleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        p("end!");
    }

    /**
     * long	    toXXX(long duration)
     * 等效于 XXX.convert(duration, this)
     */
    @Test
    public void toXXX() {
        p(TimeUnit.SECONDS.toMicros(1));   // 1e6
        p(TimeUnit.SECONDS.toNanos(1));    // 1e9
    }

    /**
     * void	    timedJoin(Thread thread, long timeout)
     * 使用此时间单元执行计时的 Thread.join
     */
    @Test
    public void timeJoin() throws InterruptedException {
        TimeUnit.SECONDS.timedJoin(Thread.currentThread(), 3);
        p("end!");
    }

    /**
     * void	    timedWait(Object obj, long timeout)
     * 使用此时间单元执行计时的 Object.wait
     */
    @Test
    public void timedWait() throws InterruptedException {
        Object lock = new Object();
        synchronized (lock) {
            TimeUnit.SECONDS.timedWait(lock, 2);
        }
        p("end!");
    }

}
