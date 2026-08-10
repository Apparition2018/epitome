package org.ljh.quartz;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 演示 spring-boot-starter-quartz 的使用与功能：
 * 1) starter 自动装配 Scheduler，直接注入使用；
 * 2) 用 Scheduler.scheduleJob 程序化注册任务（动态调度）；
 * 3) 用 triggerJob 手动触发任务执行；
 * 4) 通过 JobDataMap 给任务传参。
 */
@SpringBootTest
class QuartzTests {

    @Autowired
    private Scheduler scheduler;

    /** 测试专用计数，用于验证任务被真实执行 */
    private static final AtomicInteger executedCount = new AtomicInteger();

    @BeforeEach
    void resetState() {
        executedCount.set(0);
        DemoJob.lastMessage = null;
    }

    @AfterEach
    void cleanup() throws SchedulerException {
        scheduler.clear();
    }

    @Test
    @DisplayName("starter 自动装配了 Scheduler，可直接注入使用")
    void schedulerIsAutoConfigured() throws Exception {
        assertThat(scheduler).isNotNull();
        assertThat(scheduler.isStarted()).isTrue();
    }

    @Test
    @DisplayName("用 scheduleJob 注册任务并通过 triggerJob 手动触发执行")
    void scheduleAndTriggerJob() throws Exception {
        // 1. 动态注册并手动触发一个任务（公共逻辑，含参数 message=hello-quartz）
        scheduleAndTrigger("demoJob", "hello-quartz");

        // 2. 断言任务真实执行过（executedCount 自增）
        assertThat(executedCount.get()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("JobDataMap 参数能传入任务并被执行时读取")
    void jobDataMapIsReadableInJob() throws Exception {
        // 1. 动态注册并手动触发一个任务（公共逻辑，含参数 message=with-param）
        scheduleAndTrigger("paramJob", "with-param");

        // 2. 断言任务从 JobDataMap 读到的参数正确
        assertThat(DemoJob.lastMessage).isEqualTo("with-param");
    }

    /** 公共逻辑：动态注册一个任务并手动触发一次 */
    private void scheduleAndTrigger(String jobName, String message) throws Exception {
        // 1. 构造任务 JobDetail（附带参数 message）
        JobDetail job = JobBuilder.newJob(DemoJob.class)
                .withIdentity(jobName)
                .usingJobData("message", message)
                .build();

        // 2. 构造 Trigger：立即触发、只执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity(jobName + "Trigger")
                .startNow()
                .build();

        // 3. 动态注册任务
        scheduler.scheduleJob(job, trigger);

        // 4. 手动触发，等待异步执行完成
        scheduler.triggerJob(job.getKey());
        waitUntil(() -> DemoJob.lastMessage != null || executedCount.get() >= 1);
    }

    /** 等待条件成立，最多 5 秒 */
    private void waitUntil(Check check) throws InterruptedException {
        long deadline = System.currentTimeMillis() + 5000;
        while (!check.evaluate() && System.currentTimeMillis() < deadline) {
            Thread.sleep(100);
        }
    }

    @FunctionalInterface
    private interface Check {
        boolean evaluate();
    }

    /** 测试专用 Quartz Job：读 JobDataMap 并自增计数 */
    public static class DemoJob implements Job {
        static volatile String lastMessage;

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDataMap data = context.getMergedJobDataMap();
            lastMessage = data.getString("message");
            executedCount.incrementAndGet();
        }
    }
}
