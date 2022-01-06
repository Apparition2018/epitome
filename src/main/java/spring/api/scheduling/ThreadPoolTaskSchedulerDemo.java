package spring.api.scheduling;

import l.utils.DateUtils;
import lombok.SneakyThrows;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolTaskScheduler
 * <p>
 * 基于 Spring 的动态定时任务：https://blog.csdn.net/weixin_44248490/article/details/106900976
 *
 * @author ljh
 * created on 2021/10/18 15:50
 */
public class ThreadPoolTaskSchedulerDemo {

    public static void main(String[] args) {
        ScheduleUtil.startAtFixedRate(new MyScheduleTask("test"), Date.from(Instant.now().plusSeconds(3)), DateUtils.MILLIS_PER_SECOND);
    }

    static class ScheduleUtil {
        private static final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        private static final Map<String, ScheduledFuture<?>> scheduledFutureMap = new HashMap<>();

        static {
            threadPoolTaskScheduler.initialize();
        }

        public static void schedule(ScheduleTask scheduleTask, Trigger trigger) {
            if (scheduledFutureMap.containsKey(scheduleTask.getId())) {
                return;
            }
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(scheduleTask, trigger);
            scheduledFutureMap.put(scheduleTask.getId(), scheduledFuture);
        }

        /**
         * 执行一次
         */
        public static void schedule(ScheduleTask scheduleTask, Date startTime) {
            if (scheduledFutureMap.containsKey(scheduleTask.getId())) {
                return;
            }
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(scheduleTask, startTime);
            scheduledFutureMap.put(scheduleTask.getId(), scheduledFuture);
        }

        /**
         * 以上一次任务的开始时间为间隔，真正的间隔时间 = max(任务执行时间，间隔时间）
         */
        public static void startAtFixedRate(ScheduleTask scheduleTask, Date startTime, long period) {
            if (scheduledFutureMap.containsKey(scheduleTask.getId())) {
                return;
            }
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.scheduleAtFixedRate(scheduleTask, startTime, period);
            scheduledFutureMap.put(scheduleTask.getId(), scheduledFuture);
        }

        public static void startAtFixedRate(ScheduleTask scheduleTask, long period) {
            startAtFixedRate(scheduleTask, null, period);
        }

        /**
         * 以上一次任务的结束时间为间隔
         */
        public static void startWithFixedDelay(ScheduleTask scheduleTask, Date startTime, long period) {
            if (scheduledFutureMap.containsKey(scheduleTask.getId())) {
                return;
            }
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.scheduleWithFixedDelay(scheduleTask, startTime, period);
            scheduledFutureMap.put(scheduleTask.getId(), scheduledFuture);
        }

        public static void startWithFixedDelay(ScheduleTask scheduleTask, long period) {
            startWithFixedDelay(scheduleTask, null, period);
        }

        public static void remove(ScheduleTask scheduleTask) {
            ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(scheduleTask.getId());
            if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
                scheduledFuture.cancel(true);
            }
            scheduledFutureMap.remove(scheduleTask.getId());
        }
    }

    static abstract class ScheduleTask implements Runnable {
        private String id;

        public ScheduleTask(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    static class MyScheduleTask extends ScheduleTask {

        public static int count = 0;

        public MyScheduleTask(String id) {
            super(id);
        }

        @SneakyThrows
        @Override
        public void run() {
            System.err.println(Instant.now());
            TimeUnit.SECONDS.sleep(3);
            if (count == 10) ScheduleUtil.remove(this);
        }
    }

}
