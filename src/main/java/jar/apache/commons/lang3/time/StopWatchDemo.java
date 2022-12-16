package jar.apache.commons.lang3.time;

import l.demo.Demo;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * StopWatch
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/StopWatch.html
 *
 * @author ljh
 * @since 2020/10/16 11:04
 */
public class StopWatchDemo extends Demo {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个 StopWatch，并且马上开始
        // 相当于 StopWatch stopWatch = StopWatch.create(); stopWatch.start();
        StopWatch stopWatch = StopWatch.createStarted();

        TimeUnit.MILLISECONDS.sleep(50);
        // 切点
        stopWatch.split();
        p("开始到现在：" + stopWatch.getTime());                      // 开始到现在：50
        p("开始到现在：" + stopWatch.getTime(TimeUnit.MILLISECONDS)); // 开始到现在：50
        p("开始到现在：" + stopWatch.getNanoTime());                  // 开始到现在：50000000
        p("开始到现在：" + stopWatch.formatTime() + "\n");            // 开始到现在：00:00:00.50

        TimeUnit.MILLISECONDS.sleep(100);
        p("开始到现在：" + stopWatch.getTime());                      // 开始到现在：150
        p("开始到切点：" + stopWatch.getSplitTime());                 // 开始到切点：50
        p("开始到切点：" + stopWatch.getSplitNanoTime());             // 开始到切点：50000000
        p("开始到切点：" + stopWatch.formatSplitTime() + "\n");       // 开始到切点：00:00:00.50
        // 取消切点
        stopWatch.unsplit();

        stopWatch.reset();
        // 重置之后，开始
        stopWatch.start();
        p("isStarted：" + stopWatch.isStarted());                    // isStarted：true
        TimeUnit.MILLISECONDS.sleep(50);
        p("重开到现在：" + stopWatch.getTime());                      // 重开到现在：50
        // 暂停
        stopWatch.suspend();
        p("isSuspended：" + stopWatch.isSuspended());                // isSuspended：true
        TimeUnit.MILLISECONDS.sleep(50);
        // 恢复暂停
        stopWatch.resume();
        TimeUnit.MILLISECONDS.sleep(50);
        p("重开到现在：" + stopWatch.getTime() + "\n");               // 重开到现在：100

        // 停止
        stopWatch.stop();
        p("isStopped：" + stopWatch.isStopped());                    // isStopped：true
        p("StartTime：" + stopWatch.getStartTime());                 // startTime：1642727303268
        p("StopTime：" + stopWatch.getStopTime());                   // stopTime：1642727303456
    }
}
