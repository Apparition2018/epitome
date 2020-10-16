package jar.spring.util;

import l.demo.Demo;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * StopWatch
 *
 * @author ljh
 * created on 2020/10/16 11:05
 */
public class StopWatchDemo extends Demo {
    
    @Test
    public void testStopWatch() throws InterruptedException {
        // 创建一个 StopWatch，并设置 id
        StopWatch watch = new StopWatch(RandomStringUtils.randomAlphanumeric(8));
        
        watch.start("起床");
        TimeUnit.MILLISECONDS.sleep(100);
        p("当前任务名称：" + watch.currentTaskName()); // 当前任务名称：起床
        watch.stop();

        watch.start("洗漱");
        TimeUnit.MILLISECONDS.sleep(200);
        p("当前任务名称：" + watch.currentTaskName()); // 当前任务名称：洗漱
        watch.stop();

        watch.start("锁门");
        TimeUnit.MILLISECONDS.sleep(50);
        p("当前任务名称：" + watch.currentTaskName()); // 当前任务名称：锁门
        watch.stop();
        
        p(watch.prettyPrint());
        //  StopWatch 'sUZjSLp4': running time = 351835400 ns
        //  ---------------------------------------------
        //  ns         %     Task name
        //  ---------------------------------------------
        //  100332100  029%  起床
        //  200388000  057%  洗漱
        //  051115300  015%  锁门
        
        // StopWatch 摘要
        p(watch.shortSummary());        // StopWatch 'sUZjSLp4': running time = 350664200 ns
        // 任务数
        p(watch.getTaskCount());        // 3
        // 总耗时
        p(watch.getTotalTimeMillis());  // 350
        // 当前任务名
        p(watch.currentTaskName());     // null，stop() 后为任务名为 null
        // 所有任务信息
        StopWatch.TaskInfo[] taskInfo = watch.getTaskInfo();
        // 最后一个任务名
        p(watch.getLastTaskName());     // 锁门
        // 最后一个任务信息
        StopWatch.TaskInfo lastTaskInfo = watch.getLastTaskInfo();
    }
}
