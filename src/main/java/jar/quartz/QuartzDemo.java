package jar.quartz;

import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Quartz
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
public class QuartzDemo extends Demo {

    public void go() throws Exception {
        // 创建 Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // jobs 可以在 Scheduler 的 start() 方法前被调用

        // job1 每隔3秒执行一次
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")).build();
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        log.info(jobDetail.getKey() + " 已被安排执行于: " + DATE_TIME_SDF.format(date) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

        // job2 每隔5秒执行一次
        jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job2", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
        date = scheduler.scheduleJob(jobDetail, trigger);
        log.info(jobDetail.getKey() + " 已被安排执行于: " + DATE_TIME_SDF.format(date) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

        // 开始执行，start() 被调用后，计时器就开始工作，计时调度中允许放入多个 job
        scheduler.start();
        // 主线程等待一分钟
        TimeUnit.MINUTES.sleep(1);
        // 关闭定时调度，定时器不再工作
        scheduler.shutdown(true);
    }

    public static void main(String[] args) throws Exception {
        QuartzDemo test = new QuartzDemo();
        test.go();
    }

}