package spring.api.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

import static l.demo.Demo.p;

/**
 * StopWatch
 *
 * @author ljh
 * created on 2020/10/16 11:05
 */
public class StopWatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个 StopWatch，并设置 id
        StopWatch stopWatch = new StopWatch(RandomStringUtils.randomAlphanumeric(8));

        stopWatch.start("起床");
        TimeUnit.MILLISECONDS.sleep(100);
        p("当前任务名称：" + stopWatch.currentTaskName()); // 当前任务名称：起床
        stopWatch.stop();

        stopWatch.start("洗漱");
        TimeUnit.MILLISECONDS.sleep(200);
        p("当前任务名称：" + stopWatch.currentTaskName()); // 当前任务名称：洗漱
        stopWatch.stop();

        stopWatch.start("锁门");
        TimeUnit.MILLISECONDS.sleep(50);
        p("当前任务名称：" + stopWatch.currentTaskName()); // 当前任务名称：锁门
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        //  StopWatch 'sUZjSLp4': running time = 351835400 ns
        //  ---------------------------------------------
        //  ns         %     Task name
        //  ---------------------------------------------
        //  100332100  029%  起床
        //  200388000  057%  洗漱
        //  051115300  015%  锁门

        // StopWatch 摘要
        p(stopWatch.shortSummary());        // StopWatch 'sUZjSLp4': running time = 350664200 ns
        // 任务数
        p(stopWatch.getTaskCount());        // 3
        // 总耗时
        p(stopWatch.getTotalTimeMillis());  // 350
        // 当前任务名
        p(stopWatch.currentTaskName());     // null，stop() 后为任务名为 null
        // 所有任务信息
        StopWatch.TaskInfo[] taskInfo = stopWatch.getTaskInfo();
        // 最后一个任务名
        p(stopWatch.getLastTaskName());     // 锁门
        // 最后一个任务信息
        StopWatch.TaskInfo lastTaskInfo = stopWatch.getLastTaskInfo();
    }
}
