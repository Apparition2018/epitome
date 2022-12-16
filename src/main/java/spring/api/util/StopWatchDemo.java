package spring.api.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

import static l.demo.Demo.p;

/**
 * StopWatch
 *
 * @author ljh
 * @since 2020/10/16 11:05
 */
public class StopWatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个 StopWatch，并设置 id
        StopWatch stopWatch = new StopWatch(RandomStringUtils.randomAlphanumeric(8));
        // 设置是否在停止后保留任务
        stopWatch.setKeepTaskList(true);

        stopWatch.start("起床");
        TimeUnit.MILLISECONDS.sleep(100);
        stopWatch.stop();

        stopWatch.start("洗漱");
        TimeUnit.MILLISECONDS.sleep(200);
        stopWatch.stop();

        stopWatch.start("锁门");
        TimeUnit.MILLISECONDS.sleep(50);
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        //  StopWatch 'sUZjSLp4': running time = 351835400 ns
        //  ---------------------------------------------
        //  ns         %     Task name
        //  ---------------------------------------------
        //  098100100  027%  起床
        //  207514700  056%  洗漱
        //  062389300  017%  锁门

        // StopWatch 摘要
        p(stopWatch.shortSummary());        // StopWatch 'sUZjSLp4': running time = 350664200 ns
        // StopWatch ID
        p(stopWatch.getId());               // sUZjSLp4
        // 任务数
        p(stopWatch.getTaskCount());        // 3
        // 总耗时
        p(stopWatch.getTotalTimeMillis());  // 350
        // 最后一个任务耗时
        p(stopWatch.getLastTaskTimeMillis());// 62
        // 当前任务名
        p(stopWatch.currentTaskName());     // null
        // 最后一个任务名
        p(stopWatch.getLastTaskName());     // 锁门
        // 所有任务信息
        StopWatch.TaskInfo[] taskInfos = stopWatch.getTaskInfo();
        // 最后一个任务信息
        StopWatch.TaskInfo lastTaskInfo = stopWatch.getLastTaskInfo();
    }
}
