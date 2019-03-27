package jar.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class QuartzDemo {
    public void go() throws Exception {
        // 首先，必需要取得一个Scheduler的引用
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        // jobs可以在scheduled的sched.start()方法前被调用

        // job 1将每隔10秒执行一次
        JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
        Date ft = sched.scheduleJob(job, trigger);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

        // job 2将每隔20秒执行一次
        job = JobBuilder.newJob(MyJob.class).withIdentity("job2", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")).build();
        ft = sched.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

        // 开始执行，start()方法被调用后，计时器就开始工作，计时调度中允许放入N个Job
        sched.start();
        try {
            // 主线程等待一分钟
            TimeUnit.MINUTES.sleep(1); // 相当于 Thread.sleep(60L * 1000L)
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭定时调度，定时器不再工作
        sched.shutdown(true);
    }

    public static void main(String[] args) throws Exception {

        QuartzDemo test = new QuartzDemo();
        test.go();
    }

}