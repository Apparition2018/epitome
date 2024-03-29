package spring.api.scheduling;

import lombok.SneakyThrows;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolTaskScheduler
 * <pre>
 * <a href="https://blog.csdn.net/weixin_44248490/article/details/106900976">基于 Spring 的动态定时任务</a>
 * <a href="https://blog.lqdev.cn/2018/08/19/springboot/chapter-twenty-two/">定时任务的使用|oKong</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/10/18 15:50
 */
public class ThreadPoolTaskSchedulerDemo {

    public static void main(String[] args) {
        ScheduleUtil.startAtFixedRate(new MyScheduleTask("test"), Date.from(Instant.now().plusSeconds(3)).toInstant(), Duration.ofSeconds(1));
    }

    private static class ScheduleUtil {
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
        public static void schedule(ScheduleTask scheduleTask, Instant startTime) {
            if (scheduledFutureMap.containsKey(scheduleTask.getId())) {
                return;
            }
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(scheduleTask, startTime);
            scheduledFutureMap.put(scheduleTask.getId(), scheduledFuture);
        }

        /**
         * 以上一次任务的开始时间为间隔，真正的间隔时间 = max(任务执行时间，间隔时间）
         */
        public static void startAtFixedRate(ScheduleTask scheduleTask, Instant startTime, Duration duration) {
            if (scheduledFutureMap.containsKey(scheduleTask.getId())) {
                return;
            }
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.scheduleAtFixedRate(scheduleTask, startTime, duration);
            scheduledFutureMap.put(scheduleTask.getId(), scheduledFuture);
        }

        public static void startAtFixedRate(ScheduleTask scheduleTask, Duration duration) {
            startAtFixedRate(scheduleTask, null, duration);
        }

        /**
         * 以上一次任务的结束时间为间隔
         */
        public static void startWithFixedDelay(ScheduleTask scheduleTask, Instant startTime, Duration duration) {
            if (scheduledFutureMap.containsKey(scheduleTask.getId())) {
                return;
            }
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.scheduleWithFixedDelay(scheduleTask, startTime, duration);
            scheduledFutureMap.put(scheduleTask.getId(), scheduledFuture);
        }

        public static void startWithFixedDelay(ScheduleTask scheduleTask, Duration duration) {
            startWithFixedDelay(scheduleTask, null, duration);
        }

        public static void remove(ScheduleTask scheduleTask) {
            ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(scheduleTask.getId());
            if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
                scheduledFuture.cancel(true);
            }
            scheduledFutureMap.remove(scheduleTask.getId());
        }
    }

    private static abstract class ScheduleTask implements Runnable {
        private String id;

        private ScheduleTask(String id) {
            this.id = id;
        }

        private String getId() {
            return id;
        }

        private void setId(String id) {
            this.id = id;
        }
    }

    private static class MyScheduleTask extends ScheduleTask {

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
