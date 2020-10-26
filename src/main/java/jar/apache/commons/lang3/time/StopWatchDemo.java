package jar.apache.commons.lang3.time;

import l.demo.Demo;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * StopWatch
 * <p>
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/StopWatch.html
 *
 * @author ljh
 * created on 2020/10/16 11:04
 */
public class StopWatchDemo extends Demo {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个 StopWatch，并且马上开始
        // 相当于 StopWatch stopWatch = StopWatch.create(); stopWatch.start();
        StopWatch stopWatch = StopWatch.createStarted();

        TimeUnit.MILLISECONDS.sleep(100);
        stopWatch.split();
        p("从开始到现在的运行时间：" + stopWatch.getTime() + "ms");                     // 从开始到现在的运行时间：100ms
        p("从开始到现在的运行时间：" + stopWatch.getTime(TimeUnit.MILLISECONDS) + "ms");// 从开始到现在的运行时间：100ms

        TimeUnit.MILLISECONDS.sleep(100);
        p("从开始到现在的运行时间：" + stopWatch.getTime() + "ms");                     // 从开始到现在的运行时间：200ms
        p("从开始到切点的运行时间：" + stopWatch.getSplitTime() + "ms");                // 从开始到切点的运行时间：100ms

        // 重置，之后必须 开始
        stopWatch.reset();
        stopWatch.start();
        TimeUnit.MILLISECONDS.sleep(100);
        p("从开始到现在的运行时间：" + stopWatch.getTime() + "ms");                     // 从开始到现在的运行时间：100ms

        // 暂停
        stopWatch.suspend();
        TimeUnit.MILLISECONDS.sleep(100);
        // 恢复暂停
        stopWatch.resume();
        p("从开始到现在的运行时间：" + stopWatch.getTime() + "ms");                     // 从开始到现在的运行时间：100ms

        // 停止
        stopWatch.stop();

    }
}
