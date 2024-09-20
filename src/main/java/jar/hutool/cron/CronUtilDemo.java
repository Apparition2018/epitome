package jar.hutool.cron;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://hutool.cn/docs/#/cron/全局定时任务-CronUtil">CronUtil</a>     全局定时任务
 * <p>同时兼容 Crontab 和 Quartz 表达式
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/peida/archive/2013/01/08/2850483.html">Linux crontab 命令</a>
 * @see <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/cron/CronUtil.html">CronUtil api</a>
 * @since 2020/11/20 13:34
 */
public class CronUtilDemo extends Demo {

    /**
     * 配置文件定时任务
     */
    @Test
    public void testCronUtil() throws InterruptedException {
        setCountDownLatch(2);
        // 支持秒，如果为 true，则定时任务表达式中的第一位为秒，否则为分
        CronUtil.setMatchSecond(true);
        // 自定义定时任务配置文件路径
        CronUtil.setCronSetting("cron.setting");
        // 开始，true 为守护线程模式
        CronUtil.start(true);
        countDownLatch.countDown();
        countDownLatch.await();
        // 停止
        // CronUtil.stop();
    }

    /**
     * 动态添加定时任务
     */
    @Test
    public void testSchedule() throws InterruptedException {
        setCountDownLatch(2);
        CronUtil.schedule("*/2 * * * * *", (Task) () -> p("Task executed."));
        CronUtil.setMatchSecond(true);
        CronUtil.start();
        countDownLatch.countDown();
        countDownLatch.await();
    }

    public void cronTask() {
        p("Cron task executed.");
    }
}
