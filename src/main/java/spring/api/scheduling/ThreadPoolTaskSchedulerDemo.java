package spring.api.scheduling;

import l.utils.DateUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

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
        ScheduleUtil.start(new MyScheduleTask("test"), DateUtils.MILLIS_PER_SECOND);
    }

    static class ScheduleUtil {
        private static final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        private static final Map<String, ScheduledFuture<?>> scheduledFutureMap = new HashMap<>();

        static {
            threadPoolTaskScheduler.initialize();
        }

        public static void start(ScheduleTask scheduleTask, long period) {
            if (scheduledFutureMap.containsKey(scheduleTask.getId())) {
                return;
            }
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.scheduleAtFixedRate(scheduleTask, period);
            scheduledFutureMap.put(scheduleTask.getId(), scheduledFuture);
        }

        public static void cancel(ScheduleTask scheduleTask) {
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

        @Override
        public void run() {
            System.err.println(++count);
            if (count == 5) ScheduleUtil.cancel(this);
        }
    }

}
