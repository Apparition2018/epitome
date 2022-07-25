package jar.quartz;

import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Quartz
 * cron 表达式：秒 分 时 日 月 周 年
 * *：可用在所有字段，表示每
 * ?：只能用在日或周，日或周只能有一个字段为特定值（*或数字），另一个字段为 ?
 * -：范围
 * ,：列表
 * /：等步长，如 0/3 表示从0开始每3秒执行
 * <p>
 * RuoYi 定时任务 (ruoyi-quartz)：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1
 * 在线Cron表达式生成器：https://cron.qqe2.com/
 * Java 定时任务的6种实现方式：https://mp.weixin.qq.com/s/CtpcHJLkbkub06SBf6Ajbg
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
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(cronScheduleBuilder).build();
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        log.info(jobDetail.getKey() + " 已被安排执行于: " + DATE_TIME_FORMAT.get().format(date) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

        // job2 每隔5秒执行一次
        jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job2", "group1").build();
        cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(cronScheduleBuilder).build();
        date = scheduler.scheduleJob(jobDetail, trigger);
        log.info(jobDetail.getKey() + " 已被安排执行于: " + DATE_TIME_FORMAT.get().format(date) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

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
