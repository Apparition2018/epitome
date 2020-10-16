package jar.apache.commons.lang3.time;

import l.demo.Demo;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * StopWatchDemo
 * <p>
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/StopWatch.html
 *
 * @author ljh
 * created on 2020/10/16 11:04
 */
public class StopWatchDemo extends Demo {

    @Test
    public void testStopWatch() throws InterruptedException {
        // 创建一个 StopWatch，并且马上开始
        // 相当于 StopWatch watch = StopWatch.create(); watch.start();
        StopWatch watch = StopWatch.createStarted();

        TimeUnit.MILLISECONDS.sleep(100);
        watch.split();
        p("从开始到现在的运行时间：" + watch.getTime() + "ms");                     // 从开始到现在的运行时间：100ms
        p("从开始到现在的运行时间：" + watch.getTime(TimeUnit.MILLISECONDS) + "ms");// 从开始到现在的运行时间：100ms

        TimeUnit.MILLISECONDS.sleep(100);
        p("从开始到现在的运行时间：" + watch.getTime() + "ms");                     // 从开始到现在的运行时间：200ms
        p("从开始到切点的运行时间：" + watch.getSplitTime() + "ms");                // 从开始到切点的运行时间：100ms

        // 重置，之后必须 开始
        watch.reset();
        watch.start();
        TimeUnit.MILLISECONDS.sleep(100);
        p("从开始到现在的运行时间：" + watch.getTime() + "ms");                     // 从开始到现在的运行时间：100ms

        // 暂停
        watch.suspend();
        TimeUnit.MILLISECONDS.sleep(100);
        // 恢复暂停
        watch.resume();
        p("从开始到现在的运行时间：" + watch.getTime() + "ms");                     // 从开始到现在的运行时间：100ms

        // 停止
        watch.stop();

    }
}
