package springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * &#064;Schedule
 *
 * @author ljh
 * @since 2021/9/4 0:59
 */
@Slf4j
@Service
public class ScheduleService {

    /**
     * &#064;Scheduled
     * <pre>
     * cron         通过 cron 表达式来配置执行规则 (second minute hour day-of-month month day-fo-week)
     * fixedDelay   上一次任务执行结束到下一次任务执行开始的间隔时间 (ms)
     * fixedRate    上一次任务执行开始到下一次任务执行开始的间隔时间 (ms)
     * </pre>
     * 多实例执行时，会出现累加操作；可使用分布式锁解决
     */
    // @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        log.info("现在报时：" + DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern()));
    }
}
